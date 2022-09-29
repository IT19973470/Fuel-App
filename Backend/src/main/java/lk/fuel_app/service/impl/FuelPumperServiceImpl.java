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

    @Override
    public CustomerFuelStation addCustomerFuel(CustomerFuelStation customerFuelStation) {
        LocalDateTime localDateTime = LocalDateTime.now();
        customerFuelStation.setId(
                customerFuelStation.getCustomer().getVehicleNumber() + customerFuelStation.getFuelStation().getId() + localDateTime.format(DateTimeFormatter.ofPattern("yyyyMMddhhmmss"))
        );
        customerFuelStation.setPumpedAt(localDateTime);
        customerFuelStation.setPumpedAtDate(LocalDate.now());
        customerFuelStation = customerFuelStationRepository.save(customerFuelStation);
        Double fuelPumpedAmount = customerFuelStationRepository.getFuelPumpedAmount(customerFuelStation.getCustomer().getVehicleNumber(), LocalDate.now().with(DayOfWeek.MONDAY), LocalDate.now().with(DayOfWeek.SUNDAY));
        customerFuelStation.getCustomer().setQuota(fuelPumpedAmount == null ? 0 : fuelPumpedAmount);
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
            Double fuelPumpedAmount = customerFuelStationRepository.getFuelPumpedAmount(customerFuelStation.getCustomer().getVehicleNumber(), LocalDate.now().with(DayOfWeek.MONDAY), LocalDate.now().with(DayOfWeek.SUNDAY));
            customerFuelStation.getCustomer().setQuota(fuelPumpedAmount == null ? 0 : fuelPumpedAmount);
            customerFuelStationRepository.deleteById(customerFuelStation.getId());
            return customerFuelStation;
        }
        return null;
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
        return customerFuelStationRepository.findAll();
    }

    @Override
    public List<CustomerFuelStation> getVehicleDetailsByType(String vehicleType) {
        return customerFuelStationRepository.getVehicleDetailsByType(vehicleType);
    }

    @Override
    public List<CustomerFuelStation> getVehicleDetailsByDate(String date) {
        LocalDate localDate = LocalDate.parse(date);
        return customerFuelStationRepository.getVehicleDetailsByDate(localDate);
    }

    @Override
    public List<CustomerFuelStation> getVehicleDetailsByTypeAndDate(String vehicleType, String date) {
        LocalDate localDate = LocalDate.parse(date);
        return customerFuelStationRepository.getVehicleDetailsByTypeAndDate(vehicleType, localDate);
    }

    @Override
    public List<FuelType> getFuelTypes() {
        return fuelTypeRepository.getFuelTypes();
    }
}