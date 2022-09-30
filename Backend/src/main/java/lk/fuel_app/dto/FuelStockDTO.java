package lk.fuel_app.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FuelStockDTO implements Comparable {

    private String fuelTypeId;
    private String fuelType;
    private double distributedFuel;
    private double distributedVehicleCount;
    private double availableStock;
    private double availableHours;
    private double fuelSupplyPerHour;

    public FuelStockDTO(String fuelTypeId, String fuelType) {
        this.fuelTypeId = fuelTypeId;
        this.fuelType = fuelType;
    }

    @Override
    public int compareTo(Object o) {
        FuelStockDTO fuelStock = (FuelStockDTO) o;
        return fuelTypeId.compareTo(fuelStock.getFuelTypeId());
    }
}
