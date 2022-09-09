package lk.fuel_app.service;

import lk.fuel_app.entity.CustomerFuelStation;
import lk.fuel_app.entity.FuelPumper;
import lk.fuel_app.entity.FuelPumperAttendance;

public interface FuelPumperService {

    CustomerFuelStation addCustomerFuel(CustomerFuelStation customerFuelStation);

    CustomerFuelStation updateCustomerFuel(CustomerFuelStation customerFuelStation, String id);

    CustomerFuelStation deleteCustomerFuel(String customerNic, String fuelStation);

    FuelPumper addFuelPumper(FuelPumper fuelPumper);

    FuelPumperAttendance addFuelPumperAttendance(FuelPumperAttendance fuelPumperAttendance);
}
