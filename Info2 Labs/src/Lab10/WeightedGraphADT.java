package Lab10;

import java.util.ArrayList;

public interface WeightedGraphADT {
	public void random(int vertices, int edges);
	public Node findNearestUnvisitedNode();
	public ArrayList<Node> getNodes();
	public void print();
}
