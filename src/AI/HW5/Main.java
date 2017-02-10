package HW5;

import java.util.Scanner;

public class Main {

	ChessState chessState;
	
	public Main(int whiteDepth, int blackDepth)
	{
		chessState = new ChessState();
		chessState.resetBoard();
		MiniMaxAI whiteAI = new MiniMaxAI(chessState, 3, true);
		MiniMaxAI blackAI = new MiniMaxAI(chessState, 3, false);
		if(whiteDepth > 0)
			whiteAI = new MiniMaxAI(chessState, whiteDepth, true);
		if(blackDepth > 0)
			blackAI = new MiniMaxAI(chessState, blackDepth, false);
		
		boolean player1 = true;
		Scanner reader = new Scanner(System.in);
		
		while(!chessState.gameOver()) {
			try{
				chessState.printBoard(System.out);
				System.out.println();
				int[] move = new int[4];
				if(player1) {
					if(whiteDepth > 0) {
						move = whiteAI.makeMove();
					}
					else {
						System.out.println("Your move?");
						String humanMove = reader.nextLine();
						if(humanMove.equals("q")) {
							System.exit(0);
						}
						else {
							for(int i = 0; i < 4; i++) {
								if(i%2 == 0)
									move[i] = convertCharToInt(humanMove.charAt(i));
								else
									move[i] = Integer.parseInt(Character.toString(humanMove.charAt(i)));
							}
							//See if move is valid
						}
					}
				}
				else { //player 2's turn
					if(blackDepth > 0) {
						move = blackAI.makeMove();
					}
					else {
						System.out.println("Your move?");
						String humanMove = reader.nextLine();
						if(humanMove.equals("q")) {
							System.exit(0);
						}
						else {
							for(int i = 0; i < 4; i++) {
								if(i%2 == 0)
									move[i] = convertCharToInt(humanMove.charAt(i));
								else
									move[i] = Integer.parseInt(Character.toString(humanMove.charAt(i)))-1;
							}
							//See if move is valid
						}
					}
				}
				if(move[0] == 0 && move[1] == 0 && move[2] == 0 && move[3] == 0) {
					System.out.println("The move was 0, 0, 0, 0");
					break;
				}
				chessState.move(move[0], move[1], move[2], move[3]);
				chessState.printBoard(System.out);
				
				player1 = !player1;
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		if(chessState.getWinner() == 1) {
			System.out.println("Light wins!");
		}
		else if(chessState.getWinner() == 2) {
			System.out.println("Dark wins!");
		}
		else
			System.out.println("Tie...?");
	}
	
	public int convertCharToInt(char letter) {
		if(letter == 'a')
			return 0;
		else if(letter == 'b')
			return 1;
		else if(letter == 'c')
			return 2;
		else if(letter == 'd')
			return 3;
		else if(letter == 'e')
			return 4;
		else if(letter == 'f')
			return 5;
		else if(letter == 'g')
			return 6;
		else 
			return 7;
	}
	
	public static void main(String[] args) throws Exception {
		new Main(2, 2);
	}

}
