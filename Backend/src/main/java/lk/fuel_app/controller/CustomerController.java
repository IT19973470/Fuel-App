package lk.fuel_app.controller;

import lk.fuel_app.entity.Customer;
import lk.fuel_app.entity.CustomerFuelStation;
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

//    @GetMapping(value = "/getCustomer/{id}")
//    public Customer getCustomer(@PathVariable String id) {
//        return customerService.getCustomer(id);
//    }

    @DeleteMapping(value = "/deleteCustomer/{id}")
    public boolean deleteCustomer(@PathVariable String id) {
        return customerService.deleteCustomer(id);
    }

}
