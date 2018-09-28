package com.friendshipApp.controller;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.friendshipApp.RequestResponseDO.MakeFriendsDO;
import com.friendshipApp.RequestResponseDO.SubscribeRequestDO;
import com.friendshipApp.RequestResponseDO.SuccessResponseDO;
import com.friendshipApp.dao.FriendGraphMappingDetailsDAO;
import com.friendshipApp.dao.PersonProfileDAO;
import com.friendshipApp.model.FriendGraphMappingDetails;
import com.friendshipApp.service.FriendGraphMappingDetailsService;
import com.friendshipApp.service.PersonProfileServices;
import com.friendshipApp.utils.FriendGraph;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BlockFriendControllerTest 
{
	@InjectMocks
	BlockFriendController controller= new BlockFriendController();
	
	@Mock
	FriendGraphMappingDetailsService friendGraphMappingDetailsService;
	
	@Mock
	PersonProfileServices personProfileService;

	@Mock
	PersonProfileDAO personProfileDAO;
	
	@Mock
	FriendGraphMappingDetailsDAO friendGraphMappingDetailsDAO;
	
	SubscribeRequestDO subscribeRequestDO = new SubscribeRequestDO();
	
	public static LinkedList<Integer>[] listOfEdges =  new LinkedList[10];	
	
	public static List<Integer> listOfFriends = new ArrayList<Integer>();
	
	public static FriendGraphMappingDetails friendGraphMappingDetails = new FriendGraphMappingDetails();
	
	public static SuccessResponseDO  suscessCase = new SuccessResponseDO();
	
	public static SuccessResponseDO  failCase = new SuccessResponseDO();
	
	
	@BeforeClass
	public static void init()
	{	
		
		FriendGraph<Integer> objFriendGraph = new FriendGraph<Integer>(listOfEdges , 10);
		objFriendGraph.addEdge(1,2);
		objFriendGraph.addEdge(1,3);
		
		listOfEdges = objFriendGraph.retriveGraph();		
		friendGraphMappingDetails.setBlockgraph(listOfEdges);	
		
		listOfFriends.add(1);
		
		listOfFriends.add(2);
		
		suscessCase.setSuccess(Boolean.TRUE);
		failCase.setSuccess(Boolean.FALSE);
	}
	
	@Test
	public void blockFriendSuccessTest()
	{
		List<FriendGraphMappingDetails> mappingDetails = new ArrayList<FriendGraphMappingDetails>();
		
		mappingDetails.add(friendGraphMappingDetails);
		
		MakeFriendsDO makeFriendsDO = new MakeFriendsDO();
		
		List<String> friends = new ArrayList<String>();
		
		friends.add("swapnil_deo@hotmail.com");
		
		friends.add("ammar_khan@gmail.com");
		
		makeFriendsDO.setFriends(friends);	
		
		subscribeRequestDO.setRequestor("swapnil_deo@hotmail.com");
		
		subscribeRequestDO.setTarget("ammar_khan@gmail.com");
		
		Mockito.when(personProfileService.fetchProfileOnEmaiId(makeFriendsDO.getFriends().get(0))).thenReturn(new Long(1));
		
		Mockito.when(personProfileService.fetchProfileOnEmaiId(makeFriendsDO.getFriends().get(1))).thenReturn(new Long(2));
		
		Mockito.when(friendGraphMappingDetailsService.getGraphs()).thenReturn(friendGraphMappingDetails);
		
		Mockito.when(friendGraphMappingDetailsService.checkFriendshipConnection(friendGraphMappingDetailsService.getGraphs() , 1 ,2)).thenReturn(Boolean.TRUE);
		
		Assert.assertEquals(suscessCase.getSuccess(), controller.blockfriend(subscribeRequestDO).getBody().getSuccess()); 
		
	}
	@Test
	public void blockFriendFailTest()
	{
		List<FriendGraphMappingDetails> mappingDetails = new ArrayList<FriendGraphMappingDetails>();
		
		mappingDetails.add(friendGraphMappingDetails);
		
		MakeFriendsDO makeFriendsDO = new MakeFriendsDO();
		
		List<String> friends = new ArrayList<String>();
		
		friends.add("swapnil_deo@hotmail.com");
		
		friends.add("ammar_khan@gmail.com");
		
		makeFriendsDO.setFriends(friends);	
		
		subscribeRequestDO.setRequestor("swapnil_deo@hotmail.com");
		
		subscribeRequestDO.setTarget("ammar_khan@gmail.com");
		
		Mockito.when(personProfileService.fetchProfileOnEmaiId(makeFriendsDO.getFriends().get(0))).thenReturn(new Long(1));
		
		Mockito.when(personProfileService.fetchProfileOnEmaiId(makeFriendsDO.getFriends().get(1))).thenReturn(new Long(2));
		
		Mockito.when(friendGraphMappingDetailsService.getGraphs()).thenReturn(friendGraphMappingDetails);
		
		Mockito.when(friendGraphMappingDetailsService.checkFriendshipConnection(friendGraphMappingDetailsService.getGraphs() , 1 ,2)).thenReturn(Boolean.FALSE);
		
		Assert.assertEquals(failCase.getSuccess(), controller.blockfriend(subscribeRequestDO).getBody().getSuccess()); 
		
	}
}
