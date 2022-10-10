package lk.fuel_app.service;

import lk.fuel_app.dto.*;
import lk.fuel_app.entity.*;

import java.util.List;

public interface FuelStationService {
    FuelStation addFuelStation(FuelStation fuelStation);

    FuelStock addFuelStock(FuelStock fuelStock);
    OrderData addOrder(OrderData order);
    List<FuelStockDTO> getFuelStock(String id);
    List<FuelAdminStockOutDTO> getFuelStockIn(String id);
    List<AttandanceDTO> getAttendence();
    List<FuelAdmin> viewFuelAdmin();

    public List<Chat> getChat();
    public Chat addChat(Chat chat);

    FuelStockNext addNextFuelStock(FuelStockNext fuelStockNext);
    FuelStationDTO viewFuelStation(String id);
    List<OrderDTO> getFuelOrder(String id);
    OrderData updateOrder(OrderData orderData, String id);
    
}
