package HW5;

import java.util.ArrayList;

public class MiniMaxAI {
	
	ChessState chessState;
	
	public MiniMaxAI(ChessState chessState)
	{
		this.chessState = chessState;
	}

	public int[] makeMove()
	{
		int[] result;
		try {
			result = miniMax(2, -1);
		} 
		catch (Exception e) {
			result = new int[4];
		}
		return new int[] {result[1], result[2], result[3], result[4]};
	}
	
	public int[] miniMax(int depth, int player) throws Exception
	{
		ArrayList<ChessMove> possibleMoves = getPossibleMoves();
		int bestScore = 0;
		int currentScore = 0;
		
		if(player == 1)
			bestScore = Integer.MAX_VALUE; //This might need to be swapped with the other one
		else
			bestScore = Integer.MIN_VALUE; //This might need to be swapped with the other one
		
		ChessMove bestMove = new ChessMove();
		if(possibleMoves.size() == 0 || depth == 0) {
			bestScore = checkScores(player);
		}
		else {
			for(ChessMove move : possibleMoves) {
				chessState.move(move.xSource, move.ySource, move.xDest, move.yDest);
				
				if(player == -1) {
					currentScore = miniMax(depth - 1, 1)[0];
					if(currentScore > bestScore) {
						bestScore = currentScore;
						bestMove = move;
					}
				}
				else {
					currentScore = miniMax(depth - 1, -1)[0];
					if(currentScore < bestScore) {
						bestScore = currentScore;
						bestMove = move;
					}
				}
				chessState.move(move.xDest, move.yDest, move.xSource, move.ySource); //Moves the piece back
			}
		}
		return new int[] {bestScore, bestMove.xSource, bestMove.ySource, bestMove.xDest, bestMove.yDest};
	}
	
	public ArrayList<ChessMove> getPossibleMoves()
	{
		ArrayList<ChessMove> possibleMoves = new ArrayList<ChessMove>();
		
		
		return possibleMoves;
	}
	
	public int checkScores(int player)
	{
		int score = 0;
		
	    return score;
	}
 
	public boolean hasWon(int player) {
		return false;
	}
}
