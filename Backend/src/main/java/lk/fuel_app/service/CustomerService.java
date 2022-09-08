package lk.fuel_app.service;

import lk.fuel_app.entity.Customer;
import lk.fuel_app.entity.CustomerFuelStation;

import java.util.List;

public interface CustomerService {
    Customer addCustomer(Customer customer);

//    Customer getCustomer(String id);

    boolean deleteCustomer(String id);

    Customer updateCustomer(Customer customer, String id);

    Customer getCustomerByVehicle(String vehicle);

    List<CustomerFuelStation> getPumpedAmounts(String id);
}
