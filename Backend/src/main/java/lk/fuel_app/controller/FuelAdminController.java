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

    @GetMapping(value = "/getFuelOrders")
    public ResponseEntity getFuelOrders(){
        return ResponseEntity.ok(fuelAdminService.viewFuelOrders());
    }

    @GetMapping(value = "/getFuelStation")
    public ResponseEntity getFuelStation() {
        return ResponseEntity.ok(fuelAdminService.viewFuelStation());
    }

    //Getting order for approve or deny
    @GetMapping(value = "/getOrder")
    public ResponseEntity getOrder() {
        return ResponseEntity.ok(fuelAdminService.getOrder());
    }

    @PutMapping(value = "/updateStockIn/{id}")
    public ResponseEntity updateStockIn(@PathVariable String id, @RequestBody FuelAdminStockIn fuelAdminStockIn){
        return ResponseEntity.ok(fuelAdminService.updateStockIn(id,fuelAdminStockIn));
    }

    @DeleteMapping(value = "/deleteStockIn/{id}")
    public ResponseEntity deleteStockIn(@PathVariable String id){
        return ResponseEntity.ok(fuelAdminService.deleteStockIn(id));
    }

    @GetMapping(value = "/getStockInBystockFromStockFrom/{stockFrom}")
    public ResponseEntity getStockInBystockFrom(@PathVariable String stockFrom) {
        return ResponseEntity.ok(fuelAdminService.getStockInBystockFrom(stockFrom));
    }

    @GetMapping(value = "/getStockInByType/{type}")
    public ResponseEntity getStockInByType(@PathVariable String type) {
        System.out.println(type);
        return ResponseEntity.ok(fuelAdminService.getStockInByType(type));
    }

    @PutMapping(value = "/updateStockOut/{id}")
    public ResponseEntity updateStockOut(@PathVariable String id, @RequestBody FuelAdminStockOut fuelAdminStockOut){
        return ResponseEntity.ok(fuelAdminService.updateStockOut(id,fuelAdminStockOut));
    }

    @DeleteMapping(value = "/deleteStockOut/{id}")
    public ResponseEntity deleteStockOut(@PathVariable String id){
        return ResponseEntity.ok(fuelAdminService.deleteStockOut(id));
    }

    @GetMapping(value = "/getStockOutBystockFromStockFrom/{fuel_station_id}")
    public ResponseEntity getStockOutByStation(@PathVariable String fuel_station_id) {
        return ResponseEntity.ok(fuelAdminService.getStockOutByStation(fuel_station_id));
    }

    @GetMapping(value = "/getStockOutByType/{type}")
    public ResponseEntity getStockOutByType(@PathVariable String type) {
        return ResponseEntity.ok(fuelAdminService.getStockOutByType(type));
    }

    @GetMapping(value = "/getStockInById/{id}")
    public ResponseEntity getStockInById(@PathVariable String id) {
        return ResponseEntity.ok(fuelAdminService.getStockInById(id));
    }

    @GetMapping(value = "/getFuelTypes")
    public ResponseEntity getFuelTypes() {
        return ResponseEntity.ok(fuelAdminService.getFuelTypes());
    }

    @GetMapping(value = "/getorder/{id}")
    public ResponseEntity getOrder(@PathVariable String id) {
        return ResponseEntity.ok(fuelAdminService.getFuelOrder(id));
    }
}
