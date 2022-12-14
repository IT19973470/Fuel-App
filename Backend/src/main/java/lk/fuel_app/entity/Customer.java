package lk.fuel_app.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Customer {

    @Id
    private String nic;
    private String name;
    private String address;

    @ManyToOne
    private Vehicle vehicle;

    public Customer(Customer customer) {
        this.nic = customer.nic;
        this.name = customer.name;
        this.address = customer.address;
        this.quota = customer.quota;
        if (customer.getVehicle() != null) {
            this.vehicle = new Vehicle(customer.getVehicle());
        }
        if (customer.getFuelStationPlace() != null) {
            this.fuelStationPlace = new FuelStationPlace(customer.getFuelStationPlace());
        }
    }

//    @ManyToOne
//    private VehicleType vehicleType;

    @ManyToOne
    private FuelStationPlace fuelStationPlace;

    @Transient
    private double quota;

    @Transient
    private String otp;

    @OneToOne(cascade = CascadeType.ALL)
    private AppUser appUser;
}
