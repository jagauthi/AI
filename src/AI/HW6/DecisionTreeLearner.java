package HW6;

public class DecisionTreeLearner extends SupervisedLearner {
	
	String name;
	
	Node root;

	public DecisionTreeLearner() {
		name = "DecisionTree";
		root = new InteriorNode(null);
	}

	@Override
	String name() {
		return name;
	}

	@Override
	void train(Matrix features, Matrix labels) {
		//build decision tree (recursively)
		
		double[] mode = new double[labels.cols()];
		for(int i = 0; i < labels.cols(); i++)
		{
			if(labels.valueCount(i) == 0)
				mode[i] = labels.columnMean(i);
			else
				mode[i] = labels.mostCommonValue(i);
		}
	}

	@Override
	void predict(double[] in, double[] out) {

	}

}
