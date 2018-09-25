package com.friendshipApp.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class BreathFirstSearch 
{
	public List<Integer> BFS(int s , LinkedList<Integer>[] adj , int V)
    {
        List<Integer> localList = new ArrayList<Integer>();
    	boolean flag = true;// Mark all the vertices as not visited(By default
        // set as false)
        boolean visited[] = new boolean[V];
 
        // Create a queue for BFS
        LinkedList<Integer> queue = new LinkedList<Integer>();
 
        // Mark the current node as visited and enqueue it
        visited[s]=true;
        queue.add(s);
 
        while (queue.size() != 0)
        {
            // Dequeue a vertex from queue and print it
            s = queue.poll();
            
            localList.add(s);
 
            // Get all adjacent vertices of the dequeued vertex s
            // If a adjacent has not been visited, then mark it
            // visited and enqueue it
            
            Iterator<Integer> i = adj[s].listIterator();
            
            while (i.hasNext())
            {
                if(flag)
                {
            	int n = i.next();
                if (!visited[n])
                {
                    visited[n] = true;
                    queue.add(n);
                }
                }else{
                	
                	break;
                }
            }
            flag = false;
        }
        return localList;
 
    }
	
public static void main(String[] args) {
	LinkedList<Integer>[] listOfEdges =  new LinkedList[10];
	FriendGraph<Integer> objFriendGraph = new FriendGraph<Integer>(listOfEdges , 10);
	objFriendGraph.addEdge(1,2);
	objFriendGraph.addEdge(1,3);
	objFriendGraph.addEdge(2,3);
	objFriendGraph.addEdge(3,4);
	
	listOfEdges = objFriendGraph.retriveGraph();
	BreathFirstSearch search = new BreathFirstSearch();
	
	System.out.println( search.BFS(7, listOfEdges, 10));
}
}
