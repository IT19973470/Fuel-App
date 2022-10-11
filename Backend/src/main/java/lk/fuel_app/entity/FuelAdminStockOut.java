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

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public FuelStation getFuelStation() {
        return fuelStation;
    }

    public void setFuelStation(FuelStation fuelStation) {
        this.fuelStation = fuelStation;
    }

    public FuelAdmin getFuelAdmin() {
        return fuelAdmin;
    }

    public void setFuelAdmin(FuelAdmin fuelAdmin) {
        this.fuelAdmin = fuelAdmin;
    }

    private String date;
    private String time;
    private String amount;
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
