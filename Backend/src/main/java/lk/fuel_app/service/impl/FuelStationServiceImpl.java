package lk.fuel_app.service.impl;

import lk.fuel_app.dto.FuelStockDTO;
import lk.fuel_app.entity.*;
import lk.fuel_app.repository.*;
import lk.fuel_app.service.FuelStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FuelStationServiceImpl implements FuelStationService {

    @Autowired
    private FuelStationRepository fuelStationRepository;
    @Autowired
    private FuelStockRepository fuelStockRepository;
    @Autowired
    private FuelTypeRepository fuelTypeRepository;
    @Autowired
    private CustomerFuelStationRepository customerFuelStationRepository;

    @Override
    public FuelStation addFuelStation(FuelStation fuelStation) {
        return fuelStationRepository.save(fuelStation);
    }

    @Override
    public FuelStock addFuelStock(FuelStock fuelStock) {
        fuelStock.setId(fuelStock.getFuelStation().getId() + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddhhmmss")));
        return fuelStockRepository.save(fuelStock);
    }

    @Override
    public List<FuelStockDTO> getFuelStock(String id) {
        List<FuelStock> actualArrivalDesc = fuelStockRepository.getLastFuelPump(id);
        FuelStock fuelStockObj = actualArrivalDesc.get(0);
//        Integer fuelPumpedVehicleCount = customerFuelStationRepository.getFuelPumpedVehicleCount(id, fuelStockObj.getActualArrival().toLocalDate());
        List<FuelType> fuelTypes = fuelTypeRepository.findAll();
        List<FuelStockDTO> fuelStockDTOS = new ArrayList<>();
        for (FuelType fuelType : fuelTypes) {
            FuelStockDTO fuelStockDTO = new FuelStockDTO();
            fuelStockDTO.setFuelType(fuelType.getName());
            Integer fuelStockAmount = fuelStockRepository.getFuelStocksAmount(id, fuelType.getName());
//            for (FuelStock fuelStock : fuelStocks) {
            if (fuelStockAmount != null) {
                fuelStockDTO.setAvailableStock(fuelStockAmount);
            }
//            }
            fuelStockDTOS.add(fuelStockDTO);
        }
        return fuelStockDTOS;
    }


}
