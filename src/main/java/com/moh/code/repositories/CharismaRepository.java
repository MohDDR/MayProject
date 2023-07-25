package com.moh.code.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.moh.code.models.Charisma;

@Repository
public interface CharismaRepository extends CrudRepository<Charisma, Long> {
    
    Optional<Charisma> findByUserId(Long userId);
}
