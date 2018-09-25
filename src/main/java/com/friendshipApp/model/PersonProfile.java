package com.friendshipApp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "FS_PERSON_PROFILE")
@XmlRootElement
@SequenceGenerator(name = "seq_person", sequenceName = "seq_person", allocationSize = 1 )
public class PersonProfile
{
	@Id	
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "seq_person")
    @Column(name = "Person_Profile_Id")
    private Integer personProfileId;

    @Column(name = "Person_Name", length = 256)
    private String personName;

    @Column(name = "Person_EmailId", length = 256)
    private String personEmailId;

    @Column(name = "Contact_No", length = 256)
    private String contactNo;

	public Integer getPersonProfileId() {
		return personProfileId;
	}

	public void setPersonProfileId(Integer personProfileId) {
		this.personProfileId = personProfileId;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getPersonEmailId() {
		return personEmailId;
	}

	public void setPersonEmailId(String personEmailId) {
		this.personEmailId = personEmailId;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	
	
	
}
