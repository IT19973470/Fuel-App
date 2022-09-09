package lk.fuel_app.service;

import lk.fuel_app.entity.CustomerFuelStation;
import lk.fuel_app.entity.FuelPumper;
import lk.fuel_app.entity.FuelStation;
import lk.fuel_app.entity.FuelStock;

public interface FuelStationService {
    FuelStation addFuelStation(FuelStation fuelStation);

    FuelStock addFuelStock(FuelStock fuelStock);
}
