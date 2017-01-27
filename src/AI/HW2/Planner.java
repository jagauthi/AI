package HW2;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.TreeSet;

import HW2.Model.Sprite;
import HW2.StateComparator;

public class Planner {
	
	TreeSet<Node> beenThere;
	PriorityQueue<Node> frontier;
	Model m;
	
	public Planner()
	{
		beenThere = new TreeSet<Node>(new StateComparator());
		frontier = new PriorityQueue<Node>(new CostComparator());
	}
	
	public Node UCS(Node goalState, Sprite player, Model m) {
		beenThere.clear();
		frontier.clear();
		this.m = m;
		Node startNode = new Node(0.0, null, (int)player.x, (int)player.y);
		
		beenThere.add(startNode);
		frontier.add(startNode);
		
		while(frontier.size() > 0) {
			Node s = frontier.remove(); // get lowest-cost state
			if(isGoal(s, goalState))
			{
				return s;
			}
			
			ArrayList<Node> neighbors = findNeighbors(s);
			for( Node child : neighbors ) {
				double acost = actionCost(child); // compute the cost of the action
				child.cost = s.cost + acost;
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
	
	public double actionCost(Node source)
	{
		if(source.state[0] > 0 && source.state[0] < m.XMAX && source.state[1] > 0 && source.state[1] < m.YMAX)
			return 1/m.getTravelSpeed(source.state[0], source.state[1]) * 100;
		else
			return 1;
		//return Math.sqrt( Math.pow((goal.state[0] - source.state[0]), 2) + Math.pow((goal.state[1] - source.state[1]), 2) );
		/*
		if(source.state[0] == goal.state[0] || source.state[1] == goal.state[1])
			return 1.0;
		else
			return Math.sqrt(2);
			*/
	}
	
	public ArrayList<Node> findNeighbors(Node n)
	{
		ArrayList<Node> neighbors = new ArrayList<Node>();
		
		if(n.state[0] > -10 && n.state[0] < Model.XMAX + 10 && n.state[1] > -10 && n.state[1] < Model.YMAX + 10)
		{
			neighbors.add(new Node(0, n, (n.state[0]+10), (n.state[1]-10) ) );
			neighbors.add(new Node(0, n, (n.state[0]+10),(n.state[1])));
			neighbors.add(new Node(0, n, (n.state[0]+10),(n.state[1]+10)));
			neighbors.add(new Node(0, n, (n.state[0]),(n.state[1]+10)));
			neighbors.add(new Node(0, n, (n.state[0]),(n.state[1]-10)));
			neighbors.add(new Node(0, n, (n.state[0]-10),(n.state[1]-10)));
			neighbors.add(new Node(0, n, (n.state[0]-10),(n.state[1])));
			neighbors.add(new Node(0, n, (n.state[0]-10),(n.state[1]+10)));
		}
		return neighbors;
	}
	
	public boolean isGoal(Node node, Node goalNode)
	{
		if( (node.state[0] > goalNode.state[0] - 10) && (node.state[0] < goalNode.state[0] + 10) ) {
			if( (node.state[1] > goalNode.state[1] - 10) && (node.state[1] < goalNode.state[1] + 10) ) {
				return true;
			}
			else
				return false;
		}
		else
			return false;
	}
	
	public void printPath(Node node)
	{
		Node current = node;
		System.out.println();
		while(current.parent != null)
		{
			System.out.println(current.getStateAsString());
			current = current.parent;
		}
		System.out.println();
	}
	
	public PriorityQueue<Node> getFrontier()
	{
		return frontier;
	}
}



