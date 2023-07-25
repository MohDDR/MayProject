package com.moh.code.controllers;

import java.util.Dictionary;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.moh.code.models.Action;
import com.moh.code.models.StatIncrement;
import com.moh.code.services.ActionService;
import com.moh.code.services.StatIncrementService;
import com.moh.code.services.UserService;

@Controller
public class ActionController {
	
	@Autowired
	private UserService userServ;
	
	@Autowired
	private ActionService actServ;
	
	@Autowired
	private StatIncrementService statIncServ;
	
    // New Action: action creation page
    @GetMapping("/new-action")
    
    public String newAction(Model model, 
    		
    		@ModelAttribute("action") Action action, HttpSession session){
    	
    	if (session.getAttribute("user_id")==null) {
			
    		return "redirect:/logout";
    		
    	} else {
    		
    		return "newAction.jsp";
    		
    	}
    	
    }
    
    // action creation procedure
    @PostMapping("/new-action")
    
    public String createAction(@Valid @ModelAttribute("action") Action action,
    		
    		BindingResult result, HttpSession session) {
    	
    	if ( result.hasErrors() ) {
    		
            return "newAction.jsp";
            
        } else {
        	
        	long id = (long) session.getAttribute("user_id");
        	
        	action.setCreator(userServ.findUser(id));
        	
            actServ.createAction(action);
            
            Long actId = action.getId();
        	
        	return "redirect:/view-action/" + actId;
        	
        }
    }

    // View Action: value and tag creation, and template and tag selection
    @GetMapping("/view-action/{id}")
    
	public String viewAction(Model model, @PathVariable("id") Long actId, 
			
			HttpSession session) {
    	
    	System.out.println("show view action page");
    	
    	if (session.getAttribute("user_id")==null) {
    		
    		return "redirect:/logout";
    		
    	} else {
    		
    		long id = (long) session.getAttribute("user_id");
    		
	    	model.addAttribute("user", userServ.findUser(id));
	    	
	    	// Authenticator?
	    	
	    	Action action = actServ.findAction(actId);
	    	
	    	System.out.println("action num " + action.getId() + " statIncs are: ");
	    	
	    	action.setStatIncs(statIncServ.findStatIncsForAction(action));
	    	
	    	System.out.println("!!! " + action.getStatIncs());
	    	
			model.addAttribute("act", action);
			
			return "viewAction.jsp";
    	}
	}
    
    // View Action: statInc and tag creation, and template and tag selection
    @GetMapping("/edit-action/{id}")
    
	public String editAction(@PathVariable("id") Long actId, 
			
			Model model, @ModelAttribute("action") Action action, 
			
			HttpSession session) {
    	
    	System.out.println("show edit action page");
    	
    	if (session.getAttribute("user_id")==null) {
    		
    		return "redirect:/logout";
    		
    	} else {

	    	// Authenticator?
    		
			model.addAttribute("action", actServ.findAction(actId));
			
			return "editAction.jsp";
    	}
	}
    
    // Edit Action: edit an action procedure
    @PutMapping("/edit-action/{id}")
    
	public String updateAction(@PathVariable("id") Long actId, 
			
			@Valid @ModelAttribute("action") Action action, 
			
			BindingResult result, HttpSession session) {
    	
    	System.out.println("update action procedure");
    	
    	if ( result.hasErrors() ) {
    		
            return "editAction.jsp";
            
        } else {
        	
        	long id = (long) session.getAttribute("user_id");
        	
        	action.setCreator(userServ.findUser(id));
        	
        	System.out.println("update action id: " + action.getId());

	    	actServ.updateAction(action);
        	
        	return "redirect:/view-action/" + actId;
    	}
	}
    
    // New Action statInc: create new statInc for action page
    @GetMapping("/action/{id}/create-statInc")
    
	public String newActionStatInc(@PathVariable("id") Long actId, 
			
			Model model, @ModelAttribute("statInc") StatIncrement statInc, 
			
			HttpSession session) {
    	
    	System.out.println("show statInc creation page");
    	
    	if (session.getAttribute("user_id")==null) {
    		
    		return "redirect:/logout";
    		
    	} else {
    		
    		model.addAttribute("actId", actId);

			return "newActValue.jsp";
    	}
	}
    
    // Action Create statInc: create new statInc for action procedure
    @PostMapping("/action/{id}/create-statInc")
    
    public String createActionStatInc(@PathVariable("id") Long actId, 
    		
    		@Valid @ModelAttribute("statInc") StatIncrement statInc,
    		
    		BindingResult result, HttpSession session) {
    	
    	System.out.println("statInc create procedure");
    	
    	if ( result.hasErrors() ) {
    		
            return "viewAction.jsp";
            
        } else {
        	
        	System.out.println("statInc info: " + statInc.getId());
        	
        	System.out.println("associating new statInc with action");
        	
	    	statInc.setAction(actServ.findAction(actId));
        	
        	System.out.println("creating statInc");
        	
        	statIncServ.createStatInc(statInc);
            
            System.out.println("done");
            
	    	//Long actId = action.getId();
        	
        	return "redirect:/view-action/" + actId;
        	
        }
    }
    
	// Preview and Confirm selected actions page
	@GetMapping("/actions-preview")
	
	public String previewActions (HttpSession session, Action action, Model model) {
		
		if (session.getAttribute("user_id")==null) {
			
			System.out.println("not logged in");
			
    		return "redirect:/logout";
    		
    	} else {
    		
    		Long id = (Long) session.getAttribute("user_id");
    		
    		Dictionary<String, Double> result = actServ.previewStatChanges(userServ.findUser(id));
    		
    		model.addAttribute("result", result);
    		
    		return "previewActions.jsp";
    		
    	}
		
	}
	
	// Preview and Confirm selected actions page
	@RequestMapping("/confirm-actions")
	
	public String confirmActions (HttpSession session, Action action, Model model) {
		
		if (session.getAttribute("user_id")==null) {
			
			System.out.println("not logged in");
			
    		return "redirect:/logout";
    		
    	} else {
    		
    		// needs validation for daily limit
    		Long id = (Long) session.getAttribute("user_id");
    		
    		Dictionary<String, Double> result = actServ.previewStatChanges(userServ.findUser(id));
    		
    		userServ.updateStats(result);
    		
    		return "confirmActions.jsp";
    		
    	}
		
	}
    
	/*
    // Select Action: select action page 'not sure what I'll do with this yet'
    @GetMapping("/select-action/{id}")
    
	public String editSelectedAction(@PathVariable("id") Long actId, 
			
			Model model, @ModelAttribute("action") Action action, 
			
			HttpSession session) {
    	
    	System.out.println("show edit action page");
    	
    	if (session.getAttribute("user_id")==null) {
    		
    		return "redirect:/logout";
    		
    	} else {

	    	// Authenticator?
    		
			model.addAttribute("action", actServ.findAction(actId));
			
			return "selectAction.jsp";
    	}
	}
    
    // Select Actions: edit an action procedure
    @PutMapping("/select-action/{id}")
    
	public String selectAction(@PathVariable("id") Long actId, 
			
			@Valid @ModelAttribute("action") Action action, 
			
			BindingResult result, HttpSession session) {
    	
    	System.out.println("select action procedure");
    	
    	if ( result.hasErrors() ) {
    		
            return "redirect:/";
            
        } else {
        	
        	long id = (long) session.getAttribute("user_id");
        	
        	action.setCreator(userServ.findUser(id));
        	
	    	actServ.updateAction(action);
        	
        	return "redirect:/dashboard";
    	}
	}
	*/

}
