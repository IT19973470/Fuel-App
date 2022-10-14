package lk.fuel_app.repository;

import lk.fuel_app.entity.AppUser;
import lk.fuel_app.entity.Customer;
import lk.fuel_app.entity.CustomerFuelStation;
import lk.fuel_app.entity.FuelStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CustomerFuelStationRepository extends JpaRepository<CustomerFuelStation, String> {

    Optional<CustomerFuelStation> getTopByCustomerNicAndFuelStationIdOrderByPumpedAtDesc(String customerNic, String fuelStation);

    @Query(value = "select sum(fuelPumped) from CustomerFuelStation where customer.vehicle.vehicleNumber=?1 and pumpedAtDate between ?2 and ?3")
    Double getFuelPumpedAmount(String vehicleNumber, LocalDate startDate, LocalDate endDate);

    @Query(value = "from CustomerFuelStation where fuelStation.id=?1 and pumpedAtDate between ?2 and ?3")
    List<CustomerFuelStation> getFuelPumpedVehicles(String fuelStation, LocalDate startDate, LocalDate endDate);

    List<CustomerFuelStation> getAllByCustomerNicOrderByPumpedAtDesc(String nic);

//    @Query(value = "select sum(fuelPumped) from CustomerFuelStation where id=?1 and pumpedAtDate >= ?3")
//    Integer getFuelPumpedVehicles(String id,String fuelType, LocalDate pumpedFrom);

    @Query(value = "from CustomerFuelStation where customer.vehicle.vehicleType.id=:vehicleType")
    List<CustomerFuelStation> getVehicleDetailsByType(@Param("vehicleType") String vehicleType);

    @Query(value = "from CustomerFuelStation where pumpedAtDate=:pumpedAtDate")
    List<CustomerFuelStation> getVehicleDetailsByDate(@Param("pumpedAtDate") LocalDate pumpedAtDate);

    @Query(value = "from CustomerFuelStation where customer.vehicle.vehicleType=:vehicleType and pumpedAtDate=:pumpedAtDate")
    List<CustomerFuelStation> getVehicleDetailsByTypeAndDate(@Param("vehicleType") String vehicleType, @Param("pumpedAtDate") LocalDate pumpedAtDate);

//    @Query(value = "select sum(fuelPumped) from CustomerFuelStation where customer.vehicleType=:vehicleType")
//    Double getVehicleCountAndFuelAmount(@Param("vehicleType") String vehicleType);

    @Query("select count(id) from  CustomerFuelStation  where fuelType.id=?1 group by fuelType")
    int getCountVehicle(String id);

    @Query("select sum(fuelPumped) from  CustomerFuelStation  where fuelType.id=?1 group by fuelType")
    int getSumDistribution(String id);
    
    @Query("select sum(fuelPumped) from  CustomerFuelStation  where fuelType.id=?1 and  pumpedAt between ?2 and ?3 group by fuelType")
    Double getoneHourDeistibution(String id,LocalDateTime before, LocalDateTime timeNow);

    @Query("from CustomerFuelStation where fuelStation.id=?1 and pumpedAt between ?2 and ?3")
    List<CustomerFuelStation> getFuelSupplyPerHour(String fuelStation, LocalDateTime before, LocalDateTime timeNow);
  //  SELECT fuel_type_id,sum(fuel_pumped) from customer_fuel_station where fuel_type_id="1_Petrol_92" and  pumped_at between "2022-10-08 21:43:34" and "2022-10-08 21:43:36" group by  fuel_type_id
    @Query("from CustomerFuelStation where pumpedAtDate between :startDate and :endDate")
    List<CustomerFuelStation> getAllFuelRecord(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query(value = "from CustomerFuelStation where customer.vehicle.vehicleNumber=?1 and pumpedAt between ?3 and ?2")
    List<CustomerFuelStation> getFuelPumpedM(String vehicleNumber, LocalDateTime currentDate, LocalDateTime previousDate);
}
