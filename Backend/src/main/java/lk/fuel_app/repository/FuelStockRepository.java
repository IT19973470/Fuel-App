package lk.fuel_app.repository;

import lk.fuel_app.entity.FuelAdminStockOut;
import lk.fuel_app.entity.FuelStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface FuelStockRepository extends JpaRepository<FuelStock, String> {

    List<FuelStock> getFuelStocksById(String id);

    @Query(value = "from FuelStock where fuelStation.id=?1 and fuelType.id=?2")
    List<FuelStock> getFuelStocksAmount(String id, String fuelType);

    @Query(value = "from FuelStock where fuelStation.id=?1 order by actualArrival desc")
    List<FuelStock> getLastFuelPump(String id);

   // SElect fuel_type_id,sum(amount) from fuel_stock group by fuel_type_id
    @Query(value = "select sum(amount) from FuelStock where fuelType.id=?1 group by fuelType")
    int getTotalStockAmount(String id);

}
