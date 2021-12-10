package com.shgxzybaba.musalasoft.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
@Data
public class Medication {

    @Id
    @NotBlank
    private String code;
    @NotBlank
    private String name;
    private double weight;

    private String image;

}
