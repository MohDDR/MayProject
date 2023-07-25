package com.moh.code.services;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moh.code.models.Action;
import com.moh.code.models.Skill;
import com.moh.code.models.StatIncrement;
import com.moh.code.models.Template;
import com.moh.code.models.Title;
import com.moh.code.models.User;
import com.moh.code.repositories.StatIncrementRepository;

@Service
public class StatIncrementService {
	
	@Autowired
	StatIncrementRepository statIncRepo;
	
	@Autowired
	SkillService skillServ;
	
	public StatIncrement createStatInc(StatIncrement statInc) {
		
		statIncRepo.save(statInc);
		
		return statInc;
	}
	
	public List<StatIncrement> findStatIncsForAction(Action action) {
		
		return statIncRepo.findByAction(action);
	}
	
	public List<StatIncrement> findStatIncsForTemplate(Template template) {
		
		return statIncRepo.findByTemplate(template);
	}
	
	public List<StatIncrement> findStatIncsForSkill(Skill skill) {
		
		return statIncRepo.findBySkill(skill);
	}
	
	public List<StatIncrement> findStatIncsForTitle(Title title) {
		
		return statIncRepo.findByTitle(title);
	}
	
	public StatIncrement updateStatIncrement(StatIncrement statInc) {
		
		return statIncRepo.save(statInc);
	}
	
	public void deleteStatIncrement(Long id) {
		
		Optional<StatIncrement> optValue = statIncRepo.findById(id);
		
		if(optValue.isPresent()) {
			
			statIncRepo.deleteById(id);
		}
		
	}

	// uses way too much cpu, need to separate and find shorter more efficient code for this
	public List<Skill> findUserSkillsWithAct(Action action, User user){
		
		List<Skill> userSkills = skillServ.findUserSkills(user);
		
		List<Skill> skillsWithAct = null;
		
		for (Skill userSkill: userSkills) {
			
			for (Action skillAct: userSkill.getActions()) {
				
				if (skillAct == action) {
					
					skillsWithAct.add(userSkill);
					
				}	
				
			}
			
		}
		
		return skillsWithAct;
		
	}
	
	public Dictionary<String, Double> StatChanges(Action action, List<Skill> skillsWithAct){
		
		List<StatIncrement> actStatIncs = findStatIncsForAction(action);
		
		Dictionary<String, Double> result= new Hashtable<>();
		
		for (Skill skill: skillsWithAct) {
		
			for (StatIncrement skillStatInc: skill.getStatIncs()) {
				
				for (StatIncrement actStatInc: actStatIncs) {
				
					if (skillStatInc.getName() == actStatInc.getName()) {
						
						double totalIncAmt = actStatInc.getIncAmt()+skillStatInc.getIncAmt();
						
						result.put(actStatInc.getName(), totalIncAmt);
						
					}
						
				}
				
			}
			
		}
		
		return result;
		
	}
	
}
