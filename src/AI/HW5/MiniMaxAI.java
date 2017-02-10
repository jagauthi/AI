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
		System.out.println("\nStarting turn for " + ((this.white) ? "White" : "Black"));
		System.out.println();
		int[] result;
		try {
			result = miniMax(chessState, depth, Integer.MIN_VALUE, Integer.MAX_VALUE, white);
		} 
		catch (Exception e) {
			e.printStackTrace();
			result = new int[5];
		}
		return new int[] {result[1], result[2], result[3], result[4]};
	}
	
	public int[] miniMax(ChessState chessState, int depth, int alpha, int beta, boolean white) throws Exception
	{
		ChessState simulatedState = new ChessState(chessState);
		ArrayList<ChessMove> possibleMoves = getPossibleMoves(simulatedState, white);
		int currentScore = 0;
		
		ChessMove bestMove = new ChessMove();
		if(possibleMoves.size() == 0 || depth == 0) {
			currentScore = checkScores(simulatedState, white);
			return new int[] {currentScore, bestMove.xSource, bestMove.ySource, bestMove.xDest, bestMove.yDest};
		}
		else {
			for(ChessMove move : possibleMoves) {
				simulatedState.move(move.xSource, move.ySource, move.xDest, move.yDest);
				
				if(white == this.white) {
					currentScore = miniMax(simulatedState, depth - 1, alpha, beta, !this.white)[0];
					if(currentScore > alpha) {
						alpha = currentScore;
						bestMove = move;
					}
				}
				else {
					currentScore = miniMax(simulatedState, depth - 1, alpha, beta, this.white)[0];
					if(currentScore < beta) {
						beta = currentScore;
						bestMove = move;
					}
				}
				simulatedState.move(move.xDest, move.yDest, move.xSource, move.ySource); //Moves the piece back
				if (alpha >= beta) 
					break;
			}
		}
		int bestScore = (white == this.white) ? alpha : beta;
		return new int[] {bestScore, bestMove.xSource, bestMove.ySource, bestMove.xDest, bestMove.yDest};
	}
	
	public ArrayList<ChessMove> getPossibleMoves(ChessState chessState, boolean white)
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
		/*Not confident, but pretty sure this is the problem...*/
		if(this.white)
			return score;
		else
			return -score;
	}
 
	public boolean hasWon(int player) {
		return false;
	}
}
