package lk.fuel_app.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class Vehicle {

    @Id
    private String chassisNumber;
    private String vehicleNumber;
    private String vehicleType;
    private String fuelType;

    @ManyToOne
    private Customer customer;
}
