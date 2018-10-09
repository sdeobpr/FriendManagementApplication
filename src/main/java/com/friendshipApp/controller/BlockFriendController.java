package com.friendshipApp.controller;

/*
 * This class is used as Controller for Blocking request between friends updates email.
*/
import java.util.LinkedList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.friendshipApp.RequestResponseDO.SubscribeRequestDO;
import com.friendshipApp.RequestResponseDO.SuccessResponseDO;
import com.friendshipApp.model.FriendGraphMappingDetails;
import com.friendshipApp.service.FriendGraphMappingDetailsService;
import com.friendshipApp.service.PersonProfileServices;
import com.friendshipApp.utils.ApplicationConstants;
import com.friendshipApp.utils.FriendGraph;

@RestController
@RequestMapping(path = "/friendshipApp")
public class BlockFriendController {

	private static Logger log = LogManager.getLogger();

	@Autowired
	PersonProfileServices personProfileServices;

	@Autowired
	FriendGraphMappingDetailsService friendGraphMappingDetailsService;

	@Autowired
	FriendGraphMappingDetails newConnection;
	/*
	 * 	This method is used to block friends Emails.
	*/
	@RequestMapping(value = "/blockfriend", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SuccessResponseDO> blockfriend(
			@RequestBody SubscribeRequestDO subscribeRequestDO) {
		SuccessResponseDO successResponseDO = new SuccessResponseDO();

		successResponseDO.setSuccess(Boolean.FALSE);

		LinkedList<Integer>[] adjlocal = new LinkedList[10];

		try {

			long friendOneId = personProfileServices
					.fetchProfileOnEmaiId(subscribeRequestDO.getRequestor());

			long friendSecondId = personProfileServices
					.fetchProfileOnEmaiId(subscribeRequestDO.getTarget());

			LinkedList<Integer>[] friendBlockingGraph = null;

			FriendGraph<Integer> graph1 = new FriendGraph<Integer>(adjlocal, 10);

			FriendGraphMappingDetails listOfcurrentmappings = friendGraphMappingDetailsService
					.getGraphs();

			Boolean friendshipstatus = friendGraphMappingDetailsService
					.checkFriendshipConnection(listOfcurrentmappings, new Long(
							friendOneId).intValue(), new Long(friendSecondId)
							.intValue());

			if (friendshipstatus) 
			{
				if (null != listOfcurrentmappings) 
				{
					friendBlockingGraph = listOfcurrentmappings
							.getBlockgraph();
				}

				if (null != friendBlockingGraph
						&& friendBlockingGraph.length != 0) {
					FriendGraph<Integer> localgraph = new FriendGraph<Integer>(
							friendBlockingGraph, 10);

					localgraph.addEdge(new Long(friendOneId).intValue(),
							new Long(friendSecondId).intValue());

					listOfcurrentmappings.setBlockgraph(localgraph
							.retriveGraph());

					
					/*// To remove Friend from subscription list if he is blocked friends. start
					LinkedList<Integer>[] friendSubscriptionGraph = listOfcurrentmappings.getSubscribegraph();
					
					FriendGraph<Integer> localsubscribgraph = new FriendGraph<Integer>(
							friendSubscriptionGraph, 10);
					localsubscribgraph.removeEdge(new Long(friendOneId).intValue(), new Long(
							friendSecondId).intValue());					

					listOfcurrentmappings.setSubscribegraph(localsubscribgraph
							.retriveGraph());
					// To remove Friend from subscription list if he is blocked friends. end
*/					
					friendGraphMappingDetailsService.AddGraphs(listOfcurrentmappings);
					
					successResponseDO.setSuccess(Boolean.TRUE);

					successResponseDO
							.setMessage(ApplicationConstants.FRIEND_BLOCKING_SUCCESS);
				} else {

					graph1.addEdge(new Long(friendOneId).intValue(), new Long(
							friendSecondId).intValue());

					listOfcurrentmappings.setBlockgraph(graph1.retriveGraph());

					// To remove Friend from subscription list if he is blocked friends. start
					/*LinkedList<Integer>[] friendSubscriptionGraph = listOfcurrentmappings.getSubscribegraph();
					
					FriendGraph<Integer> localsubscribgraph = new FriendGraph<Integer>(
							friendSubscriptionGraph, 10);
					
					localsubscribgraph.removeEdge(new Long(friendOneId).intValue(), new Long(
							friendSecondId).intValue());					

					listOfcurrentmappings.setSubscribegraph(localsubscribgraph
							.retriveGraph());*/
					// To remove Friend from subscription list if he is blocked friends. end
					
					friendGraphMappingDetailsService.AddGraphs(listOfcurrentmappings);

					successResponseDO.setSuccess(Boolean.TRUE);

					successResponseDO
							.setMessage(ApplicationConstants.FRIEND_BLOCKING_SUCCESS);
				}
			} else {
				successResponseDO.setSuccess(Boolean.FALSE);

				successResponseDO
						.setMessage(ApplicationConstants.FRIEND_BLOCKING_FAILURE_NO_SUBSCRIBE);
			}
		} catch (Exception exp) {

			successResponseDO.setSuccess(Boolean.FALSE);

			log.info("Error in retriving common friend ship connection with {} {} "
					+ ":"
					+ subscribeRequestDO.getRequestor()
					+ "and "
					+ subscribeRequestDO.getTarget() + exp.getMessage());
		}

		return new ResponseEntity<SuccessResponseDO>(successResponseDO,
				HttpStatus.OK);
	}
}
