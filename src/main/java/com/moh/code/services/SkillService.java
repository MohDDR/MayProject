package com.moh.code.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moh.code.models.Skill;
import com.moh.code.models.User;
import com.moh.code.repositories.SkillRepository;

@Service
public class SkillService {

	@Autowired
	SkillRepository skillRepo;
	
	public List<Skill> allSkills(){
		
		return skillRepo.findAll();
	}
	
	public List<Skill> findUserSkills(User user){
		
		return skillRepo.findByskillUser(user);
	}
	
	public Skill createSkill(Skill skill) {
		
		skillRepo.save(skill);
		
		return skill;
	}
	
	public Skill findSkill(Long id) {
		
        Optional<Skill> optSkill = skillRepo.findById(id);
        
        if(optSkill.isPresent()) {
        	
            return optSkill.get();
            
        } else {
        	
            return null;
            
        }
    }
	
	public Skill updateSkill(Skill skill) {
		
		return skillRepo.save(skill);
	}
	
	public void deleteSkill(Long id) {
		
		Optional<Skill> optSkill = skillRepo.findById(id);
		
		if(optSkill.isPresent()) {
			
			skillRepo.deleteById(id);
		}
		
	}
}
