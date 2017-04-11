package HW7;

import java.util.ArrayList;
import java.util.Random;

public class Main {
	
	double alpha_k, gamma;
	int rows = 10;
	int cols = 20;
	double[][] Q;
	int[][] R;
	String[] policy;
	
	public Main() {
		Q = new double[rows*cols][rows*cols]; //20 wide, 10 high
		R = new int[rows*cols][rows*cols];
		policy = new String[rows*cols];
		alpha_k = 0.1;
		gamma = 0.97;
		Random random = new Random();
		
		for(int i = 0; i < rows*cols; i++) {
			for(int x = 0; x < rows*cols; x++) {
				R[i][x] = 0;
				Q[i][x] = 0;
			}
		}

		//Setting the goal for the top right corner:
		R[19][19] = 1000;
		R[18][19] = 1000;
		R[39][19] = 1000;
		
		//Setting the wall along the column x=10
		R[9][10] = Integer.MIN_VALUE;
		R[10][10] = Integer.MIN_VALUE;
		R[11][10] = Integer.MIN_VALUE;
		R[29][30] = Integer.MIN_VALUE;
		R[30][30] = Integer.MIN_VALUE;
		R[31][30] = Integer.MIN_VALUE;
		R[49][50] = Integer.MIN_VALUE;
		R[50][50] = Integer.MIN_VALUE;
		R[51][50] = Integer.MIN_VALUE;
		R[69][70] = Integer.MIN_VALUE;
		R[70][70] = Integer.MIN_VALUE;
		R[71][70] = Integer.MIN_VALUE;
		R[90][70] = Integer.MIN_VALUE;
		//Hole in the wall
		//R[89][90] = Integer.MIN_VALUE;
		//R[91][90] = Integer.MIN_VALUE;
		//R[109][110] = Integer.MIN_VALUE;
		//R[111][110] = Integer.MIN_VALUE;
		R[110][130] = Integer.MIN_VALUE;
		R[129][130] = Integer.MIN_VALUE;
		R[130][130] = Integer.MIN_VALUE;
		R[131][130] = Integer.MIN_VALUE;
		R[149][150] = Integer.MIN_VALUE;
		R[150][150] = Integer.MIN_VALUE;
		R[151][150] = Integer.MIN_VALUE;
		R[169][170] = Integer.MIN_VALUE;
		R[170][170] = Integer.MIN_VALUE;
		R[171][170] = Integer.MIN_VALUE;
		R[189][190] = Integer.MIN_VALUE;
		R[190][190] = Integer.MIN_VALUE;
		R[191][190] = Integer.MIN_VALUE;

		int goalState = 19;
		int numIterations = 100;
		for(int counter = 0; counter < numIterations; counter++) {
			if(counter % (numIterations/10) == 0){
				System.out.println("Iteration " + counter);
				printPolicy();
			}
			int state = random.nextInt(rows*cols); //initial state
			while(state != goalState) {
				int[] possibleNextStates = getPossibleActions(state);
				int nextState = possibleNextStates[random.nextInt(possibleNextStates.length)];
				double q = Q[state][nextState];
				double maxQ = getMaxQValue(nextState);
				int r = R[state][nextState];
				double value = q + alpha_k * (r + gamma*maxQ-q);
				Q[state][nextState] = value;
				state = nextState;
			}
		}
		System.out.println("Iteration " + numIterations);
		printPolicy();
	}
	
	public int[] getPossibleActions(int state) {
		ArrayList<Integer> a = new ArrayList<Integer>();
		if(state > 19)
			a.add(state - 20);
		if(state < 180)
			a.add(state + 20);
		if(state % 20 > 0)
			a.add(state - 1);
		if(state % 20 < 19)
			a.add(state + 1);
		
		int[] actions = new int[a.size()];
		for(int i = 0; i < a.size(); i++) {
			actions[i] = a.get(i);
		}
		return actions;
	}
	
	public double getMaxQValue(int state) {
		double max = Double.MIN_VALUE;
		int[] possibleNextStates = getPossibleActions(state);
		for(int i = 0; i < possibleNextStates.length; i++) {
			double value = Q[state][possibleNextStates[i]];
			if(value > max) {
				max = value;
			}
		}
		return max;
	}
	
	public void printPolicy() {
		for(int i = 0; i < rows*cols; i++) {
			int[] possibleStates = getPossibleActions(i);
			int nextState = i;
			double value = Double.MIN_VALUE;
			for(int x = 0; x < possibleStates.length; x++) {
				if(Q[i][possibleStates[x]] > value) {
					value = Q[i][possibleStates[x]];
					nextState = possibleStates[x];
				}
			}
			if(i % 20 == 10 && i != 90 && i != 110) {
				policy[i] = "#";
			}
			else if(i == 19) {
				policy[i] = "G";
			}
			else if(i == 180) {
				policy[i] = "S";
			}
			else if(nextState == i - 1) {
				policy[i] = "<";
			}
			else if(nextState == i - 20) {
				policy[i] = "^";
			}
			else if(nextState == i + 1) {
				policy[i] = ">";
			}
			else if(nextState == i + 20) {
				policy[i] = "V";
			}
			else
				policy[i] = "O";
		}
		
		for(int i = 0; i < rows*cols; i++) {
			System.out.print(policy[i] + " ");
			if(i%20 == 19)
				System.out.println();
		}
		System.out.println();
	}
	
	public static void main(String[] args) throws Exception
	{
		new Main();
	}
}
