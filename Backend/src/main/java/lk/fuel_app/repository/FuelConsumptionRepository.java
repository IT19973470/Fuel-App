package lk.fuel_app.repository;

import lk.fuel_app.entity.CustomerFuelStation;
import lk.fuel_app.entity.FuelConsumption;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface FuelConsumptionRepository extends JpaRepository<FuelConsumption, String> {

    List<FuelConsumption> getAllByCustomerNicOrderByCheckedAtDesc(String nic);

    @Query(value = "from FuelConsumption where customer.vehicle.vehicleNumber=?1 and checkedAt between ?3 and ?2")
    List<FuelConsumption> getFuelConsumptionsM(String vehicleNumber, LocalDateTime currentDate, LocalDateTime previousDate);

    @Query(value = "from FuelConsumption where customer.vehicle.vehicleNumber=?1 and consumed>0 order by checkedAt desc")
    List<FuelConsumption> getLastConsumption(String vehicleNumber, Pageable pageable);

}
