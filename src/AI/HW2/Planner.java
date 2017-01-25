package HW2;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.TreeSet;

import HW2.StateComparator;

public class Planner {
	
	TreeSet<Node> beenThere;
	PriorityQueue<Node> frontier;
	
	public Planner()
	{
		beenThere = new TreeSet<Node>(new StateComparator());
	}
	
	public Node UCS(Node goalState) {
		
		frontier = new PriorityQueue<Node>(new StateComparator());
		
		Node startNode = new Node(0.0, null);
		
		beenThere.add(startNode);
		frontier.add(startNode);
		
		while(frontier.size() > 0) {
			Node s = frontier.remove(); // get lowest-cost state
			if(isGoal(s, goalState))
				return s;
			
			for( Node child : findNeighbors(s) ) {
				double acost = actionCost(s, child); // compute the cost of the action
				if(beenThere.contains(child)) {
					Node oldChild = beenThere.floor(child);
					if(s.cost + acost < oldChild.cost) {
						oldChild.cost = s.cost + acost;
						oldChild.parent = s;
					}
					
				}
				else {
					child.cost = s.cost + acost;
					child.parent = s;
					frontier.add(child);
					beenThere.add(child);
				}
			}
		}
		return null;
	}
	
	public double actionCost(Node source, Node goal)
	{
		if(source.state[0] == goal.state[0] || source.state[1] == goal.state[1])
			return 1.0;
		else
			return Math.sqrt(2);
	}
	
	public ArrayList<Node> findNeighbors(Node n)
	{
		ArrayList<Node> neighbors = new ArrayList<Node>();
		
		neighbors.add(new Node(0, n, (byte)(n.state[0]+10),(byte)(n.state[1]-10)));
		neighbors.add(new Node(0, n, (byte)(n.state[0]+10),(byte)(n.state[1])));
		neighbors.add(new Node(0, n, (byte)(n.state[0]+10),(byte)(n.state[1]+10)));
		neighbors.add(new Node(0, n, (byte)(n.state[0]),(byte)(n.state[1]+10)));
		neighbors.add(new Node(0, n, (byte)(n.state[0]),(byte)(n.state[1]-10)));
		neighbors.add(new Node(0, n, (byte)(n.state[0]-10),(byte)(n.state[1]-10)));
		neighbors.add(new Node(0, n, (byte)(n.state[0]-10),(byte)(n.state[1])));
		neighbors.add(new Node(0, n, (byte)(n.state[0]-10),(byte)(n.state[1]+10)));
		
		return neighbors;
	}
	
	public boolean isGoal(Node node, Node goalNode)
	{
		if(node.state[0] == goalNode.state[0] && node.state[1] == goalNode.state[1])
			return true;
		else
			return false;
	}
}



