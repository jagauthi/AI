package HW2;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.TreeSet;

import HW2.Model.Sprite;
import HW2.StateComparator;

public class Planner {
	
	TreeSet<Node> beenThere;
	PriorityQueue<Node> frontier;
	PriorityQueue<Node> aStarFrontier;
	Model m;
	
	public Planner()
	{
		beenThere = new TreeSet<Node>(new StateComparator());
		frontier = new PriorityQueue<Node>(new CostComparator());
		aStarFrontier = new PriorityQueue<Node>(new FCostComparator());
	}
	
	public Node UCS(Node goalState, Sprite player, Model m) {
		beenThere.clear();
		frontier.clear();
		aStarFrontier.clear();
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
	
	public Node AStar(Node goalState, Sprite player, Model m) {
		beenThere.clear();
		frontier.clear();
		aStarFrontier.clear();
		this.m = m;
		float maxSpeed = findMaxSpeed();
		Node startNode = new Node(0.0, null, (int)player.x, (int)player.y);
		
		beenThere.add(startNode);
		aStarFrontier.add(startNode);
		
		while(aStarFrontier.size() > 0) {
			Node s = aStarFrontier.remove(); // get lowest-cost state
			if(isGoal(s, goalState))
			{
				return s;
			}
			
			ArrayList<Node> neighbors = findNeighbors(s);
			for( Node child : neighbors ) {
				double acost = actionCost(child); // compute the cost of the action
				double fcost = acost + heuristic(child, goalState, maxSpeed); // compute the cost of the action
				child.cost = s.cost + acost;
				if(beenThere.contains(child)) {
					Node oldChild = beenThere.floor(child);
					if(s.cost + acost < oldChild.cost) {
						oldChild.cost = s.cost + acost;
						oldChild.fCost = oldChild.cost + fcost;
						oldChild.parent = s;
					}
				}
				else {
					child.cost = s.cost + acost;
					child.fCost = child.cost + fcost;
					child.parent = s;
					aStarFrontier.add(child);
					beenThere.add(child);
				}
			}
		}
		return null;
	}
	
	public float findMaxSpeed()
	{
		float maxSpeed = 0.0f;
		for(float y = 0; y < m.YMAX; y += 1)
		{
			for(float x = 0; x < m.XMAX; x += 1)
			{
				if(m.getTravelSpeed(x, y) > maxSpeed)
					maxSpeed = m.getTravelSpeed(x, y);
			}
		}
		return maxSpeed;
	}
	
	public double heuristic(Node source, Node goal, float maxSpeed)
	{
		return ( Math.sqrt( Math.pow(goal.state[1]-source.state[1], 2) + Math.pow(goal.state[0]-source.state[0], 2) ) * maxSpeed );
	}
	
	public double actionCost(Node source)
	{
		if(source.state[0] > 0 && source.state[0] < m.XMAX && source.state[1] > 0 && source.state[1] < m.YMAX)
			return 1/m.getTravelSpeed(source.state[0], source.state[1]) * 100;
		else
			return 9999;
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
	
	public PriorityQueue<Node> getAStarFrontier()
	{
		return aStarFrontier;
	}
}
