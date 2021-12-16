package com.shgxzybaba.musalasoft.domain;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.sql.Timestamp;



@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Drone {
    @Id
    private String serialNumber;
    @Enumerated(EnumType.STRING)
    private DroneModel model;
    private double weightLimit;
    private int batteryCapacity;
    private State state;

    @CreationTimestamp
    private Timestamp dateAdded;

}
