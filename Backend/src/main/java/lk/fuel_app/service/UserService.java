package lk.fuel_app.service;

import lk.fuel_app.entity.AppUser;
import org.apache.catalina.User;

public interface UserService {

    AppUser login(AppUser appUser);
}
