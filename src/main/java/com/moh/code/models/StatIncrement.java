package com.moh.code.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="stat_Increments")

public class StatIncrement {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	
    private Long id;
    
	//stat name
    @NotBlank
    private String name;
    
    //points increment increase per level
    
    private double incAmt;
    
    @ManyToOne(fetch = FetchType.LAZY)
    
    @JoinColumn(name="action_id")
    
    private Action action;
    
    @ManyToOne(fetch = FetchType.LAZY)
    
    @JoinColumn(name="template_id")
    
    private Template template;
    
    @ManyToOne(fetch = FetchType.LAZY)
    
    @JoinColumn(name="skill_id")
    
    private Skill skill;
    
    @ManyToOne(fetch = FetchType.LAZY)
    
    @JoinColumn(name="title_id")
    
    private Title title;
    
    @Column(updatable=false)
    
    @DateTimeFormat(pattern="yyyy-MM-dd")
    
    private Date createdAt;
    
    @DateTimeFormat(pattern="yyyy-MM-dd")
    
    private Date updatedAt;
    
    public StatIncrement() {}

	public StatIncrement(Long id, String name, double incAmt, Action action, Template template, 
			
			Skill skill, Title title) {
		
		this.id = id;
		
		this.name = name;
		
		this.incAmt = incAmt;
		
		this.action = action;
		
		this.template = template;
		
		this.skill = skill;
		
		this.title = title;
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

	public double getIncAmt() {
		return incAmt;
	}

	public void setIncAmt(double incAmt) {
		this.incAmt = incAmt;
	}

	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}

	public Template getTemplate() {
		return template;
	}

	public void setTemplate(Template template) {
		this.template = template;
	}

	public Skill getSkill() {
		return skill;
	}

	public void setSkill(Skill skill) {
		this.skill = skill;
	}

	public Title getTitle() {
		return title;
	}

	public void setTitle(Title title) {
		this.title = title;
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
