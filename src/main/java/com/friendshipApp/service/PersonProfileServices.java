package com.friendshipApp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.friendshipApp.model.PersonProfile;


public interface PersonProfileServices 
{
	public PersonProfile createPerson(PersonProfile personProfile);
	
	public List<PersonProfile> displayAllPerson();
	
	public Long fetchProfileOnEmaiId(String EmailId);
	
	public void resetTable();
	
	public PersonProfile fetchProfileOnId(Integer EmailId);
}
