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
    private FuelStockNextRepository fuelStockNextRepository;
    @Autowired
    private VehicleTypeRepository vehicleTypeRepository;
    @Autowired
    private FuelStationFuelTypeRepository fuelStationFuelTypeRepository;

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
            FuelAvailabilityDTO fuelAvailabilityDTO = new FuelAvailabilityDTO();
            fuelAvailabilityDTO.setFuelStation(new FuelStation(fuelStation));
            Map<String, FuelAvailabilityDTO.FuelStock> availableStockObj = new HashMap<>();
            Map<String, FuelAvailabilityDTO.FuelStock> nextStockObj = new HashMap<>();
            Map<String, FuelAvailabilityDTO.Vehicle> availableVehiclesObj = new HashMap<>();
            Map<String, FuelAvailabilityDTO.Vehicle> distributedVehiclesObj = new HashMap<>();

            List<FuelType> fuelTypes = fuelTypeRepository.getFuelTypes();
            for (FuelType fuelType : fuelTypes) {
                availableStockObj.put(fuelType.getId(), new FuelAvailabilityDTO.FuelStock(fuelType.getId(), fuelType.getName()));
                nextStockObj.put(fuelType.getId(), new FuelAvailabilityDTO.FuelStock(fuelType.getId(), fuelType.getName()));
            }

            for (VehicleType vehicleType : vehicleTypeRepository.getVehicleTypes()) {
                availableVehiclesObj.put(vehicleType.getId(), new FuelAvailabilityDTO.Vehicle(vehicleType.getId(), vehicleType.getName()));
                distributedVehiclesObj.put(vehicleType.getId(), new FuelAvailabilityDTO.Vehicle(vehicleType.getId(), vehicleType.getNamePlural()));
            }

            List<FuelStationFuelType> fuelStationFuelTypeOpt = fuelStationFuelTypeRepository.getAllByFuelStation_Id(fuelStation.getId());
            for (FuelStationFuelType fuelStationFuelType : fuelStationFuelTypeOpt) {
                FuelAvailabilityDTO.FuelStock fuelStockObj = availableStockObj.get(fuelStationFuelType.getFuelType().getId());
                fuelStockObj.setQuantity(fuelStationFuelType.getFuelAmount());
                availableStockObj.put(fuelStationFuelType.getFuelType().getId(), fuelStockObj);
            }

            List<CustomerFuelStation> fuelPumpedVehicles = customerFuelStationRepository.getFuelPumpedVehicles(fuelStation.getId(), LocalDate.now());
            for (CustomerFuelStation customerFuelStation : fuelPumpedVehicles) {
//                FuelAvailabilityDTO.FuelStock fuelStockObj = availableStockObj.get(customerFuelStation.getFuelType().getId());
//                fuelStockObj.setQuantity(fuelStockObj.getQuantity() - customerFuelStation.getFuelPumped());
//                availableStockObj.put(customerFuelStation.getFuelType().getId(), fuelStockObj);

                FuelAvailabilityDTO.Vehicle vehicle = distributedVehiclesObj.get(customerFuelStation.getCustomer().getVehicleType().getId());
                vehicle.setVehicleCount(vehicle.getVehicleCount() + 1);
                distributedVehiclesObj.put(customerFuelStation.getCustomer().getVehicleType().getId(), vehicle);
            }

            List<FuelStockNext> fuelStockNexts = fuelStockNextRepository.getAllByFuelStation_Id(fuelStation.getId());
            for (FuelStockNext fuelStockNext : fuelStockNexts) {
                FuelAvailabilityDTO.FuelStock fuelStockObj = nextStockObj.get(fuelStockNext.getFuelType().getId());
                fuelStockObj.setQuantity(fuelStockNext.getAmount());
                fuelStockObj.setNextFuelAmountDateAt(fuelStockNext.getArrival().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                fuelStockObj.setNextFuelAmountTimeAt(fuelStockNext.getArrival().format(DateTimeFormatter.ofPattern("hh:mm a")));
                nextStockObj.put(fuelStockNext.getFuelType().getId(), fuelStockObj);
            }

            fuelAvailabilityDTO.setAvailableStock(new ArrayList<>(availableStockObj.values()));
            fuelAvailabilityDTO.setNextFuelAvailability(new ArrayList<>(nextStockObj.values()));
            fuelAvailabilityDTO.setDistributedVehicles(new ArrayList<>(distributedVehiclesObj.values()));
            Collections.sort(fuelAvailabilityDTO.getAvailableStock());
            Collections.sort(fuelAvailabilityDTO.getNextFuelAvailability());
            Collections.sort(fuelAvailabilityDTO.getDistributedVehicles());
            fuelAvailabilityDTOs.add(fuelAvailabilityDTO);
        }
        return fuelAvailabilityDTOs;
    }
}
