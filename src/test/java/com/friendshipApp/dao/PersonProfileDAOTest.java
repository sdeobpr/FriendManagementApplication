package com.friendshipApp.dao;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.friendshipApp.RequestResponseDO.SuccessResponseDO;
import com.friendshipApp.model.PersonProfile;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PersonProfileDAOTest 
{
	@Autowired
	PersonProfileDAO personProfileDAO;
	
	@Autowired
	TestEntityManager testEntityManager;
	
	public static PersonProfile  personProfile = new PersonProfile();
	
	public static SuccessResponseDO  suscessCase = new SuccessResponseDO();
	
	public static SuccessResponseDO  failCase = new SuccessResponseDO();
	
	@BeforeClass
	public static void init()
	{
		personProfile.setPersonName("Swapnil");
		personProfile.setPersonEmailId("swapnil_deo@hotmail.com");
		personProfile.setContactNo("9975640577");
		//personProfile.setPersonProfileId(1);
		
		suscessCase.setSuccess(Boolean.TRUE);
		failCase.setSuccess(Boolean.FALSE);
	}
	
	@Test
	public void createPersonTest()
	{
		Assert.assertEquals(new Integer(1), personProfileDAO.save(personProfile).getPersonProfileId());
		
	}
	
	
}
