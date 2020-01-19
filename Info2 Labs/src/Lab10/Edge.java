package Lab10;

public class Edge {
    private Node destination;
    private int weight;

    public Edge(Node destination, int weight) {
        this.destination = destination;
        this.weight = weight;
    }

    public Node getDestination(){
        return this.destination;
    }

    public int getWeight(){
        return this.weight;
    }
}