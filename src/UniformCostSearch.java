//Daniel Cohen 203850029 Ben Efrat 305773806

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Vector;


public class UniformCostSearch implements SearchBehavior{

	Graph g;
	Vector<Integer> path ;
	PriorityQueue <pairForQueue > queue ;//priority queue for uniform cost algorithm
	PriorityQueue <pairForPath > PathQueue ;//priority queue for holding current path
	private int Source;
	private int Destination;

	public UniformCostSearch(Graph g_, int src, int dst)

	{
		this.g = g_;
		this.Source = src;
		this.Destination = dst;
		path = new Vector<Integer>(); 
	}
	@Override
	public void Print() {
		// TODO Auto-generated method stub
		Vector<Integer> finalPath = uniform_cost_search(Source, Destination);
		int x;
		int size = finalPath.size(); 
		for (int i = 0; i < size-1; i++) { 
          x = finalPath.get(i)+65;
			System.out.print((char)(x) + " -> ");
		}
        x = finalPath.get(finalPath.size()-1)+65;
		System.out.println((char) x);
	}
	public class pairForQueue{//class to represent elements inside UCS queue
		private int key;
		private int value;
		public pairForQueue(int key_,int value_) {
			key = key_;
			value = value_;
		}

		int getKey() {
			return this.key;
		}
		int getValue() {
			return this.value;
		}
		void setKey(int  newKey) {
			key = newKey;
		}
		void setValue(int newValue) {
			value =newValue;
		}
	}
	public class paircomparator implements Comparator<pairForQueue>{//override for comparing elements inside UCS queue

		@Override
		public int compare(pairForQueue x, pairForQueue y) {
			if(x.getKey() < y.getKey())
				return 1;
			else if(x.getKey()>y.getKey())
				return -1;
			// TODO Auto-generated method stub
			return 0;
		}

	}
	public class pairForPath{//class to represent elements inside path queue
		private Vector <Integer> v;
		private int key;

		public pairForPath (Vector<Integer> v_,int key_){
			this.v=(Vector<Integer>)v_.clone();//deep copy ! VERY important
			this.key = key_;	
		}
		public Vector<Integer> getVector(){return this.v;}
		public int getKey() {return this.key;}

	}
	public class pathcomparator implements Comparator<pairForPath>{//override to compare elements inside UCS path queue

		@Override
		public int compare(pairForPath x, pairForPath y) {
			if(x.getKey() < y.getKey())
				return 1;
			else if(x.getKey()>y.getKey())
				return -1;
			// TODO Auto-generated method stub
			return 0;
		}

	}



	Vector<Integer> uniform_cost_search(int start, int goal) 
	{ 
		//will hold the final best path
		Vector<Integer> finalPath = new Vector<Integer>(0);

		// set the answer to max value 
		int answer = Integer.MAX_VALUE; 

		// create a priority queue 
		queue = new PriorityQueue<pairForQueue>(1,new paircomparator()); 
		PathQueue = new PriorityQueue<pairForPath>(1,new pathcomparator());


		// insert the starting vertex 
		pairForQueue p1 = new pairForQueue(0,start);
		queue.add(p1); 

		//insert to path queue first element (path vector,key). key will help to organize the path queue ,like the UCS queue
		path.add(start);
		PathQueue.add(new pairForPath(path,p1.getKey()));

		boolean[] visited = new boolean[g.vertices]; 

		// Run as long as queue isnt empty 
		while (queue.size() > 0) { 

			// get the top element of the priority queue and dequeue it 
			pairForQueue p = new pairForQueue(queue.peek().getKey(),queue.peek().getValue());
			queue.remove();


			//dequeue our queue of paths and store in current path
			path  = PathQueue.remove().getVector();



			p.key = p.key*-1;//set back to original value

			//if reached to destination
			if(goal ==p.value) {


				// if the cost is less , change answer to be the new lowest cost, and store current path in final path
				if (answer > p.key) 
				{
					answer=p.key ; 
					finalPath = (Vector)path.clone();
				}


			} 

			// check for the non visited nodes 
			// which are adjacent to present node 
			if (visited[p.value] == false) 
				for (int i = 0; i < g.adjacencylist[p.value].size(); i++) { 

					// value is multiplied by -1 so that  
					// least priority is at the top 	
					
					pairForQueue newpair = new pairForQueue ((p.key +  g.adjacencylist[p.value].get(i).weight)*-1 ,(
							g.adjacencylist[p.value].get(i).destination));
					queue.add(newpair);
					
					Vector<Integer> newpath=(Vector)path.clone();//deep copy ! VERY important. newpath will hold the last path + new adjacent vertex , as long as loop runs
					newpath.addElement(g.adjacencylist[p.value].get(i).destination); 
					PathQueue.add(new pairForPath(newpath,newpair.key))	;   //add our new path to the priorityQueue of paths
			
				} 
			// mark as visited 
			visited[p.value] = true; 

		} 

		return finalPath; 
	} 



}
