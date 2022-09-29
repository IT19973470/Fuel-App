package lk.fuel_app.repository;

import lk.fuel_app.entity.FuelStationPlace;
import lk.fuel_app.entity.FuelType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FuelStationPlaceRepository extends JpaRepository<FuelStationPlace, String> {

    @Query("from FuelStationPlace order by place asc")
    List<FuelStationPlace> getFuelStationPlaces();
}
