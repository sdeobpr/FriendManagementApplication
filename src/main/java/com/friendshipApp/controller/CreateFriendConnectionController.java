package com.friendshipApp.controller;

/*
 * This class is used as Controller for create person(i.e Friend) , make friend ship connection between 2 persons , check friend connection between 2 person.
*/

import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.friendshipApp.RequestResponseDO.MakeFriendsDO;
import com.friendshipApp.RequestResponseDO.SuccessResponseDO;
import com.friendshipApp.model.FriendGraphMappingDetails;
import com.friendshipApp.model.PersonProfile;
import com.friendshipApp.service.FriendGraphMappingDetailsService;
import com.friendshipApp.service.PersonProfileServices;
import com.friendshipApp.utils.ApplicationConstants;
import com.friendshipApp.utils.FriendGraph;

@RestController
@EnableAutoConfiguration
@RequestMapping(path = "/friendshipApp")
public class CreateFriendConnectionController 
{
	private static Logger log = LogManager.getLogger();
	
	@Autowired
	PersonProfileServices personProfileService;

	@Autowired
	FriendGraphMappingDetailsService friendGraphMappingDetailsService;

	@Autowired
	FriendGraphMappingDetails newConnection;
	
	/*
	 * This method is used to create person in database. It accepts Person's JSON as input and provide SuccessResoseDO as output.
	*/
	
	@RequestMapping(path = "/createPerson", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody	
	public ResponseEntity<SuccessResponseDO> createPerson(
			@RequestBody PersonProfile personProfile) {
		
		SuccessResponseDO successResponseDO = new SuccessResponseDO();
		try
		{
		
		PersonProfile resultpersonProfile = personProfileService
				.createPerson(personProfile);

		if (resultpersonProfile.getPersonProfileId() > 0) {
			
			successResponseDO.setSuccess(Boolean.TRUE);			

		} else 
		{
			successResponseDO.setSuccess(Boolean.FALSE);
			
		}
		}catch(Exception exp)
		{
			log.info("Error in Creating Person {}  " +"-"+personProfile.getPersonEmailId()+":"+exp.getMessage());
			successResponseDO.setSuccess(Boolean.FALSE);
		}
		return new ResponseEntity<SuccessResponseDO>(successResponseDO,HttpStatus.OK);
	}

	
	/*
	 * This method is used to create friendship connection between 2 person. It accepts Friend list JSON as input and provide SuccessResoseDO as output.
	*/
	@RequestMapping(path = "/createConnection",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<SuccessResponseDO>  makeFriendConnection(@RequestBody MakeFriendsDO makeFriendsDO)
    {
		SuccessResponseDO successResponseDO = new SuccessResponseDO();
		
		LinkedList<Integer>[] adjlocal = new LinkedList[10];
		
		try
		{
			long friendOneId = personProfileService.fetchProfileOnEmaiId(makeFriendsDO.getFriends().get(0));
			
			long friendSecondId = personProfileService.fetchProfileOnEmaiId(makeFriendsDO.getFriends().get(1));
			
			LinkedList<Integer>[] friendShipGraph = null;
			
			FriendGraph<Integer> graph1 = new FriendGraph<Integer>(adjlocal, 10);		
			
			FriendGraphMappingDetails listOfcurrent = friendGraphMappingDetailsService.getGraphs();		
			
			if(null != listOfcurrent)
			{
				friendShipGraph  = listOfcurrent.getFriendshipgraph();
			}	
			if(null !=friendShipGraph && friendShipGraph.length!=0)
			{				
				FriendGraph<Integer> localgraph = new FriendGraph<Integer>(friendShipGraph, 10);	
					
				localgraph.addEdge(new Long(friendOneId).intValue(), new Long(friendSecondId).intValue());
				
				localgraph.addEdge(new Long(friendSecondId).intValue(), new Long(friendOneId).intValue());
				
				listOfcurrent.setFriendshipgraph(localgraph.retriveGraph());
				
				friendGraphMappingDetailsService.AddGraphs(listOfcurrent);
				
				successResponseDO.setSuccess(Boolean.TRUE);
				
				successResponseDO.setMessage(ApplicationConstants.FRIEND_CONNECITION_SUCCESS);
					
			}else
			{
					
				graph1.addEdge(new Long(friendOneId).intValue(), new Long(friendSecondId).intValue());
				
				graph1.addEdge(new Long(friendSecondId).intValue(), new Long(friendOneId).intValue());
					
				newConnection.setFriendshipgraph(graph1.retriveGraph());
					
				friendGraphMappingDetailsService.AddGraphs(newConnection);
				
				successResponseDO.setSuccess(Boolean.TRUE);
				
				successResponseDO.setMessage(ApplicationConstants.FRIEND_CONNECITION_SUCCESS);
			}
		}catch(Exception exp)
		{
			log.info("Error in making friend ship connection in {} {} "+":"+ makeFriendsDO.getFriends().get(0) + "and "+makeFriendsDO.getFriends().get(1)+exp.getMessage());
			
			successResponseDO.setSuccess(Boolean.FALSE);
			
			successResponseDO.setMessage(ApplicationConstants.FRIEND_CONNECITION_FAIL);
		}
		return new ResponseEntity<SuccessResponseDO>( successResponseDO ,HttpStatus.OK);
    }
	/*
	 * This method is used to check friendship connection between 2 person. It accepts Friend list JSON as input and provide SuccessResoseDO as output.
	*/
	
	@RequestMapping(path = "/checkConnection", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<SuccessResponseDO> checkFriendshipConnection(
			@RequestBody MakeFriendsDO makeFriendsDO) 
			{
		SuccessResponseDO successResponseDO = new SuccessResponseDO();

		try{
			long friendOneId = personProfileService
					.fetchProfileOnEmaiId(makeFriendsDO.getFriends().get(0));
	
			long friendSecondId = personProfileService
					.fetchProfileOnEmaiId(makeFriendsDO.getFriends().get(1));
	
			FriendGraphMappingDetails listOfcurrent = friendGraphMappingDetailsService
					.getGraphs();
	
			Boolean friendshipstatus = friendGraphMappingDetailsService
					.checkFriendshipConnection(listOfcurrent, new Long(friendOneId)
							.intValue(), new Long(friendSecondId).intValue());
	
			successResponseDO.setSuccess(friendshipstatus);
			
		}catch(Exception exp)
		{
			log.info("Error in checking friend ship connection with {} {} "+":"+ makeFriendsDO.getFriends().get(0) + "and "+makeFriendsDO.getFriends().get(1)+exp.getMessage());
			
			successResponseDO.setSuccess(Boolean.FALSE);
			
			successResponseDO.setMessage(ApplicationConstants.FRIEND_CONNECITION_FAIL);
		}
		return new ResponseEntity<SuccessResponseDO>(successResponseDO,
				HttpStatus.OK);
	}

	
}
