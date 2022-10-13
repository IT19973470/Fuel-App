package lk.fuel_app.service;

import lk.fuel_app.dto.FuelAvailabilityDTO;
import lk.fuel_app.entity.Customer;
import lk.fuel_app.entity.CustomerFuelStation;
import lk.fuel_app.entity.FuelConsumption;

import java.util.List;

public interface CustomerService {
    Customer addCustomer(Customer customer);

    Customer getCustomer(String email, String contactNumber);

    boolean deleteCustomer(String id);

    Customer updateCustomer(Customer customer, String id);

    Customer getCustomerByVehicle(String vehicle);

    List<CustomerFuelStation> getPumpedAmounts(String id);

    Customer sendOTP(String email, String contactNumber);

    List<FuelAvailabilityDTO> fuelAvailability(String place, String orderBy);

    FuelConsumption addFuelConsumption(FuelConsumption fuelConsumption);

    List<FuelConsumption> getFuelConsumptions(String id);

    boolean deleteFuelConsumption(String id);
}
