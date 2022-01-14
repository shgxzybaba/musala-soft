package com.shgxzybaba.musalasoft.domain;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.Max;
import java.sql.Timestamp;



@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Drone {
    @Id
    @Column(nullable = false, length = 100)
    private String serialNumber;
    @Enumerated(EnumType.STRING)
    private DroneModel model;
    @Column(nullable = false)
    @Max(value = 500, message = "weight cannot be above 500")
    private double weightLimit;
    @Max(value = 100)
    private int batteryCapacity;
    private State state;

    @CreationTimestamp
    private Timestamp dateAdded;

}
