package com.moh.code.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.moh.code.models.Willpower;

@Repository
public interface WillpowerRepository extends CrudRepository<Willpower, Long> {
	
    Optional<Willpower> findByUserId(Long userId);
    
}
