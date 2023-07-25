package com.moh.code.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.moh.code.models.Constitution;

@Repository
public interface ConstitutionRepository extends CrudRepository<Constitution, Long> {
    
    Optional<Constitution> findByUserId(Long userId);
    
}
