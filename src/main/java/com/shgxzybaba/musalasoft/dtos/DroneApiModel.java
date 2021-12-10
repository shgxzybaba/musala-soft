package com.shgxzybaba.musalasoft.dtos;

import com.shgxzybaba.musalasoft.domain.DroneModel;
import com.shgxzybaba.musalasoft.domain.Medication;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DroneApiModel {
    @NotBlank
    private String serialNumber;
    private DroneModel model;
    @Max(value = 500, message = "weight limit cannot be above 500g")
    private double weightLimit;
    private int batteryCapacity;
    private List<MedicationApiModel> medications;
}
