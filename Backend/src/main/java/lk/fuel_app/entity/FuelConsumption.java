package lk.fuel_app.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class FuelConsumption {

    @Id
    private String id;
    @ManyToOne
    private Customer customer;
    private LocalDate checkedAt;
    private double consumption;

    public FuelConsumption(FuelConsumption fuelConsumption) {
        this.id = fuelConsumption.getId();
        this.checkedAt = fuelConsumption.getCheckedAt();
        this.consumption = fuelConsumption.getConsumption();
    }
}
