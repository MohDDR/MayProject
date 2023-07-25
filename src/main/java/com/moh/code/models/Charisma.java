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
@Table(name="charismas")

public class Charisma {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	
    private Long id;
    
    private int speech;
    
    private int conduct;
    
    private int reputation;
    
    private int average_cha;
    
    private int ability;
    
    private int network;
    
    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id")
    
    private User user;
    
    @Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    
    private Date createdAt;
    
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;
    
    public Charisma() {}

	public Charisma(Long id, User user) {

		this.id = id;	
		
		this.speech = 0;
		
		this.conduct = 0;
		
		this.reputation = 0;
		
		this.average_cha = 0;
		
		this.ability = 0;
		
		this.network = 0;
		
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getSpeech() {
		return speech;
	}

	public void setSpeech(int speech) {
		this.speech = speech;
	}

	public int getConduct() {
		return conduct;
	}

	public void setConduct(int conduct) {
		this.conduct = conduct;
	}

	public int getReputation() {
		return reputation;
	}

	public void setReputation(int reputation) {
		this.reputation = reputation;
	}

	public int getAverage_cha() {
		return average_cha;
	}

	public void setAverage_cha(int average_cha) {
		this.average_cha = average_cha;
	}

	public int getAbility() {
		return ability;
	}

	public void setAbility(int ability) {
		this.ability = ability;
	}

	public int getNetwork() {
		return network;
	}

	public void setNetwork(int network) {
		this.network = network;
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
