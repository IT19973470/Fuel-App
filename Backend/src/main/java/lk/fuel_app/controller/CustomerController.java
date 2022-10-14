package lk.fuel_app.controller;

import lk.fuel_app.dto.FuelAvailabilityDTO;
import lk.fuel_app.entity.Customer;
import lk.fuel_app.entity.CustomerFuelStation;
import lk.fuel_app.entity.FuelConsumption;
import lk.fuel_app.entity.Vehicle;
import lk.fuel_app.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "fuel/" + "customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping(value = "/addCustomer")
    public ResponseEntity addCustomer(@RequestBody Customer customer) {
        return ResponseEntity.ok(customerService.addCustomer(customer));
    }

    @PutMapping(value = "/updateCustomer/{id}")
    public ResponseEntity updateCustomer(@RequestBody Customer customer, @PathVariable String id) {
        return ResponseEntity.ok(customerService.updateCustomer(customer, id));
    }

    @GetMapping(value = "/getCustomerByVehicle/{vehicle}")
    public Customer getCustomerByVehicle(@PathVariable String vehicle) {
        return customerService.getCustomerByVehicle(vehicle);
    }

    @GetMapping(value = "/getPumpedAmounts/{id}")
    public List<CustomerFuelStation> getPumpedAmounts(@PathVariable String id) {
        return customerService.getPumpedAmounts(id);
    }

    @GetMapping(value = "/getCustomer/{email}/{contactNumber}")
    public Customer getCustomer(@PathVariable String email, @PathVariable String contactNumber) {
        return customerService.getCustomer(email, contactNumber);
    }

    @DeleteMapping(value = "/deleteCustomer/{id}")
    public boolean deleteCustomer(@PathVariable String id) {
        return customerService.deleteCustomer(id);
    }

    @GetMapping(value = "/sendOTP/{email}/{contactNumber}")
    public Customer sendOTP(@PathVariable String email, @PathVariable String contactNumber) {
        return customerService.sendOTP(email, contactNumber);
    }

    @GetMapping(value = "/fuelAvailability/{place}/{orderBy}")
    public List<FuelAvailabilityDTO> fuelAvailability(@PathVariable String place, @PathVariable String orderBy) {
        return customerService.fuelAvailability(place, orderBy);
    }

    @PostMapping(value = "/addFuelConsumption")
    public ResponseEntity addFuelConsumption(@RequestBody FuelConsumption fuelConsumption) {
        return ResponseEntity.ok(customerService.addFuelConsumption(fuelConsumption));
    }

    @DeleteMapping(value = "/deleteFuelConsumption/{id}")
    public boolean deleteFuelConsumption(@PathVariable String id) {
        return customerService.deleteFuelConsumption(id);
    }

    @GetMapping(value = "/getFuelConsumptions/{id}")
    public List<FuelConsumption> getFuelConsumptions(@PathVariable String id) {
        return customerService.getFuelConsumptions(id);
    }

    @GetMapping(value = "/getFuelAvailabilityM/{place}")
    public List<FuelAvailabilityDTO> getFuelAvailabilityM(@PathVariable String place) {
        return customerService.getFuelAvailabilityM(place);
    }

    @GetMapping(value = "/getFuelConsumptionsM/{vehicle}")
    public FuelAvailabilityDTO getFuelConsumptionsM(@PathVariable String vehicle) {
        return customerService.getFuelConsumptionsM(vehicle);
    }

    @GetMapping(value = "/regenerateQR/{vehicle}")
    public Vehicle regenerateQR(@PathVariable String vehicle) {
        return customerService.regenerateQR(vehicle);
    }
}
