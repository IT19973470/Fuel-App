package lk.fuel_app.repository;

import lk.fuel_app.entity.FuelStationFuelType;
import lk.fuel_app.entity.FuelStockNext;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FuelStationFuelTypeRepository extends JpaRepository<FuelStationFuelType, String> {

    Optional<FuelStationFuelType> getAllByFuelStation_IdAndFuelType_Id(String fuelStation, String fuelType);

    List<FuelStationFuelType> getAllByFuelStation_Id(String stationId);

}
