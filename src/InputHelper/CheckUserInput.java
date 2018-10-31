/**
 * @author Ajinkya Malhotra
 */
package InputHelper;
import java.util.ArrayList;

public class CheckUserInput {
	
	/**
	 * Checks if the move enter by the player/user is legal or not
	 * 
	 * @param move - move entered by the user
	 * @param moves - ArrayList of all possible legal moves
	 * @return - boolean value true if move is legal or else false
	 */
	public static boolean checkMove(String move, ArrayList<Integer> moves) {
		
		if(move.length() != 4) {
			System.out.println("\n Illegal move entered please choose a move from the following list: ");
			return false;
		}
		
		String print = FormatInput.formatUserMove(move);
		
		if(print == null)
			return false;
		
		int userMove = Integer.parseInt(print);
		for(int i=0; i<moves.size(); ++i) {
			if(userMove == moves.get(i))
				return true;
		}
		
		System.out.println("\n Move not found please choose a move from the following list: ");
		return false;
		
	}
}
