package lk.fuel_app.repository;

import lk.fuel_app.entity.AppUser;
import lk.fuel_app.entity.Customer;
import lk.fuel_app.entity.CustomerFuelStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CustomerFuelStationRepository extends JpaRepository<CustomerFuelStation, String> {

    Optional<CustomerFuelStation> getTopByCustomerNicAndFuelStationIdOrderByPumpedAtDesc(String customerNic, String fuelStation);

    @Query(value = "select sum(fuelPumped) from CustomerFuelStation where customer.vehicleNumber=?1 and pumpedAtDate between ?2 and ?3")
    Double getFuelPumpedAmount(String vehicleNumber, LocalDate startDate, LocalDate endDate);

    List<CustomerFuelStation> getAllByCustomerNicOrderByPumpedAtDesc(String nic);

//    @Query(value = "select sum(fuelPumped) from CustomerFuelStation where id=?1 and pumpedAtDate >= ?3")
//    Integer getFuelPumpedVehicleCount(String id,String fuelType, LocalDate pumpedFrom);

    @Query(value = "from CustomerFuelStation where customer.vehicleType=:vehicleType")
    List<CustomerFuelStation> getVehicleDetailsByType(@Param("vehicleType") String vehicleType);

    @Query(value = "from CustomerFuelStation where pumpedAtDate=:pumpedAtDate")
    List<CustomerFuelStation> getVehicleDetailsByDate(@Param("pumpedAtDate") LocalDate pumpedAtDate);

    @Query(value = "from CustomerFuelStation where customer.vehicleType=:vehicleType and pumpedAtDate=:pumpedAtDate")
    List<CustomerFuelStation> getVehicleDetailsByTypeAndDate(@Param("vehicleType") String vehicleType, @Param("pumpedAtDate") LocalDate pumpedAtDate);
}