package lk.fuel_app.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class FuelStationFuelType {

    @Id
    private String id;
    private double fuelAmount;

    @ManyToOne
    private FuelStation fuelStation;
    @ManyToOne
    private FuelType fuelType;
}
