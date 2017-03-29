package HW6;

import java.util.Random;

public class RandomForest extends SupervisedLearner{
	
	DecisionTreeLearner[] trees;
	Matrix predictionLabels;
	
	public RandomForest(int size) {
		trees = new DecisionTreeLearner[size];
		predictionLabels = new Matrix();
	}

	@Override
	String name() {
		return "RandomForest";
	}

	@Override
	void train(Matrix features, Matrix labels) {
		predictionLabels = labels;
		Random random = new Random();
		for(int i = 0; i < trees.length; i++) {
			Matrix newFeatures = new Matrix();
			Matrix newLabels = new Matrix();
			newFeatures.copyMetaData(features);
			newLabels.copyMetaData(labels);
			
			for(int x = 0; x < features.rows(); x++) {
				int randomRow = random.nextInt(features.rows());
				Vec.copy(newFeatures.newRow(), features.row(randomRow));
				Vec.copy(newLabels.newRow(), labels.row(randomRow));
			}
			trees[i] = new DecisionTreeLearner();
			trees[i].train(newFeatures, newLabels);
		}
	}

	@Override
	void predict(double[] in, double[] out) {
		Matrix predictMatrix = new Matrix();
		predictMatrix.copyMetaData(predictionLabels);
		for(int i = 0; i < trees.length; i++) {
			trees[i].predict(in, predictMatrix.newRow());
		}
		double[] mode = new double[predictMatrix.cols()];
		for(int i = 0; i < predictMatrix.cols(); i++) {
			if(predictMatrix.valueCount(i) == 0) {
				mode[i] = predictMatrix.columnMean(i);
			}
			else {
				mode[i] = predictMatrix.mostCommonValue(i);
			}
		}
		Vec.copy(out, mode);
	}
}
