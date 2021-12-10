package com.shgxzybaba.musalasoft.services.interfaces;

import com.shgxzybaba.musalasoft.domain.State;
import com.shgxzybaba.musalasoft.dtos.DroneApiModel;
import com.shgxzybaba.musalasoft.dtos.MedicationApiModel;

import java.util.List;

public interface DroneService {
    DroneApiModel registerDrone(DroneApiModel droneApiModel);
    void addMedication(DroneApiModel droneApiModel);
    List<MedicationApiModel> getLoadedMedications(DroneApiModel apiModel);
    List<DroneApiModel> getAvailableDrones(List<State> states);
    DroneApiModel getBatteryLevel(DroneApiModel droneApiModel);
}
