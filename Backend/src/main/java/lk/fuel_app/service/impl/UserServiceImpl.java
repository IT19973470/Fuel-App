package lk.fuel_app.service.impl;

import lk.fuel_app.entity.AppUser;
import lk.fuel_app.repository.UserRepository;
import lk.fuel_app.service.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.rmi.server.UnicastServerRef;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public AppUser login(AppUser appUser) {
        Optional<AppUser> userOptional = userRepository.login(appUser.getEmail(), appUser.getPassword());
        if (userOptional.isPresent()) {
            return userOptional.get();
        }
        return null;
    }
}
