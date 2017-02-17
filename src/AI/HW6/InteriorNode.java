package HW6;

import java.util.ArrayList;

public class InteriorNode extends Node{

	int attribute; // which attribute to divide on
	double pivot; // which value to divide on
	Node parent;
	ArrayList<Node> children;
	
	public InteriorNode() {
		
	}
	
	@Override
	boolean isLeaf() {
		return false;
	}

}
