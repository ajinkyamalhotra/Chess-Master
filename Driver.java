import java.util.*;

public class Driver {
	
	static GameBoard game = new GameBoard();
	
	public static void start() {
		
		Scanner kb = new Scanner(System.in);
		System.out.println("\n (1)    Player's Move\n (2)    Gideon's Move\n ");
		
		String userInput = kb.next();
		
		if(userInput.equals("1"))
			playersMove();
		
		else if(userInput.equals("2"))
			GideonsMove();
		
		else
			start();
		
	}
	
	private static void playersMove() {
		Print.board(game);
		
		boolean flag = false;
		
		//true = Gideon's move | false = user's move
		ArrayList<Integer> moves = MoveGenerator.Gen(flag);
		Print.moves(moves);
		
		System.out.print("\n\n Enter your move:  ");
		Scanner kb = new Scanner(System.in);
		String userInput = kb.next();
		
		boolean moveCheck = CheckUserInput.checkMove(userInput, moves);
		
		String formattedUserInput = null;
		
		if(!moveCheck)
			playersMove();

		formattedUserInput = CheckUserInput.formatUserMove(userInput);
		
		
		//System.out.println(formattedUserInput);
		
		
		UpdateBoard.playMove(formattedUserInput, game, flag);
		GideonsMove();
	
	}
	
	private static void GideonsMove() {
		Print.board(game);
		
		boolean flag = true;
		
		//true = Gideon's move | false = user's move
		ArrayList<Integer> moves = MoveGenerator.Gen(flag);
		Print.moves(moves);
		
		System.out.println("\n\n Gideon will play...... Feature coming soon, until then keep practicing\n");
		
		playersMove();
		
	}

	public static void main(String[] args) {
		start();
		
	}
	
}