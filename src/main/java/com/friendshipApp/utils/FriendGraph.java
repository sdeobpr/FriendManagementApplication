package com.friendshipApp.utils;


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
    // Function to add an edge into the graph
    public void addEdge(int v,T w)
    {
    	adjlocal[v].add(w);
    }
    
    public void removeEdge(int v,T w)
    {
    	adjlocal[v].remove(w);
    }
    
    public LinkedList<T>[] retriveGraph()
    {
    	return adjlocal;
    }
    
}
