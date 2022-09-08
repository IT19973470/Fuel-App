package lk.fuel_app.repository;

import lk.fuel_app.entity.FuelStation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuelStationRepository extends JpaRepository<FuelStation, String> {
}
