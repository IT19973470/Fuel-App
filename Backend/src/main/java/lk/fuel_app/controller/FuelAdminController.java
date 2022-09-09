package lk.fuel_app.controller;

import lk.fuel_app.entity.FuelAdmin;
import lk.fuel_app.entity.FuelStation;
import lk.fuel_app.entity.FuelStock;
import lk.fuel_app.service.FuelAdminService;
import lk.fuel_app.service.FuelStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "fuel/" + "fuel_admin")
public class FuelAdminController {

    @Autowired
    private FuelAdminService fuelAdminService;

    @PostMapping(value = "/addFuelAdmin")
    public ResponseEntity addFuelAdmin(@RequestBody FuelAdmin fuelAdmin) {
        return ResponseEntity.ok(fuelAdminService.addFuelAdmin(fuelAdmin));
    }

}
