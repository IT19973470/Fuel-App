package lk.fuel_app.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class FuelStock {

    @Id
    private String id;
    private String fuelType;
    private double amount;
    private String driver;
    private boolean availability;
    private String vehicleNumber;
    private LocalDateTime actualArrival;

    @ManyToOne
    private FuelStation fuelStation;
}
