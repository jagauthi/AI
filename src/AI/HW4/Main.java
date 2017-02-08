package HW4;
import java.util.Random;
import java.util.Scanner;

public class Main {
	
	Scanner reader;
	Board b;
	Random rand;
	MiniMaxAI ai;
	
	public Main()
	{
		reader = new Scanner(System.in);
        b = new Board();
        rand = new Random();
        ai = new MiniMaxAI(b);
        
        b.displayBoard();

        System.out.print("\nWho's first? (1 for computer, 2 for user): ");
        int choice = reader.nextInt();
        if(choice == 1){
        	int firstX = rand.nextInt(2);
        	int firstY = rand.nextInt(2);
        	b.makeMove(new int[]{firstX, firstY}, false);
            b.displayBoard();
        }
        
        while (true) {
            System.out.print("\nYour move: ");
            int playerMove = reader.nextInt();
            int playerCol = (playerMove -1) / 3;
            int playerRow = (playerMove -1) % 3;
            while(!b.makeMove(new int[]{playerCol, playerRow}, true)) {
            	System.out.print("\nInvalid, try again: ");
                playerMove = reader.nextInt();
            }
            
            b.displayBoard();
            
            if (b.gameOver()) {
            	System.out.println("\nGame over.\n");
                break;
            } 
            
            int[] aiMove = ai.makeMove();
            b.makeMove(aiMove, false);
            
            b.displayBoard();
            
            if (b.gameOver()) {
            	System.out.println("\nGame over.\n");
                break;
            } 
        }
        
        if (b.winner == 1) {
            System.out.println("\nYou win.");
        } 
        else if (b.winner == -1) {
            System.out.println("\nYou lost against my fantastic AI.");
        } 
        else {
            System.out.println("\nIt's a draw.");
        }
        
	}

	public static void main(String[] args) {
		new Main();
    }

}
