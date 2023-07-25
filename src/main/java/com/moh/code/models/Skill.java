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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="skills")

public class Skill {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	
    private Long id;
    
    @NotBlank
    private String name;
    
    @NotBlank
    private String description;
    
    private double lv_inc;
    
    private int lv_limit;
    
    private int gate;
    
    private int exp;
    
    @ManyToOne(fetch = FetchType.LAZY)
    
    @JoinColumn(name="template_id")
    
    private Template template;
    
    @OneToMany(mappedBy="skill", fetch = FetchType.LAZY)
    
    private List<StatIncrement> statIncs;
    
    @ManyToMany(fetch = FetchType.LAZY)
    
    @JoinTable(
    		
            name = "skills_acts", 
            
            joinColumns = @JoinColumn(name = "skill_id"), 
            
            inverseJoinColumns = @JoinColumn(name = "action_id")
        )
    
    private List<Action> actions;
    
    @ManyToMany(fetch = FetchType.LAZY)
    
    @JoinTable(
    		
            name = "skills_tags", 
            
            joinColumns = @JoinColumn(name = "skill_id"), 
            
            inverseJoinColumns = @JoinColumn(name = "tag_id")
        )
    
    private List<Tag> tags;
    
    @ManyToOne(fetch = FetchType.LAZY)
    
    @JoinColumn(name="creator_id")
    
    private User creator;
    
    @ManyToOne(fetch = FetchType.LAZY)
    
    @JoinColumn(name="user_id")
    
    private User skillUser;
    
    @Column(updatable=false)
    
    @DateTimeFormat(pattern="yyyy-MM-dd")
    
    private Date createdAt;
    
    @DateTimeFormat(pattern="yyyy-MM-dd")
    
    private Date updatedAt;
    
    public Skill() {}

	public Skill(Long id, String name, String description, double lv_inc, 
			
			int lv_limit, int gate, int exp, Template template, 
			
			List<StatIncrement> statIncs, List<Action> actions, 
			
			List<Tag> tags, User creator, User skillUser) {
		
		this.id = id;
		
		this.name = name;
		
		this.description = description;
		
		this.lv_inc = lv_inc;
		
		this.lv_limit = lv_limit;
		
		this.gate = gate;
		
		this.exp = exp;
		
		this.template = template;
		
		this.statIncs = statIncs;
		
		this.actions = actions;
		
		this.tags = tags;
		
		this.creator = creator;
		
		this.skillUser = skillUser;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getLv_inc() {
		return lv_inc;
	}

	public void setLv_inc(double lv_inc) {
		this.lv_inc = lv_inc;
	}

	public int getLv_limit() {
		return lv_limit;
	}

	public void setLv_limit(int lv_limit) {
		this.lv_limit = lv_limit;
	}

	public int getGate() {
		return gate;
	}

	public void setGate(int gate) {
		this.gate = gate;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public Template getTemplate() {
		return template;
	}

	public void setTemplate(Template template) {
		this.template = template;
	}

	public List<StatIncrement> getStatIncs() {
		return statIncs;
	}

	public void setStatIncs(List<StatIncrement> statIncs) {
		this.statIncs = statIncs;
	}

	public List<Action> getActions() {
		return actions;
	}

	public void setActions(List<Action> actions) {
		this.actions = actions;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}
	
	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
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
    
	public User getSkillUser() {
		return skillUser;
	}

	public void setSkillUser(User skillUser) {
		this.skillUser = skillUser;
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
