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

import com.friendshipApp.RequestResponseDO.FriendsListDO;
import com.friendshipApp.RequestResponseDO.MakeFriendsDO;
import com.friendshipApp.RequestResponseDO.SuccessResponseDO;
import com.friendshipApp.dao.FriendGraphMappingDetailsDAO;
import com.friendshipApp.dao.PersonProfileDAO;
import com.friendshipApp.model.FriendGraphMappingDetails;
import com.friendshipApp.model.PersonProfile;
import com.friendshipApp.service.FriendGraphMappingDetailsService;
import com.friendshipApp.service.PersonProfileServices;
import com.friendshipApp.utils.FriendGraph;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RetriveFriendControllerTest 
{
	@InjectMocks
	RetriveFriendController controller= new RetriveFriendController();
	
	@Mock
	FriendGraphMappingDetailsService friendGraphMappingDetailsService;
	
	@Mock
	PersonProfileServices personProfileService;

	@Mock
	PersonProfileDAO personProfileDAO;
	
	@Mock
	FriendGraphMappingDetailsDAO friendGraphMappingDetailsDAO;
	
	FriendsListDO friendsListDO = new FriendsListDO();  
	
	public static PersonProfile  personProfile = new PersonProfile();
	
	public static SuccessResponseDO  suscessCase = new SuccessResponseDO();
	
	public static SuccessResponseDO  failCase = new SuccessResponseDO();
	
	public static LinkedList<Integer>[] listOfEdges =  new LinkedList[10];	
	
	public static List<Integer> listOfFriends = new ArrayList<Integer>();
	
	public static FriendGraphMappingDetails friendGraphMappingDetails = new FriendGraphMappingDetails();
	
	@BeforeClass
	public static void init()
	{
		personProfile.setPersonName("Swapnil");
		personProfile.setPersonEmailId("swapnil_deo@hotmail.com");
		personProfile.setContactNo("9975640577");
		personProfile.setPersonProfileId(1);
		
		suscessCase.setSuccess(Boolean.TRUE);
		failCase.setSuccess(Boolean.FALSE);
		
		FriendGraph<Integer> objFriendGraph = new FriendGraph<Integer>(listOfEdges , 10);
		objFriendGraph.addEdge(1,2);
		objFriendGraph.addEdge(1,3);
		
		listOfEdges = objFriendGraph.retriveGraph();		
		friendGraphMappingDetails.setSubscribegraph(listOfEdges);	
		
		listOfFriends.add(1);
		
		listOfFriends.add(2);
	}
	@Test
	public void retriveFrineds()
	{
		List<FriendGraphMappingDetails> mappingDetails = new ArrayList<FriendGraphMappingDetails>();
		
		mappingDetails.add(friendGraphMappingDetails);
		
		MakeFriendsDO makeFriendsDO = new MakeFriendsDO();
		
		List<String> friends = new ArrayList<String>();
		
		friends.add("swapnil_deo@hotmail.com");
		friends.add("ammar_khan@gmail.com");
		
		List<Integer> listOffriends = new ArrayList<Integer>();
		
		listOffriends.add(1);
		listOffriends.add(2);
		
		makeFriendsDO.setFriends(friends);
		
		Mockito.when(personProfileService.fetchProfileOnEmaiId("swapnil_deo@hotmail.com")).thenReturn(new Long(1));
		
		Mockito.when(friendGraphMappingDetailsService.getGraphs()).thenReturn(friendGraphMappingDetails);
		
		Mockito.when(friendGraphMappingDetailsService.fetchFriendListOnEmailId(friendGraphMappingDetails, 1)).thenReturn(listOffriends);
		
		Mockito.when(personProfileService.fetchProfileOnId(2)).thenReturn(personProfile);
		
		Assert.assertEquals(1, controller.retriveFrineds("swapnil_deo@hotmail.com").getBody().getFriends().size());		
	}
	
	@Test
	public void retriveCommonFrineds()
	{		
		MakeFriendsDO makeFriendsDO = new MakeFriendsDO();
		
		List<String> friends = new ArrayList<String>();
		
		friends.add("swapnil_deo@hotmail.com");
		
		friends.add("ammar_khan@gmail.com");
		
		makeFriendsDO.setFriends(friends);	
		
		List<Integer> listOffriends = new ArrayList<Integer>();
		
		listOffriends.add(1);
		
		listOffriends.add(2);
		
		Mockito.when(personProfileService.fetchProfileOnEmaiId(makeFriendsDO.getFriends().get(0))).thenReturn(new Long(1));
		
		Mockito.when(personProfileService.fetchProfileOnEmaiId(makeFriendsDO.getFriends().get(1))).thenReturn(new Long(2));
		
		Mockito.when(friendGraphMappingDetailsService.getGraphs()).thenReturn(friendGraphMappingDetails);
		
		Mockito.when(friendGraphMappingDetailsDAO.save(friendGraphMappingDetails)).thenReturn(friendGraphMappingDetails);
		
		Mockito.when(friendGraphMappingDetailsService.fetchFriendListOnEmailId(friendGraphMappingDetails, 1)).thenReturn(listOffriends);
		
		Mockito.when(friendGraphMappingDetailsService.fetchFriendListOnEmailId(friendGraphMappingDetails, 2)).thenReturn(listOffriends);
		
		Assert.assertEquals(failCase.getSuccess(), controller.retriveCommonFrineds(makeFriendsDO).getBody().getSuccess());
			
	}
}
