package lk.fuel_app.repository;

import lk.fuel_app.entity.FuelStock;
import lk.fuel_app.entity.FuelStockNext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FuelStockNextRepository extends JpaRepository<FuelStockNext, String> {

    //    @Query("from FuelStockNext order by arrival desc limit 1")
    List<FuelStockNext> getAllByFuelStation_Id(String stationId);

}
