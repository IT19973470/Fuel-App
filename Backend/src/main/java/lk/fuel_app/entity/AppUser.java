package lk.fuel_app.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class AppUser {

    @Id
    private String id;
    private String email;
    private String password;
    private String userType;
    private String contactNumber;

    public AppUser(AppUser appUser) {
        this.id = appUser.id;
        this.email = appUser.email;
        this.password = appUser.password;
        this.userType = appUser.userType;
        this.contactNumber = appUser.contactNumber;
        if (appUser.getFuelPumper() != null) {
            this.fuelPumper = new FuelPumper(appUser.getFuelPumper());
        }
    }

//    public AppUser(AppUser appUser, FuelPumper fuelPumper) {
//        this(appUser);
//        this.fuelPumper = new FuelPumper(fuelPumper);
//    }

    @Transient
    private FuelPumper fuelPumper;

    @Transient
    private FuelStation fuelStation;
}
