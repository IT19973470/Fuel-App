package lk.fuel_app.repository;

import lk.fuel_app.entity.Customer;
import lk.fuel_app.entity.CustomerFuelStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, String> {

    Optional<Customer> getAllByVehicleVehicleNumber(String vehicle);

    Optional<Customer> getAllByAppUserEmailOrAppUserContactNumber(String email, String contactNumber);

//    select c.vehicle_type, count(c.id) from customer_fuel_station cf, customer c where cf.customer_nic=c.nic group by c.vehicle_type;
    @Query(value = "select c.vehicle_type, count(c.nic) from customer_fuel_station cf, customer c where cf.customer_nic=c.nic group by c.vehicle_type",nativeQuery = true)
     List<Object[]> getCustomerCount();
//    @Query(value = "select c.vehicleType from CustomerFuelStation cf, Customer c where cf.customer.nic=c.nic group by c.vehicleType")
//    int  getCustomerCount();
    
}
