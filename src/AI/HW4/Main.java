package HW4;
import java.util.Random;
import java.util.Scanner;

public class Main {
	
	Scanner reader;
	Board b;
	Random rand;
	
	public Main()
	{
		reader = new Scanner(System.in);
        b = new Board();
        rand = new Random();
        
        b.displayBoard();

        System.out.print("\nWho's first? (1 for computer, 2 for user): ");
        int choice = reader.nextInt();
        if(choice == 1){
        	int firstMove = rand.nextInt(8);
        	b.makeMove(firstMove+1, false);
            b.displayBoard();
        }
        
        while (!b.gameOver()) {
            System.out.print("\nYour move: ");
            int playerMove = reader.nextInt();
            while(!b.makeMove(playerMove, true)) {
            	System.out.print("\nInvalid, try again: ");
                playerMove = reader.nextInt();
            }
            
            b.displayBoard();
            
            if (b.gameOver()) {
                break;
            } 
            
            System.out.println("\nThis is where the AI will make a move hahah");
            
            b.displayBoard();
        }
        
        if (b.winner() == 1) {
            System.out.println("You somehow won?!");
        } 
        else if (b.winner() == -1) {
            System.out.println("You lost against my fantastic AI.");
        } 
        else {
            System.out.println("It's a draw.");
        }
        
	}

	public static void main(String[] args) {
		new Main();
    }

}
