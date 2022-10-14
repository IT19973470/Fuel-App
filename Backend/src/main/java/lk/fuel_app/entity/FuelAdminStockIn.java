package lk.fuel_app.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class FuelAdminStockIn {
    @Id
    private String id;
    private String fuelType;
    private String stockFrom;
    private String date;
    private String time;
    private double amount;

    public FuelAdminStockIn(FuelAdminStockIn fuelAdminStockIn) {
        if (fuelAdminStockIn != null) {
            this.id = fuelAdminStockIn.getId();
            this.fuelType = fuelAdminStockIn.getFuelType();
            this.stockFrom = fuelAdminStockIn.getStockFrom();
            this.date = fuelAdminStockIn.getDate();
            this.time = fuelAdminStockIn.getTime();
            this.amount = fuelAdminStockIn.getAmount();
        }
    }

    public FuelAdminStockIn() {

    }

    @ManyToOne
    private FuelAdmin fuelAdmin;
}
