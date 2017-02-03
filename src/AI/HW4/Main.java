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

        System.out.print("Who's first? (1 for compuer, 2 for user): ");
        int choice = reader.nextInt();
        if(choice == 1){
        	int x = rand.nextInt(3);
        	int y = rand.nextInt(3);
            System.out.println("Not doing anything with this yet...");
            b.displayBoard();
        }
        
        while (!b.gameOver()) {
            System.out.println("Your move (Type row number, press enter, then column number): ");
            int playerY = reader.nextInt();
            int playerX = reader.nextInt();
            while(!b.makeMove(playerX, playerY)) {
            	System.out.println("Invalid, try again (Type row number, press enter, then column number): ");
                playerY = reader.nextInt();
                playerX = reader.nextInt();
            }
            
            b.displayBoard();
            
            if (b.gameOver()) {
                break;
            } 
            
            System.out.println("This is where the AI will make a move hahah");
            
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
