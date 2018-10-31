/**
 * @author ajink
 */

package GamePieces;
public class GamePiece {

	private int X;
	private int Y;
	private boolean computerPlayer;
	
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
	
}
