/**
 * @author ajink
 */

package GamePieces;

import java.util.ArrayList;
import java.util.HashMap;

public class GamePiece {

	private int X;
	private int Y;
	private boolean computerPlayer;
	private HashMap<Integer, GamePiece> gamePiecesMap = new HashMap<Integer, GamePiece>();
	
	/**
	 * Constructor for all the game pieces
	 * 
	 * @param X - x co-ordinate of the game piece
	 * @param Y - y co-ordinate of the game piece
	 * @param computerPlayer - flag to determine if an instance of MiniNinja is Gideon's or player's
	 */
	public GamePiece(int X, int Y, boolean computerPlayer) {
		
		this.X = X;
		this.Y = Y;
		this.computerPlayer = computerPlayer;
		
	}
	
	/**
	 * Set's X co-ordinate
	 * 
	 * @param X - new X co-ordinate
	 */
	public void setX(int X) {
		
		this.X = X;
		
	}
	
	/**
	 * Set's Y co-ordinate
	 * 
	 * @param Y - new Y co-ordinate
	 */
	public void setY(int Y) {
		
		this.Y = Y;
		
	}
	
	/**
	 * @return - returns X co-ordinate of the game piece
	 */
	public int getX() {
		
		return this.X;
		
	}
	
	/**
	 * @return - returns Y co-ordinate of the game piece
	 */
	public int getY() {
		
		return this.Y;
		
	}
	
	/**
	 * @return - returns player game piece = false or Gideon game piece = true
	 */
	public boolean getComputerPlayerFlag() {
		
		return this.computerPlayer;
		
	}
	
	/**
	 * @return - return's the piece
	 */
	public GamePiece getPiece() {
		return this;
	}
	
	/**
	 * @return - the letter that is associated with the game piece
	 */
	public String printPiece() {
		String player = "   ";
		
		if(this instanceof Samurai)
			player = " S";
		
		else if(this instanceof MiniSamurai)
			player = " s";
		
		else if(this instanceof Ninja)
			player = " J";
		
		else if(this instanceof MiniNinja)
			player = " j";
		else if(this instanceof King)
			player = " K";
		
		
		if(!(player.equals("X")) && this.computerPlayer)
			player +="c";
		
		else
			player +=" ";
		
		
		return player;
	}
	
	/**
	 * Generate moves for a particular Game Piece
	 * @return - ArrayList with moves for this player
	 */
	public ArrayList<Integer> genMoves(ArrayList<Integer> moves, ArrayList<GamePiece> gamePieces, GamePiece currentPiece, int loc) { 

		return moves;
	}
	
	public void MakeMap(ArrayList<GamePiece> gamePieces) {
		gamePiecesMap = new HashMap<Integer, GamePiece>();
		for(GamePiece gp : gamePieces) {
		      int locX = gp.getX()+1;
		      int locY = gp.getY()+1;
		      int loc = locX*10 + locY;
		      gamePiecesMap.put(loc, gp);
		 }
	}
	
	   /**
	    * checkLegalMove() checks if there already exists a game piece on the location where we want to move our current piece
	    * 
	    * @param move1 - stores the move that needs to be validated
	    * @return - true = legal move was passed | false = illegal move was passed
	    */
	public boolean checkLegalMove(int move1) {
		return (this.gamePiecesMap.containsKey(move1)) ? false : true;
	}
	
	/**																	
	 * Condition check for adding an attack move for Player's game pieces	
	 * 																		
	 * @param move - attackMove that needs to be checked before adding to the ArrayList of moves
	 * @param currentPiece - gamePiece that attempting to attack another piece
	 * @param loc - location of current game piece
	 * @param moves - ArraList of moves
	 * @return - returning arrayList of moves
	 */
	public ArrayList<Integer> plus10(int move, GamePiece currentPiece, int loc, ArrayList<Integer> moves) {
		if(gamePiecesMap.containsKey(move+10) && checkLegalMove(move) && currentPiece.getComputerPlayerFlag() != gamePiecesMap.get(move+10).getComputerPlayerFlag())
			moves.add((loc*100)+move);	
			   
		return moves;
	}
				
	/**
	 * Condition check for adding an attack move for Gideon's game piece
	 * 
	 * @param move - attackMove that needs to be checked before adding to the ArrayList of moves
	 * @param currentPiece - gamePiece that attempting to attack another piece
	 * @param loc - location of current game piece
	 * @param moves - ArraList of moves
	 * @return - returning arrayList of moves 
	 */
	public ArrayList<Integer> negative10(int move, GamePiece currentPiece, int loc, ArrayList<Integer> moves) {

		if(gamePiecesMap.containsKey(move+10) && checkLegalMove(move) && currentPiece.getComputerPlayerFlag() != gamePiecesMap.get(move+10).getComputerPlayerFlag())
			moves.add((loc*100)+move);	
			   
		return moves;
	}
}
