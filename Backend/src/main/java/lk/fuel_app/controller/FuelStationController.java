package lk.fuel_app.controller;

import lk.fuel_app.entity.*;
import lk.fuel_app.service.FuelStationService;
import lk.fuel_app.util.ReportView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Map;

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

    @PostMapping(value = "/addNextFuelStock")
    public ResponseEntity addNextFuelStock(@RequestBody FuelStockNext fuelStockNext) {
        return ResponseEntity.ok(fuelStationService.addNextFuelStock(fuelStockNext));
    }

    @GetMapping(value = "/getFuelStock/{id}")
    public ResponseEntity getFuelStock(@PathVariable String id) {
        return ResponseEntity.ok(fuelStationService.getFuelStock(id));
    }
    @GetMapping(value = "/getAvailableStocks/{id}")
    public ResponseEntity getFuelStockIn(@PathVariable String id) {
        return ResponseEntity.ok(fuelStationService.getFuelStockIn(id));
    }
    @GetMapping(value = "/getAttendence")
    public ResponseEntity getAttendence() {
        return ResponseEntity.ok(fuelStationService.getAttendence());
    }
    @GetMapping(value = "/getAttendence/{startDate}/{endDate}")
    public ResponseEntity getAttendenceByDate(@PathVariable String startDate, @PathVariable String endDate) {
        return ResponseEntity.ok(fuelStationService.getAttendenceByDate(startDate,endDate));
    }
    @GetMapping(value = "/getAdmin")
    public ResponseEntity getAdmin() {
        return ResponseEntity.ok(fuelStationService.viewFuelAdmin());
    }

    @GetMapping(value = "/getchat")
    public ResponseEntity getAllChats(){
        return ResponseEntity.ok(fuelStationService.getChat());
    }
    @PostMapping(value = "/addChat")
    public ResponseEntity addChats(@RequestBody Chat chat){
        return ResponseEntity.ok(fuelStationService.addChat(chat));
    }
    @GetMapping(value = "/getFuelStation/{id}")
    public ResponseEntity getFuelStation(@PathVariable String id) {
        return ResponseEntity.ok(fuelStationService.viewFuelStation(id));
    }
    @PostMapping(value = "/order")
    public ResponseEntity addOrder(@RequestBody OrderData order){
        System.out.println(order);
       return ResponseEntity.ok(fuelStationService.addOrder(order));
    }
    @GetMapping(value = "/getorder/{id}")
    public ResponseEntity getOrder(@PathVariable String id) {
        return ResponseEntity.ok(fuelStationService.getFuelOrder(id));
    }
    @PutMapping(value = "/updateOrder/{id}")
    public ResponseEntity updateOrder(@RequestBody OrderData orderData, @PathVariable String id) {
        return ResponseEntity.ok(fuelStationService.updateOrder(orderData, id));
    }
    @GetMapping(value = "/getVehicleReport/{id}")
    public ResponseEntity getVehicleReport(@PathVariable String id) {
        return ResponseEntity.ok(fuelStationService.getVehicleReport(id));
    }
    @GetMapping(value = "/getVehicleReport/{id}/{type}")
    public ResponseEntity getVehicleReportType(@PathVariable String id,@PathVariable String type) {
        return ResponseEntity.ok(fuelStationService.getVehicleReportType(id,type));
    }
    @DeleteMapping(value = "/deleteOrder/{id}")
    public ResponseEntity deleteOrder(@PathVariable String id) {
        return ResponseEntity.ok(fuelStationService.deleteOrder(id));
    }
    @GetMapping(value = "/getAllVehicleTypes/")
    public ResponseEntity getAllVehicleTypes() {
        return ResponseEntity.ok(fuelStationService.getAllVehicleTypes());
    }

    @GetMapping(value="/allVehicleDetailsReport", produces= MediaType.APPLICATION_JSON_VALUE)
    public Map generateVehicleDetailsReport() {
        System.out.println(".................");
        ReportView review = new ReportView();

        List<CustomerFuelStation> customerFuelStations;
        customerFuelStations = fuelStationService.getAllVehicleDetails();
        String reportValue = "";
        try {
            reportValue = review.pdfReportViewInlineSystemOpen("allVehicleDetails.jasper", "All Vehicle Details Report", customerFuelStations, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.singletonMap("response", reportValue);
    }
    
}
