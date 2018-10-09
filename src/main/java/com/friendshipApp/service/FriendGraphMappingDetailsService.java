package com.friendshipApp.service;

import java.util.List;

import com.friendshipApp.model.FriendGraphMappingDetails;
import com.friendshipApp.model.PersonProfile;

public interface FriendGraphMappingDetailsService 
{
	/*
	 * This method gives graph of friend mapping of all type connection , subscribe  , block.
	 * output : graph of FriendGraphMapping
	*/
	public FriendGraphMappingDetails getGraphs();
	
	/*
	 * This method is used to add graph in database.
	*/
	public FriendGraphMappingDetails AddGraphs(FriendGraphMappingDetails details);
	
	/*
	 * This method is used to get all graphs.
	*/	
	public List<FriendGraphMappingDetails> getfullGraphs();
	
	/*
	 * This method is used to check  connection between 2 person mail id weather they are friends or not
	*/
	public Boolean checkFriendshipConnection(FriendGraphMappingDetails listOfcurrent , Integer friendId1 , Integer friendId2);
	
	/*
	 * This method is used to delete all data from friendgraphmapping table.
	*/
	public void resetTable();
	
	/*
	 * This method is used to fetch list of friend of email id provided in input. 
	*/	
	public List<Integer> fetchFriendListOnEmailId(FriendGraphMappingDetails listOfcurrent, Integer friendId1);
	
	/*
	 * This method is used to fetch list of subscriber of email id provided in input. 
	*/
	public List<Integer> fetchSubscriberOnEmailId(FriendGraphMappingDetails listOfcurrent, Integer friendId1);
}
