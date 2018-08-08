/**
 * 
 * @author Ajinkya Malhotra
 *
 */

import java.util.*;
public class Driver {
	
	static GameBoard game = new GameBoard();													//Initializing the GameBoard
	static ArrayList<GamePiece> gamePieces = game.getGamePiecesArray();							//Storing the gamePieces array
	
	/**
	 * 
	 * Gets user input to decide if player or Gideon is going to make the first move
	 * 
	 */
	public static void start() {
		@SuppressWarnings("resource")
		Scanner kb = new Scanner(System.in);
		System.out.println("\n (1)    Player's Move\n (2)    Gideon's Move\n ");
		
		String userInput = kb.next();  															//Getting the user's preference for whose playing the first move
		
		if(userInput.equals("1"))      															//Player will play the first move
			playersMove(); 
		
		else if(userInput.equals("2"))															//Gideon will play the first move
			GideonsMove();
		
		else 						   															//Invalid input, re-call method and attempt to get user's input again
			start();
		
	}
	
	/****************************************
	 * 
	 * Captures the player's move
	 * Executes the player's move if legal
	 * 
	 */
	private static void playersMove() {
		Print print = new Print(); 
		print.board(game); 																		//Prints the entire gameBoard.
		
		boolean flag = false;																	//true = Get Gideon's move | false = Get User's move
	
		ArrayList<Integer> moves = MoveGenerator.Gen(flag, gamePieces);							//returns all the legal moves possible to make by the player.
		Print.moves(moves);																		//Prints all the moves returned by the Gen method.
		
		System.out.print("\n\n Enter your move:  ");
		@SuppressWarnings("resource")
		Scanner kb = new Scanner(System.in);
		String userInput = kb.next();															//Captures the players move
		
		boolean moveCheck = CheckUserInput.checkMove(userInput, moves);							//Checks if the move entered by the player is in the legal move list
		
		String formattedUserInput = null;
		
		if(!moveCheck)																			//Recall the playersMove() method if the move entered by the user is illegal
			playersMove();

		formattedUserInput = CheckUserInput.formatUserMove(userInput);							//Formatting the user's move to make it as an executable move on the board
		
		//System.out.println(formattedUserInput);
		
		UpdateBoard boardUpdater = new UpdateBoard();
		game = boardUpdater.playMove(formattedUserInput,game, gamePieces, flag, false, true);   //Executing the legal move on the gameBoard
		gamePieces = game.getGamePiecesArray();													//Getting the updated copy of the Game Pieces Array
		game.updateGameBoard();																	//Updating the String copy of the game board (required to print the updated gameBoard)
		
		System.out.println("\n ========================");
		GideonsMove();																			//Gideon's turn to make a move
	
	}
	
	/**
	 * 
	 * Calls minimax to get Gideons move
	 * Executes Gideons Move i.e. returned by Minimax
	 * 
	 */
	private static void GideonsMove() {
		Print print = new Print();
		print.board(game);																		//Prints the entire gameBoard.
		
		boolean flag = true;																	//true = Gideon's move | false = user's move
		
		ArrayList<Integer> moves = MoveGenerator.Gen(flag, gamePieces);							//returns all the legal moves possible to make by the player.
		Print.moves(moves);																		//Prints all the moves returned by the Gen method.
		
		//System.out.println("\n\n Gideon will play......" 
						//+"Feature coming soon, until then keep practicing\n");
		
		MiniMax minimax = new MiniMax(game, moves);												//Declaring and initializing MiniMax object with parameters game and moves

		if(moves.size() < 6) {																	//Will increment the MiniMax MAX_DEPTH size by 1 if the legal moves are less than 6
			minimax.incrementMAX_DEPTH();
		}
		
		long start_time = System.nanoTime();													//Time before starting MiniMax
		
		String gideonsMove = minimax.start();													//Starting MiniMax
	
		double elapsed = System.nanoTime()-start_time;											//Total time taken by MiniMax=Time after MiniMax is finished-Time before MiniMax was started
	    elapsed=elapsed/1000000000;																//Converting to seconds
	    
		System.out.print("\n\n Gideon's move:  "+
				GideonsMoveConvertor.convert(gideonsMove)+"\n\n");								//Changing GideonsMove to A1B2 format
		
		UpdateBoard boardUpdater = new UpdateBoard();
		
		game = boardUpdater.playMove(gideonsMove, game, gamePieces, flag, false, true);			//Executing the legal move on the gameBoard
		gamePieces = game.getGamePiecesArray();													//Getting the updated copy of the Game Pieces Array
		game.updateGameBoard();																	//Updating the String copy of the game board (required to print the updated gameBoard)
		
		System.out.println("Depth used for this iteration --> "+minimax.getMAX_DEPTH());		//Printing the MAX_DEPTH used by MiniMax to calculate the best move
		System.out.println("Time Taken for MiniMax --> "+elapsed);								//Printing time taken by MiniMax to calculate the best move
		System.out.println("\n ========================");
		playersMove();																			//Player's turn to make a move
		
	}

	public static void main(String[] args) {
		start();
		
	}
	
}