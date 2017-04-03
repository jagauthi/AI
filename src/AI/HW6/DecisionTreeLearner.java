package HW6;

import java.util.Random;


public class DecisionTreeLearner extends SupervisedLearner {
	String name;
	Node root;
	int[] valueHolder;
	Random random;

	public DecisionTreeLearner() {
		name = "DecisionTree";
		root = new InteriorNode();
		random = new Random();
	}

	@Override
	String name() {
		return name;
	}

	@Override
	void train(Matrix features, Matrix labels) {
		root = buildTree(features, labels);
		valueHolder = new int[features.cols()];
		for (int i=0; i < features.cols(); i++)
			valueHolder[i] = features.valueCount(i);
	}

	Node buildTree(Matrix features, Matrix labels)
	{
		 if (features.rows() < 10)
			 return new LeafNode(labels);
		 
		 else
		 {
			int dim = random.nextInt(features.cols());
			int splitCol = random.nextInt(features.cols());
			int randomRow = random.nextInt(features.rows());
			double splitValue = features.row(randomRow)[splitCol];
			 
			Matrix leftFeatures = new Matrix();
			Matrix rightFeatures = new Matrix();
			Matrix leftLabels = new Matrix();
			Matrix rightLabels = new Matrix();		 
			leftFeatures.copyMetaData(features);
			rightFeatures.copyMetaData(features);
			leftLabels.copyMetaData(labels);
			rightLabels.copyMetaData(labels);

			int leftCount = 0;
			int rightCount = 0;
			if (features.valueCount(dim) == 0)
			{
				for (int i=0; i < features.rows(); i++)
				{
					if (features.row(i)[dim] < splitValue) {
						leftFeatures.newRow();
						leftLabels.newRow();
						leftFeatures.copyBlock(leftCount, 0, features, i, 0, 1, features.cols());
						leftLabels.copyBlock(leftCount, 0, labels, i, 0, 1, labels.cols());
						leftCount++;
					}
					 
					else {
						rightFeatures.newRow();
						rightLabels.newRow();
						rightFeatures.copyBlock(rightCount, 0, features, i, 0, 1, features.cols());
						rightLabels.copyBlock(rightCount, 0, labels, i, 0, 1, labels.cols());
						rightCount++;
					}
				}
			}
			 
			else
			{
				for (int i=0; i < features.rows(); i++)
				{
					if (features.row(i)[dim] == splitValue) {
						leftFeatures.newRow();
						leftLabels.newRow();
						leftFeatures.copyBlock(leftCount, 0, features, i, 0, 1, features.cols());
						leftLabels.copyBlock(leftCount, 0, labels, i, 0, 1, labels.cols());
						leftCount++;
					}
					 
					else {
						rightFeatures.newRow();
						rightLabels.newRow();
						rightFeatures.copyBlock(rightCount, 0, features, i, 0, 1, features.cols());
						rightLabels.copyBlock(rightCount, 0, labels, i, 0, 1, labels.cols());
						rightCount++;
					}		 				 
				}
			}
			 
			InteriorNode node = new InteriorNode();
			node.left = buildTree(leftFeatures, leftLabels);
			node.right = buildTree(rightFeatures, rightLabels);
			node.attribute = dim;
			node.pivot = splitValue;
			return node;
		}
	}
	@Override
	void predict(double[] in, double[] out) {
		Node node = root;
		while(!node.isLeaf()) {
			InteriorNode n = (InteriorNode)node;
			if(valueHolder[n.attribute] == 0) {
				if(in[n.attribute] < n.pivot) {
					node = n.left;
				}
				else {
					node = n.right;
				}
			}
			else {
				if(in[n.attribute] == n.pivot) {
					node = n.left;
				}
				else {
					node = n.right;
				}
			}
		}
		LeafNode leaf = (LeafNode)node;
		Vec.copy(out, leaf.label);
	}

}
