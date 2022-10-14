package lk.fuel_app.repository;

import lk.fuel_app.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle, String> {

    Vehicle getByVehicleNumber(String vehicle);

}
