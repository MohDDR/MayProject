package com.moh.code.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.moh.code.models.Template;

@Repository
public interface TemplateRepository extends CrudRepository<Template, Long> {

    List<Template> findAll();
    
    Optional<Template> findByName(String name);
    
}
