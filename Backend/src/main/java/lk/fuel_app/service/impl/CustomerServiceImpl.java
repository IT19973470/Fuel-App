package lk.fuel_app.service.impl;

import lk.fuel_app.dto.FuelAvailabilityDTO;
import lk.fuel_app.entity.*;
import lk.fuel_app.repository.*;
import lk.fuel_app.service.CustomerService;
import lk.fuel_app.service.SendEmailSMTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerFuelStationRepository customerFuelStationRepository;
    @Autowired
    private FuelStationRepository fuelStationRepository;
    @Autowired
    private FuelTypeRepository fuelTypeRepository;

    @Autowired
    private SendEmailSMTP sendEmailSMTP;

    @Override
    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer getCustomer(String email, String contactNumber) {
        Optional<Customer> customer = customerRepository.getAllByAppUserEmailOrAppUserContactNumber(email, contactNumber);
        if (customer.isPresent()) {
            return customer.get();
        }
        return null;
    }

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
            customerObj.setAddress(customer.getAddress());

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
            Double fuelPumpedAmount = customerFuelStationRepository.getFuelPumpedAmount(vehicleNumber, LocalDate.now().with(DayOfWeek.MONDAY), LocalDate.now().with(DayOfWeek.SUNDAY));
            customerObj.setQuota(fuelPumpedAmount == null ? 0 : fuelPumpedAmount);
            return customerObj;
        }
        return null;
    }

    @Override
    public List<CustomerFuelStation> getPumpedAmounts(String id) {
        List<CustomerFuelStation> pumpsList = new ArrayList<>();
        List<CustomerFuelStation> pumps = customerFuelStationRepository.getAllByCustomerNicOrderByPumpedAtDesc(id);
        for (CustomerFuelStation pump : pumps) {
            pump.setPumpedAtFormatted(pump.getPumpedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            pumpsList.add(new CustomerFuelStation(pump));
        }
        return pumpsList;
    }

    @Override
    public Customer sendOTP(String email, String contactNumber) {
        int randomVal = new Random().nextInt(100000) + 1;
        new Thread(new Runnable() {
            @Override
            public void run() {
                sendEmailSMTP.sendEmail(email, "Fuel OTP",
                        "You OTP is " + randomVal);
            }
        }).start();
        Customer customer = new Customer();
        customer.setOtp(randomVal + "");
        return customer;
    }

    @Override
    public List<FuelAvailabilityDTO> fuelAvailability(String place, String orderBy) {
        List<FuelAvailabilityDTO> fuelAvailabilityDTOs = new ArrayList<>();
        List<FuelStation> fuelStations = fuelStationRepository.getAllByFuelStationPlaceId(place);
        for (FuelStation fuelStation : fuelStations) {
//            double fuelAvailable = 0, total = 0, pumped = 0;
            FuelAvailabilityDTO fuelAvailabilityDTO = new FuelAvailabilityDTO();
            fuelAvailabilityDTO.setFuelStation(fuelStation.getName());
//            fuelAvailabilityDTO.setAvailableStock(new HashMap<>());
            Map<String, FuelAvailabilityDTO.FuelStock> availableStockObj = new HashMap<>();
            List<FuelType> fuelTypes = fuelTypeRepository.getFuelTypes();
            for (FuelType fuelType : fuelTypes) {
                availableStockObj.put(fuelType.getId(), new FuelAvailabilityDTO.FuelStock(fuelType.getId(), fuelType.getName()));
            }

            for (FuelStock fuelStock : fuelStation.getFuelStocks()) {
//                total += fuelStock.getAmount();
                FuelAvailabilityDTO.FuelStock fuelStockObj = availableStockObj.get(fuelStock.getFuelType().getId());
                fuelStockObj.setQuantity(fuelStockObj.getQuantity() + fuelStock.getAmount());
                availableStockObj.put(fuelStock.getFuelType().getId(), fuelStockObj);
            }
            for (CustomerFuelStation customerFuelStation : fuelStation.getFuelPumped()) {
//                pumped += customerFuelStation.getFuelPumped();
                FuelAvailabilityDTO.FuelStock fuelStockObj = availableStockObj.get(customerFuelStation.getFuelType().getId());
                fuelStockObj.setQuantity(fuelStockObj.getQuantity() - customerFuelStation.getFuelPumped());
                availableStockObj.put(customerFuelStation.getFuelType().getId(), fuelStockObj);
            }
            fuelAvailabilityDTO.setAvailableStock(new ArrayList<>(availableStockObj.values()));
            Collections.sort(fuelAvailabilityDTO.getAvailableStock());
            fuelAvailabilityDTOs.add(fuelAvailabilityDTO);
        }
        return fuelAvailabilityDTOs;
    }
}
