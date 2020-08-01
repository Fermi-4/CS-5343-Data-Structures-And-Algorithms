import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.stream.Collectors;

/*
 * 
 * 
    a. Write a program to do DFS topological sort . your program must be able to catch the loop.
    Run the program on the attached graphs.
    b. Write a program to do BFS topological sort . your program must be able to catch the loop.
	Run the program on the attached graphs.
	submit screen shots of execution results.
	submit the code
 * 
 */

public class Main {

	// numerical labels
	static Map<Integer, Node> GRAPH1 = new HashMap<Integer,Node>();
	static Map<Integer, Node> GRAPH2 = new HashMap<Integer, Node>();

	static {
		GRAPH1.put(0, new Node(0, 1, new Integer[] { 0, 1, 0, 0, 1, 1, 0, 0 }));
		GRAPH1.put(1, new Node(1, 2, new Integer[] { -1, 0, 1, 0, 1, 0, 1, 0 }));
		GRAPH1.put(2, new Node(2, 3, new Integer[] { 0, -1, 0, 1, 0, 0, 0, 0 }));
		GRAPH1.put(3, new Node(3, 4, new Integer[] { 0, 0, -1, 0, 1, 0, -1, 0 }));
		GRAPH1.put(4, new Node(4, 5, new Integer[] { -1, -1, 0, -1, 0, -1, 1, 1 }));
		GRAPH1.put(5, new Node(5, 6, new Integer[] { -1, 0, 0, 0, 1, 0, 0, 1 }));
		GRAPH1.put(6, new Node(6, 7, new Integer[] { 0, -1, 0, 1, -1, 0, 0, 1 }));
		GRAPH1.put(7, new Node(7, 8, new Integer[] { 0, 0, 0, 0, -1, -1, -1, 0 }));

		GRAPH2.put(0, new Node(0, "m", new Integer[] { 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0 }));
		GRAPH2.put(1, new Node(1, "n", new Integer[] { 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0 }));
		GRAPH2.put(2, new Node(2, "o", new Integer[] { 0, -1, 0, -1, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0 }));
		GRAPH2.put(3, new Node(3, "p", new Integer[] { 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1 }));
		GRAPH2.put(4, new Node(4, "q", new Integer[] { -1, -1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 }));
		GRAPH2.put(5, new Node(5, "r", new Integer[] { -1, 0, -1, 0, 0, 0, -1, 0, 1, 0, 0, 0, 1, 0 }));
		GRAPH2.put(6, new Node(6, "s", new Integer[] { 0, 0, -1, -1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0 }));
		GRAPH2.put(7, new Node(7, "t", new Integer[] { 0, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, 0, 0 }));
		GRAPH2.put(8, new Node(8, "u", new Integer[] { 0, -1, 0, 0, 0, -1, 0, 1, 0, 0, 0, 0, 0, 0 }));
		GRAPH2.put(9, new Node(9, "v", new Integer[] { 0, 0, -1, 0, 0, 0, 0, 0, 0, 0, 1, 1, -1, 0 }));
		GRAPH2.put(10, new Node(10, "w", new Integer[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, 0, 0, 0, 1 }));
		GRAPH2.put(11, new Node(11, "x", new Integer[] { -1, 0, 0, 0, 0, 0, 0, 0, 0, -1, 0, 0, 0, 0 }));
		GRAPH2.put(12, new Node(12, "y", new Integer[] { 0, 0, 0, 0, 0, -1, 0, 0, 0, 1, 0, 0, 0, 0 }));
		GRAPH2.put(13, new Node(13, "z", new Integer[] { 0, 0, 0, -1, 0, 0, 0, 0, 0, 0, -1, 0, 0, 0 }));

	}

	public static void main(String[] args) {
		Graph graph1 = new Graph(GRAPH1);
		Graph graph2 = new Graph(GRAPH2);
		System.out.println("===========================");
		System.out.println("DFS");
		System.out.println("===========================");
		System.out.print("Graph1 DFS: ");
		graph1.DFSTopoSort();
		System.out.println();
		System.out.print("Graph2 DFS: ");
		graph2.DFSTopoSort();
		System.out.println();
		System.out.println("===========================");
		System.out.println("BFS");
		System.out.println("===========================");
		System.out.print("Graph1 BFS: ");
		graph1.BFSTopoSort();
		System.out.print("Graph2 DFS: ");
		graph2.BFSTopoSort();
	}

	public static Graph getGraph1() {
		return null;
	}

	public static Graph getGraph2() {
		return null;
	}

	public static class Graph {
		final Map<Integer, Node> incMatrix;
		public Graph(final Map<Integer, Node> incMatrix) {
			this.incMatrix = incMatrix;
		}
		public void BFSTopoSort() {		
			
			
			int[] predCount = new int[incMatrix.size()];
			Collection<Node> l = new ArrayList<Node>();
			
			
			
			// get pred count
			for(Integer i : incMatrix.keySet()) {
				Node n = incMatrix.get(i);
				for(int k=0;k < n.incident.length;k++) {
					if(n.incident[k]==-1) {
						predCount[i]++;
					}
				}
			}
			
			
			// queue init
			Queue<Node> queue = new LinkedList<Node>();
			for(int i = 0;i < predCount.length;i++) {
				if(predCount[i] == 0) {
					Node n = incMatrix.get(i);
					queue.add(n);
					l.add(n);
				}
			}
			
			// loop
			int count = incMatrix.size();
			while(!queue.isEmpty()) {
				Node n = queue.poll();
				System.out.print(n.data + " ");
				for(int i = 0; i < n.incident.length; i++) {
					if(n.incident[i] == 1) {
						predCount[i]--;	
						if(predCount[i]==0) {	
							queue.add(incMatrix.get(i));
						}
					}
				}
				count--;
			}
			if(count != 0) {
				System.out.println(" ... Cycle Detected in BFS Loop!");
			}
			
		}
		public void DFSTopoSort() {
			// get nodes with no incoming cons
			Collection<Node> l = new ArrayList<>();
			Collection<Node> v = new ArrayList<>();
			getStartingNodes().stream().forEach(n -> {
				DFSTopoSortRec(l, v, n);
			});
		}

		// returns nodes with no incoming connections
		private Collection<Node> getStartingNodes() {
			return incMatrix.keySet().stream()
					.filter(k -> !Arrays.asList(incMatrix.get(k).incident).contains(-1))
					.map(k -> incMatrix.get(k))
					.collect(Collectors.toList());
		}

		private void DFSTopoSortRec(Collection<Node> l, Collection<Node> v, Node n) {
			if (!l.contains(n) && v.contains(n)) {
				return;
			}
			v.add(n);
			for(int i = 0; i < n.incident.length;i++) {
				if(n.incident[i]==1 && !l.contains(incMatrix.get(i)) && !v.contains(incMatrix.get(i))) {
					DFSTopoSortRec(l, v, incMatrix.get(i));
				}
			}
			System.out.print(n.data + " ");
			l.add(n);
		}


	}

	public static class Node {
		final int id;
		final Object data;
		final Integer[] incident;
		public Node(int id, Object data, Integer[] incident) {
			this.data = data;
			this.id = id;
			this.incident=incident;
		}
	}

}
