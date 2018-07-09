import java.util.ArrayList;

public class Print {

	public static void moves(ArrayList<Integer> moves) {
		
		System.out.print("\n All possible moves: ");
		for(int i=0; i<moves.size(); ++i) {
			
			String move = ""+moves.get(i);
			
			int first = Integer.parseInt(""+move.charAt(1));
			char second = move.charAt(0);
			int third = Integer.parseInt(""+move.charAt(3));
			char fourth = move.charAt(2);
			
			char one = 'A';
			char three = 'A';
			
			int count1 = 1; int count2 = 1;
			
			while(count1 != first) {
				one++;
				count1++;
			}
			
			while(count2 != third) {
				three++;
				count2++;
			}
			
			String print = ""+one+second+three+fourth;
			System.out.print(print+" ");
			
		}
		
		System.out.println();
		
		
	}
	
	public static void board(GameBoard game) {
		game.printBoard();
	}
	
}
