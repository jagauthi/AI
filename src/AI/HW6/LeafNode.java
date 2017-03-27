package HW6;

public class LeafNode extends Node {
	
	double[] label;
	
	public LeafNode(double[] labelVector) {
		label = labelVector;
	}

	@Override
	boolean isLeaf() {
		return true;
	}

}
