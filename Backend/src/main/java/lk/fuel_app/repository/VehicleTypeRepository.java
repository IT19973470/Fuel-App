package lk.fuel_app.repository;

import lk.fuel_app.entity.FuelType;
import lk.fuel_app.entity.VehicleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VehicleTypeRepository extends JpaRepository<VehicleType, String> {

    @Query("from VehicleType order by vehicleOrder asc")
    List<VehicleType> getVehicleTypes();
}
