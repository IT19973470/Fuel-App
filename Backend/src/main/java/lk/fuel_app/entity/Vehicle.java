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
public class Vehicle {

    @Id
    private String chassisNumber;
    private String vehicleNumber;
    private String fuelType;

    @ManyToOne
    private VehicleType vehicleType;

    public Vehicle(Vehicle vehicle) {
        this.chassisNumber = vehicle.chassisNumber;
        this.vehicleNumber = vehicle.vehicleNumber;
        this.fuelType = vehicle.fuelType;
        if (vehicle.getVehicleType() != null) {
            this.vehicleType = new VehicleType(vehicle.getVehicleType());
        }
    }
}
