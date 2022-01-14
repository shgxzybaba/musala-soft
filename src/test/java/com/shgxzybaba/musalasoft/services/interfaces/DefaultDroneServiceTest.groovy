package com.shgxzybaba.musalasoft.services.interfaces

import com.shgxzybaba.musalasoft.daos.DroneRepository
import com.shgxzybaba.musalasoft.daos.MedicationRepository
import com.shgxzybaba.musalasoft.domain.Drone
import com.shgxzybaba.musalasoft.domain.DroneModel
import com.shgxzybaba.musalasoft.dtos.DroneApiModel
import com.shgxzybaba.musalasoft.services.DefaultDroneService
import org.junit.jupiter.api.extension.ExtendWith
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.junit.jupiter.SpringExtension
import spock.lang.Specification

@ExtendWith(SpringExtension)
class DefaultDroneServiceTest extends Specification {


    @SpringBean
    DroneRepository droneRepository = Mock(){
        save(_ as Drone) >> new Drone()
        findById("1234") >> Optional.of(new Drone())

    }

    @SpringBean
    MedicationRepository medicationRepository = Mock()

    @SpringBean
    ImageHandlingService imageHandlingService = Mock()

    @Autowired
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

        defaultDroneService = new DefaultDroneService(droneRepository, imageHandlingService, medicationRepository)

    }


    def "Register Drone: verify that the a drone object is persisted"() {

        given:
        droneRepository.save(_ as Drone) >> new Drone()

        when:
        defaultDroneService.registerDrone(new DroneApiModel())

        then:
        1 * droneRepository.save(_ as Drone)
    }



}
