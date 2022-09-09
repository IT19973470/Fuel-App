package lk.fuel_app.repository;

import lk.fuel_app.entity.CustomerFuelStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CustomerFuelStationRepository extends JpaRepository<CustomerFuelStation, String> {

    Optional<CustomerFuelStation> getTopByCustomerNicAndFuelStationIdOrderByPumpedAtDesc(String customerNic, String fuelStation);

    @Query(value = "select sum(fuelPumped) from CustomerFuelStation where customer.vehicleNumber=?1 and pumpedAtDate between ?2 and ?3")
    Double getFuelPumpedAmount(String vehicleNumber, LocalDate startDate, LocalDate endDate);

    List<CustomerFuelStation> getAllByCustomerNicOrderByPumpedAtDesc(String nic);
}
