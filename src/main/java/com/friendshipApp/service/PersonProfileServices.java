package com.friendshipApp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.friendshipApp.exception.TechnicalException;
import com.friendshipApp.model.PersonProfile;


public interface PersonProfileServices 
{
	/*
	 * This method is used to create person in database.
	*/
	public PersonProfile createPerson(PersonProfile personProfile);
	
	/*
	 * This method is used to fetch all  person in database.
	*/
	public List<PersonProfile> displayAllPerson();
	
	/*
	 * This method is used to fetch person id from database for provided email id.
	*/
	public Long fetchProfileOnEmaiId(String EmailId);
	
	/*
	 * This method is used to delete all person data in database.
	*/
	public void resetTable();
	
	/*
	 * This method is used to fetch person object from database based on email id.
	*/
	public PersonProfile fetchProfileOnId(Integer EmailId);
}
