package com.moh.code.repositories;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.moh.code.models.Action;
import com.moh.code.models.Skill;
import com.moh.code.models.Template;
import com.moh.code.models.Title;
import com.moh.code.models.StatIncrement;

@Repository
public interface StatIncrementRepository extends CrudRepository<StatIncrement, Long> {
    
    List<StatIncrement> findByAction(Action action);
    
    List<StatIncrement> findByTemplate(Template template);
    
    List<StatIncrement> findBySkill(Skill skill);
    
    List<StatIncrement> findByTitle(Title title);
	
}
