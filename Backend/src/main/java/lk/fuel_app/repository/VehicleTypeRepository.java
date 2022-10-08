package lk.fuel_app.repository;

import lk.fuel_app.entity.VehicleType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleTypeRepository extends JpaRepository<VehicleType, String> {
}
