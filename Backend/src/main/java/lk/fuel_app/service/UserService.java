package lk.fuel_app.service;

import lk.fuel_app.entity.AppUser;
import lk.fuel_app.entity.FuelStationPlace;
import org.apache.catalina.User;

import java.util.List;

public interface UserService {

    AppUser login(AppUser appUser);

    List<FuelStationPlace> getPlaces();

}
