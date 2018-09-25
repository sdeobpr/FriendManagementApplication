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
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.friendshipApp.FriendShipAppApplication;
import com.friendshipApp.RequestResponseDO.MakeFriendsDO;
import com.friendshipApp.RequestResponseDO.SuccessResponseDO;
import com.friendshipApp.config.H2JpaConfig;
import com.friendshipApp.controller.CreateFriendConnectionController;
import com.friendshipApp.dao.FriendGraphMappingDetailsDAO;
import com.friendshipApp.dao.PersonProfileDAO;
import com.friendshipApp.model.FriendGraphMappingDetails;
import com.friendshipApp.model.PersonProfile;
import com.friendshipApp.service.FriendGraphMappingDetailsService;
import com.friendshipApp.service.PersonProfileServices;
import com.friendshipApp.utils.BreathFirstSearch;
import com.friendshipApp.utils.FriendGraph;

import static org.mockito.Matchers.any;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CreateFriendConnectionControllerTest 
{
	@InjectMocks
	CreateFriendConnectionController controller= new CreateFriendConnectionController();
	
	@Mock
	FriendGraphMappingDetailsService friendGraphMappingDetailsService;
	
	@Mock
	PersonProfileServices personProfileService;

	@Mock
	PersonProfileDAO personProfileDAO;
	
	@Mock
	FriendGraphMappingDetailsDAO friendGraphMappingDetailsDAO;
	
	@Mock 
	BreathFirstSearch breathFirstSearch;
	
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
		friendGraphMappingDetails.setFriendshipgraph(listOfEdges);	
		
		listOfFriends.add(1);
		
		listOfFriends.add(2);
	}
	
	@Test
	public void personcreateTest()
	{
		Mockito.when(personProfileDAO.save(personProfile)).thenReturn(personProfile);
		
		Mockito.when(personProfileService.createPerson(personProfile)).thenReturn(personProfile);
		
		Assert.assertEquals(suscessCase.getSuccess(), controller.createPerson(personProfile).getBody().getSuccess());
	}
	@Test
	public void makeFriendConnectionTest()
	{
		List<FriendGraphMappingDetails> mappingDetails = new ArrayList<FriendGraphMappingDetails>();
		
		mappingDetails.add(friendGraphMappingDetails);
		
		MakeFriendsDO makeFriendsDO = new MakeFriendsDO();
		
		List<String> friends = new ArrayList<String>();
		
		friends.add("swapnil_deo@hotmail.com");
		friends.add("ammar_khan@gmail.com");
		
		makeFriendsDO.setFriends(friends);	
		
		Mockito.when(personProfileService.fetchProfileOnEmaiId(makeFriendsDO.getFriends().get(0))).thenReturn(new Long(1));
		
		Mockito.when(personProfileService.fetchProfileOnEmaiId(makeFriendsDO.getFriends().get(1))).thenReturn(new Long(2));
		
		Mockito.when(friendGraphMappingDetailsService.getGraphs()).thenReturn(friendGraphMappingDetails);
		
		Assert.assertEquals(suscessCase.getSuccess(), controller.makeFriendConnection(makeFriendsDO).getBody().getSuccess());
		
	}
	@Test
	public void checkFriendshipConnectionTest()
	{
		List<FriendGraphMappingDetails> mappingDetails = new ArrayList<FriendGraphMappingDetails>();
		
		mappingDetails.add(friendGraphMappingDetails);
		
		MakeFriendsDO makeFriendsDO = new MakeFriendsDO();
		
		List<String> friends = new ArrayList<String>();
		
		friends.add("swapnil_deo@hotmail.com");
		friends.add("ammar_khan@gmail.com");
		
		makeFriendsDO.setFriends(friends);	
		
		Mockito.when(personProfileService.fetchProfileOnEmaiId(makeFriendsDO.getFriends().get(0))).thenReturn(new Long(1));
		
		Mockito.when(personProfileService.fetchProfileOnEmaiId(makeFriendsDO.getFriends().get(1))).thenReturn(new Long(2));
		
		Mockito.when(friendGraphMappingDetailsService.getGraphs()).thenReturn(friendGraphMappingDetails);
		
		Mockito.when(breathFirstSearch.BFS(1, listOfEdges, 10)).thenReturn(listOfFriends);
		
		Mockito.when(friendGraphMappingDetailsService.checkFriendshipConnection(friendGraphMappingDetails, 1, 2)).thenReturn(Boolean.TRUE);
		
		Assert.assertEquals(suscessCase.getSuccess(), controller.checkFriendshipConnection(makeFriendsDO).getBody().getSuccess());
	}
}
