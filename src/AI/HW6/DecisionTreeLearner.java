package HW6;

import java.util.Random;

public class DecisionTreeLearner extends SupervisedLearner {
	String name;
	Node root;

	public DecisionTreeLearner() {
		name = "DecisionTree";
		root = new InteriorNode();
	}

	@Override
	String name() {
		return name;
	}

	@Override
	void train(Matrix features, Matrix labels) {
		root = buildTree(features, labels);
	}
	
	Node buildTree(Matrix features, Matrix labels) {
		if(features.rows() <= 1) {
			return new LeafNode(labels);
		}

		Random random = new Random();
		int splitCol = random.nextInt(features.cols());
		int randomRow = random.nextInt(features.rows());
		double splitValue = features.row(randomRow)[splitCol];
		
		Matrix aFeatures = new Matrix();
		Matrix aLabels = new Matrix();
		Matrix bFeatures = new Matrix();
		Matrix bLabels = new Matrix();
		aFeatures.copyMetaData(features);
		aLabels.copyMetaData(labels);
		bFeatures.copyMetaData(features);
		bLabels.copyMetaData(labels);
		
		if(features.valueCount(splitCol) == 0) {
			for(int i = 0; i < features.rows(); i++) {
				if(features.row(i)[splitCol] < splitValue) {
					Vec.copy(aFeatures.newRow(), features.row(i));
					Vec.copy(aLabels.newRow(), labels.row(i));
				}
				else {
					Vec.copy(bFeatures.newRow(), features.row(i));
					Vec.copy(bLabels.newRow(), labels.row(i));
				}
			}
		}
		else {
			for(int i = 0; i < features.rows(); i++) {
				if(features.row(i)[splitCol] == splitValue) {
					Vec.copy(aFeatures.newRow(), features.row(i));
					Vec.copy(aLabels.newRow(), labels.row(i));
				}
				else {
					Vec.copy(bFeatures.newRow(), features.row(i));
					Vec.copy(bLabels.newRow(), labels.row(i));
				}
			}
		}
		
		if(aFeatures.rows() < 1) {
			return new LeafNode(bLabels);
		}
		if(bFeatures.rows() < 1) {
			return new LeafNode(aLabels);
		}
		
		InteriorNode n = new InteriorNode();
		n.attribute = splitCol;
		n.pivot = splitValue;
		n.left = buildTree(aFeatures, aLabels);
		n.right = buildTree(bFeatures, bLabels);
		return n;
	}

	@Override
	void predict(double[] in, double[] out) {
		Node node = root;
		while(!node.isLeaf()) {
			InteriorNode n = (InteriorNode)node;
			if(in[n.attribute] < n.pivot) {
				node = n.left;
			}
			else {
				node = n.right;
			}
		}
		LeafNode leaf = (LeafNode)node;
		Vec.copy(out, leaf.label);
	}

}
