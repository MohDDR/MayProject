package com.moh.code.controllers;

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

import com.moh.code.models.Skill;
import com.moh.code.models.User;
import com.moh.code.services.SkillService;
import com.moh.code.services.StatIncrementService;
import com.moh.code.services.UserService;

@Controller
public class SkillController {

	@Autowired
	private UserService userServ;
	
	@Autowired
	private SkillService skillServ;
	
	@Autowired
	private StatIncrementService statIncServ;
	
	// View All Skills: skills viewer 
	@GetMapping("/view-all/skills")
	
	public String allSkills(Model model, HttpSession session) {
		
		System.out.println("show view all skills page");
    	
    	if (session.getAttribute("user_id")==null) {
    		
    		return "redirect:/logout";
    		
    	} else {
    		
    		long id = (long) session.getAttribute("user_id");
    		
    		User user = userServ.findUser(id);
    		
    		model.addAttribute("skills", skillServ.findUserSkills(user));
    		
    		return "allSkills.jsp";
		
    	}
	}
	
    // New Skill: skill creation page *Unnecessary?
    @GetMapping("/new-skill")
    
    public String newSkill(Model model, 
    		
    		@ModelAttribute("skill") Skill skill, HttpSession session){
    	
    	if (session.getAttribute("user_id")==null) {
			
    		return "redirect:/logout";
    		
    	} else {
    		
    		return "newSkill.jsp";
    		
    	}
    	
    }

    // skill creation procedure *Unnecessary?
    @PostMapping("/new-skill")
    
    public String createSkill(@Valid @ModelAttribute("skill") Skill skill,
    		
    		BindingResult result, HttpSession session) {
    	
    	if ( result.hasErrors() ) {
    		
            return "newSkill.jsp";
            
        } else {
        	
        	long id = (long) session.getAttribute("user_id");
        	
        	skill.setCreator(userServ.findUser(id));
        	
        	skillServ.createSkill(skill);
            
            Long skillId = skill.getId();
        	
        	return "redirect:/view-skill/" + skillId;
        	
        }
    }
    
    // View Skill -- add template attachment
    @GetMapping("/view-skill/{id}")
    
	public String viewSkill(Model model, @PathVariable("id") Long skillId, 
			
			HttpSession session) {
    	
    	System.out.println("show view skill page");
    	
    	if (session.getAttribute("user_id")==null) {
    		
    		return "redirect:/logout";
    		
    	} else {
    		
    		long id = (long) session.getAttribute("user_id");
    		
	    	model.addAttribute("user", userServ.findUser(id));
	    	
	    	// Authenticator?
	    	
	    	Skill skill = skillServ.findSkill(skillId);
	    	
	    	System.out.println("skill num " + skill.getId() + " statIncs are: ");
	    	
	    	skill.setStatIncs(statIncServ.findStatIncsForSkill(skill));
	    	
	    	System.out.println("!!! " + skill.getStatIncs());
	    	
			model.addAttribute("skill", skill);
			
			return "viewSkill.jsp";
    	}
	}
    
    // View Skill: statInc and tag creation, and skill and tag selection *Unnecessary?
    @GetMapping("/edit-skill/{id}")
    
	public String editSkill(@PathVariable("id") Long skillId, 
			
			Model model, @ModelAttribute("skill") Skill skill, 
			
			HttpSession session) {
    	
    	System.out.println("show edit skill page");
    	
    	if (session.getAttribute("user_id")==null) {
    		
    		return "redirect:/logout";
    		
    	} else {

	    	// Authenticator?
    		
			model.addAttribute("skill", skillServ.findSkill(skillId));
			
			return "editSkill.jsp";
    	}
	}

    // Edit Skill: edit an skill procedure *Unnecessary?
    @PutMapping("/edit-skill/{id}")
    
	public String updateSkill(@PathVariable("id") Long skillId, 
			
			@Valid @ModelAttribute("skill") Skill skill, 
			
			BindingResult result, HttpSession session) {
    	
    	System.out.println("update template procedure");
    	
    	if ( result.hasErrors() ) {
    		
            return "editSkill.jsp";
            
        } else {
        	
        	long id = (long) session.getAttribute("user_id");
        	
        	skill.setCreator(userServ.findUser(id));
        	
        	System.out.println("update skill id: " + skill.getId());

        	skillServ.updateSkill(skill);
        	
        	return "redirect:/view-skill/" + skillId;
    	}
	}

}

