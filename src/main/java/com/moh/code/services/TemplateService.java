package com.moh.code.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moh.code.models.Skill;
import com.moh.code.models.Template;
import com.moh.code.models.User;
import com.moh.code.repositories.TemplateRepository;

@Service
public class TemplateService {
	
	@Autowired
	TemplateRepository temRepo;
	
	@Autowired
	private SkillService skillServ;
	
	public List<Template> allTemplates(){
		
		return temRepo.findAll();
	}
	
	public Template createTemplate(Template tem) {
		
		temRepo.save(tem);
		
		return tem;
	}
	
	public Template findTemplate(Long id) {
		
        Optional<Template> optTem = temRepo.findById(id);
        
        if(optTem.isPresent()) {
        	
            return optTem.get();
            
        } else {
        	
            return null;
            
        }
    }
	
	public Template updateTemplate(Template tem) {
		
		return temRepo.save(tem);
	}
	
	public void deleteTemplate(Long id) {
		
		Optional<Template> optTem = temRepo.findById(id);
		
		if(optTem.isPresent()) {
			
			temRepo.deleteById(id);
		}
		
	}
	
	public boolean userHasSkillCheck(Long tem_id, User user) {
		
		System.out.println("finding users skills");
		
		List <Skill> skills = skillServ.findUserSkills(user);
		
		System.out.println("looking through users skills for a skill that uses template #" + tem_id);
		
		for(Skill skill: skills) {
			
			System.out.println("looking through users skills");
			
			if (tem_id.equals(skill.getTemplate().getId())) {
				
				System.out.println("users skills: this skill uses template " + skill.getTemplate().getId());
				
				System.out.println("skill with template #" + tem_id + " found");
				
				return true;
				
			}
			
			System.out.println("users skills: this skill uses template " + skill.getTemplate().getId());
			
		}
		
		System.out.println("user does not have a skill with this template");
		
		return false;
		
	}

	public void templateToSkill(User user, Long tempId) {
		
		Template template = findTemplate(tempId);
		
		Skill skill = new Skill(template.getId(), template.getName(), 
				
				template.getDescription(), template.getLv_inc(), template.getLv_limit(), 
				
				template.getGate(), template.getExp(), template, template.getStatIncs(), 
				
				null, template.getTemplateTags(), template.getCreator(), user);
		
		System.out.println("associated template with skill, will now save it");
		
		skillServ.createSkill(skill);
		
		System.out.println("saved template as a new skill");
		
		template.setSkillsMade(template.getSkillsMade()+1);
		
		System.out.println("increased skills made counter for template");
		
		updateTemplate(template);
		
		System.out.println("template updated");
	}
}
