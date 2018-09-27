package com.friendshipApp.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.friendshipApp.dao.FriendGraphMappingDetailsDAO;
import com.friendshipApp.dao.impl.FriendGraphMappingDetailsDAOimpl;
import com.friendshipApp.model.FriendGraphMappingDetails;
import com.friendshipApp.service.FriendGraphMappingDetailsService;
import com.friendshipApp.utils.BreathFirstSearch;
import com.friendshipApp.utils.FriendGraph;

@Service("friendGraphMappingDetailsService")
public class FriendGraphMappingDetailsServiceImpl implements
		FriendGraphMappingDetailsService {

	@Autowired
	FriendGraphMappingDetailsDAO friendGraphMappingDetailsDAO;

	public FriendGraphMappingDetails getGraphs() {

		Optional<FriendGraphMappingDetails> optionalProject = Optional
				.ofNullable(new FriendGraphMappingDetails());
		
		FriendGraphMappingDetails dbobject = null;

		List<FriendGraphMappingDetails> details = (List<FriendGraphMappingDetails>) friendGraphMappingDetailsDAO
				.findAll();
		
		if(null != details && !details.isEmpty())
		{
			
			dbobject = details.get(0);
		}
		
		
		if (null != dbobject) 
		{

			return dbobject;
			
		} else {
			
			return optionalProject.get();
		}

	}

	public FriendGraphMappingDetails AddGraphs(FriendGraphMappingDetails details) {

		details = friendGraphMappingDetailsDAO.save(details);

		return details;
	}

	public List<FriendGraphMappingDetails> getfullGraphs() {
		List<FriendGraphMappingDetails> fullGraph = (List<FriendGraphMappingDetails>) friendGraphMappingDetailsDAO
				.findAll();

		return fullGraph;
	}

	public Boolean checkFriendshipConnection(
			FriendGraphMappingDetails listOfcurrent, Integer friendId1,
			Integer friendId2) {
		Boolean friendStatus = Boolean.FALSE;

		BreathFirstSearch bfs = new BreathFirstSearch();

		FriendGraph<Integer> graph = new FriendGraph<Integer>(
				listOfcurrent.getFriendshipgraph(), 10);

		List<Integer> listOfFriends = bfs.BFS(friendId1, graph.retriveGraph(),
				10);

		friendStatus = listOfFriends.contains(friendId2);

		return friendStatus;
	}
	
	public List<Integer> fetchFriendListOnEmailId(
			FriendGraphMappingDetails listOfcurrent, Integer friendId1) {
		Boolean friendStatus = Boolean.FALSE;

		BreathFirstSearch bfs = new BreathFirstSearch();

		FriendGraph<Integer> graph = new FriendGraph<Integer>(
				listOfcurrent.getFriendshipgraph(), listOfcurrent.getFriendshipgraph().length);

		List<Integer> listOfFriends = bfs.BFS(friendId1, graph.retriveGraph(),
				10);		

		return listOfFriends;
	}
	public void resetTable() {
		friendGraphMappingDetailsDAO.deleteAll();
	}

	@Override
	public List<Integer> fetchSubscriberOnEmailId(
			FriendGraphMappingDetails listOfcurrent, Integer friendId1) {
		Boolean friendStatus = Boolean.FALSE;

		BreathFirstSearch bfs = new BreathFirstSearch();

		FriendGraph<Integer> graph = new FriendGraph<Integer>(
				listOfcurrent.getSubscribegraph(), listOfcurrent.getSubscribegraph().length);

		List<Integer> listOfsubscriber = bfs.BFS(friendId1, graph.retriveGraph(),
				10);		

		return listOfsubscriber;
	}

}
