package com.kek.finalSpring.repository;

import com.kek.finalSpring.entity.Talk;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TalkRepo extends CrudRepository<Talk, Long> {
}
