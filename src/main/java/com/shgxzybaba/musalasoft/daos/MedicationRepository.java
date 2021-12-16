package com.shgxzybaba.musalasoft.daos;

import com.shgxzybaba.musalasoft.domain.Medication;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicationRepository extends CrudRepository<Medication, String> {
    List<Medication> findAllByDrone_SerialNumber(String serialNumber);
}
