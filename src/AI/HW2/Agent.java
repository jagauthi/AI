package HW2;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Color;

class Agent {
	
	Planner planner;
	ArrayList<Node> steps;
	
	public Agent()
	{
		planner = new Planner();
		steps = new ArrayList<Node>();
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
				if(steps.size() > 1)
				{
					System.out.println(steps.size());
					m.setDestination(steps.get(0).state[0], steps.get(0).state[1]);
					steps.remove(0);
				}
				break;
			}
			else {
				steps.clear();
				Node goalState = new Node(0, null, e.getX(), e.getY());
				Node answer = planner.UCS(goalState, m.getSprites().get(0), m);
				steps = getSteps(answer);
			}
			
		}
	}
	
	public ArrayList<Node> getSteps(Node finalNode)
	{
		Node current = finalNode;
		ArrayList<Node> answer = new ArrayList<Node>();
		
		while(current.parent != null)
		{
			answer.add(0, current); //Inserts the next step into the array list
			current = current.parent;
		}
		return answer;
	}

	public static void main(String[] args) throws Exception
	{
		Controller.playGame();
	}
}
