package lk.fuel_app.repository;

import lk.fuel_app.entity.FuelPumper;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuelPumperRepository extends JpaRepository<FuelPumper, String> {
}
