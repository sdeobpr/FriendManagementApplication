package com.friendshipApp.utils;
/*
 * This class is using breath first search algorithm to find nodes on graph. We are considering each friend as Node in graph.
*/
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

}
