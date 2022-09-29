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
    private String chassisNumber;
    private String vehicleNumber;
    private String vehicleType;
    private String fuelType;

    public Customer(Customer customer) {
        this.nic = customer.nic;
        this.name = customer.name;
        this.address = customer.address;
        this.chassisNumber = customer.chassisNumber;
        this.vehicleNumber = customer.vehicleNumber;
        this.vehicleType = customer.vehicleType;
        this.fuelType = customer.fuelType;
        this.quota = customer.quota;
        if (customer.getFuelStationPlace() != null) {
            this.fuelStationPlace = new FuelStationPlace(customer.getFuelStationPlace());
        }
    }

    @ManyToOne
    private FuelStationPlace fuelStationPlace;

    @Transient
    private double quota;

    @Transient
    private String otp;

    @OneToOne(cascade = CascadeType.ALL)
    private AppUser appUser;
}
