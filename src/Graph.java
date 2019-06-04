//Daniel Cohen 203850029 Ben Efrat 305773806

import java.util.LinkedList;

public class Graph {

	int vertices;
	LinkedList<Edge> [] adjacencylist;
	int[] Depths ;

	Graph(int vertices) {
		System.out.println("number of vertices "+ vertices);
		this.vertices = vertices;
		Depths = new int[vertices];
		adjacencylist = new LinkedList[vertices];
		//initialize adjacency lists for all the vertices
		for (int i = 0; i <vertices ; i++) {
			adjacencylist[i] = new LinkedList<>();
		}
	}

	public void matrixToLinkedList(int[][] Matrix,int Rows,int Cols)
	{
		for(int i=0;i<Rows;i++)
		{
			for(int j=0;j<Cols;j++)
			{
				if(Matrix[i][j]!=-1) {
					addEdge(i,j,Matrix[i][j]);
				}
			}

		}
	}
	public void addEdge(int source, int destination, int weight) {
		Edge edge = new Edge(source, destination, weight);
		adjacencylist[source].addFirst(edge); //for directed graph
	}

	public void printGraph(){
		for (int i = 0; i <vertices ; i++) {
			LinkedList<Edge> list = adjacencylist[i];
			for (int j = 0; j <list.size() ; j++) {
				System.out.println("vertex-" + i + " is connected to " +
						list.get(j).destination + " with weight " +  list.get(j).weight);
			}
		}
	}







}
