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
import com.moh.code.models.StatIncrement;
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
		
		System.out.println("preview stats model begin");
		
		for (Action compAct: findCompletedActions(user)) {
			
			System.out.println("looking for skills associated with " + compAct.getName() + " .....!");
			
			List<Skill> skillsWithAct = statIncServ.findUserSkillsWithAct(compAct, user);
			
			if (skillsWithAct == null || skillsWithAct.isEmpty()) {
				
				System.out.println(" none found !!!");
				
				List<StatIncrement> actStatIncs = statIncServ.findStatIncsForAction(compAct);
				
				for (StatIncrement actStatInc: actStatIncs) {
					
					System.out.println(" result value !!!" + result);
					
					if (result == null || result.isEmpty()) {
					
						System.out.println(" put this in result !!!");
						
						result.put((String) actStatInc.getName() , actStatInc.getIncAmt());
						
					} else {
						
						for (Enumeration<String> resultKeys = result.keys(); resultKeys.hasMoreElements();){
							
							String resultKey = resultKeys.nextElement();
							
							if (resultKey == actStatInc.getName()) {
								
								System.out.println(" match found !!!");
								
								result.put( resultKey ,(result.get(resultKey) + actStatInc.getIncAmt()));
								
							} else {
								
								System.out.println(" result value part 2 !!!" + result);
								
								result.put( actStatInc.getName() , actStatInc.getIncAmt());
								
							}
								
				        }
						
					}
					
				}
				
			} else {
				
				System.out.println("found some! ");
				
				Dictionary<String, Double> oneResult = statIncServ.StatChanges(compAct, skillsWithAct);

				for (Enumeration<String> oneResultKeys = oneResult.keys(); oneResultKeys.hasMoreElements();){
					
					String oneResultKey = oneResultKeys.nextElement();

					if (result == null || result.isEmpty()) {		
							
						result.put((String) oneResultKey , oneResult.get(oneResultKey));
													
					} else {
						
						for (Enumeration<String> resultKeys = result.keys(); resultKeys.hasMoreElements();){
							
							String resultKey = resultKeys.nextElement();
						
							if (resultKey == oneResultKey) {
								
								result.put((String) resultKey,(result.get(resultKey)+oneResult.get(oneResultKey)));
								
							} else {
								
								result.put((String) oneResultKey , oneResult.get(oneResultKey));
								
							}
							
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
