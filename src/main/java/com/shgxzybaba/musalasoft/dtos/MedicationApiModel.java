package com.shgxzybaba.musalasoft.dtos;

import com.shgxzybaba.musalasoft.domain.Medication;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicationApiModel {
    @NotBlank(message = "Medication name is required")
    private String name;
    private double weight;
    @NotBlank(message = "Medication code cannot be empty")
    private String code;
    private MultipartFile image;

    public Medication toMedication() {
        Medication medication = new Medication();
        medication.setName(name);
        medication.setCode(code);
        medication.setWeight(weight);

        return medication;
    }

    public MedicationApiModel fromMedication(Medication medication) {
        MedicationApiModel model = new MedicationApiModel();
        model.setName(medication.getName());
        model.setCode(medication.getCode());
        model.setWeight(medication.getWeight());

        return model;
    }
}
