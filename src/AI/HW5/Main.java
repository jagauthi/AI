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
				int[] move = new int[4];
				if(player1) {
					if(whiteDepth > 0) {
						move = whiteAI.makeMove();
						//System.out.println("White move: " + convertIntToChar(move[0]) + "" + (move[1]+1) + "" + convertIntToChar(move[2]) + "" + (move[3]+1));
					}
					else {
						boolean validMove = false;
						while(!validMove) {
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
								if(chessState.isValidMove(move[0], move[1], move[2], move[3]))
									validMove = true;
								else 
									System.out.println("Invalid move, try again.");
							}
						}
					}
				}
				else { //player 2's turn
					if(blackDepth > 0) {
						move = blackAI.makeMove();
						//System.out.println("Black move: " + convertIntToChar(move[0]) + "" + (move[1]+1) + "" + convertIntToChar(move[2]) + "" + (move[3]+1));
					}
					else {
						boolean validMove = false;
						while(!validMove) {
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
								if(chessState.isValidMove(move[0], move[1], move[2], move[3]))
									validMove = true;
								else 
									System.out.println("Invalid move, try again.");
							}
						}
					}
				}
				if(move[0] == 0 && move[1] == 0 && move[2] == 0 && move[3] == 0) {
					System.out.println("The move was 0, 0, 0, 0");
					break;
				}
				if(chessState.move(move[0], move[1], move[2], move[3])) {
					chessState.setWinner((player1 ? 1 : 2));
					break;
				}
				
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
	
	public static int convertCharToInt(char letter) {
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
	
	public static char convertIntToChar(int number) {
		if(number == 0)
			return 'a';
		else if(number == 1)
			return 'b';
		else if(number == 2)
			return 'c';
		else if(number == 3)
			return 'd';
		else if(number == 4)
			return 'e';
		else if(number == 5)
			return 'f';
		else if(number == 6)
			return 'g';
		else 
			return 'h';
	}
	
	public static void main(String[] args) throws Exception {
		new Main(2, 1);
	}

}
