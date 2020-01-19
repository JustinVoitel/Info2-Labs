package Lab10;

import java.util.LinkedList;

public class Node{
    private String id;
    private LinkedList<Edge> edges;
    private int shortestDistance;
    private boolean visited;
    private Node previousVertex;

    public Node(String name){
        shortestDistance = -1; // meaning infinity
        visited = false;
        this.id = name;
        edges = new LinkedList<>();
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public int getShortestDistance() {
    return shortestDistance;
    }

    public void setShortestDistance(int shortestDistance) {
        this.shortestDistance = shortestDistance;
    }

    public Node getPreviousVertex() {
        return previousVertex;
    }

    public void setPreviousVertex(Node previousVertex) {
        this.previousVertex = previousVertex;
    }

    public LinkedList<Edge> getEdges(){
        return this.edges;
    }

    public String getId(){
        return this.id;
    }

    public void addEdge(Edge edge){
        edges.addFirst(edge);
    }

    public void printEdges(){
        for(Edge edge:edges){
            System.out.println(
                    "vertex-" + id + " is connected to " +
                            edge.getDestination().getId() + " with weight " +  edge.getWeight()
            );
        }
    }

    public void printNode(){
        System.out.println(this.getId()+" -> "+this.getShortestDistance());
    }
}