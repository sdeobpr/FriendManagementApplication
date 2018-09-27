package com.friendshipApp.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.friendshipApp.dao.PersonProfileDAO;
import com.friendshipApp.exception.TechnicalException;
import com.friendshipApp.model.FriendGraphMappingDetails;
import com.friendshipApp.model.PersonProfile;
import com.friendshipApp.service.PersonProfileServices;

@Service("personProfileService")
public class PersonProfileServiceImpl implements PersonProfileServices
{

	@Autowired
	PersonProfileDAO personProfileDAO;
	
	public PersonProfile createPerson(PersonProfile personProfile) 
	{
		
		personProfile  = personProfileDAO.save(personProfile);
		
		return personProfile;
	}

	public List<PersonProfile> displayAllPerson() {
		
		return (List<PersonProfile>) personProfileDAO.findAll();
	}

	public Long fetchProfileOnEmaiId(String EmailId) {
		
		long profileId =  personProfileDAO.findByProfileByEmailId(EmailId);
		
		return profileId;
	}

	public void resetTable() {
		personProfileDAO.deleteAll();		
	}

	public PersonProfile fetchProfileOnId(Integer personId) 
	{
		Optional<PersonProfile> optionalProject = Optional
				.ofNullable(new PersonProfile());
		
		Optional<PersonProfile> localPersonProfile = personProfileDAO.findById(personId);
		
		if(localPersonProfile.isPresent())
		{			
			return localPersonProfile.get();
		}else
		{
			return optionalProject.get();
		}	
		
	}

}
