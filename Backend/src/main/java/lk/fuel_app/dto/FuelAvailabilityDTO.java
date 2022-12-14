package lk.fuel_app.dto;

import lk.fuel_app.entity.FuelStation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class FuelAvailabilityDTO {

    private FuelStation fuelStation;
    private String fuelStationStr;
    private List<FuelStock> availableStock;
    private List<FuelStock> pumpedStock;
    private List<FuelStock> fuelSupplyPerHour;
    private List<FuelStock> nextFuelAvailability;
    private List<FuelStock> totalPumped;
    private List<Vehicle> availableVehicles;
    private List<Vehicle> distributedVehicles;
    private List<FuelReport> fuelReports;
    private List<FuelConsumption> fuelConsumptions;

    @Getter
    @Setter
    @NoArgsConstructor
    public static class FuelStock implements Comparable {
        private String fuelTypeId;
        private String fuelType;
        private double quantity;
        private double count;
        private LocalDateTime fuelAvailableAt;
        private String nextFuelAmountDateAt;
        private String nextFuelAmountTimeAt;
        private String fuelPumpedAt;

        public FuelStock(String fuelTypeId, String fuelType) {
            this.fuelTypeId = fuelTypeId;
            this.fuelType = fuelType;
        }

        @Override
        public int compareTo(Object o) {
            FuelStock fuelStock = (FuelStock) o;
            return fuelTypeId.compareTo(fuelStock.getFuelTypeId());
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Vehicle implements Comparable {
        private String vehicleTypeId;
        private String vehicleType;
        private int vehicleCount;
        private double pumped;
        private List<Vehicle> vehicles;

        public Vehicle(String vehicleTypeId, String vehicleType) {
            this.vehicleTypeId = vehicleTypeId;
            this.vehicleType = vehicleType;
        }

        @Override
        public int compareTo(Object o) {
            Vehicle vehicle = (Vehicle) o;
            return vehicleTypeId.compareTo(vehicle.getVehicleTypeId());
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class FuelReport {
        private int week;
        private List<FuelStock> fuelStocks;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class FuelConsumption {
        private int week;
        private double fuelPumped;
//        private double trip;
        private double fuelConsumed;
//        private double fuelRemain;

    }
}