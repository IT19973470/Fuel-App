package lk.fuel_app.service.impl;

import lk.fuel_app.entity.*;
import lk.fuel_app.repository.*;
import lk.fuel_app.service.FuelPumperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        return fuelPumperAttendanceRepository.save(fuelPumperAttendance);
    }

    @Override
    public List<CustomerFuelStation> getAllVehicleDetails() {
        List<CustomerFuelStation> fuelStations = customerFuelStationRepository.findAll();
        List<CustomerFuelStation> customerFuelStations = new ArrayList<>();
        for (CustomerFuelStation customerFuelStation : fuelStations) {
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
    public List<CustomerFuelStation> getAllFuelRecord(String startDate, String endDate) {
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