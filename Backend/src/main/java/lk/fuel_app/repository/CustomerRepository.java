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

    Optional<Customer> getAllByVehicleSecKey(String vehicle);

    Optional<Customer> getAllByAppUserEmailOrAppUserContactNumber(String email, String contactNumber);

//    select c.vehicle_type, count(c.id) from customer_fuel_station cf, customer c where cf.customer_nic=c.nic group by c.vehicle_type;
    
    @Query(value = "select vt.name,count(cf.id) from customer_fuel_station cf, customer c, vehicle v, vehicle_type vt where cf.customer_nic=c.nic and c.vehicle_chassis_number =v.chassis_number and vt.id=v.vehicle_type_id and cf.fuel_station_id=?1  group by v.vehicle_type_id ",nativeQuery = true)
     List<Object[]> getCustomerCount(String fuel_station_id );
    @Query(value = "select vt.name,count(cf.id) from customer_fuel_station cf, customer c, vehicle v, vehicle_type vt where cf.customer_nic=c.nic and c.vehicle_chassis_number =v.chassis_number and vt.id=v.vehicle_type_id and cf.fuel_station_id=?1 and vt.name=?2  group by v.vehicle_type_id ",nativeQuery = true)
    List<Object[]> getCustomerCountType(String fuel_station_id,String vehicleType );
//    @Query(value = "select c.vehicleType from CustomerFuelStation cf, Customer c where cf.customer.nic=c.nic group by c.vehicleType")
//    int  getCustomerCount();
    
}
