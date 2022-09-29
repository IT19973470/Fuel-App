package lk.fuel_app.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class FuelAvailabilityDTO {

    private String fuelStation;
    private Map<String,FuelStock> availableStock;
    private Map<String,FuelStock> fuelSupplyPerHour;
    private Map<String,FuelStock> nextFuelAvailability;
    private Map<String,FuelStock> totalPumped;
    private List<Vehicle> availableVehicles;
    private List<Vehicle> distributedVehicles;

    @Getter
    @Setter
    public static class FuelStock {
//        private String fuelType;
        private double quantity;
        private LocalDateTime fuelAvailableAt;
    }

    @Getter
    @Setter
    public static class Vehicle {
        private String vehicleType;
        private int vehicleCount;
        private double pumped;
    }
}
