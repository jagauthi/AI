package HW2;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Color;

class Agent {
	
	Planner planner;
	Node goalState;
	Node nextStep;
	boolean started;
	
	public Agent()
	{
		started = false;
		planner = new Planner();
		goalState = new Node(0, null, 100, 100);
		nextStep = new Node(0, null, 100, 100);
	}

	void drawPlan(Graphics g, Model m) {
		g.setColor(Color.red);
		g.drawLine((int)m.getX(), (int)m.getY(), (int)m.getDestinationX(), (int)m.getDestinationY());
	}

	void update(Model m)
	{
		Controller c = m.getController();
		while(true)
		{
			MouseEvent e = c.nextMouseEvent();
			if(e == null) {
				if(m.getDistanceToDestination(goalState) == 0)
					break;
				
				if(m.getDistanceToDestination(nextStep) == 0 && started)
				{
					Node answer = planner.UCS(goalState, m.getSprites().get(0), m);
					nextStep = getNextStep(answer);
					m.setDestination(nextStep.state[0], nextStep.state[1]);
				}
				break;
			}
			else
			{
				goalState = new Node(0, null, e.getX(), e.getY());
				Node answer = planner.UCS(goalState, m.getSprites().get(0), m);
				nextStep = getNextStep(answer);
				m.setDestination(nextStep.state[0], nextStep.state[1]);
				started = true;
			}
			
		}
	}
	
	public Node getNextStep(Node finalNode)
	{
		Node current = finalNode;
		Node prev = current.parent;
		if(prev == null)
			return current;
		
		while(prev.parent != null)
		{
			current = prev;
			prev = current.parent;
		}
		return current;
	}

	public static void main(String[] args) throws Exception
	{
		Controller.playGame();
	}
}
