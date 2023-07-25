package com.moh.code.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moh.code.models.User;
import com.moh.code.models.Willpower;
import com.moh.code.repositories.WillpowerRepository;

@Service
public class WillpowerService {

	@Autowired
	WillpowerRepository willRepo;
	
	public void createWill( User user ) {
		
		Willpower will = new Willpower();
		
		will.setUser(user);
		
		willRepo.save(will);
	}
	
	public Willpower findWill(Long userId) {
		
        Optional<Willpower> optWill = willRepo.findByUserId(userId);
        
        if(optWill.isPresent()) {
        	
        	System.out.println("heres the found willpower " + optWill.get());
        	
            return optWill.get();
            
        } else {
        	
            return null;
            
        }
    }
	
	public Willpower updateWill(Willpower will) {
		
		return willRepo.save(will);
	}
	
	public void deleteWill(Long id) {
		
		Optional<Willpower> optWill = willRepo.findById(id);
		
		if(optWill.isPresent()) {
			
			willRepo.deleteById(id);
		}
		
	}
}
