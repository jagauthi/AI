package HW5;

import java.util.ArrayList;

public class ChessMoveIterator
{
	int x, y;
	ArrayList<Integer> moves;
	ChessState state;
	boolean white;

	/// Constructs a move iterator
	ChessMoveIterator(ChessState curState, boolean whiteMoves) {
		x = -1;
		y = 0;
		moves = null;
		state = curState;
		white = whiteMoves;
		advance();
	}

	private void advance() {
		if(moves != null && moves.size() >= 2) {
			moves.remove(moves.size() - 1);
			moves.remove(moves.size() - 1);
		}
		while(y < 8 && (moves == null || moves.size() < 2)) {
			if(++x >= 8) {
				x = 0;
				y++;
			}
			if(y < 8) {
				if(state.getPiece(x, y) != ChessState.None && state.isWhite(x, y) == white)
					moves = state.moves(x, y);
				else
					moves = null;
			}
		}
	}

	/// Returns true iff there is another move to visit
	boolean hasNext() {
		return (moves != null && moves.size() >= 2);
	}

	/// Returns the next move
	ChessMove next() {
		ChessMove m = new ChessMove();
		m.xSource = x;
		m.ySource = y;
		m.xDest = moves.get(moves.size() - 2);
		m.yDest = moves.get(moves.size() - 1);
		advance();
		return m;
	}
}