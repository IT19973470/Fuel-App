package lk.fuel_app.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Customer {

    @Id
    private String nic;
    private String name;
    private String address;
    private String chassisNumber;
    private String vehicleNumber;
    private String vehicleType;
    private String fuelType;

    @Transient
    private double quota;

    @Transient
    private String otp;

    @OneToOne(cascade = CascadeType.ALL)
    private AppUser appUser;
}
