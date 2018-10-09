package com.friendshipApp.RequestResponseDO;

/*
 * This is input data object class which take friend email id whose data user wants to retrieve.
*/
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class RetriveFriendDO 
{
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
