package lk.fuel_app.service.impl;

import lk.fuel_app.entity.*;
import lk.fuel_app.repository.CustomerRepository;
import lk.fuel_app.repository.FuelPumperRepository;
import lk.fuel_app.repository.FuelStationPlaceRepository;
import lk.fuel_app.repository.UserRepository;
import lk.fuel_app.service.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FuelPumperRepository fuelPumperRepository;
    @Autowired
    private FuelStationPlaceRepository fuelStationPlaceRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public AppUser login(AppUser appUser) {
        Optional<AppUser> userOptional = userRepository.login(appUser.getEmail(), appUser.getPassword());
        if (userOptional.isPresent()) {
            AppUser appUserObj = userOptional.get();
            if (appUserObj.getUserType().equals("fuelPumper")) {
                Optional<FuelPumper> optionalFuelPumper = fuelPumperRepository.findById(appUserObj.getId());
                if (optionalFuelPumper.isPresent()) {
                    FuelPumper fuelPumper = optionalFuelPumper.get();
//                    FuelStation fuelStation = fuelPumper.getFuelStation();
                    appUserObj.setFuelPumper(new FuelPumper(fuelPumper));
                }
            } else if (appUserObj.getUserType().equals("customer")) {
                Optional<Customer> optionalCustomer = customerRepository.findById(appUserObj.getId());
                if (optionalCustomer.isPresent()) {
                    Customer customer = optionalCustomer.get();
//                    FuelStation fuelStation = fuelPumper.getFuelStation();
                    appUserObj.setCustomer(new Customer(customer));
                }
            }
            return new AppUser(appUserObj);
        }
        return null;
    }

    @Override
    public List<FuelStationPlace> getPlaces() {
        return fuelStationPlaceRepository.getFuelStationPlaces();
    }
}
