package HW2;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.awt.Graphics;
import java.awt.Color;

class Agent {
	
	Planner planner;
	Node goalState;
	Node nextStep;
	ArrayList<Node> path;
	boolean started;
	PriorityQueue<Node> frontier;
	PriorityQueue<Node> aStarFrontier;
	int algorithmChoice;
	
	public Agent()
	{
		started = false;
		algorithmChoice = 1; //1 for UCS, 2 for AStar
		planner = new Planner();
		path = new ArrayList<Node>();
		frontier = new PriorityQueue<Node>();
		aStarFrontier = new PriorityQueue<Node>();
		goalState = new Node(0, null, 100, 100);
		nextStep = new Node(0, null, 100, 100);
	}

	void drawPlan(Graphics g, Model m) {
		g.setColor(Color.red);
		for(int i = 1; i < path.size(); i++) {
			g.drawLine(path.get(i-1).state[0], path.get(i-1).state[1], path.get(i).state[0], path.get(i).state[1]);
		}
		for(int x = 0; x < frontier.size(); x++) {
			Node node = frontier.poll();
			g.drawOval(node.state[0], node.state[1], 10, 10);
		}
		for(int x = 0; x < aStarFrontier.size(); x++) {
			Node node = aStarFrontier.poll();
			g.drawOval(node.state[0], node.state[1], 10, 10);
		}
	}

	void update(Model m)
	{
		Controller c = m.getController();
		while(true) {
			MouseEvent e = c.nextMouseEvent();
			if(e == null) {
				if(m.getDistanceToDestination(goalState) == 0){
					break;
				}
				if(m.getDistanceToDestination(nextStep) == 0 && started) {
					Node answer;
					if(algorithmChoice == 1) {
						answer = planner.UCS(goalState, m.getSprites().get(0), m);
					}
					else {
						answer = planner.AStar(goalState, m.getSprites().get(0), m);
					}
					//frontier = planner.getFrontier();
					nextStep = getNextStep(answer);
					m.setDestination(nextStep.state[0], nextStep.state[1]);
				}
				break;
			}
			else {
				if(e.getButton() == 1) {
					algorithmChoice = 1;
				}
				else {
					algorithmChoice = 2;
				}
				goalState = new Node(0, null, e.getX(), e.getY());
				Node answer;
				if(algorithmChoice == 1) {
					answer = planner.UCS(goalState, m.getSprites().get(0), m);
				}
				else {
					answer = planner.AStar(goalState, m.getSprites().get(0), m);
				}
				frontier = planner.getFrontier();
				aStarFrontier = planner.getAStarFrontier();
				nextStep = getNextStep(answer);
				m.setDestination(nextStep.state[0], nextStep.state[1]);
				started = true;
			}
		}
	}
	
	public Node getNextStep(Node finalNode)
	{
		path.clear();
		Node current = finalNode;
		Node answer = finalNode;
		if(current == null)
			return current;
		
		while(current.parent != null) {
			answer = current;
			path.add(0, current);
			current = current.parent;
		}
		return answer;
	}
	
	public void printPath(Node finalNode)
	{
		Node current = finalNode;
		
		while(current.parent != null) {
			System.out.println(current.getStateAsString());
			current = current.parent;
		}
	}

	public static void main(String[] args) throws Exception
	{
		Controller.playGame();
	}
}
