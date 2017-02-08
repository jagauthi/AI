package HW4;

public class Board {
	
	int[][] board;
	int winner;
	
	public Board()
	{
		board = new int[3][3];
		winner = 0;
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
		int humanCount = 0;
		int aiCount = 0;
		int tieCount = 0;
		
		//Check rows
		for(int row = 0; row < 3; row++) {
			humanCount = 0;
			aiCount = 0;
			for(int col = 0; col < 3; col++) {
				if(board[col][row] == 1) {
					tieCount++;
					humanCount++;
				}
				else if(board[col][row] == -1) {
					tieCount++;
					aiCount++;
				}
				
				if(humanCount == 3) {
					winner = 1;
					return true;
				}
				else if(aiCount == 3) {
					winner = -1;
					return true;
				}
			}
			
			if(tieCount == 9) {
				winner = 0;
				return true;
			}
		}

		//Check columns
		for(int col = 0; col < 3; col++) {
			humanCount = 0;
			aiCount = 0;
			for(int row = 0; row < 3; row++) {
				if(board[col][row] == 1) {
					humanCount++;
				}
				else if(board[col][row] == -1) {
					aiCount++;
				}
				
				if(humanCount == 3) {
					winner = 1;
					return true;
				}
				else if(aiCount == 3) {
					winner = -1;
					return true;
				}
			}
		}
		
		//Check first diagonal
		humanCount = 0;
		aiCount = 0;
		for(int col = 0; col < 3; col++) {
			for(int row = 0; row < 3; row++) {
				if(row == col) {
					if(board[col][row] == 1) {
						humanCount++;
					}
					else if(board[col][row] == -1) {
						aiCount++;
					}
					
					if(humanCount == 3) {
						winner = 1;
						return true;
					}
					else if(aiCount == 3) {
						winner = -1;
						return true;
					}
				}
			}
		}
		
		//Check second diagonal
		humanCount = 0;
		aiCount = 0;
		for(int col = 0; col < 3; col++) {
			for(int row = 0; row < 3; row++) {
				if((col == 0 && row == 2) || (row == 1 && col == 1) || (col == 2 && row == 0) ) {
					if(board[col][row] == 1) {
						humanCount++;
					}
					else if(board[col][row] == -1) {
						aiCount++;
					}
					
					if(humanCount == 3) {
						winner = 1;
						return true;
					}
					else if(aiCount == 3) {
						winner = -1;
						return true;
					}
				}
			}
		}
		
		return false;
	}
}





