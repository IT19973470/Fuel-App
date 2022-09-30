package lk.fuel_app.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class FuelStockNext {

    @Id
    private String id;
    //    private String fuelType;
    private double amount;
    private LocalDateTime arrival;

    public FuelStockNext(FuelStockNext fuelStock) {
        this.id = fuelStock.id;
        this.amount = fuelStock.amount;
    }

    @ManyToOne
    private FuelStation fuelStation;

    @ManyToOne
    private FuelType fuelType;
}
