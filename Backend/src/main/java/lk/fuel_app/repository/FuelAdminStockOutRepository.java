package lk.fuel_app.repository;
import lk.fuel_app.entity.FuelAdminStockOut;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FuelAdminStockOutRepository extends JpaRepository<FuelAdminStockOut, String> {
    List<FuelAdminStockOut> findAllByFuelStation_Id(String id);
}
