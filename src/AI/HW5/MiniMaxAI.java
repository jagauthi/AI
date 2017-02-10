package HW5;

import java.util.ArrayList;
import java.util.Random;

public class MiniMaxAI {
	
	ChessState chessState;
	int depth;
	boolean white;
	int count = 0;
	
	public MiniMaxAI(ChessState chessState, int depth, boolean white)
	{
		this.chessState = chessState;
		this.depth = depth;
		this.white = white;
	}

	public int[] makeMove()
	{
		System.out.println("\nStarting turn for white=" + white);
		int[] result;
		try {
			result = miniMax(chessState, depth, white);
		} 
		catch (Exception e) {
			e.printStackTrace();
			result = new int[5];
		}
		return new int[] {result[1], result[2], result[3], result[4]};
	}
	
	public int[] miniMax(ChessState chessState, int depth, boolean white) throws Exception
	{
		ChessState simulatedState = new ChessState(chessState);
		ArrayList<ChessMove> possibleMoves = getPossibleMoves();
		int bestScore = 0;
		int currentScore = 0;
		
		if(white)
			bestScore = Integer.MAX_VALUE; 
		else
			bestScore = Integer.MIN_VALUE; 
		
		ChessMove bestMove = new ChessMove();
		if(possibleMoves.size() == 0 || depth == 0) {
			bestScore = checkScores(simulatedState, white);
		}
		else {
			for(ChessMove move : possibleMoves) {
				chessState.move(move.xSource, move.ySource, move.xDest, move.yDest);
				
				if(white == false) {
					currentScore = miniMax(simulatedState, depth - 1, true)[0];
					if(currentScore > bestScore) {
						bestScore = currentScore;
						bestMove = move;
					}
				}
				else {
					currentScore = miniMax(simulatedState, depth - 1, false)[0];
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
	
	public int checkScores(ChessState chessState, boolean white)
	{
		int score = chessState.heuristic(new Random());
		if(white)
			return score;
		else
			return -score;
	}
 
	public boolean hasWon(int player) {
		return false;
	}
}
