package lk.fuel_app.repository;

import lk.fuel_app.entity.FuelStock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuelStockRepository extends JpaRepository<FuelStock, String> {
}
