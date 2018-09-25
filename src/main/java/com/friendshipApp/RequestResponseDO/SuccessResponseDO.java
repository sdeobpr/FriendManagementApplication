package com.friendshipApp.RequestResponseDO;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class SuccessResponseDO 
{
	private boolean Success;
	private String Message;
	
	public boolean getSuccess() {
		return Success;
	}

	public void setSuccess(boolean Success) {
		this.Success = Success;
	}

	public String getMessage() {
		return Message;
	}

	public void setMessage(String message) {
		Message = message;
	}
	
	
}
