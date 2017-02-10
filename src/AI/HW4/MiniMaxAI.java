package HW4;

import java.util.ArrayList;

public class MiniMaxAI {
	
	Board board;
	
	public MiniMaxAI(Board board)
	{
		this.board = board;
	}

	public int[] makeMove()
	{
		int[] result = miniMax(2, -1);
		return new int[] {result[1], result[2]};
	}
	
	public int[] miniMax(int depth, int player)
	{
		ArrayList<int[]> possibleMoves = getPossibleMoves();
		int bestScore = 0;
		int currentScore = 0;
		int bestCol = -1;
		int bestRow = -1;
		
		if(player == 1)
			bestScore = Integer.MAX_VALUE; //This might need to be swapped with the other one
		else
			bestScore = Integer.MIN_VALUE; //This might need to be swapped with the other one
		
		if(possibleMoves.size() == 0 || depth == 0) {
			bestScore = checkScores(player);
		}
		else {
			for(int[] move : possibleMoves) {
				board.getBoard()[move[0]][move[1]] = player;
				if(player == -1) {
					currentScore = miniMax(depth - 1, 1)[0];
					if(currentScore > bestScore) {
						bestScore = currentScore;
						bestCol = move[0];
						bestRow = move[1];
					}
				}
				else {
					currentScore = miniMax(depth - 1, -1)[0];
					if(currentScore < bestScore) {
						bestScore = currentScore;
						bestCol = move[0];
						bestRow = move[1];
					}
				}
				board.getBoard()[move[0]][move[1]] = 0;
			}
		}
		return new int[] {bestScore, bestCol, bestRow};
	}
	
	public ArrayList<int[]> getPossibleMoves()
	{
		ArrayList<int[]> possibleMoves = new ArrayList<int[]>();
		
		if(hasWon(1) || hasWon(-1))
			return possibleMoves;
		
		for(int col = 0; col < 3; col++) {
			for(int row = 0; row < 3; row++) {
				if(board.getBoard()[col][row] == 0) {
					possibleMoves.add(new int[] {col, row});
				}
			}
		}
		return possibleMoves;
	}
	
	public int checkScores(int player)
	{
		int score = 0;
		score += getScore(0, 0, 0, 1, 0, 2, player);
		score += getScore(1, 0, 1, 1, 1, 2, player);
		score += getScore(2, 0, 2, 1, 2, 2, player);
		score += getScore(0, 0, 1, 0, 2, 0, player);
		score += getScore(0, 1, 1, 1, 2, 1, player);
		score += getScore(0, 2, 1, 2, 2, 2, player);
		score += getScore(0, 0, 1, 1, 2, 2, player);
		score += getScore(2, 0, 1, 1, 0, 2, player);
	    return score;
	}
	
	private int getScore(int col1, int row1, int col2, int row2, int col3, int row3, int player) {
	    int score = 0;
	    int opponent = -player;
	 
		if (board.getBoard()[col1][row1] == -1) {
			score = 1;
		} 
		else if (board.getBoard()[col1][row1] == 1) {
			score = -1;
		}
		 
		if (board.getBoard()[col2][row2] == -1) {
			if (score == 1) { 
				score = 10;
			} 
			else if (score == -1) {
				return 0;
			} 
			else { 
				score = 1;
			}
		} 
		else if (board.getBoard()[col2][row2] == 1) {
			if (score == -1) {
				score = -10;
			} 
			else if (score == 1) { 
				return 0;
			} 
			else { 
				score = -1;
			}
		}
		 
		if (board.getBoard()[col3][row3] == -1) {
			if (score > 0) { 
				score *= 10;
			} 
			else if (score < 0) { 
				return 0;
			} 
			else { 
				score = 1;
			}
		} 
		else if (board.getBoard()[col3][row3] == 1) {
			if (score < 0) { 
				score *= 10;
			} 
			else if (score > 1) { 
				return 0;
			} 
			else { 
				score = -1;
			}
		}
		return score;
	}
 
	private String[] winStrings = {
		"111000000", "000111000", "000000111", "100100100", "010010010", "001001001", "100010001", "001010100" 
	};
 
	public boolean hasWon(int player) {
		String boardString = "";
		
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				if (board.getBoard()[col][row] == player)
					boardString += "1";
				else
					boardString += "0";
			}
		}
		
		for (String pattern : winStrings) {
			if (boardString.equals(pattern)) 
				return true;
		}
		return false;
	}
}
