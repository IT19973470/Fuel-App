package lk.fuel_app.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
@Getter
@Setter
public class FuelAdminStockOut {
    @Id
    private String id;
    private String fuelType;
    private String date;
    private String time;
    private String amount;
    private String vehicleNumber;
    private String driverName;
    private String number;
    @OneToOne(cascade = CascadeType.ALL)
    private FuelStation fuelStation;
}
