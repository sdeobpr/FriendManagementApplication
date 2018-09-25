package com.friendshipApp.utils;

public interface ApplicationConstants 
{
	String FRIEND_CONNECITION_SUCCESS = "FriendShip Connection Successfully connected.";
	
	String FRIEND_CONNECITION_FAIL = "FriendShip Connection Failure.";
	
	String FRIEND_SUBSCRITPION_SUCCESS = "You are succesfully subscribe to Email Id.";
	
	String FRIEND_SUBSCRITPION_FAILURE_NO_FRIENDS = "Your are not sucscribe to Email as you are not connected to Email Id.";
	
	String CHECK_CONNECTION_SUCCESS = "Your both are friends.";
	
	String CHECK_CONNECTION_FAILURE="Your both are not friends.";
	
	String FRIEND_SUBSCRIBE_SUCCESS = "Your are successfully subscribe to updates.";
	
	String FRIEND_SUBSCRIBE_FAILURE = "Your are not subscribe to Email Id";
	
	String FRIEND_BLOCKING_SUCCESS = "Your are successfully block Email Id";
	
	String FRIEND_BLOCKING_FAILURE = "Friend Blocking failure."; 
	
	String FRIEND_BLOCKING_FAILURE_NO_SUBSCRIBE = "You can not block to Email Id as you are not subscribed.";
	
	String EX_FRIEND_CONNECTION= "There is some error in creating friend connection.";
	
	String EX_CHECKING_FRIEND_CONNECTION= "There is some error in checking friend connection.";
	
	String EX_RETRIVE_FRINDS_LIST= "There is some error in retriving friends list.";
	
	String EX_RETRIVE_COMMON_FRINDS_LIST= "There is some error in retriving common friends list.";
	
	String EX_SUBSCRIBE_TO_EMAIL = "There is some error in subscribing to friend update.";
	
	String EX_BLOCK_TO_EMAIL = "There is some error in blocking to friend update.";
}
