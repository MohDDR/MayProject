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
import org.springframework.web.bind.annotation.RequestMapping;

import com.moh.code.models.StatIncrement;
import com.moh.code.models.Template;
import com.moh.code.models.User;
import com.moh.code.services.StatIncrementService;
import com.moh.code.services.TemplateService;
import com.moh.code.services.UserService;

@Controller
public class TemplateController {

	@Autowired
	TemplateService tempServ;
	
	@Autowired
	private UserService userServ;
	
	@Autowired
	private StatIncrementService statIncServ;
	
	// View All Templates: templates viewer
	@GetMapping("/view-all/templates")
	
	public String allTemplates(Model model, HttpSession session) {
		
		System.out.println("show view template page");
    	
    	if (session.getAttribute("user_id")==null) {
    		
    		return "redirect:/logout";
    		
    	} else {
    		
    		model.addAttribute("templates", tempServ.allTemplates());
    		
    		return "allTemplates.jsp";
		
    	}
	}
	
    // New Template: template creation page
    @GetMapping("/new-template")
    
    public String newTemplate(Model model, 
    		
    		@ModelAttribute("template") Template template, HttpSession session){
    	
    	if (session.getAttribute("user_id")==null) {
			
    		return "redirect:/logout";
    		
    	} else {
    		
    		return "newTemplate.jsp";
    		
    	}
    	
    }

    // template creation procedure
    @PostMapping("/new-template")
    
    public String createTemplate(@Valid @ModelAttribute("template") Template template,
    		
    		BindingResult result, HttpSession session) {
    	
    	if ( result.hasErrors() ) {
    		
            return "newTemplate.jsp";
            
        } else {
        	
        	long id = (long) session.getAttribute("user_id");
        	
        	template.setCreator(userServ.findUser(id));
        	
            tempServ.createTemplate(template);
            
            Long tempId = template.getId();
        	
        	return "redirect:/view-template/" + tempId;
        	
        }
    }
    
	// Template to Skill procedure: create skill from template
	@RequestMapping("/view-template/{id}/create-skill")
	
	public String templateCreateSkill(Model model, @PathVariable("id") Long tempId, 
			
			HttpSession session) {
		
		System.out.println("creating skill using template " + tempId);
    	
    	if (session.getAttribute("user_id")==null) {
    		
    		return "redirect:/logout";
    		
    	} else {
    		
    		System.out.println("find template and user");
    		
    		long id = (long) session.getAttribute("user_id");
    		
    		User user = userServ.findUser(id);
    		
    		if (!tempServ.userHasSkillCheck(tempId, user)) {
    			
    			System.out.println("creating skill using template");
    			
    			tempServ.templateToSkill(user, tempId);
    			
    			System.out.println("done");
        		
        		return "redirect:/view-all/skills";
    			
    		} 
    		
    		else {
    			
    			System.out.println("user already has this template as a skill");
    			
    			return "redirect:/view-template/" + tempId;
    			
    		}
    	}
	}
    
    // View Template: value and tag creation, and action and tag selection
    @GetMapping("/view-template/{id}")
    
	public String viewTemplate(Model model, @PathVariable("id") Long tempId, 
			
			HttpSession session) {
    	
    	System.out.println("show view template page");
    	
    	if (session.getAttribute("user_id")==null) {
    		
    		return "redirect:/logout";
    		
    	} else {
    		
    		long id = (long) session.getAttribute("user_id");
    		
	    	model.addAttribute("user", userServ.findUser(id));
	    	
	    	// Authenticator?
	    	
	    	Template template = tempServ.findTemplate(tempId);
	    	
	    	System.out.println("template num " + template.getId() + " statIncs are: ");
	    	
	    	template.setStatIncs(statIncServ.findStatIncsForTemplate(template));
	    	
	    	System.out.println("!!! " + template.getStatIncs());
	    	
			model.addAttribute("template", template);
			
			return "viewTemplate.jsp";
    	}
	}
    
    // View Template: statInc and tag creation, and template and tag selection
    @GetMapping("/edit-template/{id}")
    
	public String editTemplate(@PathVariable("id") Long tempId, 
			
			Model model, @ModelAttribute("template") Template template, 
			
			HttpSession session) {
    	
    	System.out.println("show edit template page");
    	
    	if (session.getAttribute("user_id")==null) {
    		
    		return "redirect:/logout";
    		
    	} else {

	    	// Authenticator?
    		
			model.addAttribute("template", tempServ.findTemplate(tempId));
			
			return "editTemplate.jsp";
    	}
	}

    // Edit Template: edit an template procedure
    @PutMapping("/edit-template/{id}")
    
	public String updateTemplate(@PathVariable("id") Long tempId, 
			
			@Valid @ModelAttribute("template") Template template, 
			
			BindingResult result, HttpSession session) {
    	
    	System.out.println("update template procedure");
    	
    	if ( result.hasErrors() ) {
    		
            return "editTemplate.jsp";
            
        } else {
        	
        	long id = (long) session.getAttribute("user_id");
        	
        	template.setCreator(userServ.findUser(id));
        	
        	System.out.println("update template id: " + template.getId());

	    	tempServ.updateTemplate(template);
        	
        	return "redirect:/view-template/" + tempId;
    	}
	}
    
    // New Template statInc: 'create new statInc for template' page
    @GetMapping("/template/{id}/create-statInc")
    
	public String newTemplateStatInc(@PathVariable("id") Long tempId, 
			
			Model model, @ModelAttribute("statInc") StatIncrement statInc, 
			
			HttpSession session) {
    	
    	System.out.println("show statInc creation page");
    	
    	if (session.getAttribute("user_id")==null) {
    		
    		return "redirect:/logout";
    		
    	} else {
    		
    		model.addAttribute("tempId", tempId);

			return "newTempValue.jsp";
    	}
	}

    // Template Create statInc: create new statInc for template procedure
    @PostMapping("/template/{id}/create-statInc")
    
    public String createTemplateStatInc(@PathVariable("id") Long tempId, 
    		
    		@Valid @ModelAttribute("statInc") StatIncrement statInc,
    		
    		BindingResult result, HttpSession session) {
    	
    	System.out.println("statInc create procedure");
    	
    	if ( result.hasErrors() ) {
    		
            return "viewTemplate.jsp";
            
        } else {
        	
        	System.out.println("statInc info: " + statInc.getId());
        	
        	System.out.println("associating new statInc with template");
        	
	    	statInc.setTemplate(tempServ.findTemplate(tempId));
        	
        	System.out.println("creating statInc");
        	
        	statIncServ.createStatInc(statInc);
            
            System.out.println("done");
        	
        	return "redirect:/view-template/" + tempId;
        	
        }
    }

}
