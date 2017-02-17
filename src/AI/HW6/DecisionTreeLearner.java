package HW6;

public class DecisionTreeLearner extends SupervisedLearner {
	
	String name;
	
	Node root;

	public DecisionTreeLearner() {
		name = "";
	}

	@Override
	String name() {
		return name;
	}

	@Override
	void train(Matrix features, Matrix labels) {
		
	}

	@Override
	void predict(double[] in, double[] out) {

	}

}
