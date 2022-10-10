package lk.fuel_app.repository;


import lk.fuel_app.entity.FuelStation;
import lk.fuel_app.entity.OrderData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderData, String> {
    List<OrderData> getAllByFuelStationId(String id);
}
