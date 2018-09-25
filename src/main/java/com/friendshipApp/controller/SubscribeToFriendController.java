package com.friendshipApp.controller;

import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.friendshipApp.RequestResponseDO.SubscribeRequestDO;
import com.friendshipApp.RequestResponseDO.SuccessResponseDO;
import com.friendshipApp.model.FriendGraphMappingDetails;
import com.friendshipApp.service.FriendGraphMappingDetailsService;
import com.friendshipApp.service.PersonProfileServices;
import com.friendshipApp.utils.ApplicationConstants;
import com.friendshipApp.utils.FriendGraph;

@RestController
public class SubscribeToFriendController 
{
	@Autowired 
	PersonProfileServices personProfileServices;
	
	@Autowired
	FriendGraphMappingDetailsService friendGraphMappingDetailsService;
	
	@Autowired
	FriendGraphMappingDetails newConnection;
	
	@RequestMapping(value = "subscribeToFriendUpdates" ,method = RequestMethod.POST )
	public ResponseEntity<SuccessResponseDO> subscribeToFriendupdates(@RequestBody SubscribeRequestDO subscribeRequestDO)
	 {
		SuccessResponseDO successResponseDO = new SuccessResponseDO();
		
		successResponseDO.setSuccess(Boolean.FALSE);
		
		LinkedList<Integer>[] adjlocal = new LinkedList[10];
		
		long friendOneId = personProfileServices.fetchProfileOnEmaiId(subscribeRequestDO.getRequestor());
			
		long friendSecondId = personProfileServices.fetchProfileOnEmaiId(subscribeRequestDO.getTarget());
		
		LinkedList<Integer>[] friendSubscriptionGraph = null;
		
		FriendGraph<Integer> graph1 = new FriendGraph<Integer>(adjlocal, 10);		
		
		FriendGraphMappingDetails listOfcurrentmappings = friendGraphMappingDetailsService.getGraphs();
		
		Boolean friendshipstatus = friendGraphMappingDetailsService.checkFriendshipConnection(listOfcurrentmappings, new Long(friendOneId).intValue(), new Long(friendSecondId).intValue());		
		
		if(friendshipstatus)
		{
				if(null != listOfcurrentmappings)
				{
					friendSubscriptionGraph  = listOfcurrentmappings.getSubscribegraph();
				}
				 
				if(null !=friendSubscriptionGraph && friendSubscriptionGraph.length!=0)
				{				
					FriendGraph<Integer> localgraph = new FriendGraph<Integer>(friendSubscriptionGraph, 10);	
						
					localgraph.addEdge(new Long(friendOneId).intValue(), new Long(friendSecondId).intValue());
					
					listOfcurrentmappings.setSubscribegraph(localgraph.retriveGraph());
					
					friendGraphMappingDetailsService.AddGraphs(listOfcurrentmappings);
						
					successResponseDO.setSuccess(Boolean.TRUE);
					
					successResponseDO.setMessage(ApplicationConstants.FRIEND_SUBSCRITPION_SUCCESS);
				}else
				{
						
					graph1.addEdge(new Long(friendOneId).intValue(), new Long(friendSecondId).intValue());
						
					newConnection.setSubscribegraph(graph1.retriveGraph());
						
					friendGraphMappingDetailsService.AddGraphs(newConnection);
					
					successResponseDO.setSuccess(Boolean.TRUE);
					
					successResponseDO.setMessage(ApplicationConstants.FRIEND_SUBSCRITPION_SUCCESS);
				}
		}else{
			successResponseDO.setSuccess(Boolean.FALSE);
			
			successResponseDO.setMessage(ApplicationConstants.FRIEND_SUBSCRITPION_FAILURE_NO_FRIENDS);
		}
		
		return new ResponseEntity<SuccessResponseDO>( successResponseDO ,HttpStatus.OK);
	 }
	
}
