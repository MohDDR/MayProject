package com.moh.code.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moh.code.models.Tag;
import com.moh.code.repositories.TagRepository;

@Service
public class TagService {

	@Autowired
	TagRepository tagRepo;
	
	public List<Tag> allTags(){
		
		return tagRepo.findAll();
	}
	
	public Tag createTag(Tag tag) {
		
		tagRepo.save(tag);
		
		return tag;
	}
	
	public Tag findTag(Long id) {
		
        Optional<Tag> optTag = tagRepo.findById(id);
        
        if(optTag.isPresent()) {
        	
            return optTag.get();
            
        } else {
        	
            return null;
            
        }
    }
	
	public Tag updateTag(Tag tag) {
		
		return tagRepo.save(tag);
	}
	
	public void deleteTag(Long id) {
		
		Optional<Tag> optTag = tagRepo.findById(id);
		
		if(optTag.isPresent()) {
			
			tagRepo.deleteById(id);
		}
		
	}
}
