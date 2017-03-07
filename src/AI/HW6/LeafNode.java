package HW6;

public class LeafNode extends Node {
	
	double[] labels;
	
	public LeafNode(double[] labelVector) {
		labels = labelVector;
	}

	@Override
	boolean isLeaf() {
		
		return true;
	}

}
