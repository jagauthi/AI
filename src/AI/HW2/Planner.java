package HW2;

import java.util.PriorityQueue;
import java.util.TreeSet;

import HW1.GameState;
import HW1.StateComparator;

public class Planner {
	
	TreeSet<Node> beenThere;
	
	public void UCS(Node startState, Node goalState) {
		PriorityQueue<Node> frontier = new PriorityQueue<Node>();
		StateComparator comp = new StateComparator();
		beenThere = new TreeSet<Node>(comp);
		
		startState.cost = 0.0;
		startState.parent = null;
		beenThere.add(startState);
		frontier.add(startState);
		while(frontier.size() > 0) {
			Node s = frontier.remove(); // get lowest-cost state
			if(s.state.isEqual(goalState.state))
				return s;
			
			for each action, a {
				child = transition(s, a); // compute the next state
				acost = action_cost(s, a); // compute the cost of the action
				if(beenThere.contains(child)) {
					oldchild = beenThere.find(child);
					if(s.cost + acost < oldchild.cost) {
						oldchild.cost = s.cost + acost;
						oldchild.parent = s;
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
	}
}
