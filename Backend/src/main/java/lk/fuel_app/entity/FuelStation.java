package lk.fuel_app.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class FuelStation {

    @Id
    private String id;
    private String name;
    private String address;
    private double latitude;
    private double longitude;

    public FuelStation(FuelStation fuelStation) {
        this.id = fuelStation.id;
        this.name = fuelStation.name;
        this.address = fuelStation.address;
        this.latitude = fuelStation.latitude;
        this.longitude = fuelStation.longitude;
        if (fuelStation.getFuelStationPlace() != null) {
            this.fuelStationPlace = new FuelStationPlace(fuelStation.getFuelStationPlace());
        }
    }

    @ManyToOne
    private FuelStationPlace fuelStationPlace;

    @OneToOne(cascade = CascadeType.ALL)
    private AppUser appUser;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fuelStation")
    private Set<FuelStock> fuelStocks;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fuelStation")
    private Set<CustomerFuelStation> fuelPumped;

}
