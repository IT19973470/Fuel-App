package lk.fuel_app.service;

import lk.fuel_app.entity.CustomerFuelStation;
import lk.fuel_app.entity.FuelPumper;
import lk.fuel_app.entity.FuelStation;

public interface FuelStationService {
    FuelStation addFuelStation(FuelStation fuelStation);

    CustomerFuelStation addCustomerFuel(CustomerFuelStation customerFuelStation);

    CustomerFuelStation updateCustomerFuel(CustomerFuelStation customerFuelStation, String id);

    boolean deleteCustomerFuel(String customerNic, String fuelStation);

    FuelPumper addFuelPumper(FuelPumper fuelPumper);
}
