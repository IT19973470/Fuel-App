package lk.fuel_app.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class FuelStock {

    @Id
    private String id;
    //    private String fuelType;
    private double amount;
    private String driver;
    private boolean availability;
    private String vehicleNumber;
    private LocalDateTime actualArrival;

    public FuelStock(FuelStock fuelStock) {
        this.id = fuelStock.id;
        this.amount = fuelStock.amount;
        this.driver = fuelStock.driver;
        this.availability = fuelStock.availability;
        this.vehicleNumber = fuelStock.vehicleNumber;
        this.actualArrival = fuelStock.actualArrival;
    }

    @ManyToOne
    private FuelStation fuelStation;

    @ManyToOne
    private FuelType fuelType;
}
