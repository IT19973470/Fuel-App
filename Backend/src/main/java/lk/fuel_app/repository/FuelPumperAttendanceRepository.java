package lk.fuel_app.repository;

import lk.fuel_app.entity.FuelPumperAttendance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuelPumperAttendanceRepository extends JpaRepository<FuelPumperAttendance, String> {
}
