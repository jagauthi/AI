package HW2;

public class Node {
	
	double cost;
	Node parent;
	int[] state;
	
	public Node(double cost, Node parent) {
		
		this.cost = cost;
		this.parent = parent;
		
		if(parent != null) {
			state = parent.state;
		}
		else {
			state = new int[2];
			state[0] = 100;
			state[1] = 100;
		}
	}

	public Node(double cost, Node parent, int x, int y) {
	
		this.cost = cost;
		this.parent = parent;
		state = new int[2];
		state[0] = x;
		state[1] = y;
	}
	
	public void setState(int x, int y)
	{
		state[0] = x;
		state[1] = y;
	}
	
	public String getStateAsString()
	{
		return "(" + state[0] + "," + state[1] + ")";
	}
}
