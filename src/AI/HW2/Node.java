package HW2;

public class Node {
	
	double cost;
	Node parent;
	byte[] state;
	
	public Node(double cost, Node parent) {
		
		this.cost = cost;
		this.parent = parent;
		
		if(parent != null) {
			state = parent.state;
		}
		else {
			state = new byte[2];
		}
	}

	public Node(double cost, Node parent, byte x, byte y) {
	
		this.cost = cost;
		this.parent = parent;
		state = new byte[2];
		state[0] = x;
		state[1] = y;
	}
	
	public void setState(byte x, byte y)
	{
		state[0] = x;
		state[1] = y;
	}
}
