package com.shgxzybaba.musalasoft.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Drone {
    @Id
    private String serialNumber;
    @Enumerated(EnumType.STRING)
    private DroneModel model;
    private double weightLimit;
    private double batteryCapacity;
    private State state;
    @OneToMany(targetEntity = Medication.class)
    private List<Medication> medications = new ArrayList<>();
}
