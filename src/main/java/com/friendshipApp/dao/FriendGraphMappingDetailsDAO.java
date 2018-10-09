package com.friendshipApp.dao;

/*
 * This interface is for performing all predefined database operation.
*/
import org.springframework.data.repository.CrudRepository;

import com.friendshipApp.model.FriendGraphMappingDetails;

public interface FriendGraphMappingDetailsDAO extends CrudRepository<FriendGraphMappingDetails, Long> 
{
	
}
