package lk.fuel_app.service;

import lk.fuel_app.dto.FuelStockDTO;
import lk.fuel_app.entity.*;

import java.util.List;

public interface FuelStationService {
    FuelStation addFuelStation(FuelStation fuelStation);

    FuelStock addFuelStock(FuelStock fuelStock);

    List<FuelStockDTO> getFuelStock(String id);
    List<FuelAdminStockOut> getFuelStockIn(String id);
    List<FuelPumperAttendance> getAttendence();
    List<FuelAdmin> viewFuelAdmin();

    public List<Chat> getChat();
    public Chat addChat(Chat chat);
}
