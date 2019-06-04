//Daniel Cohen 203850029 Ben Efrat 305773806

import java.util.*; 

public class BFS implements SearchBehavior{

	Graph g;
	int Source , Destination;
	private boolean isDone=false;
	boolean visited[] ;
	boolean allNeighboursVisited = false;
	LinkedList<Vector<Integer> > q;

	public BFS(Graph g ,int src, int dst)
	{
		this.Source = src;
		this.Destination = dst;
		this.g = g;
		visited = new boolean[g.vertices]; 
		q = new LinkedList<Vector<Integer>>();
	}
	@Override
	public void Print() {
		// TODO Auto-generated method stub
		findpaths(g, Source, Destination);
	}

	public void printpath(Vector<Integer>  path) 
	{ 
		int x;
		int size = path.size(); 
		for (int i = 0; i < size-1; i++) { 
          x = path.get(i)+65;
			System.out.print((char)(x) + " -> ");
		}
        x = path.get(path.size()-1)+65;
		System.out.println((char) x);
	} 
	// utility function to check if current 
	
	// vertex is already present in path 
	public boolean isNotVisited(int x, Vector<Integer> path) 
	{ 
		int size = path.size(); 
		for (int i = 0; i < size; i++)  
		{
			if (path.get(i) == x)  
			{
				return false;}  
		}
		return true; 
	} 


	// utility function for finding paths in graph 
	// from source to destination 
	public void findpaths(Graph g, int src,  
			int dst) 
	{ 


		// path vector to store the current path 
		Vector<Integer> path = new Vector<Integer>(); 
		path.addElement(src); 

		q.add(path); 
		while (!q.isEmpty()) { 

			path = q.poll();

			//holds last element of current path
			int last = path.get(path.size()-1);

			// if last vertex is the desired destination 
			// then print the path 
			if (last == dst)  {
				printpath(path);
				isDone = true;
			}

			// traverse to all the nodes connected to  
			// current vertex and push new path to queue 

			for (int i = 0; i < g.adjacencylist[last].size(); i++) { 
				if (isNotVisited(g.adjacencylist[last].get(i).destination, path)) { 
					Vector<Integer> newpath=(Vector)path.clone();//deep copy ! VERY important
					newpath.addElement(g.adjacencylist[last].get(i).destination); 
					q.add(newpath);
				}	

			}			

		} 
	}


}






