package com.friendshipApp.model;

/*
 * This table is used store standard constant message of application.
*/

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "FS_STANDARD_MESSAGES")
public class StandardMessges 
{
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Standard_Message_Id", length = 256, updatable = false,
            nullable = false)
    private Integer standardMessageId;
	
	
	@Column(name = "Standard_Message_Code", length = 256)
    private String standardMessageCode;
	
	@Column(name = "Standard_Message_Code_Desc", length = 256)
    private String standardMessageCodeDesc;

	public Integer getStandardMessageId() {
		return standardMessageId;
	}

	public void setStandardMessageId(Integer standardMessageId) {
		this.standardMessageId = standardMessageId;
	}

	public String getStandardMessageCode() {
		return standardMessageCode;
	}

	public void setStandardMessageCode(String standardMessageCode) {
		this.standardMessageCode = standardMessageCode;
	}

	public String getStandardMessageCodeDesc() {
		return standardMessageCodeDesc;
	}

	public void setStandardMessageCodeDesc(String standardMessageCodeDesc) {
		this.standardMessageCodeDesc = standardMessageCodeDesc;
	}
	
	
	
}
