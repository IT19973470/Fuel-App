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
    private FuelStationFuelTypeRepository fuelStationFuelTypeRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CustomerFuelStationRepository customerFuelStationRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private VehicleTypeRepository vehicleTypeRepository;

    @Override
    public FuelStation addFuelStation(FuelStation fuelStation) {
        return fuelStationRepository.save(fuelStation);
    }

    @Override
    public FuelStock addFuelStock(FuelStock fuelStock) {
        fuelStock.setId(fuelStock.getFuelStation().getId() + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddhhmmss")));
        fuelStock.setAvailability(true);
        fuelStockRepository.save(fuelStock);

        Optional<FuelStationFuelType> fuelStationFuelTypeOpt = fuelStationFuelTypeRepository.getAllByFuelStation_IdAndFuelType_Id(fuelStock.getFuelStation().getId(), fuelStock.getFuelType().getId());
        if (fuelStationFuelTypeOpt.isPresent()) {
            FuelStationFuelType fuelStationFuelType = fuelStationFuelTypeOpt.get();
            fuelStationFuelType.setFuelAmount(fuelStationFuelType.getFuelAmount() + fuelStock.getAmount());
            fuelStationFuelTypeRepository.save(fuelStationFuelType);
        } else {
            FuelStationFuelType fuelStationFuelType = new FuelStationFuelType();
            fuelStationFuelType.setId(fuelStock.getFuelStation().getId() + fuelStock.getFuelType().getId());
            fuelStationFuelType.setFuelStation(fuelStock.getFuelStation());
            fuelStationFuelType.setFuelType(fuelStock.getFuelType());
            fuelStationFuelType.setFuelAmount(fuelStock.getAmount());
            fuelStationFuelTypeRepository.save(fuelStationFuelType);
        }
        return new FuelStock(fuelStock);
    }

    @Override
    public OrderData addOrder(OrderData order) {
        System.out.println(order);
        order.setId("O" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddhhmmss")));
        order.setFuelAdmin(order.getFuelAdmin());
        OrderData orderData = orderRepository.save(order);
        return new OrderData(orderData);
    }


    @Override
    public List<CustomerFuelStation> getAllVehicleDetails() {
        List<CustomerFuelStation> fuelStations = customerFuelStationRepository.findAll();
        List<CustomerFuelStation> customerFuelStations = new ArrayList<>();
        for (CustomerFuelStation customerFuelStation : fuelStations) {
//            customerFuelStation.setCustomer(null);
            customerFuelStation.setFuelStation(null);
            customerFuelStation.setFuelType(null);
            customerFuelStations.add(new CustomerFuelStation(customerFuelStation));
        }

        return customerFuelStations;
    }

    @Override
    public List<FuelStockDTO> getFuelStock(String id) {
//        List<FuelStock> actualArrivalDesc = fuelStockRepository.getLastFuelPump(id);
//        Integer fuelPumpedVehicleCount = customerFuelStationRepository.getFuelPumpedVehicles(id, fuelStockObj.getActualArrival().toLocalDate());
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
                fuelStockDTO.setAvailableStock(fuelStockRepository.getTotalStockAmount(fuelStock.getFuelType().getId()));
                fuelStockDTO.setDistributedVehicleCount(customerFuelStationRepository.getCountVehicle(fuelStock.getFuelType().getId()));
                fuelStockDTO.setDistributedFuel(customerFuelStationRepository.getSumDistribution(fuelStock.getFuelType().getId()));
                Double hour = customerFuelStationRepository.getoneHourDeistibution(fuelStock.getFuelType().getId(), LocalDateTime.now().minusHours(48), LocalDateTime.now());
                if (hour != null) {
                    fuelStockDTO.setFuelSupplyPerHour(hour);
                } else {
                    fuelStockDTO.setFuelSupplyPerHour(0);
                }

                fuelStockObj.put(fuelStock.getFuelType().getId(), fuelStockDTO);
            }
            for (CustomerFuelStation customerFuelStation : fuelStation.getFuelPumped()) {
                FuelStockDTO fuelStockDTO = fuelStockObj.get(customerFuelStation.getFuelType().getId());
                fuelStockDTO.setAvailableStock(fuelStockDTO.getAvailableStock() - customerFuelStation.getFuelPumped());
                fuelStockObj.put(customerFuelStation.getFuelType().getId(), fuelStockDTO);
            }


            List<FuelStockNext> fuelStockNexts = fuelStockNextRepository.getAllByFuelStation_Id(fuelStation.getId());
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
        List<FuelAdminStockOut> fuelAdminStockOuts = fuelAdminStockOutRepository.findAllByFuelStation_Id(id);
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
        Integer countdata;
        List<FuelPumperAttendance> fuelPumperAttendances = fuelPumperAttendanceRepository.findAll();
        for (FuelPumperAttendance fuelPumperAttendance : fuelPumperAttendances) {
            AttandanceDTO attandanceDTO = new AttandanceDTO();
            attandanceDTO.setFuelPumperAttendance(new FuelPumperAttendance(fuelPumperAttendance, fuelPumperAttendance.getFuelPumper()));
            countdata = fuelPumperAttendanceRepository.getFuelPumpedCount(fuelPumperAttendance.getMarkedAt(), fuelPumperAttendance.getFuelPumper().getNic());
            if (countdata == null) {
                attandanceDTO.setCountdata(0);
            }
            else {
                attandanceDTO.setCountdata(countdata);
            }

            attandanceDTOS.add(attandanceDTO);
        }
        return attandanceDTOS;
    }

    @Override
    public List<AttandanceDTO> getAttendenceByDate(String startDate, String endDate) {
        List<AttandanceDTO> attandanceDTOS = new ArrayList<>();
        LocalDate sDate = LocalDate.parse(startDate);
        LocalDate eDate = LocalDate.parse(endDate);
        int countdata = 0;
        List<FuelPumperAttendance> fuelPumperAttendances = fuelPumperAttendanceRepository.FindAllBetween(sDate, eDate);
        for (FuelPumperAttendance fuelPumperAttendance : fuelPumperAttendances) {
            AttandanceDTO attandanceDTO = new AttandanceDTO();
            attandanceDTO.setFuelPumperAttendance(new FuelPumperAttendance(fuelPumperAttendance, fuelPumperAttendance.getFuelPumper()));
            countdata = fuelPumperAttendanceRepository.getFuelPumpedCount(fuelPumperAttendance.getMarkedAt(), fuelPumperAttendance.getFuelPumper().getNic());
            if (countdata != 0) {
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
        List<OrderData> orderData = orderRepository.getAllByFuelStationId(id);
        for (OrderData orderData1 : orderData) {
            OrderDTO orderData2 = new OrderDTO();
            orderData2.setOrderData(new OrderData(orderData1));
            orderData2.setFuelStation(new FuelStation(orderData1.getFuelStation()));
            orderData2.setFuelAdmin(orderData1.getFuelAdmin());
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
        FuelStation fuelStations = fuelStationRepository.getByAppUserId(id);
        FuelStationDTO fuelStationDTO = new FuelStationDTO();
        fuelStationDTO.setFuelStation(new FuelStation(fuelStations));
        return fuelStationDTO;
    }

    @Override
    public OrderData updateOrder(OrderData orderData, String id) {
        Optional<OrderData> orderoptional = orderRepository.findById(id);
        if (orderoptional.isPresent()) {
            OrderData orderObj = orderoptional.get();
            orderObj.setAmount(orderData.getAmount());
            orderObj.setFuelType(orderData.getFuelType());
            OrderData orderData1 = orderRepository.save(orderObj);;
            return new OrderData(orderData1);
        }
        return null;
    }

    @Override
    public List<VehicleReportDTO> getVehicleReport(String id) {
        List<VehicleReportDTO> vehicleReportDTOS = new ArrayList<>();
        List<Object[]> customerCount = customerRepository.getCustomerCount(id);
        for (Object[] obj : customerCount) {
            VehicleReportDTO vehicleReportDTO = new VehicleReportDTO();
            vehicleReportDTO.setVehicleType(obj[0].toString());
            vehicleReportDTO.setCount(Integer.parseInt(obj[1].toString()));

            vehicleReportDTOS.add(vehicleReportDTO);
        }

        return vehicleReportDTOS;
    }

    public List<VehicleReportDTO> getVehicleReportType(String id, String type) {
        List<VehicleReportDTO> vehicleReportDTOS = new ArrayList<>();
        List<Object[]> customerCount = customerRepository.getCustomerCountType(id, type);
        for (Object[] obj : customerCount) {
            VehicleReportDTO vehicleReportDTO = new VehicleReportDTO();
            vehicleReportDTO.setVehicleType(obj[0].toString());
            vehicleReportDTO.setCount(Integer.parseInt(obj[1].toString()));

            vehicleReportDTOS.add(vehicleReportDTO);
        }

        return vehicleReportDTOS;
    }

    @Override
    public boolean deleteOrder(String id) {
        orderRepository.deleteById(id);
        return true;
    }

    @Override
    public List<VehicleType> getAllVehicleTypes() {
        return vehicleTypeRepository.findAll();
    }


}
