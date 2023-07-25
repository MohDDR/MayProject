package com.moh.code.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moh.code.models.Charisma;
import com.moh.code.models.User;
import com.moh.code.repositories.CharismaRepository;

@Service
public class CharismaService {

	@Autowired
	CharismaRepository chaRepo;
	
	public void createCha( User user ) {
		
		Charisma cha = new Charisma();
		
		cha.setUser(user);
		
		chaRepo.save(cha);
	}
	
	public Charisma findCha(Long userId) {
		
        Optional<Charisma> optCha = chaRepo.findByUserId(userId);
        
        if(optCha.isPresent()) {
        	
        	System.out.println("heres the found charisma " + optCha.get());
        	
            return optCha.get();
            
        } else {
        	
            return null;
            
        }
    }
	
	public Charisma updateCha(Charisma cha) {
		
		return chaRepo.save(cha);
	}
	
	public void deleteCha(Long id) {
		
		Optional<Charisma> optCha = chaRepo.findById(id);
		
		if(optCha.isPresent()) {
			
			chaRepo.deleteById(id);
		}
		
	}
}
