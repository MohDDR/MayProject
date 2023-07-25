package com.moh.code.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import com.moh.code.models.Action;
import com.moh.code.models.User;

@Repository
public interface ActionRepository extends CrudRepository<Action, Long> {

    List<Action> findAll();
    
    Optional<Action> findByName(String name);

	List<Action> findAllByUsers(User user);

}
