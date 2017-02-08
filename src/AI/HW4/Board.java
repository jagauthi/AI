package HW4;

public class Board {
	
	int[][] board;
	
	public Board()
	{
		board = new int[3][3];
	}
	
	public int[][] getBoard()
	{
		return board;
	}
	
	public void displayBoard()
	{
		System.out.println();
		int counter = 1;
		for(int col = 0; col < board.length; col++) {
			for(int row = 0; row < board[0].length; row++) {
			
				if(board[col][row] == 0) 
					System.out.print(" " + counter + " ");
				else if(board[col][row] == -1) 
					System.out.print(" O ");
				else 
					System.out.print(" X ");
				
				if(row % 3 != 2) {
					System.out.print("|");
				}
				counter++;
			}
			if(col%3 != 2 ) {
				System.out.println();
				System.out.println("---+---+---");
			}
		}
		System.out.println();
	}
	
	public boolean makeMove(int[] move, boolean player)
	{
		if(move[0] < 0 || move[0] > 2 || move[1] < 0 || move[1] > 2)
			return false;
		
		if(board[move[0]][move[1]] == 1 || board[move[0]][move[1]] == -1)
			return false;
		else if(player == true) {
			board[move[0]][move[1]] = 1;
			return true;
		}
		else {
			board[move[0]][move[1]] = -1;
			return true;
		}
	}

	public boolean gameOver()
	{
		return false;
	}
}
