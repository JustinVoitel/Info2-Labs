package Lab10;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class WeightedGraph implements WeightedGraphADT{
	private ArrayList<Node> adjacencyList;

	public WeightedGraph() {
		adjacencyList = new ArrayList<>();
	}

	public void random(int vertices, int edges) {
		this.adjacencyList = new ArrayList<Node>();
		Random r = new Random();
		
		//generating nodes
		for (int i = 0; i < vertices; i++) {
			String name = "";
		
			char c = (char) (i % 26 + 65);
			for (int j = -1; j < (i / 26); j++) {
				name += String.valueOf(c);
			}
			this.adjacencyList.add(new Node(name));
		}
		
		for(int k = 0; k<edges;k++) {
			int weight = r.nextInt(20);
			Node source;
			Node destination;
			
			do{
				source = this.adjacencyList.get(r.nextInt(this.adjacencyList.size()));
				destination = this.adjacencyList.get(r.nextInt(this.adjacencyList.size()));				
			}while(source == destination);
			
			
			source.addEdge(new Edge(destination, weight));
		}
	}

	public Node findNearestUnvisitedNode() {
		return adjacencyList.stream().filter(n -> !n.isVisited() && n.getPreviousVertex() != null)
				.min(Comparator.comparing(Node::getShortestDistance)).orElse(null);
	}

	public ArrayList<Node> getNodes() {
		return this.adjacencyList;
	}

	public void print() {
        for(Node node: adjacencyList){
            node.printEdges();
            System.out.println(node.getId());
        }
	}


}
