package com.moh.code.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moh.code.models.Constitution;
import com.moh.code.models.User;
import com.moh.code.repositories.ConstitutionRepository;

@Service
public class ConstitutionService {

	@Autowired
	ConstitutionRepository conRepo;
	
	public void createCon( User user ) {
		
		Constitution con = new Constitution();
		
		con.setUser(user);
		
		conRepo.save(con);
	}
	
	public Constitution findCon(Long userId) {
		
        Optional<Constitution> optCon = conRepo.findByUserId(userId);
        
        if(optCon.isPresent()) {
        	
        	System.out.println("heres the found constitution " + optCon.get());
        	
            return optCon.get();
            
        } else {
        	
            return null;
            
        }
    }
	
	public Constitution updateCon(Constitution con) {
		
		return conRepo.save(con);
	}
	
	public void deleteCon(Long id) {
		
		Optional<Constitution> optCon = conRepo.findById(id);
		
		if(optCon.isPresent()) {
			
			conRepo.deleteById(id);
		}
		
	}
}

