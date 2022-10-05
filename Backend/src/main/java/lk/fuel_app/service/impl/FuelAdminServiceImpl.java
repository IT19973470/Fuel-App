package lk.fuel_app.service.impl;

import lk.fuel_app.entity.*;
import lk.fuel_app.repository.FuelAdminRepository;
import lk.fuel_app.repository.FuelAdminStockInRepository;
import lk.fuel_app.repository.FuelAdminStockOutRepository;
import lk.fuel_app.service.FuelAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class FuelAdminServiceImpl implements FuelAdminService {

    @Autowired
    private FuelAdminRepository fuelAdminRepository;
    @Autowired
    private FuelAdminStockInRepository fuelAdminStockInRepository;
    @Autowired
    private FuelAdminStockOutRepository fuelAdminStockOutRepository;

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
        return fuelAdminStockInRepository.findAll();
    }

    @Override
    public List<FuelAdminStockOut> viewStockOut() {
        return fuelAdminStockOutRepository.findAll();    }


}
