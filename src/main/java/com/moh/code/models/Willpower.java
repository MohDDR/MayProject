package com.moh.code.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="willpowers")

public class Willpower {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	
    private Long id;
    
    private int mood;
    
    private int mentality;
    
    private int faith;
    
    private int discipline;
    
    private int average_will;
    
    private int focus;
    
    private int drive;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id")
    
    private User user;
    
    @Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    
    private Date createdAt;
    
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;
    
    public Willpower() {}

	public Willpower(Long id, User user) {

		this.id = id;
		
		this.mood = 0;
		
		this.mentality = 0;
		
		this.faith = 0;
		
		this.discipline = 0;
		
		this.average_will = 0;
		
		this.focus = 0;
		
		this.drive = 0;
		
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getMood() {
		return mood;
	}

	public void setMood(int mood) {
		this.mood = mood;
	}

	public int getMentality() {
		return mentality;
	}

	public void setMentality(int mentality) {
		this.mentality = mentality;
	}

	public int getFaith() {
		return faith;
	}

	public void setFaith(int faith) {
		this.faith = faith;
	}

	public int getDiscipline() {
		return discipline;
	}

	public void setDiscipline(int dicipline) {
		this.discipline = dicipline;
	}

	public int getAverage_will() {
		return average_will;
	}

	public void setAverage_will(int average_will) {
		this.average_will = average_will;
	}

	public int getFocus() {
		return focus;
	}

	public void setFocus(int focus) {
		this.focus = focus;
	}

	public int getDrive() {
		return drive;
	}

	public void setDrive(int drive) {
		this.drive = drive;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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
