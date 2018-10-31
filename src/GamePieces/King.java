/**
 * @author ajinma
 */

package GamePieces;

public class King extends GamePiece{

	/**
	 * Constructor for game piece King
	 * 
	 * @param X - x co-ordinate of the game piece King
	 * @param Y - y co-ordinate of the game piece King
	 * @param computerPlayer - flag to determine if an instance of King is Gideon's or player's
	 */
	public King(int X, int Y, boolean computerPlayer) {
		
		super(X, Y, computerPlayer);

	}

	//Overriding parent's setX method(King does not move)
	public void setX(int X) {
	
		
	
	}

	//Overriding parent's setY method(King does not move)
	public void setY(int Y) {
	
		
	
	}
	
}
