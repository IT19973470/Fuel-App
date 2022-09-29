package lk.fuel_app.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FuelType {

    @Id
    private String id;
    private String name;
    private int fuelOrder;

    public FuelType(FuelType fuelType) {
        this.id = fuelType.id;
        this.name = fuelType.name;
    }
}
