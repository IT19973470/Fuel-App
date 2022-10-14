package lk.fuel_app.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class OrderData {
    @Id
    private String id;
    

    public OrderData(OrderData orderData) {
        this.id = orderData.id;
        this.fuelType = orderData.fuelType;
        this.amount = orderData.amount;
        this.date = orderData.date;
        this.status = orderData.status;
//        this.fuelAdmin = orderData.fuelAdmin;
//        this.fuelStation = orderData.fuelStation;
    }

    private String fuelType;
    private  String amount;
    private LocalDate date;
    private String status;
    
    @ManyToOne
    private FuelAdmin fuelAdmin;

    @ManyToOne
    private FuelStation fuelStation;
    
}
