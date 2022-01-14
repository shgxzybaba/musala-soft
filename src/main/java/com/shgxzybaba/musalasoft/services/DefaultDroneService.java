package com.shgxzybaba.musalasoft.services;

import com.shgxzybaba.musalasoft.daos.DroneRepository;
import com.shgxzybaba.musalasoft.daos.MedicationRepository;
import com.shgxzybaba.musalasoft.domain.Drone;
import com.shgxzybaba.musalasoft.domain.Medication;
import com.shgxzybaba.musalasoft.domain.State;
import com.shgxzybaba.musalasoft.dtos.DroneApiModel;
import com.shgxzybaba.musalasoft.dtos.MedicationApiModel;
import com.shgxzybaba.musalasoft.services.interfaces.DroneService;
import com.shgxzybaba.musalasoft.services.interfaces.ImageHandlingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DefaultDroneService implements DroneService {

    DroneRepository droneRepository;
    ImageHandlingService imageHandlingService;
    MedicationRepository medicationRepository;
    Integer minimumBatteryCapacity;

    @Autowired
    public DefaultDroneService(DroneRepository droneRepository, ImageHandlingService imageHandlingService,
                               MedicationRepository medicationRepository,
                               @Value("${minimum.load.battery:25}")Integer minimumBattery) {
        this.droneRepository = droneRepository;
        this.imageHandlingService = imageHandlingService;
        this.medicationRepository = medicationRepository;
        this.minimumBatteryCapacity = minimumBattery;
    }

    @Override
    public void registerDrone(DroneApiModel droneApiModel) {
        Drone drone = new Drone();
        drone.setModel(droneApiModel.getModel());
        drone.setState(State.IDLE);
        drone.setWeightLimit(droneApiModel.getWeightLimit());
        drone.setSerialNumber(droneApiModel.getSerialNumber());
        drone.setBatteryCapacity(droneApiModel.getBatteryCapacity());

        droneRepository.save(drone);
    }

    @Override
    public void addMedication(DroneApiModel droneApiModel) {
        Optional<Drone> drone = droneRepository.findById(droneApiModel.getSerialNumber());
        if (drone.isEmpty()) {
            throw  new NoSuchElementException("Drone with the given serial number not found!");
        }

        if (drone.get().getBatteryCapacity() < minimumBatteryCapacity) {
            throw new UnsupportedOperationException("Cannot load a drone when the battery capacity is lower than " + minimumBatteryCapacity);
        }
        State previousState = drone.get().getState();
        if (Arrays.asList(State.LOADING, State.DELIVERING, State.RETURNING).contains(previousState)) {
            throw new UnsupportedOperationException("Cannot load a drone when it is  " + previousState.name());
        }

        drone.get().setState(State.LOADING);
        droneRepository.save(drone.get());

        try {

            List<Medication> medicationsToSave = new ArrayList<>();

            double totalWeight = 0D;
            for (MedicationApiModel i : droneApiModel.getMedications()) {
                validateMedication(i);
                Medication m = i.toMedication();
                String imageString = imageHandlingService.processImage(i.getImage());
                m.setImage(imageString);
                m.setDrone(drone.get());
                medicationsToSave.add(m);
                totalWeight = totalWeight + i.getWeight();
            }

            if (State.LOADED.equals(previousState)) {
                double existingWeight = medicationRepository.findAllByDrone_SerialNumber(drone.get().getSerialNumber()).stream().mapToDouble(Medication::getWeight).sum();
                totalWeight = totalWeight + existingWeight;
            } //assuming a drone can be loaded at multiple different locations before unloading

            if (totalWeight > drone.get().getWeightLimit()) {
                throw new UnsupportedOperationException("Cannot add medication(s) above " + drone.get().getWeightLimit());
            }

            medicationRepository.saveAll(medicationsToSave);
        } finally {
            drone.get().setState(previousState);
            droneRepository.save(drone.get());
        }
    }

    private void validateMedication(MedicationApiModel i) {
        if (i.getName().matches("[^-a-zA-Z[0-9[\"_\"]]]")) {
            throw new IllegalArgumentException("Medication name can only contain letters, numbers, ‘-‘, ‘_’");
        }

        if (i.getCode().matches("[^_A-Z0-9]")){
            throw new IllegalArgumentException("Medication code can only contain upper case letters, numbers, and ‘_’");
        }
    }

    @Override
    public List<MedicationApiModel> getLoadedMedications(@NotNull DroneApiModel apiModel) {
        if (apiModel.getSerialNumber() == null || apiModel.getSerialNumber().isBlank()) {
            throw new IllegalArgumentException("Drone serial number cannot be blank!");
        }
        List<Medication> medications = medicationRepository.findAllByDrone_SerialNumber(apiModel.getSerialNumber());

        return medications.stream()
                .map(i -> new MedicationApiModel().fromMedication(i)).collect(Collectors.toList());
    }

    @Override
    public List<DroneApiModel> getAvailableDrones(List<State> states) {
        if (states.isEmpty()) {
            return getIdleDrones();
        }

        List<Drone> drones = droneRepository.findByStateIn(states);
        return drones.stream().map(DroneApiModel::new).collect(Collectors.toList());
    }

    public List<DroneApiModel> getIdleDrones() {
        List<Drone> drones = droneRepository.findByStateIn(List.of(State.IDLE));
        return drones.stream().map(DroneApiModel::new).collect(Collectors.toList());
    }


    @Override
    public DroneApiModel getBatteryLevel(@NotNull DroneApiModel droneApiModel) {
        if (droneApiModel.getSerialNumber() == null || droneApiModel.getSerialNumber().isBlank()) {
            throw new IllegalArgumentException("Drone serial number cannot be blank!");
        }

        Optional<Drone> drone = droneRepository.findById(droneApiModel.getSerialNumber());
        if (drone.isEmpty()) {
            throw  new NoSuchElementException("Drone with the given serial number not found!");
        }

        return new DroneApiModel(drone.get().getSerialNumber(), drone.get().getBatteryCapacity());
    }
}
