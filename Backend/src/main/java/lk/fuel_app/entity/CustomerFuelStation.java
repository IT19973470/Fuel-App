package lk.fuel_app.entity;

import lombok.Getter;
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
public class CustomerFuelStation {

    @Id
    private String id;
    private double fuelPumped;
    private LocalDateTime pumpedAt;
    private LocalDate pumpedAtDate;

    @Transient
    private String pumpedAtFormatted;

    @ManyToOne
    private Customer customer;
    @ManyToOne
    private FuelStation fuelStation;
}
