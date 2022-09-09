package lk.fuel_app.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class FuelType {

    @Id
    private String id;
    private String name;
}
