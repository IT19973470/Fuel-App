package lk.fuel_app.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class FuelPumperAttendance {

    @Id
    private String id;
    private LocalDate markedAt;
    private LocalTime timeIn;
    private LocalTime timeOut;

    public FuelPumperAttendance(FuelPumperAttendance fuelPumperAttendance) {
        this.id = fuelPumperAttendance.id;
        this.markedAt = fuelPumperAttendance.markedAt;
        this.timeIn = fuelPumperAttendance.timeIn;
        this.timeOut=fuelPumperAttendance.timeOut;
        if (fuelPumperAttendance.getFuelPumper() != null) {
            this.fuelPumper = new FuelPumper(fuelPumperAttendance.getFuelPumper());
        }
    }

    @ManyToOne
    private FuelPumper fuelPumper;
}
