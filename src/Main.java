//Daniel Cohen 203850029 Ben Efrat 305773806
import java.util.Scanner;

public class Main
{


	public static void main(String[] args) throws Exception {
        int Rows , Cols ,MaxDepth;		
        int src = 0, dst = 5;
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Please Enter Number Of Vertices");
        Rows = sc.nextInt();
        Cols = Rows;
        System.out.println("Please Enter Max Depth for DFS Limited");
        MaxDepth = sc.nextInt();

		ReadMatrix matrixToRead = new ReadMatrix(Rows,Cols);
		int[][] Matrix = matrixToRead.readFromFile();
		
		// create the graph 
		Graph graph = new Graph(Rows); 
		graph.matrixToLinkedList(Matrix, Rows, Cols
				);
		
		SearchBehavior BFS = new BFS(graph,src,dst);
		SearchBehavior DFS = new DFS(graph,src,dst);
		SearchBehavior UCS = new UniformCostSearch(graph,src,dst);
		SearchBehavior DFSL = new DFSLimited(graph,MaxDepth,src,dst);

		SearchAlgo search_algo = new SearchAlgo(BFS,src,dst);
        System.out.println("BFS Output");
        search_algo.Print();
        
        search_algo.setSearchBehavior(DFS);
        System.out.println("DFS Output");
        search_algo.Print();
        
        search_algo.setSearchBehavior(UCS);
        System.out.println("UCS Output");
        search_algo.Print();
        
        search_algo.setSearchBehavior(DFSL);
        System.out.println("DFSL Output");
        search_algo.Print();
		
	}



























}


