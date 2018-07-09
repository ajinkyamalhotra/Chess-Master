import java.util.ArrayList;

public class CheckUserInput {
	
	public static boolean checkMove(String move, ArrayList<Integer> moves) {
		
		if(move.length() != 4) {
			System.out.println("\n Illegal move entered please choose a move from the following list: ");
			return false;
		}
		
		String print = formatUserMove(move);
		
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
	
	public static String formatUserMove(String move) {
		char first = 'Z';
		int second = -100;
		char third = 'Z';
		int fourth = -100;
		
		try {
			first = move.charAt(0);
			second = Integer.parseInt(""+move.charAt(1));
			third = move.charAt(2);
			fourth = Integer.parseInt(""+move.charAt(3));
		}
		catch (Exception e){
			System.out.println("\n Illegal move entered please choose a move from the following list.");
			return null;
			
		}
		
		char one = 'A';
		char three = 'A';
		
		int count1 = 1; int count2 = 1;
		
		while(!(""+first).equalsIgnoreCase(""+one)) {
			one++;
			count1++;
		}
		
		while(!(""+third).equalsIgnoreCase(""+three)) {
			three++;
			count2++;
		}
		
		String formattedUserInput = ""+second+count1+fourth+count2;
		
		return formattedUserInput;
	}
}
