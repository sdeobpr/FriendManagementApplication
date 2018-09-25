package com.friendshipApp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.friendshipApp.RequestResponseDO.SuccessResponseDO;
import com.friendshipApp.dao.PersonProfileDAO;
import com.friendshipApp.model.PersonProfile;
import com.friendshipApp.service.impl.PersonProfileServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonProfileServicesTest 
{
	@Autowired
	PersonProfileServices personProfileService;
	
	@MockBean
	PersonProfileDAO personProfileDao;
	
	public static PersonProfile  personProfile = new PersonProfile();
	
	public static SuccessResponseDO  suscessCase = new SuccessResponseDO();
	
	public static SuccessResponseDO  failCase = new SuccessResponseDO();
	
	@BeforeClass
	public static void init()
	{
		personProfile.setPersonName("Swapnil");
		personProfile.setPersonEmailId("swapnil_deo@hotmail.com");
		personProfile.setContactNo("9975640577");
		personProfile.setPersonProfileId(1);
		
		suscessCase.setSuccess(Boolean.TRUE);
		failCase.setSuccess(Boolean.FALSE);
	}
	
	@Test
	public void createPersonTest()
	{
		Mockito.when(personProfileDao.save(personProfile)).thenReturn(personProfile);
		
		Assert.assertEquals(new Integer(1), personProfileService.createPerson(personProfile).getPersonProfileId());
	}
	
	@Test
	public void displayAllPersonTest()
	{
		List<PersonProfile> lisProfilers = new ArrayList<PersonProfile>();
 		
		lisProfilers.add(personProfile);
		
		Mockito.when(personProfileDao.findAll()).thenReturn(lisProfilers);
		
		Assert.assertEquals(lisProfilers.size(), personProfileService.displayAllPerson().size());
		
	}
	
	@Test
	public void fetchProfileOnEmaiIdTest()
	{
		Mockito.when(personProfileDao.findByProfileByEmailId("swapnil_deo@hotmail.com")).thenReturn(new Long(1));
		
		Assert.assertEquals(new Long(1), personProfileService.fetchProfileOnEmaiId("swapnil_deo@hotmail.com"));
		
	}
	
	@Test
	public void fetchProfileOnIdTest()
	{
		Optional<PersonProfile> optionalProject = Optional
				.ofNullable(personProfile);
		
		
		Mockito.when(personProfileDao.findById(new Integer(1))).thenReturn(optionalProject);
		
		Assert.assertEquals(new Integer(1), personProfileService.fetchProfileOnId(new Integer(1)).getPersonProfileId());
		
	}
	
}
