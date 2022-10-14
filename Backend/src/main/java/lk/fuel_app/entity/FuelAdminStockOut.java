package lk.fuel_app.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class FuelAdminStockOut {
    @Id
    private String id;
    private String fuelType;
    private String date;
    private String time;
    private double amount;
    private String vehicleNumber;
    private String driverName;
    private String number;
    
    public FuelAdminStockOut (FuelAdminStockOut fuelAdminStockOut) {
        this.id = fuelAdminStockOut.id;
        this.fuelType = fuelAdminStockOut.fuelType;
        this.date = fuelAdminStockOut.date;
        this.time=fuelAdminStockOut.time;
        this.amount=fuelAdminStockOut.amount;
        this.vehicleNumber= fuelAdminStockOut.vehicleNumber;
        this.driverName=fuelAdminStockOut.driverName;
        this.number=fuelAdminStockOut.number;
        if (fuelAdminStockOut.getFuelStation() != null) {
            this.fuelStation = new FuelStation(fuelAdminStockOut.getFuelStation());
        }
    }
    @OneToOne()
    private FuelStation fuelStation;

    @ManyToOne
    private FuelAdmin fuelAdmin;
}
