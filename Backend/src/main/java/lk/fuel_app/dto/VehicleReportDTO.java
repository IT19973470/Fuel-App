package lk.fuel_app.dto;

import lk.fuel_app.entity.CustomerFuelStation;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VehicleReportDTO {
    private int count;
   private String vehicleType;
}
