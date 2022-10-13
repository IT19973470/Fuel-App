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
import java.time.LocalTime;
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
    private VehicleRepository vehicleRepository;
    @Autowired
    private FuelConsumptionRepository fuelConsumptionRepository;
    @Autowired
    private FuelStockRepository fuelStockRepository;

    @Autowired
    private SendEmailSMTP sendEmailSMTP;

    @Override
    public Customer addCustomer(Customer customer) {
        customer.setVehicle(vehicleRepository.save(customer.getVehicle()));
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
        Optional<Customer> customer = customerRepository.getAllByVehicleVehicleNumber(vehicleNumber);
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
            Map<String, FuelAvailabilityDTO.FuelStock> fuelSupplyObj = new HashMap<>();
            Map<String, FuelAvailabilityDTO.Vehicle> distributedVehiclesObj = new HashMap<>();

            Map<String, Map<String, FuelAvailabilityDTO.FuelStock>> vehicleCountAvgObj = new HashMap<>();
            Map<String, Map<String, FuelAvailabilityDTO.FuelStock>> fuelCountAvgObj = new HashMap<>();

//            for (Map<String, FuelAvailabilityDTO.FuelStock> vehicleCountAvgObjO : vehicleCountAvgObj.values()) {
//                vehicleCountAvgObjO = new HashMap<>();
//            }
//            for (Map<String, FuelAvailabilityDTO.FuelStock> fuelCountAvgObjO : fuelCountAvgObj.values()) {
//                fuelCountAvgObjO = new HashMap<>();
//            }

            List<FuelType> fuelTypes = fuelTypeRepository.getFuelTypes();
            for (FuelType fuelType : fuelTypes) {
                availableStockObj.put(fuelType.getId(), new FuelAvailabilityDTO.FuelStock(fuelType.getId(), fuelType.getName()));
                nextStockObj.put(fuelType.getId(), new FuelAvailabilityDTO.FuelStock(fuelType.getId(), fuelType.getName()));
                fuelSupplyObj.put(fuelType.getId(), new FuelAvailabilityDTO.FuelStock(fuelType.getId(), fuelType.getName()));
            }

            List<VehicleType> vehicleTypes = vehicleTypeRepository.getVehicleTypes();
            for (VehicleType vehicleType : vehicleTypes) {
                distributedVehiclesObj.put(vehicleType.getId(), new FuelAvailabilityDTO.Vehicle(vehicleType.getId(), vehicleType.getNamePlural()));

                Map<String, FuelAvailabilityDTO.FuelStock> fuelList = new HashMap<>();
                for (FuelType fuelType : fuelTypes) {
                    fuelList.put(fuelType.getId(), new FuelAvailabilityDTO.FuelStock(fuelType.getId(), fuelType.getName()));
                }
                vehicleCountAvgObj.put(vehicleType.getId(), fuelList);
                fuelList = new HashMap<>();
                for (FuelType fuelType : fuelTypes) {
                    fuelList.put(fuelType.getId(), new FuelAvailabilityDTO.FuelStock(fuelType.getId(), fuelType.getName()));
                }
                fuelCountAvgObj.put(vehicleType.getId(), fuelList);
            }

            List<FuelStationFuelType> fuelStationFuelTypeOpt = fuelStationFuelTypeRepository.getAllByFuelStation_Id(fuelStation.getId());
            for (FuelStationFuelType fuelStationFuelType : fuelStationFuelTypeOpt) {
                FuelAvailabilityDTO.FuelStock fuelStockObj = availableStockObj.get(fuelStationFuelType.getFuelType().getId());
                fuelStockObj.setQuantity(fuelStationFuelType.getFuelAmount());
//                availableStockObj.put(fuelStationFuelType.getFuelType().getId(), fuelStockObj);
            }

            List<CustomerFuelStation> fuelPumpedVehicles = customerFuelStationRepository.getFuelPumpedVehicles(fuelStation.getId(), LocalDate.now().minusDays(1), LocalDate.now());
            for (CustomerFuelStation customerFuelStation : fuelPumpedVehicles) {
//                FuelAvailabilityDTO.FuelStock fuelStockObj = availableStockObj.get(customerFuelStation.getFuelType().getId());
//                fuelStockObj.setQuantity(fuelStockObj.getQuantity() - customerFuelStation.getFuelPumped());
//                availableStockObj.put(customerFuelStation.getFuelType().getId(), fuelStockObj);
                FuelAvailabilityDTO.Vehicle vehicle = distributedVehiclesObj.get(customerFuelStation.getCustomer().getVehicle().getVehicleType().getId());
                vehicle.setVehicleCount(vehicle.getVehicleCount() + 1);
//                distributedVehiclesObj.put(customerFuelStation.getCustomer().getVehicleType().getId(), vehicle);

            }

            List<CustomerFuelStation> fuelSupplyPerHour = customerFuelStationRepository.getFuelSupplyPerHour(fuelStation.getId(), LocalDateTime.now().minusHours(24), LocalDateTime.now());
            for (CustomerFuelStation customerFuelStation : fuelSupplyPerHour) {
                FuelAvailabilityDTO.FuelStock fuelStockObj = fuelSupplyObj.get(customerFuelStation.getFuelType().getId());
                fuelStockObj.setQuantity(fuelStockObj.getQuantity() + customerFuelStation.getFuelPumped());
//                fuelSupplyObj.put(customerFuelStation.getFuelType().getId(), fuelStockObj);

                Map<String, FuelAvailabilityDTO.FuelStock> vehicleCountAvgMap = vehicleCountAvgObj.get(customerFuelStation.getCustomer().getVehicle().getVehicleType().getId());
                FuelAvailabilityDTO.FuelStock vehicleCountAvg = vehicleCountAvgMap.get(customerFuelStation.getFuelType().getId());
                vehicleCountAvg.setCount(vehicleCountAvg.getCount() + 1);
//                vehicleCountAvgMap.put(customerFuelStation.getCustomer().getVehicleType().getId(), vehicleCountAvg);

                Map<String, FuelAvailabilityDTO.FuelStock> fuelCountAvgMap = fuelCountAvgObj.get(customerFuelStation.getCustomer().getVehicle().getVehicleType().getId());
                FuelAvailabilityDTO.FuelStock fuelCountAvg = fuelCountAvgMap.get(customerFuelStation.getFuelType().getId());
                fuelCountAvg.setCount(fuelCountAvg.getCount() + customerFuelStation.getFuelPumped());
//                fuelCountAvgMap.put(customerFuelStation.getCustomer().getVehicleType().getId(), fuelCountAvg);
            }

            for (VehicleType vehicleType : vehicleTypes) {
                Map<String, FuelAvailabilityDTO.FuelStock> vehicleCountAvg = vehicleCountAvgObj.get(vehicleType.getId());
                Map<String, FuelAvailabilityDTO.FuelStock> fuelCountAvg = fuelCountAvgObj.get(vehicleType.getId());
                for (FuelType fuelType : fuelTypes) {
                    FuelAvailabilityDTO.FuelStock vehicleCount = vehicleCountAvg.get(fuelType.getId());
                    vehicleCount.setCount(vehicleCount.getCount() / 24.0);

                    FuelAvailabilityDTO.FuelStock fuelStock = fuelCountAvg.get(fuelType.getId());
                    fuelStock.setCount(fuelStock.getCount() / 24.0);
                    fuelStock.setCount(fuelStock.getCount() * vehicleCount.getCount());
                }
            }

            for (FuelType fuelType : fuelTypes) {
                FuelAvailabilityDTO.FuelStock fuelStockObj = fuelSupplyObj.get(fuelType.getId());
                fuelStockObj.setQuantity(fuelStockObj.getQuantity() / 24.0);
//                fuelSupplyObj.put(fuelType.getId(), fuelStockObj);
                double fuelTotal = 0;
                for (Map<String, FuelAvailabilityDTO.FuelStock> fuelCountAvg : fuelCountAvgObj.values()) {
                    FuelAvailabilityDTO.FuelStock fuelStock = fuelCountAvg.get(fuelType.getId());
                    fuelTotal += fuelStock.getCount();
                }
                if (fuelTotal <= 1) {
                    fuelTotal = 1;
                }
                FuelAvailabilityDTO.FuelStock fuelStock = availableStockObj.get(fuelType.getId());
                for (Map<String, FuelAvailabilityDTO.FuelStock> vehicleCountMap : vehicleCountAvgObj.values()) {
                    FuelAvailabilityDTO.FuelStock vehicleCountAvg = vehicleCountMap.get(fuelType.getId());
                    vehicleCountAvg.setCount(vehicleCountAvg.getCount() * (fuelStock.getQuantity() / fuelTotal));
                }
            }

            List<FuelStockNext> fuelStockNexts = fuelStockNextRepository.getAllByFuelStation_Id(fuelStation.getId());
            for (FuelStockNext fuelStockNext : fuelStockNexts) {
                FuelAvailabilityDTO.FuelStock fuelStockObj = nextStockObj.get(fuelStockNext.getFuelType().getId());
                fuelStockObj.setQuantity(fuelStockNext.getAmount());
                fuelStockObj.setNextFuelAmountDateAt(fuelStockNext.getArrival().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                fuelStockObj.setNextFuelAmountTimeAt(fuelStockNext.getArrival().format(DateTimeFormatter.ofPattern("hh:mm a")));
//                nextStockObj.put(fuelStockNext.getFuelType().getId(), fuelStockObj);
            }

            fuelAvailabilityDTO.setAvailableStock(new ArrayList<>(availableStockObj.values()));
            fuelAvailabilityDTO.setNextFuelAvailability(new ArrayList<>(nextStockObj.values()));
            fuelAvailabilityDTO.setDistributedVehicles(new ArrayList<>(distributedVehiclesObj.values()));
            fuelAvailabilityDTO.setFuelSupplyPerHour(new ArrayList<>(fuelSupplyObj.values()));

            ArrayList<FuelAvailabilityDTO.Vehicle> availableVehicleTypes = new ArrayList<>();
            for (Map.Entry<String, Map<String, FuelAvailabilityDTO.FuelStock>> stringMapEntry : vehicleCountAvgObj.entrySet()) {
                FuelAvailabilityDTO.Vehicle vehicle = new FuelAvailabilityDTO.Vehicle();
                vehicle.setVehicleTypeId(stringMapEntry.getKey());
                vehicle.setVehicleType(distributedVehiclesObj.get(stringMapEntry.getKey()).getVehicleType());
                vehicle.setVehicles(new ArrayList(stringMapEntry.getValue().values()));
                availableVehicleTypes.add(vehicle);
            }
            fuelAvailabilityDTO.setAvailableVehicles(availableVehicleTypes);

            Collections.sort(fuelAvailabilityDTO.getAvailableStock());
            Collections.sort(fuelAvailabilityDTO.getNextFuelAvailability());
            Collections.sort(fuelAvailabilityDTO.getDistributedVehicles());
            Collections.sort(fuelAvailabilityDTO.getFuelSupplyPerHour());
            Collections.sort(fuelAvailabilityDTO.getAvailableVehicles());
            fuelAvailabilityDTOs.add(fuelAvailabilityDTO);
        }
        return fuelAvailabilityDTOs;
    }

    @Override
    public FuelConsumption addFuelConsumption(FuelConsumption fuelConsumption) {
        LocalDate localDate = LocalDate.now();
        fuelConsumption.setId(fuelConsumption.getCustomer().getVehicle().getVehicleNumber() + localDate.format(DateTimeFormatter.ofPattern("yyyyMMdd")));
        fuelConsumption.setCheckedAt(localDate);
        return new FuelConsumption(fuelConsumptionRepository.save(fuelConsumption));
    }

    @Override
    public List<FuelConsumption> getFuelConsumptions(String id) {
        List<FuelConsumption> allByCustomerNic = fuelConsumptionRepository.getAllByCustomerNicOrderByCheckedAtDesc(id);
        List<FuelConsumption> fuelConsumptions = new ArrayList<>();
        for (FuelConsumption fuelConsumption : allByCustomerNic) {
            fuelConsumptions.add(new FuelConsumption(fuelConsumption));
        }
        return fuelConsumptions;
    }

    @Override
    public boolean deleteFuelConsumption(String id) {
        fuelConsumptionRepository.deleteById(id);
        return true;
    }

    @Override
    public List<FuelAvailabilityDTO> getFuelAvailabilityM(String place) {
        List<FuelAvailabilityDTO> fuelAvailabilityDTOS = new ArrayList<>();
        LocalDateTime localDateTimeCur = LocalDateTime.of(LocalDate.now(), LocalTime.parse("23:59"));
        Map<String, FuelAvailabilityDTO.FuelStock> availableStockObj;
        Map<Integer, Map<String, FuelAvailabilityDTO.FuelStock>> stockWeeksObj;
        Map<String, Map<Integer, Map<String, FuelAvailabilityDTO.FuelStock>>> fuelStations = new HashMap<>();
        List<FuelType> fuelTypes = fuelTypeRepository.getFuelTypes();

        for (int i = 0; i < 4; i++) {
            localDateTimeCur = localDateTimeCur.minusWeeks(i);
            LocalDateTime localDateTimePre = localDateTimeCur.minusWeeks(i + 1);
            List<FuelStock> fuelAvailabilityM = fuelStockRepository.getFuelAvailabilityM(place, localDateTimeCur, localDateTimePre);

            for (FuelStock fuelStock : fuelAvailabilityM) {
                Map<Integer, Map<String, FuelAvailabilityDTO.FuelStock>> fuelStationsMap = fuelStations.get(fuelStock.getFuelStation().getName());
                if (fuelStationsMap == null) {
                    stockWeeksObj = new HashMap<>();
                    for (int j = 0; j < 4; j++) {
                        availableStockObj = new HashMap<>();
                        for (FuelType fuelType : fuelTypes) {
                            FuelAvailabilityDTO.FuelStock fuelStockObj = new FuelAvailabilityDTO.FuelStock(fuelType.getId(), fuelType.getName());
                            fuelStockObj.setQuantity(100);
                            availableStockObj.put(fuelType.getId(), fuelStockObj);
                        }
                        stockWeeksObj.put(j, availableStockObj);
                    }
                    fuelStations.put(fuelStock.getFuelStation().getName(), stockWeeksObj);
                    fuelStationsMap = fuelStations.get(fuelStock.getFuelStation().getName());
                }
                Map<String, FuelAvailabilityDTO.FuelStock> fuelStockMap = fuelStationsMap.get(4 - (i + 1));
                FuelAvailabilityDTO.FuelStock fuelStockObj = fuelStockMap.get(fuelStock.getFuelType().getId());
                fuelStockObj.setQuantity(fuelStockObj.getQuantity() + fuelStock.getAmount() - 100);
            }
        }

        for (Map.Entry<String, Map<Integer, Map<String, FuelAvailabilityDTO.FuelStock>>> fuelStationsObj : fuelStations.entrySet()) {
            FuelAvailabilityDTO fuelAvailabilityDTO = new FuelAvailabilityDTO();
            fuelAvailabilityDTO.setFuelStationStr(fuelStationsObj.getKey());
            List<FuelAvailabilityDTO.FuelReport> fuelReports = new ArrayList<>();
            for (Map.Entry<Integer, Map<String, FuelAvailabilityDTO.FuelStock>> weeklyObj : fuelStationsObj.getValue().entrySet()) {
                FuelAvailabilityDTO.FuelReport fuelReport = new FuelAvailabilityDTO.FuelReport();
                fuelReport.setWeek(weeklyObj.getKey());
                List<FuelAvailabilityDTO.FuelStock> fuelStocks = new ArrayList<>();
                for (Map.Entry<String, FuelAvailabilityDTO.FuelStock> fuelStockObj : weeklyObj.getValue().entrySet()) {
                    fuelStocks.add(fuelStockObj.getValue());
                }
                fuelReport.setFuelStocks(fuelStocks);
                fuelReports.add(fuelReport);
            }
            fuelAvailabilityDTO.setFuelReports(fuelReports);
            fuelAvailabilityDTOS.add(fuelAvailabilityDTO);
        }

//        for (Map<Integer, Map<String, FuelAvailabilityDTO.FuelStock>> fuelStationsObj :) {
//            FuelAvailabilityDTO fuelAvailabilityDTO = new FuelAvailabilityDTO();
//            fuelAvailabilityDTO.setFuelStation(fuelStationsObj.);
//        }
        return fuelAvailabilityDTOS;
    }
}
