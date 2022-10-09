package lk.fuel_app.repository;

import lk.fuel_app.entity.FuelStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FuelStationRepository extends JpaRepository<FuelStation, String> {

    List<FuelStation> getAllByFuelStationPlaceId(String place);
    FuelStation getByAppUserId(String id);

//    @Query("from FuelStation where ")
//    List<FuelStation> getFuelStationPlaceId(String place);

}
