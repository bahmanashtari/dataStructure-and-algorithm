package edu.csus.csc130.assignment4;

import java.io.File;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * This Graph implementation maintains a vertex-indexed array of lists of integers. 
 * Every edge appears twice: if an edge connects v and w, then w appears in v’s list and v appears in w’s list. 
 */
public class Graph {
	private int V = 0; // number of vertices
	private int E; // number of edges
	private List<Integer>[] adj; // adjacency lists
	
	//create a V-vertex graph with no edges
	@SuppressWarnings("unchecked")
	public Graph(int V)  {
		this.V = V; 
		this.E = 0;
		adj = new LinkedList[V]; // Create array of lists.
		// Initialize all lists to empty
		for (int v = 0; v < V; v++) {
			adj[v] = new LinkedList<Integer>(); 
		}
	}
	
	/**
	 * reads a graph from an input file, in the format V followed by E followed by a list of 
	 * pairs of int values between 0 and V-1.
	 */
	@SuppressWarnings("unchecked")
	public Graph(String inFileName) {
		Scanner sc = null;
		try {
			URL url = Graph.class.getResource(inFileName);
			sc = new Scanner(new File(url.getPath()));
			int numVertices = sc.nextInt();
			int numEdges = sc.nextInt();
			
			this.V = numVertices; 
			adj = new LinkedList[V]; // Create array of lists.
			// Initialize all lists to empty
			for (int v = 0; v < V; v++) {
				adj[v] = new LinkedList<Integer>(); 
			}
			
			for (int i=0; i<numEdges; i++) {
				int vertexA = sc.nextInt();
				int vertexB = sc.nextInt();
				addEdge(vertexA, vertexB);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sc != null) {
				sc.close();
			}
		}
	}
	
	//return number of vertices
	public int V() {
		return V;
	}
	
	//return number of edges
	public int E() {
		return E;
	}
	
	//add edge v-w to this graph
	public void addEdge(int v, int w) {
		adj[v].add(0, w);
		adj[w].add(0, v);
		E++;
	}
	
	//vertices adjacent to v
	public Iterable<Integer> adj(int v)  {
		return adj[v];
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(V + " vertices, " + E + " edges\n");
		for (int v = 0; v < V; v++) {
			sb.append(v + ": ");
			for (int w : this.adj(v)) {
				sb.append(w + " ");
			}
			sb.append("\n");
		}
		return sb.toString();
	}
	
	@SuppressWarnings("unused")
	public static int degree(Graph G, int v) {
		int degree = 0;
		for (int w : G.adj(v)) {
			degree++;
		}
		return degree;
	}

	public static int maxDegree(Graph G) {
		int max = 0;
		for (int v = 0; v < G.V(); v++)
			if (degree(G, v) > max)
				max = degree(G, v);
		return max;
	}

	public static int avgDegree(Graph G) {
		return 2 * G.E() / G.V();
	}

	public static int numberOfSelfLoops(Graph G) {
		int count = 0;
		for (int v = 0; v < G.V(); v++)
			for (int w : G.adj(v))
				if (v == w)
					count++;
		return count / 2; 
	}	
	
	
	/**
	 * returns true if the given graph has an edge v-w, false otherwise
	 */
	public static boolean hasEdge(Graph graph, int v, int w) {
		//i'm providing both ways cause they both work
		
		//if (v > graph.V || w > graph.V) return false;
		//else if (graph.adj[v] == null || graph.adj[w] == null) return false;
		
		try {
			return graph.adj[v].contains(w);
		} catch(Exception e) {
			return false;
		}
	}
	
	/**
	 * @return number of parallel edges in the given graph in linear time
	 */
	public static int numberOfParallelEdges(Graph graph) {
		// provide your implementation here
		// timing will be linear; in worst case it will be: O(V + E + V)
		int counter = 0;
		for (int i=0; i < graph.V(); i++) {  // repeats V times
			int[] counterList = new int[graph.V()];
			for (int e : graph.adj[i]) {	// repeats between 0 to E times
				counterList[e]++;
			}
			for (int e=0; e < graph.V(); e++) {	// repeats V times
				if (e != i && counterList[e] > 1)
					counter += counterList[e];
			}
		}
		return counter/2;
	}	
	
	public static void main(String[] args) {
		String inFileName = "tinyG_2ParallelEdges_1SelfLoop.txt";
		Graph graph = new Graph(inFileName);
		System.out.println(graph.toString());
	}

}
