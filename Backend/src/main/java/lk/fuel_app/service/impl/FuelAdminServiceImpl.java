package lk.fuel_app.service.impl;

import lk.fuel_app.entity.FuelAdmin;
import lk.fuel_app.entity.FuelStation;
import lk.fuel_app.entity.FuelStock;
import lk.fuel_app.repository.FuelAdminRepository;
import lk.fuel_app.repository.FuelStationRepository;
import lk.fuel_app.service.FuelAdminService;
import lk.fuel_app.service.FuelStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FuelAdminServiceImpl implements FuelAdminService {

    @Autowired
    private FuelAdminRepository fuelAdminRepository;

    @Override
    public FuelAdmin addFuelAdmin(FuelAdmin fuelAdmin) {
        return fuelAdminRepository.save(fuelAdmin);
    }


}
