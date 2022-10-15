package lk.fuel_app.repository;

import lk.fuel_app.entity.CustomerFuelStation;
import lk.fuel_app.entity.FuelAdminStockOut;
import lk.fuel_app.entity.FuelPumperAttendance;
import org.hibernate.sql.Select;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface FuelPumperAttendanceRepository extends JpaRepository<FuelPumperAttendance, String> {
    @Query(value = "select count(id) from CustomerFuelStation where  pumpedAtDate  =?1 and fuelPumper.nic=?2")
    Integer getFuelPumpedCount(LocalDate pumpedAtDate, String nic);

    @Query(value = "select a from  FuelPumperAttendance a where a.markedAt between :startDate and :endDate")
    public List<FuelPumperAttendance> FindAllBetween(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);


//    @Query(value = "select count(id) from CustomerFuelStation where  pumpedAtDate  =?1 and fuelPumper.nic=?2 and pumpedAtDate between =?3 and =?4")
//    int getFuelPumpedCountByDate(LocalDate pumpedAtDate, String nic, LocalDate startDate, LocalDate endDate);
}
//between :startDate and :endDate
