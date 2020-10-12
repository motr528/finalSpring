package com.kek.finalSpring.repository;

import com.kek.finalSpring.entity.Conference;
import com.kek.finalSpring.entity.Participant;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface ConferenceRepo extends CrudRepository<Conference, Long> {

    List<Conference> findByLocation(String location);
    List<Conference> findByDateBetween(Date dateFrom, Date dateTo);
    List<Conference> findByDateAfter(Date dateFrom);

    @Query(value = "select pc.conference_id from participant_conferences pc where pc.participant_id = :id", nativeQuery = true)
    List<Long> findByParticipantId(@Param("id") Long participant_id);


}
