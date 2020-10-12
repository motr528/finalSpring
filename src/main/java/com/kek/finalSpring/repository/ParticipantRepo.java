package com.kek.finalSpring.repository;

import com.kek.finalSpring.entity.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ParticipantRepo extends JpaRepository<Participant, Long> {
    Participant findByEmail(String email);

    @Transactional
    @Modifying
    @Query(
            value =
                    "insert into participant_conferences (participant_id, conference_id) values (:p_id,:c_id)",
            nativeQuery = true)
    void insertUniqueConf(@Param("p_id") Long participantId, @Param("c_id") Long conferenceId);
}
