package com.friendshipApp.model;

/*
 * This table is used to store the all business exception message.
*/
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "FS_BUSINESS_EXCEPTION_CODES")
@SequenceGenerator(name = "seq_business_exception", sequenceName = "seq_business_exception", initialValue = 1, allocationSize = 1)
public class BusinessExceptionDetails 
{
	@Id
    @GeneratedValue(generator = "seq_business_exception")
    @Column(name = "businessExceptionId", length = 256, updatable = false,
            nullable = false)
    private Integer businessExceptionId;
	
	@Column(name = "Business_Exception_Code", length = 256)
    private String businessExceptionCode;
	
	@Column(name = "Business_Exception_Code_Desc", length = 256)
    private String businessExceptionCodeDesc;

	public Integer getBusinessExceptionId() {
		return businessExceptionId;
	}

	public void setBusinessExceptionId(Integer businessExceptionId) {
		this.businessExceptionId = businessExceptionId;
	}

	public String getBusinessExceptionCode() {
		return businessExceptionCode;
	}

	public void setBusinessExceptionCode(String businessExceptionCode) {
		this.businessExceptionCode = businessExceptionCode;
	}

	public String getBusinessExceptionCodeDesc() {
		return businessExceptionCodeDesc;
	}

	public void setBusinessExceptionCodeDesc(String businessExceptionCodeDesc) {
		this.businessExceptionCodeDesc = businessExceptionCodeDesc;
	}
	
	
}
