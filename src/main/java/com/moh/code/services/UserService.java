package com.moh.code.services;

import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.moh.code.models.Action;
import com.moh.code.models.LoginUser;
import com.moh.code.models.User;
import com.moh.code.repositories.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepo;
    
    @Autowired
	private CharismaService chaServ;
    
    @Autowired
	private ConstitutionService conServ;
    
    @Autowired
	private WillpowerService willServ;
    
    @Autowired 
    private ActionService actServ;
    
    public User register(User newUser, BindingResult result) {
    	
    		System.out.println("user object email is being checked");
    		
    	Optional<User> potentialUser = userRepo.findByEmail(newUser.getEmail());
    	
    	if(potentialUser.isPresent()) {
    		
    		System.out.println("user object email is already in use");
    		
    		result.rejectValue("email", "Matches", "This email address is already associated with an account!");
    	}
    	
    	if(!newUser.getPassword().equals(newUser.getConfirm())) {
    		
    		System.out.println("user object password is false");
    		
    	    result.rejectValue("confirm", "Matches", "The Confirm Password must match Password!");
    	}

    	if(result.hasErrors()) {
    		
    		System.out.println("user object has errors");
    		
    	    return null;
    	}

    	else {
    			System.out.println("user object has no errors");
    			
    		String hashed = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
    		
    			System.out.println("user object is hashed and being saved");
    			
    		newUser.setPassword(hashed);
    		
    		return userRepo.save(newUser);
    	}  	
    }

    public User login(LoginUser newLogin, BindingResult result) {

    		System.out.println("login object email is being checked");
    		
    	Optional<User> potentialUser = userRepo.findByEmail(newLogin.getEmail());

    	if(!potentialUser.isPresent()) {
    		
    		System.out.println("login object email is not registered");
    		
    		result.rejectValue("email", "Matches", "This email address is not registered!");
    	}

    	else if(!BCrypt.checkpw(newLogin.getPassword(), potentialUser.orElse(null).getPassword())) {
    		
    		System.out.println("login object password is false");
    		
    		result.rejectValue("password", "Matches", "Invalid Password!");
    	}

    	if(result.hasErrors()) {
    		
    		System.out.println("login object has errors");
    		
    	    return null;
    	}

    	else {
    		System.out.println("login object has no errors");
    		
    		return potentialUser.orElse(null);
    	}
    }
    
    public User findUser(long id) {
    	
		Optional<User> optUser = userRepo.findById(id);
		
		if(optUser.isPresent()) {
			
			return optUser.get();
		}
		else {
			
			return null;
		}
	}
    
	public void createUserStats( User user ) {
		
		chaServ.createCha(user);
		
		conServ.createCon(user);
		
		willServ.createWill(user);
		
		user.setDailyLimit(600);
	}
	
	public User collectUserStats(Long id) {
		
		//uses session id to find user
    	
    	User user = findUser(id); 
    	
    	//finds the users stats and attaches them to the user found
    	
    	user.setCharisma(chaServ.findCha(id));
    	
    	user.setConsti(conServ.findCon(id));
    	
    	user.setWill(willServ.findWill(id));
    	
    	return user;
		
	}

	/* Select Action Check: this will check to see if user has alrealdy selected this action to its limit
	and if the user still has enough daily limit hours */
	public boolean selectActionCheck(HttpSession session, Long actId){

		User user = findUser((long) session.getAttribute("user_id"));
		
		Action action = actServ.findAction(actId);
		
		int amtCompleted = 0;
		
		//check if theres enough time in daily hours
		int dL = user.getDailyLimit();
		
		//check if max limit has been reached
		int mL = action.getMaxLimit();
		
		System.out.println("iterating through users completed acts");
		
		for(Action completedAct: user.getCompletedActs()) {
			
	    	if (actId == completedAct.getId()) {
	    		
	    		System.out.println("found act");
	    		
	    		amtCompleted++;
	    		
	    		System.out.println("completed = " + amtCompleted);
	    		
	    	}
		}
		
		//future changes will be neccessary for overdrive skill
		if (amtCompleted < mL && dL > 0) {
			
			System.out.println("saving this action");
			
			action.getUsers().add(user);
			
			actServ.updateAction(action);
			
			return true;
		}
		
		else {
			
			System.out.println("will not save action, user limit or action limit reached");
			
			return false;
		}
	}
	
	public Dictionary<Action, Long> findCompletedActionsCount(User user){
		
		Dictionary<Action, Long> result= new Hashtable<>();
		
		for (Action action: user.getCompletedActs()) {
			
			System.out.println("... heres the action ... " + action);
			
			Long actCount = userRepo.countAllByCompletedActs(action);
			
			System.out.println("... heres user count in this action ... " + actCount);
			
			result.put(action, actCount);
			
			System.out.println("... heres current results ... " + result);
			
		}
		
		System.out.println("... heres results ... " + result);
		
		return result;
	}
	
	public void updateStats(Dictionary<String, Double> result) {
		
		for (Enumeration key = result.elements(); key.hasMoreElements();){
			
			if (key.nextElement() == key.nextElement()) {
				//main category title needed for stat incs
				//adding main category would require major changes to the entire app
				//check for main category and iterate through the its subcategories for matching stat name
				//add the result value and the current value in the stat together
				
				
			}
			
		}
		
	}
	
}