package lk.fuel_app.repository;

import lk.fuel_app.entity.FuelType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuelTypeRepository extends JpaRepository<FuelType, String> {
}
