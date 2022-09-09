package lk.fuel_app.service.impl;

import lk.fuel_app.entity.CustomerFuelStation;
import lk.fuel_app.entity.FuelPumper;
import lk.fuel_app.entity.FuelStation;
import lk.fuel_app.entity.FuelStock;
import lk.fuel_app.repository.CustomerFuelStationRepository;
import lk.fuel_app.repository.FuelPumperRepository;
import lk.fuel_app.repository.FuelStationRepository;
import lk.fuel_app.repository.FuelStockRepository;
import lk.fuel_app.service.FuelStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class FuelStationServiceImpl implements FuelStationService {

    @Autowired
    private FuelStationRepository fuelStationRepository;
    @Autowired
    private FuelStockRepository fuelStockRepository;

    @Override
    public FuelStation addFuelStation(FuelStation fuelStation) {
        return fuelStationRepository.save(fuelStation);
    }

    @Override
    public FuelStock addFuelStock(FuelStock fuelStock) {
        fuelStock.setId(fuelStock.getFuelStation().getId() + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddhhmmss")));
        return fuelStockRepository.save(fuelStock);
    }


}
