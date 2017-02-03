package HW4;

public class Board {
	
	int[] board;
	
	public Board()
	{
		board = new int[9];
	}
	
	public void displayBoard()
	{
		System.out.println();
		for(int i = 0; i < board.length; i++) {
			if(board[i] == 0) 
				System.out.print(" " + (i+1) + " ");
			else if(board[i] == -1) 
				System.out.print(" O ");
			else 
				System.out.print(" X ");
			
			if(i%3 != 2) {
				System.out.print("|");
			}
			if(i%3 == 2 && i != 0 && i != 8) {
				System.out.println();
				System.out.println("---+---+---");
			}
		}
		System.out.println();
		System.out.println();
	}
	
	public boolean makeMove(int x, int y)
	{
		return true;
	}

	public boolean gameOver()
	{
		return false;
	}
	
	public int winner()
	{
		return 1;
	}
}
