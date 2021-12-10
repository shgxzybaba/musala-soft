package com.shgxzybaba.musalasoft.services.interfaces;

import com.shgxzybaba.musalasoft.daos.DroneRepository;
import com.shgxzybaba.musalasoft.domain.State;
import com.shgxzybaba.musalasoft.dtos.DroneApiModel;
import com.shgxzybaba.musalasoft.dtos.MedicationApiModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultDroneService implements DroneService{

    DroneRepository droneRepository;

    @Autowired
    public DefaultDroneService(DroneRepository droneRepository) {
        this.droneRepository = droneRepository;
    }

    @Override
    public DroneApiModel registerDrone(DroneApiModel droneApiModel) {
        return null;
    }

    @Override
    public void addMedication(DroneApiModel droneApiModel) {

    }

    @Override
    public List<MedicationApiModel> getLoadedMedications(DroneApiModel apiModel) {
        return null;
    }

    @Override
    public List<DroneApiModel> getAvailableDrones(List<State> states) {
        return null;
    }

    @Override
    public DroneApiModel getBatteryLevel(DroneApiModel droneApiModel) {
        return null;
    }
}
