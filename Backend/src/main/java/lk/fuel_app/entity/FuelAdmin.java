package lk.fuel_app.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
@Getter
@Setter
public class FuelAdmin {

    @Id
    private String nic;
    private String name;
    private String address;
    private String contactNumber;

    @OneToOne(cascade = CascadeType.ALL)
    private AppUser appUser;
}
