package com.kek.finalSpring.repository;

import com.kek.finalSpring.entity.Talk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TalkRepo extends JpaRepository<Talk, Long> {
}
