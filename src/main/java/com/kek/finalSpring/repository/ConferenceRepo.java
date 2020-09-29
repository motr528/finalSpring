package com.kek.finalSpring.repository;

import com.kek.finalSpring.entity.Conference;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ConferenceRepo extends CrudRepository<Conference, Long> {

    List<Conference> findByLocation(String location);

}
