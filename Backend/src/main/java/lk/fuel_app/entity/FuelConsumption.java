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
public class FuelConsumption {

    @Id
    private String id;
    @ManyToOne
    private Customer customer;
    private LocalDateTime checkedAt;
    @Transient
    private String checkedAtDate;
//    private LocalDateTime pumpedAt;
    private double consumed;
    private double trip;

    public FuelConsumption(FuelConsumption fuelConsumption) {
        this.id = fuelConsumption.getId();
        this.checkedAt = fuelConsumption.getCheckedAt();
        this.consumed = fuelConsumption.getConsumed();
        this.trip = fuelConsumption.getTrip();
    }
}
