package lk.fuel_app.controller;

import lk.fuel_app.entity.CustomerFuelStation;
import lk.fuel_app.entity.FuelStation;
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

    @PostMapping(value = "/addCustomerFuel")
    public ResponseEntity addCustomerFuel(@RequestBody CustomerFuelStation customerFuelStation) {
        return ResponseEntity.ok(fuelStationService.addCustomerFuel(customerFuelStation));
    }

    @PutMapping(value = "/updateCustomerFuel/{id}")
    public ResponseEntity updateCustomerFuel(@RequestBody CustomerFuelStation customerFuelStation, @PathVariable String id) {
        return ResponseEntity.ok(fuelStationService.updateCustomerFuel(customerFuelStation, id));
    }

    @DeleteMapping(value = "/deleteCustomerFuel/{customerNic}/{fuelStation}")
    public boolean deleteCustomerFuel(@PathVariable String customerNic, @PathVariable String fuelStation) {
        return fuelStationService.deleteCustomerFuel(customerNic, fuelStation);
    }
}
