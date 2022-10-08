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
public class VehicleType {

    @Id
    private String id;
    private String name;
    private String namePlural;
    private int vehicleOrder;

    public VehicleType(VehicleType vehicleType) {
        this.id = vehicleType.id;
        this.name = vehicleType.name;
        this.namePlural = vehicleType.namePlural;
    }
}
