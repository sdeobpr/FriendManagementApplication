package com.friendshipApp.RequestResponseDO;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class SubscribeRequestDO 
{
	private String requestor;
	private String target;
	
	public String getRequestor() {
		return requestor;
	}
	public void setRequestor(String requestor) {
		this.requestor = requestor;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	
	
}
