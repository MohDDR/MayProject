package com.moh.code.services;

import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moh.code.models.Action;
import com.moh.code.models.Skill;
import com.moh.code.models.User;
import com.moh.code.repositories.ActionRepository;

@Service
public class ActionService {
	
	@Autowired
	ActionRepository actionRepo;
	
	@Autowired
	StatIncrementService statIncServ;
	
	public List<Action> allActions(){
		
		return actionRepo.findAll();
	}
	
	public List<Action> findCompletedActions(User user){
		
		return actionRepo.findAllByUsers(user);
	}
	
	public Action createAction(Action action) {
		
		actionRepo.save(action);
		
		return action;
	}
	
	public Action findAction(Long id) {
		
        Optional<Action> optAction = actionRepo.findById(id);
        
        if(optAction.isPresent()) {
        	
            return optAction.get();
            
        } else {
        	
            return null;
            
        }
    }
	
	public Action findByName(String name) {
		
        Optional<Action> optName = actionRepo.findByName(name);
        
        if(optName.isPresent()) {
        	
            return optName.get();
            
        } else {
        	
            return null;
            
        }
    }
	
	public Dictionary<String, Double> previewStatChanges(User user) {
		
		Dictionary<String, Double> result= new Hashtable<>();
		
		for (Action compAct: findCompletedActions(user)) {
			
			List<Skill> skillsWithAct = statIncServ.findUserSkillsWithAct(compAct, user);
			
			if (skillsWithAct != null) {
				
				Dictionary<String, Double> oneResult = statIncServ.StatChanges(compAct, skillsWithAct);

				for (Enumeration oneResultKey = oneResult.elements(); oneResultKey.hasMoreElements();){
					
					for (Enumeration resultKey = result.elements(); resultKey.hasMoreElements();){
						
						if (resultKey.nextElement() == oneResultKey.nextElement()) {
							
							result.put((String) resultKey.nextElement(),(result.get(resultKey)+oneResult.get(oneResultKey)));
							
						} else {
							
							result.put((String) oneResultKey.nextElement() , oneResult.get(oneResultKey));
							
						}
							
			        }
					
				}
				
			}
				
		}
		
		return result;
		
	}
	
	public Action updateAction(Action action) {
		
		return actionRepo.save(action);
	}
	
	public void deleteAction(Long id) {
		
		Optional<Action> optAction = actionRepo.findById(id);
		
		if(optAction.isPresent()) {
			
			actionRepo.deleteById(id);
		}
		
	}
	
}
