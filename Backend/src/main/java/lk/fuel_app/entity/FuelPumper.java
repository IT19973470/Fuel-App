package lk.fuel_app.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class FuelPumper {

    @Id
    private String nic;
    private String name;
    private String address;

    @OneToOne(cascade = CascadeType.ALL)
    private AppUser appUser;

    @ManyToOne
    private FuelStation fuelStation;

    public FuelPumper(FuelPumper fuelPumper) {
        this.nic = fuelPumper.nic;
        this.name = fuelPumper.name;
        this.address = fuelPumper.address;
        if (fuelPumper.getFuelStation() != null) {
            this.fuelStation = new FuelStation(fuelPumper.getFuelStation());
        }
    }

//    public FuelPumper(FuelPumper fuelPumper, FuelStation fuelStation) {
//        this(fuelPumper);
//        this.fuelStation = fuelStation;
//    }
}
