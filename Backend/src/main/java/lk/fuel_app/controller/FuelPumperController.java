package lk.fuel_app.controller;

import lk.fuel_app.entity.CustomerFuelStation;
import lk.fuel_app.entity.FuelPumper;
import lk.fuel_app.entity.FuelPumperAttendance;
import lk.fuel_app.service.FuelPumperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@CrossOrigin
@RestController
@RequestMapping(value = "fuel/" + "fuelPumper")
public class FuelPumperController {

    @Autowired
    private FuelPumperService fuelPumperService;

    @PostMapping(value = "/addCustomerFuel")
    public ResponseEntity addCustomerFuel(@RequestBody CustomerFuelStation customerFuelStation) {
        return ResponseEntity.ok(fuelPumperService.addCustomerFuel(customerFuelStation));
    }

    @PutMapping(value = "/updateCustomerFuel/{id}")
    public ResponseEntity updateCustomerFuel(@RequestBody CustomerFuelStation customerFuelStation, @PathVariable String id) {
        return ResponseEntity.ok(fuelPumperService.updateCustomerFuel(customerFuelStation, id));
    }

    @DeleteMapping(value = "/deleteCustomerFuel/{customerNic}/{fuelStation}")
    public CustomerFuelStation deleteCustomerFuel(@PathVariable String customerNic, @PathVariable String fuelStation) {
        return fuelPumperService.deleteCustomerFuel(customerNic, fuelStation);
    }

    @PostMapping(value = "/addFuelPumper")
    public ResponseEntity addFuelPumper(@RequestBody FuelPumper fuelPumper) {
        return ResponseEntity.ok(fuelPumperService.addFuelPumper(fuelPumper));
    }

    @PostMapping(value = "/addFuelPumperAttendance")
    public ResponseEntity addFuelPumperAttendance(@RequestBody FuelPumperAttendance fuelPumperAttendance) {
        return ResponseEntity.ok(fuelPumperService.addFuelPumperAttendance(fuelPumperAttendance));
    }

    @GetMapping(value = "/getAllVehicleDetails")
    public ResponseEntity getAllVehicleDetails() {
        return ResponseEntity.ok(fuelPumperService.getAllVehicleDetails());
    }

    @GetMapping(value = "/getVehicleDetailsByType/{vehicleType}")
    public ResponseEntity getVehicleDetailsByType(@PathVariable String vehicleType) {
        System.out.println(vehicleType + ".........");
        return ResponseEntity.ok(fuelPumperService.getVehicleDetailsByType(vehicleType));
    }

    @GetMapping(value = "/getVehicleDetailsByDate/{date}")
    public ResponseEntity getVehicleDetailsByDate(@PathVariable String date) {
        return ResponseEntity.ok(fuelPumperService.getVehicleDetailsByDate(date));
    }

    @GetMapping(value = "/getVehicleDetailsByDate/{vehicleType}/{date}")
    public ResponseEntity getVehicleDetailsByTypeAndDate(@PathVariable String vehicleType, @PathVariable String date) {
        return ResponseEntity.ok(fuelPumperService.getVehicleDetailsByTypeAndDate(vehicleType, date));
    }
}