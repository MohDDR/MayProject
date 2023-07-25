package com.moh.code.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moh.code.models.Title;
import com.moh.code.models.User;
import com.moh.code.repositories.TitleRepository;

@Service
public class TitleService {

	@Autowired
	TitleRepository titleRepo;
	
	public List<Title> allTitles(){
		
		return titleRepo.findAll();
	}
	
	public List<Title> findUsersTitles(User user){
		
		return titleRepo.findByUsersContaining(user);
	}
	
	public Title createTitle(Title title) {
		
		titleRepo.save(title);
		
		return title;
	}
	
	public Title findTitle(Long id) {
		
        Optional<Title> optTitle = titleRepo.findById(id);
        
        if(optTitle.isPresent()) {
        	
            return optTitle.get();
            
        } else {
        	
            return null;
            
        }
    }
	
	public List<Title> findByUsers(User user) {
		
		System.out.println("looking for " + user + " titles------");
		
		List<Title> titles = titleRepo.findByUsersContaining(user);
        
		System.out.println("here----- " + titles);
		
        return titles;
		
	}

	public Title updateTitle(Title title) {
		
		return titleRepo.save(title);
	}
	
	public void deleteTitle(Long id) {
		
		Optional<Title> optTitle = titleRepo.findById(id);
		
		if(optTitle.isPresent()) {
			
			titleRepo.deleteById(id);
		}
		
	}
}
