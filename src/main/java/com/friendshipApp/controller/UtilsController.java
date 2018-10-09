package com.friendshipApp.controller;

/*
 * This utility controller for house keeping of database person and friend connection table. 
*/
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
@RequestMapping(path = "/friendshipApp")
public class UtilsController 
{
	@Autowired	
	FriendGraphMappingDetailsService friendGraphMappingDetailsService;
	
	@Autowired 
	PersonProfileServices personProfileService;
	
	/*
	 * This method is used to delete all data of FS_PROFILE_MAPPING_DTL table.
	*/
	@RequestMapping(value = "/resetMapping", method = RequestMethod.GET)
	public void resetMappingTable()
	{
		friendGraphMappingDetailsService.resetTable();
	}
	/*
	 * This method is used to delete all data of FS_PERSON_PROFILE table.
	*/
	@RequestMapping(value = "/resetPerson", method = RequestMethod.GET)
	public void resetPersonTable()
	{
		personProfileService.resetTable();
	}
	/* 
	 * This method is used to display all person records.
	*/
	@RequestMapping(value = "/dispalyAllPerson", method = RequestMethod.GET)
	public List<PersonProfile> displayAllPerson() {
		return personProfileService.displayAllPerson();
	}
	/* 
	 * This method is used to display all friendship details records.
	*/
	@RequestMapping(value = "/dispalyAllConnection", method = RequestMethod.GET)
	public List<FriendGraphMappingDetails> displayAllConnection() {
		return friendGraphMappingDetailsService.getfullGraphs();
	}
}
