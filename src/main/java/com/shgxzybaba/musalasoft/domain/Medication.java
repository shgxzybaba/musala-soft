package com.shgxzybaba.musalasoft.domain;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
    @ManyToOne
    @JoinColumn(name = "drone_serial_number")
    private Drone drone;

}
