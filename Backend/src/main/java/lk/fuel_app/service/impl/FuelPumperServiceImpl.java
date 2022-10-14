package lk.fuel_app.service.impl;

import lk.fuel_app.dto.FuelAvailabilityDTO;
import lk.fuel_app.entity.*;
import lk.fuel_app.repository.*;
import lk.fuel_app.service.FuelPumperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class FuelPumperServiceImpl implements FuelPumperService {

    @Autowired
    private CustomerFuelStationRepository customerFuelStationRepository;

    @Autowired
    private FuelPumperRepository fuelPumperRepository;

    @Autowired
    private FuelPumperAttendanceRepository fuelPumperAttendanceRepository;

    @Autowired
    private FuelTypeRepository fuelTypeRepository;
    @Autowired
    private FuelStationFuelTypeRepository fuelStationFuelTypeRepository;

    @Autowired
    private VehicleTypeRepository vehicleTypeRepository;

    @Override
    public CustomerFuelStation addCustomerFuel(CustomerFuelStation customerFuelStation) {
        LocalDateTime localDateTime = LocalDateTime.now();
        customerFuelStation.setId(
                customerFuelStation.getCustomer().getVehicle().getVehicleNumber() + customerFuelStation.getFuelStation().getId() + localDateTime.format(DateTimeFormatter.ofPattern("yyyyMMddhhmmss"))
        );
        customerFuelStation.setPumpedAt(localDateTime);
        customerFuelStation.setPumpedAtDate(LocalDate.now());
        customerFuelStation.setFuelPumper(customerFuelStation.getFuelPumper());
        customerFuelStation = customerFuelStationRepository.save(customerFuelStation);
        Double fuelPumpedAmount = customerFuelStationRepository.getFuelPumpedAmount(customerFuelStation.getCustomer().getVehicle().getVehicleNumber(), LocalDate.now().with(DayOfWeek.MONDAY), LocalDate.now().with(DayOfWeek.SUNDAY));
        customerFuelStation.getCustomer().setQuota(fuelPumpedAmount == null ? 0 : fuelPumpedAmount);

        updateFuelAmount(customerFuelStation.getFuelPumped(), "add", customerFuelStation.getFuelStation().getId(), customerFuelStation.getFuelType().getId());
        return new CustomerFuelStation(customerFuelStation);
    }

    @Override
    public CustomerFuelStation updateCustomerFuel(CustomerFuelStation customerFuelStation, String id) {
        Optional<CustomerFuelStation> customerFuelStationOptional = customerFuelStationRepository.findById(id);
        if (customerFuelStationOptional.isPresent()) {
            CustomerFuelStation customerFuelStationObj = customerFuelStationOptional.get();
            customerFuelStationObj.setFuelPumped(customerFuelStation.getFuelPumped());
            return customerFuelStationRepository.save(customerFuelStationObj);
        }
        return null;
    }

    @Override
    public CustomerFuelStation deleteCustomerFuel(String customerNic, String fuelStation) {
        Optional<CustomerFuelStation> customerFuelStationOptional = customerFuelStationRepository.getTopByCustomerNicAndFuelStationIdOrderByPumpedAtDesc(customerNic, fuelStation);
        if (customerFuelStationOptional.isPresent()) {
            CustomerFuelStation customerFuelStation = customerFuelStationOptional.get();
            Double fuelPumpedAmount = customerFuelStationRepository.getFuelPumpedAmount(customerFuelStation.getCustomer().getVehicle().getVehicleNumber(), LocalDate.now().with(DayOfWeek.MONDAY), LocalDate.now().with(DayOfWeek.SUNDAY));
            customerFuelStation.getCustomer().setQuota(fuelPumpedAmount == null ? 0 : fuelPumpedAmount);
            customerFuelStationRepository.deleteById(customerFuelStation.getId());
            updateFuelAmount(customerFuelStation.getFuelPumped(), "deduct", fuelStation, customerFuelStation.getFuelType().getId());
            return customerFuelStation;
        }
        return null;
    }

    private void updateFuelAmount(double fuelAmount, String val, String fuelStationId, String fuelTypeId) {
        Optional<FuelStationFuelType> fuelStationFuelTypeOpt = fuelStationFuelTypeRepository.getAllByFuelStation_IdAndFuelType_Id(fuelStationId, fuelTypeId);
        if (fuelStationFuelTypeOpt.isPresent()) {
            FuelStationFuelType fuelStationFuelType = fuelStationFuelTypeOpt.get();
            if (val.equals("add")) {
                fuelStationFuelType.setFuelAmount(fuelStationFuelType.getFuelAmount() - fuelAmount);
            } else {
                fuelStationFuelType.setFuelAmount(fuelStationFuelType.getFuelAmount() + fuelAmount);
            }
            fuelStationFuelTypeRepository.save(fuelStationFuelType);
        }
    }

    @Override
    public FuelPumper addFuelPumper(FuelPumper fuelPumper) {
        return fuelPumperRepository.save(fuelPumper);
    }

    @Override
    public FuelPumperAttendance addFuelPumperAttendance(FuelPumperAttendance fuelPumperAttendance) {
        fuelPumperAttendance.setId(fuelPumperAttendance.getFuelPumper().getNic() + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")));
        return fuelPumperAttendanceRepository.save(new FuelPumperAttendance(fuelPumperAttendance));
    }

    @Override
    public FuelPumperAttendance markTimeOutAttendance(FuelPumperAttendance fuelPumperAttendance, String id) {
        Optional<FuelPumperAttendance> fuelPumperAttendance1 = fuelPumperAttendanceRepository.findById(id);

        if(fuelPumperAttendance1.isPresent()){
            FuelPumperAttendance attendance1 = fuelPumperAttendance1.get();
            attendance1.setTimeOut(fuelPumperAttendance.getTimeOut());
            return fuelPumperAttendanceRepository.save(attendance1);
        }
        return null;
    }

    @Override
    public List<FuelPumperAttendance> getAttendance() {
        List<FuelPumperAttendance> fuelPumperAttendances = fuelPumperAttendanceRepository.findAll();
        List<FuelPumperAttendance> pumperAttendances = new ArrayList<>();
        for (FuelPumperAttendance attendance : fuelPumperAttendances) {
            pumperAttendances.add(new FuelPumperAttendance(attendance));
        }
        return pumperAttendances;

//        return fuelPumperAttendanceRepository.findAll();
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
    public List<CustomerFuelStation> getVehicleDetailsByType(String vehicleType) {
        List<CustomerFuelStation> vehicleDetailsByType = customerFuelStationRepository.getVehicleDetailsByType(vehicleType);
        List<CustomerFuelStation> customerFuelStations = new ArrayList<>();
        for (CustomerFuelStation customerFuelStation : vehicleDetailsByType) {
            customerFuelStations.add(new CustomerFuelStation(customerFuelStation));
        }

        return customerFuelStations;
    }

    @Override
    public List<CustomerFuelStation> getVehicleDetailsByDate(String date) {
        LocalDate localDate = LocalDate.parse(date);
        List<CustomerFuelStation> vehicleDetailsByDate = customerFuelStationRepository.getVehicleDetailsByDate(localDate);
        List<CustomerFuelStation> customerFuelStations = new ArrayList<>();
        for (CustomerFuelStation customerFuelStation : vehicleDetailsByDate) {
            customerFuelStations.add(new CustomerFuelStation(customerFuelStation));
        }
        return customerFuelStations;
    }

    @Override
    public List<CustomerFuelStation> getVehicleDetailsByTypeAndDate(String vehicleType, String date) {
        LocalDate localDate = LocalDate.parse(date);
        return customerFuelStationRepository.getVehicleDetailsByTypeAndDate(vehicleType, localDate);
    }


//    @Override
//    public List<CustomerFuelStation> getVehicleCountAndFuelAmount(String vehicleType) {
//        Integer noOfVehicles, fuelAmount;
//        List<CustomerFuelStation> customerFuelStations = customerFuelStationRepository.getVehicleCountAndFuelAmount(vehicleType);
//
//        return customerFuelStations;
//    }

    @Override
    public List<FuelType> getFuelTypes() {
        return fuelTypeRepository.getFuelTypes();
    }

    @Override
    public List<VehicleType> getAllVehicleTypes() {
        return vehicleTypeRepository.getVehicleTypes();
    }

    @Override
    public List<FuelAvailabilityDTO> getAllFuelRecord(String startDate, String endDate) {
        List<FuelAvailabilityDTO> fuelAvailabilityDTOs;
        LocalDate sDate = LocalDate.parse(startDate);
        LocalDate eDate = LocalDate.parse(endDate);

        Map<String, FuelAvailabilityDTO.FuelStock> pumpedStockObj = new HashMap<>();

        List<FuelType> fuelTypes = fuelTypeRepository.getFuelTypes();
        for (FuelType fuelType : fuelTypes) {
            pumpedStockObj.put(fuelType.getId(), new FuelAvailabilityDTO.FuelStock(fuelType.getId(), fuelType.getName()));
        }

        List<CustomerFuelStation> fuelRecordByDateRange = customerFuelStationRepository.getAllFuelRecord(sDate, eDate);
//        List<CustomerFuelStation> customerFuelStations = new ArrayList<>();
        for (CustomerFuelStation customerFuelStation : fuelRecordByDateRange) {
//            customerFuelStations.add(new CustomerFuelStation(customerFuelStation));
            FuelAvailabilityDTO.FuelStock fuelStockObj = pumpedStockObj.get(customerFuelStation.getFuelType().getId());
            fuelStockObj.setQuantity(fuelStockObj.getQuantity() + customerFuelStation.getFuelPumped());
            fuelStockObj.setFuelPumpedAt(customerFuelStation.getPumpedAtDate().format(DateTimeFormatter.ofPattern("yyy-MM-dd")));
            pumpedStockObj.put(customerFuelStation.getFuelType().getId(), fuelStockObj);
        }
        fuelAvailabilityDTOs = new ArrayList(pumpedStockObj.values());
//        Collections.sort(fuelAvailabilityDTOs);
        return fuelAvailabilityDTOs;
    }

    @Override
    public List<CustomerFuelStation> getAllFuelRecordChart(String startDate, String endDate) {
        LocalDate sDate = LocalDate.parse(startDate);
        LocalDate eDate = LocalDate.parse(endDate);

        List<CustomerFuelStation> fuelRecordByDateRange = customerFuelStationRepository.getAllFuelRecord(sDate, eDate);
        List<CustomerFuelStation> customerFuelStations = new ArrayList<>();
        for (CustomerFuelStation customerFuelStation : fuelRecordByDateRange) {
            customerFuelStations.add(new CustomerFuelStation(customerFuelStation));
        }
        return customerFuelStations;
    }


}
