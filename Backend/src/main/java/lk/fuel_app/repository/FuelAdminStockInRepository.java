package lk.fuel_app.repository;
import lk.fuel_app.entity.FuelAdminStockIn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FuelAdminStockInRepository extends JpaRepository<FuelAdminStockIn, String> {
    @Query(value = "from FuelAdminStockIn where fuelType=:fuelType")
    List<FuelAdminStockIn> getStockInByFuelType(@Param("fuelType") String fuelType);

    @Query(value = "from FuelAdminStockIn where stockFrom=:stockFrom")
    List<FuelAdminStockIn> getStockInByStockFrom(@Param("stockFrom") String stockFrom);

}
