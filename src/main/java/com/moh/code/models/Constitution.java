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
@Table(name="constitutions")

public class Constitution {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	
    private Long id;
    
    private int health;
    
    private int training;
    
    private int diet;
    
    private int average_con;
    
    private int length;
    
    private int consistency;
    
    private int intensity;
    
    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id")
    
    private User user;
    
    @Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    
    private Date createdAt;
    
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;
    
    public Constitution() {}

	public Constitution(Long id, User user) {

		this.id = id;
		
		this.health = 0;
		
		this.training = 0;
		
		this.diet = 0;
		
		this.average_con = 0;
		
		this.length = 0;
		
		this.consistency = 0;
		
		this.intensity = 0;
		
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getTraining() {
		return training;
	}

	public void setTraining(int training) {
		this.training = training;
	}

	public int getDiet() {
		return diet;
	}

	public void setDiet(int diet) {
		this.diet = diet;
	}

	public int getAverage_con() {
		return average_con;
	}

	public void setAverage_con(int average_con) {
		this.average_con = average_con;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getConsistency() {
		return consistency;
	}

	public void setConsistency(int consistency) {
		this.consistency = consistency;
	}

	public int getIntensity() {
		return intensity;
	}

	public void setIntensity(int intensity) {
		this.intensity = intensity;
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
