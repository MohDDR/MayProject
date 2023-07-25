package com.moh.code.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.moh.code.models.Tag;

@Repository
public interface TagRepository extends CrudRepository<Tag, Long> {

    List<Tag> findAll();
    
    Optional<Tag> findByName(String name);
    
}
