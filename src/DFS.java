//Daniel Cohen 203850029 Ben Efrat 305773806

import java.util.ArrayList;
import java.util.List;


public class DFS implements SearchBehavior {


	Graph g;
	private boolean isDone=false;
    private int Source,Destination;
	public DFS(Graph g, int src, int dst)
	{
		this.g = g;
		this.Source = src;
		this.Destination = dst;
	
	}
	@Override
	public void Print() {
		// TODO Auto-generated method stub
		printAllPaths(Source, Destination);
	}
	// Prints all paths from 
	// 's' to 'd' 
	public void printAllPaths(int s, int d)  
	{ 
		boolean[] isVisited = new boolean[g.vertices]; 
		ArrayList<Integer> pathList = new ArrayList<>(); 

		//add source to path[] 
		pathList.add(s); 

		//Call recursive utility 
		printAllPathsUtil(s, d, isVisited, pathList); 
	} 

	// A recursive function to print 
	// all paths from 'u' to 'd'. 
	// isVisited[] keeps track of 
	// vertices in current path. 
	// localPathList<> stores actual 
	// vertices in the current path 
	private void printAllPathsUtil(Integer u, Integer d, 
			boolean[] isVisited, 
			List<Integer> localPathList) { 

		// Mark the current node 
		isVisited[u] = true; 

		if (u.equals(d))  
		{ 
			printFinalPath(localPathList); 
			//  isDone = true;
			// if match found then no need to traverse more till depth 
			isVisited[u]= false; 
			return ; 
		} 

		// Recur for all the vertices 
		// adjacent to current vertex 
		if(!isDone)
		{
			for(int i=0;i<g.adjacencylist[u].size();i++)
			{

				if (!isVisited[g.adjacencylist[u].get(i).destination]) 
				{ 
					// store current node  
					// in path[] 
					localPathList.add(g.adjacencylist[u].get(i).destination); 

					printAllPathsUtil(g.adjacencylist[u].get(i).destination, d, isVisited, localPathList); 

					// remove current node in path[]   
					localPathList.remove(localPathList.indexOf(g.adjacencylist[u].get(i).destination));
				} 
			} 

			// Mark the current node 
			isVisited[u] = false;
		} 
	}
	private void printFinalPath(List<Integer> localPathList) {
		// TODO Auto-generated method stubs
		int x;
		int size = localPathList.size(); 
		for (int i = 0; i < size-1; i++) { 
          x = localPathList.get(i)+65;
			System.out.print((char)(x) + " -> ");
		}
        x = localPathList.get(localPathList.size()-1)+65;
		System.out.println((char) x);
       
	}

}
