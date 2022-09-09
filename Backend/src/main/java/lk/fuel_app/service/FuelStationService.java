package lk.fuel_app.service;

import lk.fuel_app.dto.FuelStockDTO;
import lk.fuel_app.entity.CustomerFuelStation;
import lk.fuel_app.entity.FuelPumper;
import lk.fuel_app.entity.FuelStation;
import lk.fuel_app.entity.FuelStock;

import java.util.List;

public interface FuelStationService {
    FuelStation addFuelStation(FuelStation fuelStation);

    FuelStock addFuelStock(FuelStock fuelStock);

    List<FuelStockDTO> getFuelStock(String id);
}
