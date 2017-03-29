package HW6;

public class LeafNode extends Node {
	
	double[] label;
	
	public LeafNode(Matrix labels)
	{
		label = new double[labels.cols()];
		for(int i = 0; i < labels.cols(); i++) {
			if(labels.valueCount(i) == 0) {
				label[i] = labels.columnMean(i);
			}
			else {
				label[i] = labels.mostCommonValue(i);
			}
		}
	}

	@Override
	boolean isLeaf() {
		return true;
	}
}
