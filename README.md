# FriendManagementApplication
This application is used to create friend connection , show all friends , show all common friends , Subscribe to friends update , block friends.

Technology Used  
1. Java J2ee  -  Used core java to write business logic. User Graphs to store friends ids and Breath First search to search friend connections , Subscription emails etc
2. Spring Boot - Spring boot supports micro service architecture for rapid development. Spring boot support easy for JPA integrations.
3. in memory H2 database.

##Database : 
###Database consist of 2 tables. 
	FS_PERSON_PROFILE : This table is used to store Person information.
	FS_PROFILE_MAPPING_DTL : This table is used to store Friend Connection mapping , subscription mapping , Block Mapping.

###Database schema diagram and database DDL scripts are on below path 

####List Of Rest End Points:
1.	Create person : 
a.	Path : /friendshipApp/ createPerson
b.	Input : This End Point accepts PersonProfile json object.	
Ex :       {
		“personName”:”Swapnil Deo”,
		“personEmailId”:swapnil_deo@hotmail.com”,
“contactNo”:”1234567890”	
			}
c.	Output : {
“Success”:”true”
  } 
2.	Create connection between 2 email ids: 
a.	Path : : /friendshipApp/createConnection
b.	Input : {
               “friends”: [“swapnil_deo@hotmail.com” ,”ammar.khan@gmail.com”]
               }
c.	Output : {
                 “Success”:”true”
               }
3.	Check Friend Ship Status : 
a.	Path : /friendshipApp/checkConnection
b.	Input : {
             “friends”: [“swapnil_deo@hotmail.com” ,”ammar.khan@gmail.com”]
}
c.	Output: {
“Success”:”true”
  }

4.	Retrieve Friends : 
a.	Path : /friendshipApp /retriveFriend
b.	Input : {
             “email”:”swapnil_deo@hotmail.com”
         }
c.	Output: {
  		“Success”: “true”,
                               “friends”:[ammar.khan@gmail.com , amol.patil@gmail.com],
                                “count”: “2”
                }
5.	Retrieve Common Friends :
a.	Path: /friendshipApp /retriveCommonFriends
b.	Input : {
               “friends”: [“swapnil_deo@hotmail.com“, “ammar.khan@gmail.com”]
}
c.	Output : {
                    “Success”: “true”,
                               “friends”:[ammar.khan@gmail.com , amol.patil@gmail.com],
                                “count”: “2”
               }

6.	Subscribe to Update from Email : 
a.	Path: /friendshipApp /subscribeToFriendUpdates
b.	Input : {
                 “requestor”:”swapnil_deo@hotmail.com”,
                  “target”: “ammar.khan.gmail.com”
}
c.	Output : {
                     “Success”:”true”
}
7.	All Email ids which can receive updates.
a.	Path : “/friendshipApp/allsubscruiber”
b.	Input : {
                “sender”:”swapnil_deo@hotmail.com”,	
                “text”: “Hello ammar.khan@gmail.com” 
}
c.	Output: {
                 “Success”:”true”,
                “recipients”:[“ammar.khan@gmail.com”],
                “count”: “1”
}

8.	Block User Email
a.	Path : / friendshipApp/blockfriend
b.	 Input : {
                  “requestor”:”swapnil_deo@hotmail.com”, 
                  “target”:”amol.patil@gmail.com”

}
c.	Output : { “Success”: “true”}

###Junit Test case are in below location :
	




