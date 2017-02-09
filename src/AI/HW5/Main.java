package HW5;

public class Main {

	MiniMaxAI ai;
	ChessState chessState;
	
	public Main()
	{
		chessState = new ChessState();
		chessState.resetBoard();
		ai = new MiniMaxAI(chessState);
		
		chessState.printBoard(System.out);
		System.out.println();
		
		int[] aiMove = ai.makeMove();
		chessState.move(aiMove[0], aiMove[1], aiMove[2], aiMove[3]);
		chessState.printBoard(System.out);
	}
	
	public static void main(String[] args) throws Exception {
		new Main();
	}

}
