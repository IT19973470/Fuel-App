package lk.fuel_app.controller;

import lk.fuel_app.entity.*;
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
    @PostMapping(value = "/addFuelStock")
    public ResponseEntity addFuelStock(@RequestBody FuelAdminStockIn fuelAdminStockIn) {
        return ResponseEntity.ok(fuelAdminService.addStockIn(fuelAdminStockIn));
    }
    @PostMapping(value = "/outFuelStock")
    public ResponseEntity outFuelStock(@RequestBody FuelAdminStockOut fuelAdminStockOut) {
        return ResponseEntity.ok(fuelAdminService.addStockOut(fuelAdminStockOut));
    }
    @GetMapping(value = "/getFuelStockIn")
    public ResponseEntity getFuelStockIn() {
        return ResponseEntity.ok(fuelAdminService.viewStockIn());
    }

    @GetMapping(value = "/getFuelStockOut")
    public ResponseEntity getFuelStockOut() {
        return ResponseEntity.ok(fuelAdminService.viewStockOut());
    }


}
