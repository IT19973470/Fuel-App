package lk.fuel_app.repository;
import lk.fuel_app.entity.FuelAdminStockIn;
import lk.fuel_app.entity.FuelAdminStockOut;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FuelAdminStockOutRepository extends JpaRepository<FuelAdminStockOut, String> {
    List<FuelAdminStockOut> findAllByFuelStation_Id(String id);

    @Query(value = "from FuelAdminStockOut where fuelType=:fuelType")
    List<FuelAdminStockOut> getStockOutByFuelType(@Param("fuelType") String fuelType);
}
