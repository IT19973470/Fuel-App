package lk.fuel_app.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FuelStockDTO {

    private String fuelType;
    private double distributedFuel;
    private double distributedVehicleCount;
    private double availableStock;
    private double availableHours;
    private double fuelSupplyPerHour;

}
