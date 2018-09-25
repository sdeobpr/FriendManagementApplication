package com.friendshipApp.utils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.friendshipApp.utils.BreathFirstSearch;
import com.friendshipApp.utils.FriendGraph;


public class BreathFirstSearchTest 
{
			
	List<Integer> listOfOutput =  new ArrayList<Integer>();
	@SuppressWarnings("unchecked")
	LinkedList<Integer>[] listOfEdges =  new LinkedList[10];	
	BreathFirstSearch objOfBFS = new BreathFirstSearch();
	
	
	@Test
	public void TestBFS()
	{
		FriendGraph<Integer> objFriendGraph = new FriendGraph<Integer>(listOfEdges , 10);
		objFriendGraph.addEdge(1,2);
		objFriendGraph.addEdge(1,3);
		objFriendGraph.addEdge(2,3);
		objFriendGraph.addEdge(3,4);
		
		listOfEdges = objFriendGraph.retriveGraph();
		
		listOfOutput.add(1);
		listOfOutput.add(2);
		listOfOutput.add(3);
		Assert.assertEquals(listOfOutput, objOfBFS.BFS(1, listOfEdges, 10));
	}
	@Test
	public void TestBFSWithoutEdges()
	{
		FriendGraph<Integer> objFriendGraph = new FriendGraph<Integer>(listOfEdges , 10);
		listOfEdges = objFriendGraph.retriveGraph();		
		listOfOutput.add(1);		
		Assert.assertEquals(listOfOutput, objOfBFS.BFS(1, listOfEdges, 10));
	}
	
	
	
}
