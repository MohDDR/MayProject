package com.moh.code.repositories;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.moh.code.models.Title;
import com.moh.code.models.User;

@Repository
public interface TitleRepository extends CrudRepository<Title, Long> {

    List<Title> findAll();
    
    List<Title> findByName(String name);
    
    List<Title> findByUsersContaining(User user);
    
}
