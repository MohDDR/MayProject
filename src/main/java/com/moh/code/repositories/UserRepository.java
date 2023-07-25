package com.moh.code.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.moh.code.models.Action;
import com.moh.code.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    
    Optional<User> findByEmail(String email);
    
    Long countAllByCompletedActs(Action action);
    
}