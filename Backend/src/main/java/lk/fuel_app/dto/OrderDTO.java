package lk.fuel_app.dto;

import lk.fuel_app.entity.FuelAdmin;
import lk.fuel_app.entity.FuelStation;
import lk.fuel_app.entity.OrderData;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDTO {
    public OrderData orderData;
    private FuelAdmin fuelAdmin;
    private FuelStation fuelStation;
}
