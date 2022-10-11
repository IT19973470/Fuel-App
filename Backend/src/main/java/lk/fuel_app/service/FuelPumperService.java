package lk.fuel_app.service;

import lk.fuel_app.dto.FuelAvailabilityDTO;
import lk.fuel_app.entity.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface FuelPumperService {

    CustomerFuelStation addCustomerFuel(CustomerFuelStation customerFuelStation);

    CustomerFuelStation updateCustomerFuel(CustomerFuelStation customerFuelStation, String id);

    CustomerFuelStation deleteCustomerFuel(String customerNic, String fuelStation);

    FuelPumper addFuelPumper(FuelPumper fuelPumper);

    FuelPumperAttendance addFuelPumperAttendance(FuelPumperAttendance fuelPumperAttendance);

    FuelPumperAttendance markTimeOutAttendance(FuelPumperAttendance fuelPumperAttendance, String id);

    List<FuelPumperAttendance>  getAttendance();
    List<CustomerFuelStation> getAllVehicleDetails();

    List<CustomerFuelStation> getVehicleDetailsByType(String vehicleType);

    List<CustomerFuelStation> getVehicleDetailsByDate(String date);

    List<CustomerFuelStation> getVehicleDetailsByTypeAndDate(String vehicleType, String date);


//    List<CustomerFuelStation> getVehicleCountAndFuelAmount(String vehicleType);

    List<FuelType> getFuelTypes();


    List<VehicleType> getAllVehicleTypes();

    List<FuelAvailabilityDTO> getAllFuelRecord(String startDate, String endDate);

    List<CustomerFuelStation> getAllFuelRecordChart(String startDate, String endDate);

}