package lk.fuel_app.service.impl;

import lk.fuel_app.entity.AppUser;
import lk.fuel_app.entity.FuelPumper;
import lk.fuel_app.repository.FuelPumperRepository;
import lk.fuel_app.repository.UserRepository;
import lk.fuel_app.service.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FuelPumperRepository fuelPumperRepository;

    @Override
    public AppUser login(AppUser appUser) {
        Optional<AppUser> userOptional = userRepository.login(appUser.getEmail(), appUser.getPassword());
        if (userOptional.isPresent()) {
            AppUser appUserObj = userOptional.get();
            if (appUserObj.getUserType().equals("fuelPumper")) {
                Optional<FuelPumper> optionalFuelPumper = fuelPumperRepository.findById(appUserObj.getId());
                if (optionalFuelPumper.isPresent()) {
                    appUserObj.setFuelPumper(new FuelPumper(optionalFuelPumper.get()));
                }
            }
            return appUserObj;
        }
        return null;
    }
}
