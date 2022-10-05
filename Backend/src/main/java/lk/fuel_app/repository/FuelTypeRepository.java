package lk.fuel_app.repository;

import lk.fuel_app.entity.FuelType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FuelTypeRepository extends JpaRepository<FuelType, String> {

    @Query("from FuelType order by fuelOrder asc")
    List<FuelType> getFuelTypes();

}
