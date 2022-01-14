package com.shgxzybaba.musalasoft.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.shgxzybaba.musalasoft.domain.Drone;
import com.shgxzybaba.musalasoft.domain.DroneModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DroneApiModel {
    @NotBlank
    private String serialNumber;
    private DroneModel model;
    @Max(value = 500, message = "weight limit cannot be above 500g")
    private double weightLimit;
    private int batteryCapacity;
    @Valid
    private List<MedicationApiModel> medications = new ArrayList<>();

    public DroneApiModel(String  serialNumber) {
        this.serialNumber = serialNumber;
    }

    public DroneApiModel(String  serialNumber, int batteryCapacity) {
        this.serialNumber = serialNumber;
        this.batteryCapacity = batteryCapacity;
    }

    public DroneApiModel(Drone i) {
        serialNumber = i.getSerialNumber();
        model = i.getModel();
        weightLimit = i.getWeightLimit();
        batteryCapacity = i.getBatteryCapacity();
    }
}
