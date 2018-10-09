package com.friendshipApp.controller;


/*
 * This class is used as Controller for subscribe friends.
*/
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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
import com.friendshipApp.RequestResponseDO.SubscribeRequestDO;
import com.friendshipApp.RequestResponseDO.SubscriberDO;
import com.friendshipApp.RequestResponseDO.SubscriptionListDO;
import com.friendshipApp.RequestResponseDO.SuccessResponseDO;
import com.friendshipApp.model.FriendGraphMappingDetails;
import com.friendshipApp.service.FriendGraphMappingDetailsService;
import com.friendshipApp.service.PersonProfileServices;
import com.friendshipApp.utils.ApplicationConstants;
import com.friendshipApp.utils.FriendGraph;

@RestController
@RequestMapping(path = "/friendshipApp")
public class SubscribeToFriendController 
{
	private static Logger log = LogManager.getLogger();
	
	@Autowired 
	PersonProfileServices personProfileServices;
	
	@Autowired
	FriendGraphMappingDetailsService friendGraphMappingDetailsService;
	
	@Autowired
	FriendGraphMappingDetails newConnection;
	
	/*
	 * 	This method is used to subscribe friends updates.
	*/
	
	@RequestMapping(value = "subscribeToFriendUpdates" ,method = RequestMethod.POST )
	public ResponseEntity<SuccessResponseDO> subscribeToFriendupdates(@RequestBody SubscribeRequestDO subscribeRequestDO)
	{
		SuccessResponseDO successResponseDO = new SuccessResponseDO();
		
		successResponseDO.setSuccess(Boolean.FALSE);
		
		LinkedList<Integer>[] adjlocal = new LinkedList[10];
		
		try {
		
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
		}catch(Exception exp ){
			
			successResponseDO.setSuccess(Boolean.FALSE);
			
			log.info("Error in retriving common friend ship connection with {} {} "+":"+ subscribeRequestDO.getRequestor() + "and "+ subscribeRequestDO.getTarget()+exp.getMessage());
		}
		return new ResponseEntity<SuccessResponseDO>( successResponseDO ,HttpStatus.OK);
	}
	
	/*
	 * 	This method is used to find friends who are subscribed for friends updates.
	*/
	
	@RequestMapping(value = "/updateSubscriber" ,method = RequestMethod.POST )	
	public ResponseEntity<SubscriptionListDO> subscriberforUpdated(@RequestBody SubscriberDO subscriberDO)
	{
		List<String> listOfSubscriberEmails = new ArrayList<String>();
		
		SubscriptionListDO subscriptionListDO = new SubscriptionListDO();
		try{
			
			long friendOneId = personProfileServices.fetchProfileOnEmaiId(subscriberDO.getSender());
			
			FriendGraphMappingDetails listOfcurrent = friendGraphMappingDetailsService.getGraphs();
			
			List<Integer> listOfSubscriber  = friendGraphMappingDetailsService.fetchSubscriberOnEmailId(listOfcurrent, new Long(friendOneId).intValue());
			
			listOfSubscriber.removeIf(s -> s == friendOneId);
			
			for(Integer localIds: listOfSubscriber)
			{			
				listOfSubscriberEmails.add(personProfileServices.fetchProfileOnId(localIds).getPersonEmailId());
			}
			if(listOfSubscriberEmails.size()>0)
			{
				subscriptionListDO.setSuccess(Boolean.TRUE);
				subscriptionListDO.setRecipients(listOfSubscriberEmails);
				subscriptionListDO.setCount(listOfSubscriberEmails.size());
				
			}else{
				subscriptionListDO.setSuccess(Boolean.FALSE);			
			}
			
			
			
		}catch(Exception exp)
		{
			log.info("Error in retriving scriber info of friend ship connection of {} "+":"+ subscriberDO.getSender() +"::"+exp.getMessage());
			
			subscriptionListDO.setSuccess(Boolean.FALSE);
		}
				
		return new ResponseEntity<SubscriptionListDO>( subscriptionListDO ,HttpStatus.OK);
	}
	
	
	
}
