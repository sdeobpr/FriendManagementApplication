package com.friendshipApp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.friendshipApp.RequestResponseDO.FriendsListDO;
import com.friendshipApp.RequestResponseDO.MakeFriendsDO;
import com.friendshipApp.RequestResponseDO.RetriveFriendDO;
import com.friendshipApp.model.FriendGraphMappingDetails;
import com.friendshipApp.service.FriendGraphMappingDetailsService;
import com.friendshipApp.service.PersonProfileServices;

@RestController
@RequestMapping(path = "/friendshipApp")
public class RetriveFriendController 
{
	private static Logger log = LogManager.getLogger();
	
	@Autowired	
	PersonProfileServices personProfileService;
	
	@Autowired 
	FriendGraphMappingDetailsService friendGraphMappingDetailsService;	
			
	@Autowired
	FriendGraphMappingDetails newConnection;
	
	@RequestMapping(value = "/retriveFriend" , method = RequestMethod.POST)
	public ResponseEntity<FriendsListDO> retriveFrineds(@RequestBody RetriveFriendDO retriveFriendDO)
	{
		FriendsListDO friendsListDO = new FriendsListDO();
		
		List<String> listOfFriends = new ArrayList<String>();
		
		try
		{
		long friendOneId = personProfileService.fetchProfileOnEmaiId(retriveFriendDO.getEmail());
		
		FriendGraphMappingDetails listOfcurrent = friendGraphMappingDetailsService.getGraphs();
		
		List<Integer> listOfFriendIds  = friendGraphMappingDetailsService.fetchFriendListOnEmailId(listOfcurrent, new Long(friendOneId).intValue());
		
		listOfFriendIds.removeIf(s -> s == friendOneId);
		
		for(Integer localIds: listOfFriendIds)
		{			
			listOfFriends.add(personProfileService.fetchProfileOnId(localIds).getPersonEmailId());
		}
		
		if(listOfFriends.size()>0)
		{
			friendsListDO.setSuccess(Boolean.TRUE);
			friendsListDO.setFriends(listOfFriends);
			friendsListDO.setCount(listOfFriends.size());
			
		}else{
			friendsListDO.setSuccess(Boolean.FALSE);			
		}
		}catch(Exception exp)
		{
			log.info("Error in retriving friend ship connection of {} "+":"+ retriveFriendDO.getEmail() +"::"+exp.getMessage());
			
			friendsListDO.setSuccess(Boolean.FALSE);
		}
		
		return new ResponseEntity<FriendsListDO>( friendsListDO ,HttpStatus.OK);
	}
	@RequestMapping(value = "/retriveCommonFriends" , method = RequestMethod.POST)
	public ResponseEntity<FriendsListDO>  retriveCommonFrineds(@RequestBody MakeFriendsDO makeFriendsDO)
	{
		FriendsListDO friendsListDO = new FriendsListDO();
		
		List<String> listOfFriends = new ArrayList<String>();
		
		try
		{
		long friendOneId = personProfileService.fetchProfileOnEmaiId(makeFriendsDO.getFriends().get(0));
		
		long friendSecondId = personProfileService.fetchProfileOnEmaiId(makeFriendsDO.getFriends().get(1));
		
		FriendGraphMappingDetails listOfcurrent = friendGraphMappingDetailsService.getGraphs();
		
		List<Integer> listOfFriendIdsOfPerson1  = friendGraphMappingDetailsService.fetchFriendListOnEmailId(listOfcurrent, new Long(friendOneId).intValue());
		
		List<Integer> listOfFriendIdsOfPerson2  = friendGraphMappingDetailsService.fetchFriendListOnEmailId(listOfcurrent, new Long(friendSecondId).intValue());
		
		listOfFriendIdsOfPerson1.removeIf(s -> s == friendOneId);
		
		listOfFriendIdsOfPerson2.removeIf(s -> s == friendSecondId);
		
		List<Integer> listOfCommonFriends = listOfFriendIdsOfPerson1
										   .stream()
										   .filter(listOfFriendIdsOfPerson2::contains)
										   .collect(Collectors.toList());
		 
		for(Integer localIds: listOfCommonFriends)
		{			
			listOfFriends.add(personProfileService.fetchProfileOnId(localIds).getPersonEmailId());
		}
		
		if(listOfFriends.size()>0)
		{
			friendsListDO.setSuccess(Boolean.TRUE);
			friendsListDO.setFriends(listOfFriends);
			friendsListDO.setCount(listOfFriends.size());
			
		}else{
			friendsListDO.setSuccess(Boolean.FALSE);			
		}
		}catch(Exception exp)
		{
			friendsListDO.setSuccess(Boolean.FALSE);
			
			log.info("Error in retriving common friend ship connection with {} {} "+":"+ makeFriendsDO.getFriends().get(0) + "and "+makeFriendsDO.getFriends().get(1)+exp.getMessage());
		}
		return new ResponseEntity<FriendsListDO>( friendsListDO ,HttpStatus.OK);
	}
}
