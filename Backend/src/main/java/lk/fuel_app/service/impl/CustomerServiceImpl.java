package lk.fuel_app.service.impl;

import lk.fuel_app.entity.Customer;
import lk.fuel_app.entity.CustomerFuelStation;
import lk.fuel_app.repository.CustomerFuelStationRepository;
import lk.fuel_app.repository.CustomerRepository;
import lk.fuel_app.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerFuelStationRepository customerFuelStationRepository;

    @Override
    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

//    @Override
//    public Customer getCustomer(String id) {
//        Optional<Customer> customer = customerRepository.findById(id);
//        if (customer.isPresent()) {
//            return customer.get();
//        }
//        return null;
//    }

    @Override
    public boolean deleteCustomer(String id) {
        customerRepository.deleteById(id);
        return true;
    }

    @Override
    public Customer updateCustomer(Customer customer, String id) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        if (customerOptional.isPresent()) {
            Customer customerObj = customerOptional.get();
            customerObj.setName(customer.getName());
//            customerObj.setQuota(customer.getQuota());
            return customerRepository.save(customerObj);
        }
        return null;
    }

    @Override
    public Customer getCustomerByVehicle(String vehicleNumber) {
        Optional<Customer> customer = customerRepository.getAllByVehicleNumber(vehicleNumber);
        if (customer.isPresent()) {
            Customer customerObj = customer.get();
            customerObj.setQuota(customerFuelStationRepository.getFuelPumpedAmount(vehicleNumber, LocalDate.now().with(DayOfWeek.MONDAY), LocalDate.now().with(DayOfWeek.SUNDAY)));
            return customerObj;
        }
        return null;
    }

    @Override
    public List<CustomerFuelStation> getPumpedAmounts(String id) {
        List<CustomerFuelStation> pumps = customerFuelStationRepository.getAllByCustomerNicOrderByPumpedAtDesc(id);
        for (CustomerFuelStation pump : pumps) {
            pump.setPumpedAtFormatted(pump.getPumpedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        }
        return pumps;
    }
}
