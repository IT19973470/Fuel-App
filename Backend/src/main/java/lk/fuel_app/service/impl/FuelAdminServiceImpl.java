package lk.fuel_app.service.impl;

import lk.fuel_app.dto.FuelStationDTO;
import lk.fuel_app.entity.*;
import lk.fuel_app.repository.*;
import lk.fuel_app.service.FuelAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FuelAdminServiceImpl implements FuelAdminService {

    @Autowired
    private FuelAdminRepository fuelAdminRepository;
    @Autowired
    private FuelAdminStockInRepository fuelAdminStockInRepository;
    @Autowired
    private FuelAdminStockOutRepository fuelAdminStockOutRepository;
    @Autowired
    private FuelStationRepository fuelStationRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public FuelAdmin addFuelAdmin(FuelAdmin fuelAdmin) {
        return fuelAdminRepository.save(fuelAdmin);
    }

    @Override
    public FuelAdminStockIn addStockIn(FuelAdminStockIn fuelAdminStockIn) {
        fuelAdminStockIn.setId(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddhhmmss")));
        return fuelAdminStockInRepository.save(fuelAdminStockIn);
    }
    @Override
    public FuelAdminStockOut addStockOut(FuelAdminStockOut fuelAdminStockout) {
        fuelAdminStockout.setId(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddhhmmss")));
        return fuelAdminStockOutRepository.save(fuelAdminStockout);
    }
    @Override
    public List<FuelAdminStockIn> viewStockIn() {
        List<FuelAdminStockIn> fuelAdminStockIns = fuelAdminStockInRepository.findAll();
        List<FuelAdminStockIn> adminStockIns = new ArrayList<>();
        for(FuelAdminStockIn fuelAdminStockIn: adminStockIns){
            fuelAdminStockIns.add(new FuelAdminStockIn(fuelAdminStockIn));
        }
        return fuelAdminStockIns;
    }

    @Override
    public List<FuelAdminStockOut> viewStockOut() {
        List<FuelAdminStockOut>  fuelAdminStockOuts = fuelAdminStockOutRepository.findAll();
        List<FuelAdminStockOut> adminStockOuts = new ArrayList<>();
        for(FuelAdminStockOut fuelAdminStockOut: adminStockOuts){
            fuelAdminStockOuts.add(new FuelAdminStockOut(fuelAdminStockOut));
        }
        return fuelAdminStockOuts;    }


    @Override
    public List<OrderData> viewFuelOrders() {
        List<OrderData>  orderDatas = orderRepository.findAll();
        List<OrderData> orderData = new ArrayList<>();
        for(OrderData orderData1: orderData){
            orderDatas.add(new OrderData(orderData1));
        }
        return orderDatas;
    }

    @Override
    public List<FuelStationDTO> viewFuelStation() {
        
        List<FuelStationDTO> fuelStationDTOS = new ArrayList<>();
        List<FuelStation> fuelStations= fuelStationRepository.findAll();
        for (FuelStation fuelStation : fuelStations) {
            FuelStationDTO fuelStationDTO = new FuelStationDTO();
            fuelStationDTO.setFuelStation(new FuelStation(fuelStation));
            fuelStationDTOS.add(fuelStationDTO);
        }
        return fuelStationDTOS;
        
    }

    @Override
    public List<OrderData> getOrder() {
        return orderRepository.findAll();
    }

    @Override
    public Object updateStockIn(String id, FuelAdminStockIn fuelAdminStockIn) {
        Optional<FuelAdminStockIn> optionalFuelAdminStockIn = fuelAdminStockInRepository.findById(id);
        if (optionalFuelAdminStockIn.isPresent()){
            FuelAdminStockIn fuelAdminStockIn1 = optionalFuelAdminStockIn.get();
            fuelAdminStockIn1.setAmount(fuelAdminStockIn.getAmount());
            fuelAdminStockIn1.setStockFrom(fuelAdminStockIn.getStockFrom());
            fuelAdminStockIn1.setTime(fuelAdminStockIn.getTime());

            return new FuelAdminStockIn(fuelAdminStockInRepository.save(fuelAdminStockIn1));
        }
        return null;
    }

    @Override
    public boolean deleteStockIn(String id) {
        fuelAdminStockInRepository.deleteById(id);
        return true;
    }

    @Override
    public FuelAdminStockIn getStockInBystockFrom(String stockFrom) {
        Optional<FuelAdminStockIn> fuelAdminStockInOptional = fuelAdminStockInRepository.findById(stockFrom);
        if(fuelAdminStockInOptional.isPresent()){
            FuelAdminStockIn fuelAdminStockIn = fuelAdminStockInOptional.get();
            return  new FuelAdminStockIn((fuelAdminStockIn));
        }
        return null;
    }

    @Override
    public FuelAdminStockIn getStockInByType(String type) {
        Optional<FuelAdminStockIn> fuelAdminStockInOptional = fuelAdminStockInRepository.findById(type);
        if(fuelAdminStockInOptional.isPresent()){
            FuelAdminStockIn fuelAdminStockIn = fuelAdminStockInOptional.get();
            return  new FuelAdminStockIn((fuelAdminStockIn));
        }
        return null;
    }

    @Override
    public Object updateStockOut(String id, FuelAdminStockOut fuelAdminStockOut) {
        Optional<FuelAdminStockOut> optionalFuelAdminStockOut = fuelAdminStockOutRepository.findById(id);
        if (optionalFuelAdminStockOut.isPresent()){
            FuelAdminStockOut fuelAdminStockOut1 = optionalFuelAdminStockOut.get();
            fuelAdminStockOut1.setAmount(fuelAdminStockOut.getAmount());
            fuelAdminStockOut1.setDate(fuelAdminStockOut.getDate());
            fuelAdminStockOut1.setDriverName(fuelAdminStockOut.getDriverName());
            fuelAdminStockOut1.setFuelType(fuelAdminStockOut.getFuelType());
            fuelAdminStockOut1.setNumber(fuelAdminStockOut.getNumber());
            fuelAdminStockOut1.setTime(fuelAdminStockOut.getTime());
            fuelAdminStockOut1.setFuelStation(fuelAdminStockOut.getFuelStation());
            return new FuelAdminStockOut(fuelAdminStockOutRepository.save(fuelAdminStockOut1));
        }
        return null;
    }

    @Override
    public boolean deleteStockOut(String id) {
        fuelAdminStockOutRepository.deleteById(id);
        return true;
    }

    @Override
    public FuelAdminStockOut getStockOutByStation(String fuel_station_id) {
        Optional<FuelAdminStockOut> fuelAdminStockoutOptional = fuelAdminStockOutRepository.findById(fuel_station_id);
        if(fuelAdminStockoutOptional.isPresent()){
            FuelAdminStockOut fuelAdminStockOut = fuelAdminStockoutOptional.get();
            return  new FuelAdminStockOut((fuelAdminStockOut));
        }
        return null;
    }

    @Override
    public FuelAdminStockOut getStockOutByType(String type) {
        Optional<FuelAdminStockOut> fuelAdminStockoutOptional = fuelAdminStockOutRepository.findById(type);
        if(fuelAdminStockoutOptional.isPresent()){
            FuelAdminStockOut fuelAdminStockOut = fuelAdminStockoutOptional.get();
            return  new FuelAdminStockOut((fuelAdminStockOut));
        }
        return null;
    }


}
