package lk.fuel_app.service.impl;

import lk.fuel_app.dto.*;
import lk.fuel_app.entity.*;
import lk.fuel_app.repository.*;
import lk.fuel_app.service.FuelStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class FuelStationServiceImpl implements FuelStationService {

    @Autowired
    private FuelStationRepository fuelStationRepository;
    @Autowired
    private FuelStockRepository fuelStockRepository;
    @Autowired
    private FuelTypeRepository fuelTypeRepository;
    @Autowired
    private FuelStockNextRepository fuelStockNextRepository;
    @Autowired
    private FuelAdminStockOutRepository fuelAdminStockOutRepository;
    @Autowired
    private FuelPumperAttendanceRepository fuelPumperAttendanceRepository;
    @Autowired
    private FuelAdminRepository fuelAdminRepository;
    @Autowired
    private ChatRepository chatRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CustomerFuelStationRepository customerFuelStationRepository;

    @Override
    public FuelStation addFuelStation(FuelStation fuelStation) {
        return fuelStationRepository.save(fuelStation);
    }

    @Override
    public FuelStock addFuelStock(FuelStock fuelStock) {
        fuelStock.setId(fuelStock.getFuelStation().getId() + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddhhmmss")));
        fuelStock.setAvailability(true);
        fuelStockRepository.save(fuelStock);
        return new FuelStock(fuelStock);
    }
    @Override
    public OrderData addOrder(OrderData order) {
        System.out.println(order);
        order.setId("O" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddhhmmss")));
        order.setFuelAdmin(order.getFuelAdmin());
        return orderRepository.save(order);
    }
    
    @Override
    public List<FuelStockDTO> getFuelStock(String id) {
//        List<FuelStock> actualArrivalDesc = fuelStockRepository.getLastFuelPump(id);
//        Integer fuelPumpedVehicleCount = customerFuelStationRepository.getFuelPumpedVehicleCount(id, fuelStockObj.getActualArrival().toLocalDate());
        List<FuelStockDTO> fuelStockDTOS = new ArrayList<>();

        Map<String, FuelStockDTO> fuelStockObj = new HashMap<>();
        Map<String, FuelStockDTO> nextFuelStockObj = new HashMap<>();
        List<FuelType> fuelTypes = fuelTypeRepository.getFuelTypes();
        for (FuelType fuelType : fuelTypes) {
            fuelStockObj.put(fuelType.getId(), new FuelStockDTO(fuelType.getId(), fuelType.getName()));
            nextFuelStockObj.put(fuelType.getId(), new FuelStockDTO(fuelType.getId(), fuelType.getName()));
        }

        Optional<FuelStation> fuelStationOptional = fuelStationRepository.findById(id);
        if (fuelStationOptional.isPresent()) {
            FuelStation fuelStation = fuelStationOptional.get();
            for (FuelStock fuelStock : fuelStation.getFuelStocks()) {
                FuelStockDTO fuelStockDTO = fuelStockObj.get(fuelStock.getFuelType().getId());
                fuelStockDTO.setAvailableStock(fuelStockDTO.getAvailableStock() + fuelStock.getAmount());
                fuelStockObj.put(fuelStock.getFuelType().getId(), fuelStockDTO);
            }
            for (CustomerFuelStation customerFuelStation : fuelStation.getFuelPumped()) {
                FuelStockDTO fuelStockDTO = fuelStockObj.get(customerFuelStation.getFuelType().getId());
                fuelStockDTO.setAvailableStock(fuelStockDTO.getAvailableStock() - customerFuelStation.getFuelPumped());
                fuelStockObj.put(customerFuelStation.getFuelType().getId(), fuelStockDTO);
            }

            List<FuelStockNext> fuelStockNexts = fuelStockNextRepository.getAllByFuelStationName(fuelStation.getName());
            for (FuelStockNext fuelStockNext : fuelStockNexts) {
                FuelStockDTO fuelStockDTO = fuelStockObj.get(fuelStockNext.getFuelType().getId());
                fuelStockDTO.setNextFuelAmount(fuelStockNext.getAmount());
                fuelStockDTO.setNextFuelAmountDateAt(fuelStockNext.getArrival().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                fuelStockDTO.setNextFuelAmountTimeAt(fuelStockNext.getArrival().format(DateTimeFormatter.ofPattern("hh:mm a")));
                fuelStockObj.put(fuelStockNext.getFuelType().getId(), fuelStockDTO);
            }

            fuelStockDTOS = new ArrayList<>(fuelStockObj.values());
            Collections.sort(fuelStockDTOS);
//            fuelStockDTOS.add(fuelAvailabilityDTO);
        }
//        FuelStock fuelStockObj = actualArrivalDesc.get(0);
//        for (FuelType fuelType : fuelTypes) {
//            FuelStockDTO fuelStockDTO = new FuelStockDTO();
//            fuelStockDTO.setFuelType(fuelType.getName());
//            for (FuelStock fuelStock : fuelStation.getFuelStocks()) {
////                total += fuelStock.getAmount();
//                FuelAvailabilityDTO.FuelStock fuelStockObj = availableStockObj.get(fuelStock.getFuelType().getId());
//                fuelStockObj.setQuantity(fuelStockObj.getQuantity() + fuelStock.getAmount());
//                availableStockObj.put(fuelStock.getFuelType().getId(), fuelStockObj);
//            }
//            for (CustomerFuelStation customerFuelStation : fuelStation.getFuelPumped()) {
////                pumped += customerFuelStation.getFuelPumped();
//                FuelAvailabilityDTO.FuelStock fuelStockObj = availableStockObj.get(customerFuelStation.getFuelType().getId());
//                fuelStockObj.setQuantity(fuelStockObj.getQuantity() - customerFuelStation.getFuelPumped());
//                availableStockObj.put(customerFuelStation.getFuelType().getId(), fuelStockObj);
//            }
////            }
//            fuelStockDTOS.add(fuelStockDTO);
//        }
        return fuelStockDTOS;
    }

    @Override
    public List<FuelAdminStockOutDTO> getFuelStockIn(String id) {
        List<FuelAdminStockOutDTO> fuelAdminStockOutDTOS = new ArrayList<>();
        List<FuelAdminStockOut> fuelAdminStockOuts= fuelAdminStockOutRepository.findAllByFuelStation_Id(id);
        for (FuelAdminStockOut fuelAdminStockOut : fuelAdminStockOuts) {
            FuelAdminStockOutDTO fuelAdminStockOutDTO = new FuelAdminStockOutDTO();
            fuelAdminStockOutDTO.setFuelAdminStockOut(new FuelAdminStockOut(fuelAdminStockOut));
            fuelAdminStockOutDTOS.add(fuelAdminStockOutDTO);
        }
        return fuelAdminStockOutDTOS;
    }

    @Override
    public List<AttandanceDTO> getAttendence() {
        List<AttandanceDTO> attandanceDTOS = new ArrayList<>();
        int countdata=0;
        List<FuelPumperAttendance> fuelPumperAttendances= fuelPumperAttendanceRepository.findAll();
        for (FuelPumperAttendance fuelPumperAttendance : fuelPumperAttendances) {
            AttandanceDTO attandanceDTO = new AttandanceDTO();
            attandanceDTO.setFuelPumperAttendance(new FuelPumperAttendance(fuelPumperAttendance));
             countdata = fuelPumperAttendanceRepository.getFuelPumpedCount(fuelPumperAttendance.getMarkedAt(),fuelPumperAttendance.getFuelPumper().getNic());
            if(countdata!=0){
                attandanceDTO.setCountdata(countdata);
            }
           
            attandanceDTOS.add(attandanceDTO);
        }
      return attandanceDTOS;
    }

    @Override
    public List<FuelAdmin> viewFuelAdmin() {
        return fuelAdminRepository.findAll();
    }

    @Override
    public List<Chat> getChat() {
        return chatRepository.findAll();
    }

    @Override
    public List<OrderDTO> getFuelOrder(String id) {
        List<OrderDTO> orderDTOS = new ArrayList<>();
        List<OrderData> orderData= orderRepository.getAllByFuelStationId(id);
        for (OrderData orderData1 : orderData) {
            OrderDTO orderData2 = new OrderDTO();
            orderData2.setOrderData(orderData1);
            orderDTOS.add(orderData2);
        }
        
        return orderDTOS;
    }

    @Override
    public Chat addChat(Chat chat) {
        String dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddhhmmss"));
        chat.setChatId("chat" + dateTime);
        return chatRepository.save(chat);
    }

    @Override
    public FuelStockNext addNextFuelStock(FuelStockNext fuelStockNext) {
        fuelStockNext.setId(fuelStockNext.getFuelStation().getId());
        fuelStockNextRepository.save(fuelStockNext);
        return new FuelStockNext(fuelStockNext);
    }

    public FuelStationDTO viewFuelStation(String id) {
        FuelStation fuelStations= fuelStationRepository.getByAppUserId(id);
        FuelStationDTO fuelStationDTO=new FuelStationDTO();
        fuelStationDTO.setFuelStation(fuelStations);
        return fuelStationDTO;
    }

    @Override
    public OrderData updateOrder(OrderData orderData, String id) {
        Optional<OrderData> orderoptional = orderRepository.findById(id);
        if (orderoptional.isPresent()) {
            OrderData orderObj = orderoptional.get();
            orderObj.setAmount(orderData.getAmount());
            orderObj.setFuelType(orderData.getFuelType());
            return orderRepository.save(orderObj);
        }
        return null;
    }

    @Override
    public List<VehicleReportDTO> getVehicleReport(String id) {

        List<VehicleReportDTO> vehicleReportDTOS = new ArrayList<>();
        List<CustomerFuelStation> orderData= customerFuelStationRepository.getAllByFuelStationId(id);
        for (OrderData orderData1 : orderData) {
            OrderDTO orderData2 = new OrderDTO();
            orderData2.setOrderData(orderData1);
            orderDTOS.add(orderData2);
        }
        return chatRepository.findAll();
    }

}
