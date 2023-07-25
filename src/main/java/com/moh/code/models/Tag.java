package com.moh.code.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="tags")

public class Tag {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	
    private Long id;
    
    @NotBlank
    @Size(min=4, message="Tag name must be greater than 3 characters!")
    
    private String name;
    
    @ManyToMany(fetch = FetchType.LAZY)
    
    @JoinTable(
    		
            name = "titles_tags", 
            
            joinColumns = @JoinColumn(name = "tag_id"), 
            
            inverseJoinColumns = @JoinColumn(name = "title_id")
        )
    
    private List<Title> titles;
    
    @ManyToMany(fetch = FetchType.LAZY)
    
    @JoinTable(
    		
            name = "acts_tags", 
            
            joinColumns = @JoinColumn(name = "tag_id"), 
            
            inverseJoinColumns = @JoinColumn(name = "action_id")
        )
    
    private List<Action> actions;
    
    @ManyToMany(fetch = FetchType.LAZY)
    
    @JoinTable(
    		
            name = "skills_tags", 
            
            joinColumns = @JoinColumn(name = "tag_id"), 
            
            inverseJoinColumns = @JoinColumn(name = "skill_id")
        )
    
    private List<Skill> skills;
    
    @ManyToMany(fetch = FetchType.LAZY)
    
    @JoinTable(
    		
            name = "templates_tags", 
            
            joinColumns = @JoinColumn(name = "tag_id"), 
            
            inverseJoinColumns = @JoinColumn(name = "template_id")
        )
    
    private List<Template> templates;
    
    @Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    
    private Date createdAt;
    
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;
    
    public Tag() {}
    
	public Tag(Long id, String name, List<Title> titles, List<Action> actions, 
			
			List<Skill> skills, List<Template> templates) {
		
		this.id = id;
		
		this.name = name;
		
		this.titles = titles;
		
		this.actions = actions;
		
		this.skills = skills;
		
		this.templates = templates;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Title> getTitles() {
		return titles;
	}

	public void setTitles(List<Title> titles) {
		this.titles = titles;
	}

	public List<Action> getActions() {
		return actions;
	}

	public void setActions(List<Action> actions) {
		this.actions = actions;
	}

	public List<Skill> getSkills() {
		return skills;
	}

	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}

	public List<Template> getTemplates() {
		return templates;
	}

	public void setTemplates(List<Template> templates) {
		this.templates = templates;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	@PrePersist
    protected void onCreate(){
		
        this.createdAt = new Date();
    }
	
    @PreUpdate
    protected void onUpdate(){
    	
        this.updatedAt = new Date();
    }
    
}
