package com.friendshipApp.utils;

/*
 * This class created graph. We are creating graph as LinkedList of array.
*/
import java.util.LinkedList;


public class FriendGraph<T> 
{
	private int V;
	private LinkedList<T> adjlocal[]; //Adjacency Lists
	 
    @SuppressWarnings("unchecked")
	public FriendGraph(LinkedList<T>[] adj , int v)
    {
        V = v;
        this.adjlocal = adj ;
        for (int i=0; i<v; ++i)
        {
        	if(null == adjlocal[i] || adjlocal[i].size()==0)
        	{
        	adjlocal[i] = new LinkedList<T>();
        	}
        }
    } 
    /*
     * This method is used to add edge in graph
     */
    public void addEdge(int v,T w)
    {
    	adjlocal[v].add(w);
    }
    /*
     * This method is used to remove edge in graph
     */
    public void removeEdge(int v,T w)
    {
    	adjlocal[v].remove(w);
    }
    /*
     * This method is used to retrieve current graph.
     */
    public LinkedList<T>[] retriveGraph()
    {
    	return adjlocal;
    }
    
}
