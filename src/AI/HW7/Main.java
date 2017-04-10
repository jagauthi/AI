package HW7;

import java.util.Random;

public class Main {
	
	double alpha_k, gamma;
	int rows = 20;
	int cols = 10;
	int[][] Q, R;
	
	public Main() {
		
		/*
		 * Useful resource: 
		 * http://people.revoledu.com/kardi/tutorial/ReinforcementLearning/Q-Learning-Algorithm.htm
		 */
		
		Q = new int[rows*cols][rows*cols]; //20 wide, 10 high
		R = new int[rows*cols][rows*cols];
		
		int goalState = 1;
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
		R[19][19] = 100;
		R[18][19] = 100;
		R[39][19] = 100;
		
		//Setting the wall along the column x=10
		R[9][10] = Integer.MIN_VALUE;
		R[11][10] = Integer.MIN_VALUE;
		R[29][30] = Integer.MIN_VALUE;
		R[31][30] = Integer.MIN_VALUE;
		R[49][50] = Integer.MIN_VALUE;
		R[51][50] = Integer.MIN_VALUE;
		R[69][70] = Integer.MIN_VALUE;
		R[71][70] = Integer.MIN_VALUE;
		//Hole in the wall
		//R[89][90] = Integer.MIN_VALUE;
		//R[91][90] = Integer.MIN_VALUE;
		//R[109][110] = Integer.MIN_VALUE;
		//R[111][110] = Integer.MIN_VALUE;
		R[129][130] = Integer.MIN_VALUE;
		R[131][130] = Integer.MIN_VALUE;
		R[149][150] = Integer.MIN_VALUE;
		R[151][150] = Integer.MIN_VALUE;
		R[169][170] = Integer.MIN_VALUE;
		R[171][170] = Integer.MIN_VALUE;
		R[189][190] = Integer.MIN_VALUE;
		R[191][190] = Integer.MIN_VALUE;

		int counter = 0;
		while(counter < 100) {
			counter++;
			int state = random.nextInt(rows*cols); //initial state
			while(state != goalState) {
				int action;
				if(random.nextDouble() <= 0.05) {
					action = random.nextInt(4);
				}
				else {
					action = 0;
					for(int candidate = 0; candidate < 4; candidate++) {
						if(getQValue(state, candidate) > getQValue(state, action)) {
							action = candidate;
						}
					}
				}
				
				doAction(state, action);
			}
		}
		
		/*
		// Pick an action
		Let i be the current state;
		if(rand.nextDouble() < 0.05)
		{
			// Explore (pick a random action)
			action = rand.nextInt(4);
		}
		else
		{
			// Exploit (pick the best action)
			action = 0;
			for(int candidate = 0; candidate < 4; candidate++)
				if(Q(i, candidate) > Q(i, action))
					action = candidate;
		}

		// Do the action
		do_action(action);
		Let j be the new state

		// Learn from that experience
		Apply the equation below to update the Q-table.
		Where a = action.
		Q(i,a) refers to the Q-table entry for doing action "a" in state "i".
		Q(j,b) refers to the Q-table entry for doing action "b" in state "j".
		Use 0.1 for alpha^k. (Don't get "alpha^k" mixed up with "a".)
		use 0.97 for gamma.
		A(j) is the set of four possible actions, {<,>,^,v}.
		r(i,a,j) is the reward you obtained when you landed in state j.

		// Reset
		If j is the goal state, teleport to the start state.
		*/
	}
	
	public int getQValue(int state, int action) {
		int nextState = 0;
		//Actions (in order): {<, >, ^, v}
		if(action == 0) { //left
			nextState = state - 1;
		}
		else if(action == 1) { //right
			nextState = state + 1;
		}
		else if(action == 2) { //up
			nextState = state - rows;
		}
		else if(action == 3) { //down
			nextState = state + rows;
		}
		
		if(nextState < 0 || nextState >= rows*cols) {
			return 0;
		}
		else {
			return R[state][nextState] + (int)(gamma * getMaxQValue(state));
		}
	}
	
	public int getMaxQValue(int state) {
		int value0 = getQValue(state, 0);
		int value1 = getQValue(state, 1);
		int value2 = getQValue(state, 2);
		int value3 = getQValue(state, 3);
		
		return Math.max(value0, Math.max(value1, Math.max(value2, value3)));
	}
	
	public void doAction(int state, int action) {
		System.out.println("Unimplemented :)");
	}
	
	public static void main(String[] args) throws Exception
	{
		new Main();
	}
}
