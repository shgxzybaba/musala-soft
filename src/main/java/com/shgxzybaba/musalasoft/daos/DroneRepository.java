package com.shgxzybaba.musalasoft.daos;

import com.shgxzybaba.musalasoft.domain.Drone;
import com.shgxzybaba.musalasoft.domain.State;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface DroneRepository extends CrudRepository<Drone, String> {
    List<Drone> findByStateIn(Collection<State> states);

}
