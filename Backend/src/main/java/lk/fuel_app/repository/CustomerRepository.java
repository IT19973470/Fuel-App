package lk.fuel_app.repository;

import lk.fuel_app.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, String> {

    Optional<Customer> getAllByVehicleNumber(String vehicle);

}
