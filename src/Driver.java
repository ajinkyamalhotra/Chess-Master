/**
 * @author Ajinkya Malhotra
 */

import java.util.*;

import Board.*;
import GamePieces.*;
import InputHelper.*;
import MiniMax.*;
import MoveGen.*;
import Printer.*;
public class Driver {
	
	//Initializing the GameBoard
	static GameBoard game = new GameBoard();
	
	//Storing the gamePieces array
	static ArrayList<GamePiece> gamePieces = game.getGamePiecesArray();							
	
	/**
	 * Gets user input to decide if player or Gideon is going to make the first move
	 */
	public static void start() {
		@SuppressWarnings("resource")
		Scanner kb = new Scanner(System.in);
		System.out.println("\n (1)    Player's Move\n (2)    Gideon's Move\n ");
		
		String prompt = "GAME: Chess Master\r\n" + 
				"      This is a chess-like game with a \"Karate\" theme.  Each side starts with 3 samurai,\r\n" + 
				"      3 ninjas, 3 mini-samurai, 3 mini-ninjas, and a King.  It is played on a 7x8 board,\r\n" + 
				"      and players \"attack\" opposing pieces by moving one of their pieces in front of\r\n" + 
				"      an opponents piece.  When a piece is attacked, it is \"demoted\".  When a \"mini\"\r\n" + 
				"      piece is attacked, it is removed from the board.  The king cannot move, and if it\r\n" + 
				"      is attacked, then that side loses.  The initial position is:";
		
		//Getting the user's preference for whose playing the first move
		String userInput = kb.next();
		
		//Player will play the first move
		if(userInput.equals("1")) {    															
			System.out.println(prompt);
			playersMove(); 
		}
		
		//Gideon will play the first move
		else if(userInput.equals("2")) {														
			System.out.println(prompt);
			GideonsMove();
		}
		
		//Invalid input, re-call method and attempt to get user's input again
		else 						   															
			start();
		
	}
	
	/**
	 * Captures the player's move
	 * Executes the player's move if legal
	 */
	private static void playersMove() {
		Print print = new Print(); 
		
		//Prints the entire gameBoard.
		print.board(game);
		
		//true = Get Gideon's move | false = Get User's move
		boolean flag = false;
	
		//returns all the legal moves possible to make by the player.
		ArrayList<Integer> moves = MoveGenerator.Gen(flag, gamePieces);
		//Prints all the moves returned by the Gen method.
		Print.moves(moves);
		
		System.out.print("\n\n Enter your move:  ");
		
		@SuppressWarnings("resource")
		Scanner kb = new Scanner(System.in);
		
		//Captures the players move
		String userInput = kb.next();
		
		//Checks if the move entered by the player is in the legal move list
		boolean moveCheck = CheckUserInput.checkMove(userInput, moves);
		
		String formattedUserInput = null;
		
		//Recall the playersMove() method if the move entered by the user is illegal
		if(!moveCheck)
			playersMove();

		//Formatting the user's move to make it as an executable move on the board
		formattedUserInput = FormatInput.formatUserMove(userInput);							
		
		//System.out.println(formattedUserInput);
		
		UpdateBoard boardUpdater = new UpdateBoard();
		
		//Executing the legal move on the gameBoard
		game = boardUpdater.playMove(formattedUserInput,game, gamePieces, flag, false, true);
		
		//Getting the updated copy of the Game Pieces Array
		gamePieces = game.getGamePiecesArray();
		
		//Updating the String copy of the game board (required to print the updated gameBoard)
		game.updateGameBoard();
		
		System.out.println("\n ========================");
		
		//Gideon's turn to make a move
		GideonsMove();
	
	}
	
	/**
	 * Calls minimax to get Gideons move
	 * Executes Gideons Move i.e. returned by Minimax
	 */
	private static void GideonsMove() {
		Print print = new Print();
		
		//Prints the entire gameBoard.
		print.board(game);
		
		//true = Gideon's move | false = user's move
		boolean flag = true;
		
		//returns all the legal moves possible to make by the player.
		ArrayList<Integer> moves = MoveGenerator.Gen(flag, gamePieces);
		
		//Prints all the moves returned by the Gen method.
		Print.moves(moves);
		
		//System.out.println("\n\n Gideon will play......" 
						//+"Feature coming soon, until then keep practicing\n");
		  
		//Declaring and initializing MiniMax object with parameters game and moves
		MiniMax minimax = new MiniMax(game, moves);

		//Will increment the MiniMax MAX_DEPTH size by 1 if the legal moves are less than 6
		if(moves.size() < 6) {
			minimax.incrementMAX_DEPTH();
		}
		
		//Time before starting MiniMax
		long start_time = System.nanoTime();
		
		//Starting MiniMax
		String gideonsMove = minimax.start();
	
		//Total time taken by MiniMax=Time after MiniMax is finished-Time before MiniMax was started
		double elapsed = System.nanoTime()-start_time;
		
		//Converting to seconds
	    elapsed=elapsed/1000000000;
	    
	    //Changing GideonsMove to A1B2 format
		System.out.print("\n\n Gideon's move:  "+
				GideonsMoveConvertor.convert(gideonsMove)+"\n\n");
		
		UpdateBoard boardUpdater = new UpdateBoard();
		
		//Executing the legal move on the gameBoard
		game = boardUpdater.playMove(gideonsMove, game, gamePieces, flag, false, true);
		
		//Getting the updated copy of the Game Pieces Array
		gamePieces = game.getGamePiecesArray();
		
		//Updating the String copy of the game board (required to print the updated gameBoard)
		game.updateGameBoard();
		
		//Printing the MAX_DEPTH used by MiniMax to calculate the best move
		System.out.println("Depth used for this iteration --> "+minimax.getMAX_DEPTH());
		
		//Printing time taken by MiniMax to calculate the best move
		System.out.println("Time Taken for MiniMax --> "+elapsed);
		System.out.println("\n ========================");
		
		//Player's turn to make a move
		playersMove();
		
	}

	public static void main(String[] args) {
		start();
		
	}
	
}