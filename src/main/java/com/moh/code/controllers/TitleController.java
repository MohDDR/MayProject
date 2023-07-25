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
import com.moh.code.models.StatIncrement;
import com.moh.code.models.Title;
import com.moh.code.services.StatIncrementService;
import com.moh.code.services.TitleService;
import com.moh.code.services.UserService;

@Controller
public class TitleController {

	@Autowired
	private UserService userServ;
	
	@Autowired
	private StatIncrementService statIncServ;
	
	@Autowired
	private TitleService titleServ;
	
	// View All Title: titles viewer
	@GetMapping("/view-all/titles")
	
	public String allTitle(Model model, HttpSession session) {
		
		System.out.println("show view title page");
    	
    	if (session.getAttribute("user_id")==null) {
    		
    		return "redirect:/logout";
    		
    	} else {
    		
    		model.addAttribute("titles", titleServ.allTitles());
    		
    		return "allTitles.jsp";
		
    	}
	}
	
    // New Title: title creation page
    @GetMapping("/new-title")
    
    public String newTitle(Model model, 
    		
    		@ModelAttribute("title") Title title, HttpSession session){
    	
    	if (session.getAttribute("user_id")==null) {
			
    		return "redirect:/logout";
    		
    	} else {
    		
    		return "newTitle.jsp";
    		
    	}
    	
    }

    // title creation procedure
    @PostMapping("/new-title")
    
    public String createTitle(@Valid @ModelAttribute("template") Title title,
    		
    		BindingResult result, HttpSession session) {
    	
    	if ( result.hasErrors() ) {
    		
            return "newTitle.jsp";
            
        } else {
        	
        	long id = (long) session.getAttribute("user_id");
        	
        	title.setCreator(userServ.findUser(id));
        	
        	titleServ.createTitle(title);
            
            Long titleId = title.getId();
        	
        	return "redirect:/view-title/" + titleId;
        	
        }
    }
    
    // View Title: value and tag creation, and action and tag selection
    @GetMapping("/view-title/{id}")
    
	public String viewTitle(Model model, @PathVariable("id") Long titleId, 
			
			HttpSession session) {
    	
    	System.out.println("show view title page");
    	
    	if (session.getAttribute("user_id")==null) {
    		
    		return "redirect:/logout";
    		
    	} else {
    		
    		long id = (long) session.getAttribute("user_id");
    		
	    	model.addAttribute("user", userServ.findUser(id));
	    	
	    	// Authenticator?
	    	
	    	Title title = titleServ.findTitle(titleId);
	    	
	    	System.out.println("title num " + title.getId() + " statIncs are: ");
	    	
	    	title.setStatIncs(statIncServ.findStatIncsForTitle(title));
	    	
	    	System.out.println("!!! " + title.getStatIncs());
	    	
			model.addAttribute("title", title);
			
			return "viewTitle.jsp";
    	}
	}
    
    // View Title: statInc and tag creation, and title and tag selection
    @GetMapping("/edit-title/{id}")
    
	public String editTitle(@PathVariable("id") Long titleId, 
			
			Model model, @ModelAttribute("title") Title title, 
			
			HttpSession session) {
    	
    	System.out.println("show edit title page");
    	
    	if (session.getAttribute("user_id")==null) {
    		
    		return "redirect:/logout";
    		
    	} else {

	    	// Authenticator?
    		
			model.addAttribute("title", titleServ.findTitle(titleId));
			
			return "editTitle.jsp";
    	}
	}

    // Edit Title: edit an title procedure
    @PutMapping("/edit-title/{id}")
    
	public String updateTitle(@PathVariable("id") Long titleId, 
			
			@Valid @ModelAttribute("title") Title title, 
			
			BindingResult result, HttpSession session) {
    	
    	System.out.println("update title procedure");
    	
    	if ( result.hasErrors() ) {
    		
            return "editTitle.jsp";
            
        } else {
        	
        	long id = (long) session.getAttribute("user_id");
        	
        	title.setCreator(userServ.findUser(id));
        	
        	System.out.println("update title id: " + title.getId());

        	titleServ.updateTitle(title);
        	
        	return "redirect:/view-title/" + titleId;
    	}
	}
    
    // New Title statInc: 'create new statInc for title' page
    @GetMapping("/title/{id}/create-statInc")
    
	public String newTitleStatInc(@PathVariable("id") Long titleId, 
			
			Model model, @ModelAttribute("statInc") StatIncrement statInc, 
			
			HttpSession session) {
    	
    	System.out.println("show statInc creation page");
    	
    	if (session.getAttribute("user_id")==null) {
    		
    		return "redirect:/logout";
    		
    	} else {
    		
    		model.addAttribute("titleId", titleId);

			return "newTitleValue.jsp";
    	}
	}

    // Title Create statInc: create new statInc for title procedure
    @PostMapping("/title/{id}/create-statInc")
    
    public String createTitleStatInc(@PathVariable("id") Long titleId, 
    		
    		@Valid @ModelAttribute("statInc") StatIncrement statInc,
    		
    		BindingResult result, HttpSession session) {
    	
    	System.out.println("statInc create procedure");
    	
    	if ( result.hasErrors() ) {
    		
            return "viewTitle.jsp";
            
        } else {
        	
        	System.out.println("statInc info: " + statInc.getId());
        	
        	System.out.println("associating new statInc with title");
        	
	    	statInc.setTitle(titleServ.findTitle(titleId));
        	
        	System.out.println("creating statInc");
        	
        	statIncServ.createStatInc(statInc);
            
            System.out.println("done");
        	
        	return "redirect:/view-title/" + titleId;
        	
        }
    }
}
