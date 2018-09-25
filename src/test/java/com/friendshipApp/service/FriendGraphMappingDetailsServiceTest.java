package com.friendshipApp.service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.friendshipApp.dao.FriendGraphMappingDetailsDAO;
import com.friendshipApp.model.FriendGraphMappingDetails;
import com.friendshipApp.utils.BreathFirstSearch;
import com.friendshipApp.utils.FriendGraph;


@RunWith(SpringRunner.class)
@SpringBootTest
public class FriendGraphMappingDetailsServiceTest 
{
	@Autowired
	FriendGraphMappingDetailsService friendGraphMappingDetailsService;
	
	@MockBean
	FriendGraphMappingDetailsDAO friendGraphMappingDetailsDAO;
	
	@MockBean 
	BreathFirstSearch breathFirstSearch;
	
	public static LinkedList<Integer>[] listOfEdges =  new LinkedList[10];	
	
	public static List<Integer> listOfFriends = new ArrayList<Integer>();
	
	public static FriendGraphMappingDetails friendGraphMappingDetails = new FriendGraphMappingDetails();
	
	@BeforeClass
	public static void init()
	{
		
		FriendGraph<Integer> objFriendGraph = new FriendGraph<Integer>(listOfEdges , 10);
		objFriendGraph.addEdge(1,2);
		objFriendGraph.addEdge(1,3);
		
		listOfEdges = objFriendGraph.retriveGraph();		
		friendGraphMappingDetails.setFriendshipgraph(listOfEdges);		
		
		listOfFriends.add(1);
		
		listOfFriends.add(2);
		
	}
	
	@SuppressWarnings("deprecation")
	
	@Test
	public void getGraphsTest()
	{
		List<FriendGraphMappingDetails> mappingDetails = new ArrayList<FriendGraphMappingDetails>();
		
		mappingDetails.add(friendGraphMappingDetails);
		
		Mockito.when(friendGraphMappingDetailsDAO.findAll()).thenReturn(mappingDetails);
		
		Assert.assertEquals(mappingDetails.get(0).getFriendshipgraph().length, friendGraphMappingDetailsService.getGraphs().getFriendshipgraph().length);
		
		
	}
	
	@Test
	public void AddGraphsTest()
	{
		Mockito.when(friendGraphMappingDetailsDAO.save(friendGraphMappingDetails)).thenReturn(friendGraphMappingDetails);
		
		Assert.assertEquals(friendGraphMappingDetails.getFriendshipgraph().length, friendGraphMappingDetailsService.AddGraphs(friendGraphMappingDetails).getFriendshipgraph().length);
		
	}
	
	@Test
	public void getfullGraphsTest()
	{
		List<FriendGraphMappingDetails> mappingDetails = new ArrayList<FriendGraphMappingDetails>();
		
		mappingDetails.add(friendGraphMappingDetails);
		
		Mockito.when(friendGraphMappingDetailsDAO.findAll()).thenReturn(mappingDetails);
		
		Assert.assertEquals(mappingDetails.get(0).getFriendshipgraph().length, friendGraphMappingDetailsService.getfullGraphs().get(0).getFriendshipgraph().length);		
	}

	@Test
	public void checkFriendshipConnectionTrueTest()
	{
		Mockito.when(breathFirstSearch.BFS(1, listOfEdges, 10)).thenReturn(listOfFriends);
		
		Assert.assertEquals(Boolean.TRUE, friendGraphMappingDetailsService.checkFriendshipConnection(friendGraphMappingDetails, 1, 2));
		
	}
	@Test
	public void checkFriendshipConnectionFalseTest()
	{
		Mockito.when(breathFirstSearch.BFS(1, listOfEdges, 10)).thenReturn(listOfFriends);
		
		Assert.assertEquals(Boolean.TRUE, friendGraphMappingDetailsService.checkFriendshipConnection(friendGraphMappingDetails, 1, 3));
		
	}
	
	@Test
	public void fetchFriendListOnEmailIdSuccessTest()
	{
		List<Integer> listOfFriends = new ArrayList<Integer>();
		
		listOfFriends.add(1);
		listOfFriends.add(2);
		listOfFriends.add(3);
		
		Mockito.when(breathFirstSearch.BFS(1, listOfEdges, 10)).thenReturn(listOfFriends);
		
		Assert.assertEquals(listOfFriends.size(), friendGraphMappingDetailsService.fetchFriendListOnEmailId(friendGraphMappingDetails,1).size());
	}
	
	@Test
	public void fetchFriendListOnEmailIdFailTest()
	{
		List<Integer> listOfFriends = new ArrayList<Integer>();
		
		listOfFriends.add(1);
		listOfFriends.add(2);
		listOfFriends.add(3);
		
		Mockito.when(breathFirstSearch.BFS(1, listOfEdges, 10)).thenReturn(listOfFriends);
		
		Assert.assertEquals(1, friendGraphMappingDetailsService.fetchFriendListOnEmailId(friendGraphMappingDetails,4).size());
	}
}
