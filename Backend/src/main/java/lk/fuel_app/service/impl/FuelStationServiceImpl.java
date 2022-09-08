package lk.fuel_app.service.impl;

import lk.fuel_app.entity.CustomerFuelStation;
import lk.fuel_app.entity.FuelStation;
import lk.fuel_app.repository.CustomerFuelStationRepository;
import lk.fuel_app.repository.FuelStationRepository;
import lk.fuel_app.service.FuelStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class FuelStationServiceImpl implements FuelStationService {

    @Autowired
    private FuelStationRepository fuelStationRepository;
    @Autowired
    private CustomerFuelStationRepository customerFuelStationRepository;

    @Override
    public FuelStation addFuelStation(FuelStation fuelStation) {
        return fuelStationRepository.save(fuelStation);
    }

    @Override
    public CustomerFuelStation addCustomerFuel(CustomerFuelStation customerFuelStation) {
        LocalDateTime localDateTime = LocalDateTime.now();
        customerFuelStation.setId(
                customerFuelStation.getCustomer().getVehicleNumber() + customerFuelStation.getFuelStation().getId() + localDateTime.format(DateTimeFormatter.ofPattern("yyyyMMddhhmmss"))
        );
        customerFuelStation.setPumpedAt(localDateTime);
        return customerFuelStationRepository.save(customerFuelStation);
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
    public boolean deleteCustomerFuel(String customerNic, String fuelStation) {
        Optional<CustomerFuelStation> customerFuelStationOptional = customerFuelStationRepository.getTopByCustomerNicAndFuelStationIdOrderByPumpedAtDesc(customerNic, fuelStation);
        if (customerFuelStationOptional.isPresent()) {
            CustomerFuelStation customerFuelStation = customerFuelStationOptional.get();
            customerFuelStationRepository.deleteById(customerFuelStation.getId());
        }
        return true;
    }
}
