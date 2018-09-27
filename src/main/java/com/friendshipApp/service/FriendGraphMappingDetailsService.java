package com.friendshipApp.service;

import java.util.List;

import com.friendshipApp.model.FriendGraphMappingDetails;
import com.friendshipApp.model.PersonProfile;

public interface FriendGraphMappingDetailsService 
{

	public FriendGraphMappingDetails getGraphs();
	
	public FriendGraphMappingDetails AddGraphs(FriendGraphMappingDetails details);
	
	public List<FriendGraphMappingDetails> getfullGraphs();
	
	public Boolean checkFriendshipConnection(FriendGraphMappingDetails listOfcurrent , Integer friendId1 , Integer friendId2);
	
	public void resetTable();
	
	public List<Integer> fetchFriendListOnEmailId(FriendGraphMappingDetails listOfcurrent, Integer friendId1);
	
	public List<Integer> fetchSubscriberOnEmailId(FriendGraphMappingDetails listOfcurrent, Integer friendId1);
}
