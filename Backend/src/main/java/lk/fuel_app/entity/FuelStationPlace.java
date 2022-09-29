package lk.fuel_app.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FuelStationPlace {

    @Id
    private String id;
    private String district;
    private String place;

    public FuelStationPlace(FuelStationPlace fuelStationPlace) {
        this.id = fuelStationPlace.id;
        this.district = fuelStationPlace.district;
        this.place = fuelStationPlace.place;
    }
}
