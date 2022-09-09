package lk.fuel_app.repository;

import lk.fuel_app.entity.FuelStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface FuelStockRepository extends JpaRepository<FuelStock, String> {

    List<FuelStock> getFuelStocksById(String id);

    @Query(value = "select sum(amount) from FuelStock where fuelStation.id=?1 and fuelType=?2")
    Integer getFuelStocksAmount(String id, String fuelType);

    @Query(value = "from FuelStock where fuelStation.id=?1 order by actualArrival desc")
    List<FuelStock> getLastFuelPump(String id);

}
