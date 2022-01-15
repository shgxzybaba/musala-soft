package com.shgxzybaba.musalasoft.controllers;

import com.shgxzybaba.musalasoft.domain.State;
import com.shgxzybaba.musalasoft.dtos.DroneApiModel;
import com.shgxzybaba.musalasoft.dtos.MedicationApiModel;
import com.shgxzybaba.musalasoft.services.interfaces.DroneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;


@RestController
@RequestMapping("/api/v1/drone-service")
public class DroneController {

    @Autowired
    private DroneService droneService;

    @PostMapping("/reqister")
    public void registerDrone(@RequestBody @Validated DroneApiModel model) {
        droneService.registerDrone(model);
    }

    @PostMapping("/add-medication")
    public void addMedication(@RequestBody DroneApiModel model) {
        droneService.addMedication(model);
    }

    @GetMapping("/drone/get-medications")
    @ResponseBody
    public List<MedicationApiModel> getLoadedMedications(@RequestParam(required = true) String serialNumber) {
        return droneService.getLoadedMedications(new DroneApiModel(serialNumber));
    }

    @GetMapping("/available-drones")
    @ResponseBody
    public List<DroneApiModel> getAvailableDrones(@RequestParam(required = false) State state) {
        if (null != state) {
            return droneService.getAvailableDrones(Collections.singletonList(state));
        } else
            return droneService.getAvailableDrones(Collections.emptyList());
    }

    @GetMapping("/drone/battery-level")
    @ResponseBody
    public DroneApiModel getBatteryLevel(@RequestParam String serialNumber) {
        return droneService.getBatteryLevel(new DroneApiModel(serialNumber));
    }

}
