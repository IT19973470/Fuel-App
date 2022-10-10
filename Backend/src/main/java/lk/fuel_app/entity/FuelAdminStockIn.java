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
    private String amount;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getStockFrom() {
        return stockFrom;
    }

    public void setStockFrom(String stockFrom) {
        this.stockFrom = stockFrom;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public FuelAdmin getFuelAdmin() {
        return fuelAdmin;
    }

    public void setFuelAdmin(FuelAdmin fuelAdmin) {
        this.fuelAdmin = fuelAdmin;
    }

    @ManyToOne
    private FuelAdmin fuelAdmin;
}
