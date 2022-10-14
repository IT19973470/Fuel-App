package lk.fuel_app.service;

import lk.fuel_app.dto.FuelStationDTO;
import lk.fuel_app.dto.OrderDTO;
import lk.fuel_app.entity.*;

import java.util.List;
import java.util.Optional;

public interface FuelAdminService {

    FuelAdmin addFuelAdmin(FuelAdmin fuelAdmin);
    FuelAdminStockIn addStockIn(FuelAdminStockIn fuelAdminStockIn);
    FuelAdminStockOut addStockOut(FuelAdminStockOut fuelAdminStockOut);
    List<FuelAdminStockIn> viewStockIn();
    List<FuelAdminStockOut> viewStockOut();
    List<FuelStationDTO> viewFuelStation();

    List<OrderData> getOrder();

    Object updateStockIn(String id, FuelAdminStockIn fuelAdminStockIn);

    boolean deleteStockIn(String id);

    List<FuelAdminStockIn>  getStockInBystockFrom(String stockFrom);

    List<FuelAdminStockIn> getStockInByType(String type);

    Object updateStockOut(String id, FuelAdminStockOut fuelAdminStockOut);

    boolean deleteStockOut(String id);

    FuelAdminStockOut getStockOutByStation(String fuel_station_id);

    FuelAdminStockOut getStockOutByType(String type);

    List<OrderData> viewFuelOrders();

    Optional<FuelAdminStockIn> getStockInById(String id);

    List<FuelType> getFuelTypes();

    List<OrderDTO> getFuelOrder(String id);
}
