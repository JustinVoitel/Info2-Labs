package Lab10;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class DijkstraAlgorithm {

    public Node shortestPath(WeightedGraph graph, Node source, Node destination){
    	ArrayList<Node> visited = new ArrayList<>();
    	ArrayList<Node> unvisited = graph.getNodes();

        if(source==null || destination==null){
            System.out.println("Can´t find Vertex");
            System.out.println("SourceVertex: "+source);
            System.out.println("DestVertex: "+destination);
            return null;
        }
        
        source.setShortestDistance(0);
        Node currVertex =  source;
        
        while(visited.size()<unvisited.size()){
            for(Edge e:currVertex.getEdges()){
                Node dest = e.getDestination();
                if(!dest.isVisited()){
                    int currShortestDist =  currVertex.getShortestDistance();
                    if(currShortestDist + e.getWeight() < dest.getShortestDistance() || dest.getShortestDistance()==-1){
                        dest.setShortestDistance(currShortestDist + e.getWeight());
                        dest.setPreviousVertex(currVertex);
                    }
                }

            }
            currVertex.setVisited(true);
            visited.add(currVertex);
            
            Node newCurrVertex = graph.findNearestUnvisitedNode();
            
            if(newCurrVertex != null) {
            	
            	currVertex = graph.findNearestUnvisitedNode();
            }
            
        }
        return destination;
    }
    
    public void findCheapestPath(WeightedGraph graph, Node source, Node dest){
        System.out.println("From: "+source.getId());
        System.out.println("To: "+dest.getId());
        System.out.println();
        Node currVertex = dest;
        boolean foundStart = false;
        ArrayList<String> results = new ArrayList<>();
        while(!foundStart){
        	if(currVertex == null) {
        		System.out.println("There is no possible route :(");
        		return;
        	}
            String res = currVertex.getId()+"("+currVertex.getShortestDistance()+"min)";
            if(!currVertex.equals(source)){
                res = " -> "+res;
                currVertex = currVertex.getPreviousVertex();
            }else{
                foundStart=true;
            }
            results.add(0,res);
        }
        for (String line:results){
            System.out.println(line);
        }
    }

    public static void main(String args[]){
        DijkstraAlgorithm a = new DijkstraAlgorithm();
        WeightedGraph graph = new WeightedGraph();
        graph.random(20, 40);
        graph.print();
        Random r = new Random();
        Node source;
        Node destination;
        
        do{
			source = graph.getNodes().get(r.nextInt(graph.getNodes().size()));
			destination = graph.getNodes().get(r.nextInt(graph.getNodes().size()));				
		}while(source == destination);
        
        
        a.shortestPath(graph, source, destination);

        a.findCheapestPath(graph, source, destination);
    }
}
