package com.moh.code.controllers;

import java.util.Dictionary;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.moh.code.models.Action;
import com.moh.code.models.LoginUser;
import com.moh.code.models.User;
import com.moh.code.services.ActionService;
import com.moh.code.services.SkillService;
import com.moh.code.services.TitleService;
import com.moh.code.services.UserService;

@Controller
public class MainController {
	
	@Autowired
	private UserService userServ;
	
	@Autowired
	private ActionService actServ;
	
	@Autowired
	private TitleService titleServ;
	
	@Autowired
	private SkillService skillServ;
    
	//index: login and logout page
    @GetMapping("/")
    
    public String index(Model model, HttpSession session) {

    	
    	if (session.getAttribute("user_id")==null) {
    		
    		System.out.println("login and registration page");
    		
    		model.addAttribute("newUser", new User());
    		
    		model.addAttribute("newLogin", new LoginUser());
    		
    		return "index.jsp";
    		
    	} else {
    		
    		return "redirect:/dashboard";
    	}
    }
    
    //register procedure
    @PostMapping("/register")
    
    public String register(@Valid @ModelAttribute("newUser") User newUser, 
    		
            BindingResult result, Model model, HttpSession session) {
    	
    	System.out.println("user object is going to register service");
    	
    	User user = userServ.register(newUser, result);
    	
    	System.out.println("user object has returned from register service");
    	
    	if(result.hasErrors()) {
    		
            model.addAttribute("newLogin", new LoginUser());
            
            return "index.jsp";
        }
    	
    	System.out.println("creating user stats");
    	
    	userServ.createUserStats(user);
        
        long id = user.getId();
        
        System.out.println("user object id is in session");
        
        session.setAttribute("user_id", id);
        
        return "redirect:/dashboard";
    }
    
    //login procedure
    @PostMapping("/login")
    
    public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin, 
    		
            BindingResult result, Model model, HttpSession session) {

    	User user = userServ.login(newLogin, result);
    	
        if(result.hasErrors()) {
        	
            model.addAttribute("newUser", new User());
            
            return "index.jsp";
        }
    
        long id = user.getId();
        
        session.setAttribute("user_id", id);
        
        return "redirect:/dashboard";
    }
    
    // Dashboard: home page
	@GetMapping("/dashboard")
	
    public String dashboard(Model model, HttpSession session) {
		
		if (session.getAttribute("user_id")==null) {
			
    		return "redirect:/logout";
    		
    	} else {
    		
    		System.out.println("logged in? " + session.getAttribute("user_id"));
    		
    		//viewUserStats finds the user and attaches their stats onto them
    		
    		Long id = (Long) session.getAttribute("user_id");
    		
    		User user = userServ.collectUserStats(id);
    		
    		user.setCompletedActs(actServ.findCompletedActions(user));

    		Dictionary<Action, Long> compActs = userServ.findCompletedActionsCount(user);
    		
    		model.addAttribute("user", user);
    		
			model.addAttribute("compActs", compActs);
	    	
	    	//pulls out all existing actions and adds it to the model
    		
    		List <Action> actions = actServ.allActions();
	    	
	    	model.addAttribute("actions", actions);
	        
	        return "dashboard.jsp";
    	}
    }
	
	// Dashboard: select action
	@RequestMapping("/dashboard/{id}")
	
	public String selectAction(@PathVariable("id") Long actId,
			
			HttpSession session, Model model) {
		
		if (session.getAttribute("user_id")==null) {
			
			System.out.println("not logged in");
			
    		return "redirect:/logout";
    		
    	} else {
    		
    		if (userServ.selectActionCheck(session, actId)) {
    			
    			System.out.println("action selected and saved");
    			
    		} else {
    			
    			System.out.println("action failed to select and save");
    			
    		}
    		
    		return "redirect:/dashboard";
    	}
	}
	
    //logout procedure
    @GetMapping("/logout")
    
    public String logout(HttpSession session) {
    	
    	session.setAttribute("actId", null);
    	
    	session.setAttribute("user_id", null);
    	
    	return "redirect:/";
    }
    
    //View Account: users account page
    @GetMapping("/view/account")
    
    public String viewAccount(HttpSession session, Model model) {
    	
    	if (session.getAttribute("user_id")==null) {
			
    		return "redirect:/logout";
    		
    	} else {
    		
    		System.out.println("logged in... heres account info");
    		
    		Long id = (Long) session.getAttribute("user_id");

    		User user = userServ.collectUserStats(id);
    		
    		//rename to findTitlesEarned, findSkillsEarned, ...
    		user.setTitles(titleServ.findUsersTitles(user));
    		
    		user.setCompletedActs(actServ.findCompletedActions(user));
    		
    		user.setSkills(skillServ.findUserSkills(user));
    		
    		model.addAttribute("user", user);
    		
    		return "viewUserAcc.jsp";
    		
    	}
    	
    }
    
}
