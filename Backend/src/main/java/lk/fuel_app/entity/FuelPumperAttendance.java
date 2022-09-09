package lk.fuel_app.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
public class FuelPumperAttendance {

    @Id
    private String id;
    private LocalDate markedAt;
    private LocalTime timeIn;
    private LocalTime timeOut;

    @ManyToOne(cascade = CascadeType.ALL)
    private FuelPumper fuelPumper;
}
