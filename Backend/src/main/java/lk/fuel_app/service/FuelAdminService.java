package lk.fuel_app.service;

import lk.fuel_app.dto.FuelStationDTO;
import lk.fuel_app.entity.*;

import java.util.List;

public interface FuelAdminService {

    FuelAdmin addFuelAdmin(FuelAdmin fuelAdmin);
    FuelAdminStockIn addStockIn(FuelAdminStockIn fuelAdminStockIn);
    FuelAdminStockOut addStockOut(FuelAdminStockOut fuelAdminStockOut);
    List<FuelAdminStockIn> viewStockIn();
    List<FuelAdminStockOut> viewStockOut();
    List<FuelStationDTO> viewFuelStation();
    
}
