package com.moh.code.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.moh.code.models.Skill;
import com.moh.code.models.User;

@Repository
public interface SkillRepository extends CrudRepository<Skill, Long> {

    List<Skill> findAll();
    
    Optional<Skill> findByName(String name);
    
    List<Skill> findByskillUser(User user);
    
}
