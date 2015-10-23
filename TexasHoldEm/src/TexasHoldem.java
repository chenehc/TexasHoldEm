
import java.util.Scanner;

public class TexasHoldem{
	private static Game game; 
	private static Player player1, player2;
	
	public static void main(String args[]){
		Scanner in = new Scanner(System.in);
		player1 = new Player();
		player2 = new Player();
		game = new Game(player1, player2);
		
		game.deal();
		System.out.println(player1.getHand().get(0));
		System.out.println(player1.getHand().get(1));
		System.out.println(player2.getHand().get(0));
		System.out.println(player2.getHand().get(1));
		
		
		//read user input for next move
		System.out.println("Choose move for player 1: "
				+ "\n(1): Call/Check"
				+ "\n(2): Fold"
				+ "\n(3): Raise");	
		int i = in.nextInt();
		playerChoice(i);
		
		System.out.println("Choose move for player 2: "
				+ "\n(1): Call/Check"
				+ "\n(2): Fold"
				+ "\n(3): Raise");	
		int j = in.nextInt();
		playerChoice(j);
		
	}
	
	public static void displayGame(){
		System.out.println("Player 1 has " + player1.getChips() + " chips.");
		System.out.println("Player 2 has " + player2.getChips() + " chips.");
	}
	
	public static void playerChoice(int choice){
		switch (choice){
		case 1: game.call(); break;
		case 2: game.fold(); break;
		case 3: Scanner in = new Scanner(System.in);
				System.out.println("Amount to raise: ");
				int k = in.nextInt();
				game.raise(k); break;
		}
	}
	
	public static void raiseError(){
		
	}
}
