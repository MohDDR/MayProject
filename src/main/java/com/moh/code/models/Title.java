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
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="titles")

public class Title {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	
    private Long id;
    
    @NotBlank
    
    @Size(min=4, message="Tag name must be greater than 3 characters!")
    
    private String name;
    
    @NotBlank
    private String description;
    
    @OneToMany(mappedBy="action", fetch = FetchType.LAZY)
    
    private List<StatIncrement> statIncs;
    
    @ManyToMany(fetch = FetchType.LAZY)
    
    @JoinTable(
    		
            name = "titles_earned", 
            
            joinColumns = @JoinColumn(name = "title_id"), 
            
            inverseJoinColumns = @JoinColumn(name = "user_id")
        )
    
    private List<User> users;
    
    @ManyToMany(fetch = FetchType.LAZY)
    
    @JoinTable(
    		
            name = "titles_tags", 
            
            joinColumns = @JoinColumn(name = "title_id"), 
            
            inverseJoinColumns = @JoinColumn(name = "tag_id")
        )
    
    private List<Tag> tags;
    
    @ManyToOne(fetch = FetchType.LAZY)
    
    @JoinColumn(name="user_id")
    
    private User creator;
    
    @Column(updatable=false)
    
    @DateTimeFormat(pattern="yyyy-MM-dd")
    
    private Date createdAt;
    
    @DateTimeFormat(pattern="yyyy-MM-dd")
    
    private Date updatedAt;

    public Title() {}
    
	public Title(Long id, String name, String description, List<StatIncrement> statIncs, 
			
			List<User> users, List<Tag> tags, User creator) {

		this.id = id;
		
		this.name = name;
		
		this.description = description;
		
		this.statIncs = statIncs;
		
		this.users = users;
		
		this.tags = tags;
		
		this.creator = creator;;
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

	public List<StatIncrement> getStatIncs() {
		return statIncs;
	}

	public void setStatIncs(List<StatIncrement> statIncs) {
		this.statIncs = statIncs;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
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
    
	@PrePersist
    protected void onCreate(){
		
        this.createdAt = new Date();
    }
	
    @PreUpdate
    protected void onUpdate(){
    	
        this.updatedAt = new Date();
    }
    
}
