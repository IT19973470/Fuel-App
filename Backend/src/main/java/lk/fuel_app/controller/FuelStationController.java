package lk.fuel_app.controller;

import lk.fuel_app.entity.FuelStation;
import lk.fuel_app.entity.FuelStock;
import lk.fuel_app.service.FuelStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "fuel/" + "fuel_station")
public class FuelStationController {

    @Autowired
    private FuelStationService fuelStationService;

    @PostMapping(value = "/addFuelStation")
    public ResponseEntity addFuelStation(@RequestBody FuelStation fuelStation) {
        return ResponseEntity.ok(fuelStationService.addFuelStation(fuelStation));
    }

    @PostMapping(value = "/addFuelStock")
    public ResponseEntity addFuelStock(@RequestBody FuelStock fuelStock) {
        return ResponseEntity.ok(fuelStationService.addFuelStock(fuelStock));
    }

}
