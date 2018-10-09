package com.friendshipApp.dao;

/*
 * This interface is for performing all predefined database operation.
*/

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.friendshipApp.model.PersonProfile;

@Repository("personProfileDAO")
public interface PersonProfileDAO extends JpaRepository<PersonProfile, Integer> 
{
	@Query("select c.id from PersonProfile c where c.personEmailId = :personEmailId")
	public Long findByProfileByEmailId(@Param("personEmailId") String personEmailId );
}
