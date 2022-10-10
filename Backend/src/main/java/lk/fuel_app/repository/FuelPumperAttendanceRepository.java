package lk.fuel_app.repository;

import lk.fuel_app.entity.FuelAdminStockOut;
import lk.fuel_app.entity.FuelPumperAttendance;
import org.hibernate.sql.Select;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface FuelPumperAttendanceRepository extends JpaRepository<FuelPumperAttendance, String> {
    @Query(value = "select count(id) from CustomerFuelStation where  pumpedAtDate  =?1 and fuelPumper.nic=?2")
    int getFuelPumpedCount(LocalDate pumpedAtDate, String nic);
}
