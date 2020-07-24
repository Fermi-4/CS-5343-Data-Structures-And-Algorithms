import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

//  Make an undirected, but disconnected graph of at least 15 vertices and 25 edges.
//  you may add function calls in your main to add edges between vertices - one function call per edge.  
//  You may choose any graph representation of your choice from the 3 ways we discussed in the class.
//  Write a program to do DFS traversal of the graph.
//  Also write a program to do BFS traversal of the same graph.
//  Draw your graph on a paper and upload it with you code.
//  run the each of the two programs and capture screen shots and submit them.  
//  Check that the program does indeed do the correct traversal (based on your hand drawn graph)
public class Main {
	
	// numerical labels
	static int[] VERTICES = { 0,1,2,3,4,5,6,7,8,9,10,11,12,13,14 };
	
	// map to program the edges
	static Map<Integer, List<Integer>> connectionMap = new HashMap<Integer, List<Integer>>();
	
	
	static {
		connectionMap.put(0, Arrays.asList(1,4,10));
		connectionMap.put(1, Arrays.asList(0,2,4,7,8,10));
		connectionMap.put(2, Arrays.asList(1,3,10,12,13));
		connectionMap.put(3, Arrays.asList(2,10,12,13));
		connectionMap.put(4, Arrays.asList(0,1,6,8,11));
		connectionMap.put(5, Arrays.asList(14));
		connectionMap.put(6, Arrays.asList(4,8));
		connectionMap.put(7, Arrays.asList(1,8,11));
		connectionMap.put(8, Arrays.asList(1,4,6,7,11));
		connectionMap.put(9, Arrays.asList(14));
		connectionMap.put(10, Arrays.asList(0,1,2,3,12));
		connectionMap.put(11, Arrays.asList(4,7,8));
		connectionMap.put(12, Arrays.asList(2,3,10));
		connectionMap.put(13, Arrays.asList(2,3));
		connectionMap.put(14, Arrays.asList(5,9));
	}
	
	
	// adj maxtrix to represent graph n x n
	static int[][] adjMaxtrix = new int[VERTICES.length][VERTICES.length];

	// visited array
	static boolean[] VISITED = new boolean[VERTICES.length];
	
	public static void main(String[] args) {
		printConnectionMap();
		connect();
		printAdjMatrix();
		
		System.out.println("DFS: ");
		dfs(adjMaxtrix, VISITED, 10);
		System.out.println();
		System.out.println("----------------------------------");
		VISITED = new boolean[VERTICES.length];
		
		System.out.println("BFS: ");
		bfs(adjMaxtrix, VISITED, 10);
		System.out.println();
	}
	
	private static void printConnectionMap() {
		System.out.println("Connections Map: ");
		System.out.println("----------------------------------");
		System.out.println(connectionMap.toString()); 
		System.out.println("----------------------------------");	
	}
	
	private static void printAdjMatrix() {
		System.out.println("Adj Matrix: ");
		System.out.println("----------------------------------");
		printArray(adjMaxtrix);
		System.out.println("----------------------------------");
	}
	private static void printArray(int[][] arr) {
		for(int i = 0; i < arr[0].length; i++) {
			for(int j = 0; j < arr.length; j++)	 {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println("");
		}
	}
	
	private static void connect() {
		for(Integer k : connectionMap.keySet()) {
			connectionMap.get(k).stream().forEach(n -> adjMaxtrix[k][n]=1);
		}
	}
	
	private static void dfs(int[][] G, boolean[] V, int i) {
		 if(!V[i]){        
		        V[i] = true;
		        System.out.print(i + " ");
		        for (int j = 0; j < G[i].length; j++) {
		            if (G[i][j]==1 && !V[j]) {   
		                dfs(G, V, j);
		            }
		        }
		    }
	}
	
	private static void bfs(int[][] G, boolean[] V, int i) {
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(i);
		int currentNode;
		while(!queue.isEmpty()) {
			currentNode = queue.poll();
			V[currentNode]=true;
			System.out.print(currentNode + " ");
			for(int k=0;k < G.length;k++) {
				if(G[currentNode][k]==1 && !V[k]) {
					if(!queue.contains(k)) {
						queue.add(k);
					}
				}
			}
		}
	}	
}


