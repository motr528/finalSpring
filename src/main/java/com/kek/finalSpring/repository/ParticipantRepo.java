package com.kek.finalSpring.repository;

import com.kek.finalSpring.entity.Participant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipantRepo extends JpaRepository<Participant, Long> {
    Participant findByEmail(String email);
}
