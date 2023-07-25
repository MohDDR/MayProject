package com.moh.code.models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="users")

public class User {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	
    private Long id;
	
	@NotBlank(message="Username is required!")
	
	@Size(min=3, max=30, message="Username must be between 3 and 30 characters")
	
	private String firstName;
    
    @NotBlank(message="Username is required!")
    
    @Size(min=3, max=30, message="Username must be between 3 and 30 characters")
    
    private String lastName;
    
    @NotBlank(message="Email is required!")
    
    @Email(message="Please enter a valid email!")
    
    private String email;
    
    @NotBlank(message="Password is required!")
    
    @Size(min=8, max=128, message="Password must be between 8 and 128 characters")
    
    private String password;
    
    @Transient
    @NotBlank(message="Confirm Password is required!")
    
    @Size(min=8, max=128, message="Confirm Password must be between 8 and 128 characters")
    
    private String confirm;
    
    private int dailyLimit;
    
    @OneToOne(mappedBy="user", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    
    private Charisma charisma;
    
    @OneToOne(mappedBy="user", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    
    private Constitution consti;
    
    @OneToOne(mappedBy="user", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    
    private Willpower will;
    
    // limit total amount of skills a user can activate per month, they can then shift their focus around often
    @OneToMany(mappedBy="skillUser", fetch = FetchType.LAZY)
    
    private List<Skill> skills;
    
    @ManyToMany(fetch = FetchType.LAZY)
    
    @JoinTable(
    		
            name = "completed_acts", 
            
            joinColumns = @JoinColumn(name = "user_id"), 
            
            inverseJoinColumns = @JoinColumn(name = "action_id")
        )
    
    // create new relationship for users and actions called permanent completed acts that will hold and tally all actions ever completed
    
    // reset completed acts whenever daily limit resets, edit it with overdrive skill
    private List<Action> completedActs;
    
    @ManyToMany(fetch = FetchType.LAZY)
    
    @JoinTable(
    		
            name = "titles_earned", 
            
            joinColumns = @JoinColumn(name = "user_id"), 
            
            inverseJoinColumns = @JoinColumn(name = "title_id")
        )
    
    private List<Title> titles;
    
    @OneToMany(mappedBy="creator", fetch = FetchType.LAZY)
    
    private List<Action> actsCreated;
    
    @OneToMany(mappedBy="creator", fetch = FetchType.LAZY)
    
    private List<Template> temsCreated;
    
    @OneToMany(mappedBy="creator", fetch = FetchType.LAZY)
    
    private List<Title> titlesCreated;
    
    @OneToMany(mappedBy="creator", fetch = FetchType.LAZY)
    
    private List<Skill> skillsCreated;
    
    @Column(updatable=false)
    
    @DateTimeFormat(pattern="yyyy-MM-dd")
    
    private Date createdAt;
    
    @DateTimeFormat(pattern="yyyy-MM-dd")
    
    private Date updatedAt;
    
    public User() {}

	public User(Long id, String firstName, String lastName, String email, 
			
			String password, String confirm, Charisma charisma, 
			
			Constitution consti, Willpower will, List<Action> completedActs,
			
			List<Skill> skills, List<Title> titles, List<Action> actsCreated, 
			
			List<Template> temsCreated, List<Title> titlesCreated, 
			
			List<Skill> skillsCreated) {
		
		this.id = id;
		
		this.firstName = firstName;
		
		this.lastName = lastName;
		
		this.email = email;
		
		this.password = password;
		
		this.confirm = confirm;
		
		this.charisma = charisma;
		
		this.consti = consti;
		
		this.will = will;
		
		this.completedActs = completedActs;
		
		this.skills = skills;
		
		this.titles = titles;
		
		this.actsCreated = actsCreated;
		
		this.temsCreated = temsCreated;
		
		this.titlesCreated = titlesCreated;
		
		this.skillsCreated = skillsCreated;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirm() {
		return confirm;
	}

	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}

	public Charisma getCharisma() {
		return charisma;
	}

	public void setCharisma(Charisma charisma) {
		this.charisma = charisma;
	}

	public Constitution getConsti() {
		return consti;
	}

	public void setConsti(Constitution consti) {
		this.consti = consti;
	}

	public Willpower getWill() {
		return will;
	}

	public void setWill(Willpower will) {
		this.will = will;
	}

	public List<Skill> getSkills() {
		return skills;
	}

	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}

	public List<Action> getCompletedActs() {
		return completedActs;
	}

	public void setCompletedActs(List<Action> completedActs) {
		this.completedActs = completedActs;
	}

	public List<Title> getTitles() {
		return titles;
	}

	public void setTitles(List<Title> titles) {
		this.titles = titles;
	}

	public Date getCreatedAt() {
		return createdAt;
	}
	
	public List<Action> getActsCreated() {
		return actsCreated;
	}

	public void setActsCreated(List<Action> actsCreated) {
		this.actsCreated = actsCreated;
	}

	public List<Template> getTemsCreated() {
		return temsCreated;
	}

	public void setTemsCreated(List<Template> temsCreated) {
		this.temsCreated = temsCreated;
	}

	public List<Title> getTitlesCreated() {
		return titlesCreated;
	}

	public void setTitlesCreated(List<Title> titlesCreated) {
		this.titlesCreated = titlesCreated;
	}

	public List<Skill> getSkillsCreated() {
		return skillsCreated;
	}

	public void setSkillsCreated(List<Skill> skillsCreated) {
		this.skillsCreated = skillsCreated;
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

	public int getDailyLimit() {
		return dailyLimit;
	}
	
	public void setDailyLimit(int dailyLimit) {
		this.dailyLimit = dailyLimit;
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
