package HW2;

import java.awt.event.MouseEvent;
import java.awt.Graphics;
import java.awt.Color;

class Agent {
	
	Planner planner;
	
	public Agent()
	{
		planner = new Planner();
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
			if(e == null)
				break;
			else {
				Node goalState = new Node(0, null, (byte)e.getX(), (byte)e.getY());
				Node answer = planner.UCS(goalState);
				Node nextStep = getNextStep(answer);
				m.setDestination(nextStep.state[0], nextStep.state[1]);
			}
		}
	}
	
	public Node getNextStep(Node finalNode)
	{
		Node current = finalNode;
		while(current.parent != null)
		{
			current = current.parent;
		}
		return current;
	}

	public static void main(String[] args) throws Exception
	{
		Controller.playGame();
	}
}
