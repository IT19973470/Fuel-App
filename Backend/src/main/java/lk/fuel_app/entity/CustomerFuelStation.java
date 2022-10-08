package lk.fuel_app.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class CustomerFuelStation {

    @Id
    private String id;
    private double fuelPumped;
    private LocalDateTime pumpedAt;
    private LocalDate pumpedAtDate;

    public CustomerFuelStation(CustomerFuelStation customerFuelStation) {
        this.id = customerFuelStation.id;
        this.fuelPumped = customerFuelStation.fuelPumped;
        this.pumpedAt = customerFuelStation.pumpedAt;
        this.pumpedAtDate = customerFuelStation.pumpedAtDate;
        this.pumpedAtFormatted = customerFuelStation.pumpedAtFormatted;
        if (customerFuelStation.getCustomer() != null) {
            this.customer = new Customer(customerFuelStation.getCustomer());
        }
        if (customerFuelStation.getFuelStation() != null) {
            this.fuelStation = new FuelStation(customerFuelStation.getFuelStation());
        }
        if (customerFuelStation.getFuelType() != null) {
            this.fuelType = new FuelType(customerFuelStation.getFuelType());
        }
    }

    @Transient
    private String pumpedAtFormatted;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private FuelStation fuelStation;
    
    @ManyToOne
    private FuelPumper fuelPumper;

    @ManyToOne
    private FuelType fuelType;
}
