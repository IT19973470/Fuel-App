package lk.fuel_app.util;

import lk.fuel_app.entity.FuelStationPlace;
import lk.fuel_app.entity.FuelType;
import lk.fuel_app.repository.FuelStationPlaceRepository;
import lk.fuel_app.repository.FuelTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class InitDB {

    @Autowired
    private FuelTypeRepository fuelTypeRepository;
    @Autowired
    private FuelStationPlaceRepository fuelStationPlaceRepository;

    @EventListener
    public void appReady(ApplicationReadyEvent event) {
        if (fuelTypeRepository.findAll().size() == 0) {
            fuelTypeRepository.save(new FuelType("1_Petrol_92", "Petrol 92", 1));
            fuelTypeRepository.save(new FuelType("2_Petrol_95", "Petrol 95", 2));
            fuelTypeRepository.save(new FuelType("3_Diesel", "Diesel", 3));
            fuelTypeRepository.save(new FuelType("4_Super_Diesel", "Super Diesel", 4));
        }

        if (fuelStationPlaceRepository.findAll().size() == 0) {
            fuelStationPlaceRepository.save(new FuelStationPlace("Galle1", "Galle", "Wakwella"));
            fuelStationPlaceRepository.save(new FuelStationPlace("Galle2", "Galle", "Beligaha"));
            fuelStationPlaceRepository.save(new FuelStationPlace("Galle3", "Galle", "Karapitiya"));
            fuelStationPlaceRepository.save(new FuelStationPlace("Galle4", "Galle", "Galle"));
        }

//
//        if (subjectRepository.findAll().size() == 0) {
//            subjectRepository.save(new Subject("S1","Physics","Physics A/L"));
//            subjectRepository.save(new Subject("S2","Applied Maths","Applied Maths A/L"));
//            subjectRepository.save(new Subject("S3","Chemistry","Chemistry A/L"));
//            subjectRepository.save(new Subject("S4","ICT","ICT A/L"));
//            subjectRepository.save(new Subject("S5","Maths","Maths O/L"));
//            subjectRepository.save(new Subject("S6","Sinhala","Sinhala O/L"));
//            subjectRepository.save(new Subject("S7","Science","Science O/L"));
//            subjectRepository.save(new Subject("S8","English","English O/L"));
//        }
//        System.out.println(123);
//        repo.save(new Entity(...));
    }

}
