package com.shgxzybaba.musalasoft.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class MedicationApiModel {
    @NotBlank(message = "Medication name is required")
    private String name;
    private double weight;
    @NotBlank(message = "Medication code cannot be empty")
    private String code;
    private MultipartFile image;
}
