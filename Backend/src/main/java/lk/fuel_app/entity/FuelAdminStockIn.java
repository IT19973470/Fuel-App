package lk.fuel_app.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

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
}
