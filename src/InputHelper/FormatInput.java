/**
 * @author Ajinkya Malhotra
 */
package InputHelper;

public class FormatInput {

	/**
	 * Formats user entered move to be compatible with the playMove() method
	 * 
	 * @param move - move that has to be formatted
	 * @return - formatted user move
	 */
	public static String formatUserMove(String move) {
		char first = 'Z';
		int second = -100;
		char third = 'Z';
		int fourth = -100;
		
		try {
			//x-axis location of game piece
			first = move.charAt(0);
			
			//y-axis location of game piece
			second = Integer.parseInt(""+move.charAt(1));
			
			//x-axis location where the game piece wants to be moved
			third = move.charAt(2);
			
			//y-axis location where the game piece wants to be moved
			fourth = Integer.parseInt(""+move.charAt(3));
		}
		catch (Exception e){
			System.out.println("\n Illegal move entered please choose a move from the following list.");
			return null;
		}
		
		char one = 'A';
		char three = 'A';
		
		int count1 = 1; int count2 = 1;
		
		//Getting integer equivalent for old x-axis character
		while(!(""+first).equalsIgnoreCase(""+one)) {
			one++;
			count1++;
		}
		
		//Getting integer equivalent for new x-axis character
		while(!(""+third).equalsIgnoreCase(""+three)) {
			three++;
			count2++;
		}
		
		//Old Y-axis + Old X-axis + New Y-axis + New X-axis
		String formattedUserInput = ""+second+count1+fourth+count2;
		
		return formattedUserInput;
	}
	
}
