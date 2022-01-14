package com.shgxzybaba.musalasoft.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.shgxzybaba.musalasoft.domain.Drone;
import com.shgxzybaba.musalasoft.domain.DroneModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DroneApiModel {
    @NotBlank(message = "Serial number cannot be null or empty!")
    @Size(max = 100, message = "Serial number must contain 100 characters or less!")
    private String serialNumber;
    private DroneModel model;
    @Max(value = 500, message = "weight limit cannot be above 500g")
    @Min(value = 0, message = "weight limit cannot be below 0g")

    private double weightLimit;
    @Max(value = 100, message = "battery capacity cannot be greater than 100")
    @Min(value = 0, message = "battery capacity cannot be less than zero")
    private int batteryCapacity;

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
