package HW5;

import java.util.ArrayList;
import java.util.Random;

public class MiniMaxAI {
	
	ChessState chessState;
	int depth;
	boolean white;
	
	public MiniMaxAI(ChessState chessState, int depth, boolean white)
	{
		this.chessState = chessState;
		this.depth = depth;
		this.white = white;
	}

	public int[] makeMove()
	{
		int[] result;
		try {
			result = miniMax(depth, white);
		} 
		catch (Exception e) {
			result = new int[4];
		}
		return new int[] {result[1], result[2], result[3], result[4]};
	}
	
	public int[] miniMax(int depth, boolean white) throws Exception
	{
		ArrayList<ChessMove> possibleMoves = getPossibleMoves();
		int bestScore = 0;
		int currentScore = 0;
		
		if(white)
			bestScore = Integer.MAX_VALUE; 
		else
			bestScore = Integer.MIN_VALUE; 
		
		ChessMove bestMove = new ChessMove();
		if(possibleMoves.size() == 0 || depth == 0) {
			bestScore = checkScores(white);
		}
		else {
			for(ChessMove move : possibleMoves) {
				chessState.move(move.xSource, move.ySource, move.xDest, move.yDest);
				
				if(white == false) {
					currentScore = miniMax(depth - 1, true)[0];
					if(currentScore > bestScore) {
						bestScore = currentScore;
						bestMove = move;
					}
				}
				else {
					currentScore = miniMax(depth - 1, false)[0];
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
		ChessMoveIterator iterator = new ChessMoveIterator(chessState, white);
		while(iterator.hasNext()) {
			possibleMoves.add(iterator.next());
		}
		
		return possibleMoves;
	}
	
	public int checkScores(boolean white)
	{
		int score = chessState.heuristic(new Random());
		
	    return score;
	}
 
	public boolean hasWon(int player) {
		return false;
	}
}
