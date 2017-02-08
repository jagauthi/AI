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
		if(result == null) {
			if(hasWon(1))
				return new int[] {-1, 1};
			else
				return new int[] {-1, -1};
		}
		else
			return new int[] {result[1], result[2]};
	}
	
	public int[] miniMax(int depth, int player)
	{
		ArrayList<int[]> possibleMoves = getPossibleMoves();
		if(possibleMoves.size() == 0)
			return null;
		int bestScore = 0;
		int currentScore = 0;
		int bestCol = -1;
		int bestRow = -1;
		
		if(player == 1)
			bestScore = Integer.MIN_VALUE;
		else
			bestScore = Integer.MAX_VALUE;
		
		if(possibleMoves.size() == 0 || depth == 0) {
			bestScore = checkScores(player);
		}
		else {
			for(int[] move : possibleMoves) {
				board.getBoard()[move[0]][move[1]] = player;
				if(player == -1) {

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
		score += evaluateLine(0, 0, 1, 0, 2, 0, player);
		score += evaluateLine(0, 1, 1, 1, 2, 1, player);
		score += evaluateLine(0, 2, 1, 2, 2, 2, player);
		score += evaluateLine(0, 0, 0, 1, 0, 2, player);
		score += evaluateLine(1, 0, 1, 1, 1, 2, player);
		score += evaluateLine(2, 0, 2, 1, 2, 2, player);
		score += evaluateLine(0, 0, 1, 1, 2, 2, player);
		score += evaluateLine(2, 0, 1, 1, 0, 2, player);
	    return score;
	}
	
	private int evaluateLine(int col1, int row1, int col2, int row2, int col3, int row3, int player) {
	    int score = 0;
	    int opponent = 0;
	    if(player == 1)
	    	opponent = -1;
	    else
	    	opponent = 1;
	 
		if (board.getBoard()[col1][row1] == player) {
			score = 1;
		} 
		else if (board.getBoard()[col1][row1] == opponent) {
			score = -1;
		}
		 
		if (board.getBoard()[col2][row2] == player) {
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
		else if (board.getBoard()[col2][row2] == opponent) {
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
		 
		if (board.getBoard()[col3][row3] == player) {
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
		else if (board.getBoard()[col3][row3] == opponent) {
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
 
	private String[] winningPatterns = {
		"111000000", "000111000", "000000111", "100100100", "010010010", "001001001", "100010001", "001010100" 
	};
 
	public boolean hasWon(int player) {
		String pattern = "";
		
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				if (board.getBoard()[col][row] == player)
					pattern += "1";
				else
					pattern += "0";
			}
		}
		
		for (String winningPattern : winningPatterns) {
			if (pattern.equals(winningPattern)) 
				return true;
		}
		return false;
	}
}
