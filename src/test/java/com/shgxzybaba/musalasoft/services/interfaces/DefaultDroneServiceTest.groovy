package com.shgxzybaba.musalasoft.services.interfaces

import com.shgxzybaba.musalasoft.domain.DroneModel
import com.shgxzybaba.musalasoft.dtos.DroneApiModel
import com.shgxzybaba.musalasoft.dtos.MedicationApiModel
import org.junit.jupiter.api.extension.ExtendWith

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.junit.jupiter.SpringExtension
import spock.lang.Ignore
import spock.lang.IgnoreRest
import spock.lang.Specification

@ExtendWith(SpringExtension)

class DefaultDroneServiceTest extends Specification {


    DefaultDroneService defaultDroneService

    DroneApiModel droneApiModel;

    void setup() {
        droneApiModel = new DroneApiModel(
                "1234",
                DroneModel.Heavy_weight,
                200,
                100,
                new ArrayList<>()
        )

        defaultDroneService = new DefaultDroneService()
    }

    @IgnoreRest
    def "Register Drone"() {

        when:
        DroneApiModel response = defaultDroneService.registerDrone(new DroneApiModel())

        then:
        response != null
    }


    def "Add Medication"() {

    }


    def "Get Loaded Medications"() {

    }


    def "Get Available Drones"() {

    }

    def "Get Battery Level"() {

    }
}
