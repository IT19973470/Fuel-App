package lk.fuel_app.repository;

import lk.fuel_app.entity.FuelConsumption;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FuelConsumptionRepository extends JpaRepository<FuelConsumption, String> {

    List<FuelConsumption> getAllByCustomerNicOrderByCheckedAtDesc(String nic);

}
