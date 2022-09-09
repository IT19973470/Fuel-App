package lk.fuel_app.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
@Getter
@Setter
public class AppUser {

    @Id
    private String id;
    private String email;
    private String password;
    private String userType;
    private String contactNumber;

    @Transient
    private FuelPumper fuelPumper;

    @Transient
    private FuelStation fuelStation;
}
