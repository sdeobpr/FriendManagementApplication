package com.friendshipApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.friendshipApp.model.FriendGraphMappingDetails;
import com.friendshipApp.model.PersonProfile;
import com.friendshipApp.service.FriendGraphMappingDetailsService;
import com.friendshipApp.service.PersonProfileServices;

@RestController
public class UtilsController 
{
	@Autowired	
	FriendGraphMappingDetailsService friendGraphMappingDetailsService;
	
	@Autowired 
	PersonProfileServices personProfileService;
	
	@RequestMapping(value = "/resetMapping", method = RequestMethod.GET)
	public void resetMappingTable()
	{
		friendGraphMappingDetailsService.resetTable();
	}
	
	@RequestMapping(value = "/resetPerson", method = RequestMethod.GET)
	public void resetPersonTable()
	{
		personProfileService.resetTable();
	}
	@RequestMapping(value = "/dispalyAllPerson", method = RequestMethod.GET)
	public List<PersonProfile> displayAllPerson() {
		return personProfileService.displayAllPerson();
	}
	@RequestMapping(value = "/dispalyAllConnection", method = RequestMethod.GET)
	public List<FriendGraphMappingDetails> displayAllConnection() {
		return friendGraphMappingDetailsService.getfullGraphs();
	}
}
