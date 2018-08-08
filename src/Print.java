import java.util.ArrayList;

public class Print {
	
	public Print() {																//Constructor
		
	}

	/********************************************************************************************************************************************
	 * 																																			*
	 * All the legal Moves are stored in 2534 format, moves(...) changes the format to E2D3														*
	 * Where in 25(Y-axis, X-axis) represents the location of the piece and 34(Y-axis, X-axis) is the location where the piece will be moved	*
	 * moves(..) prints out all the possible legal moves in E2D3 format																			*
	 * Where in E2(X-axis, Y-axis) represents the location of the piece and D3(X-axis, Y-axis) is the location where the piece will be moved 	*
	 * 																																			*
	 * @param moves - Contains all the possible moves Player or Gideon can make during their turns												*
	 * 																																			*
	 ********************************************************************************************************************************************
	 */
	public static void moves(ArrayList<Integer> moves) {
		
		System.out.print("\n All possible moves: ");
		for(int i=0; i<moves.size(); ++i) {
			
			String move = ""+moves.get(i);
			
			int first = Integer.parseInt(""+move.charAt(1));						//Getting the original X-axis location of the game piece(int) 
			char second = move.charAt(0);											//Getting the original Y-axis location of the game piece(int stored as char) 
			int third = Integer.parseInt(""+move.charAt(3));						//Getting the new X-axis location of the game piece(int)
			char fourth = move.charAt(2);											//Getting the new Y-axis location of the game piece(int stored as char)
			
			char one = 'A'; char three = 'A';
			
			int count1 = 1; int count2 = 1;
			
			while(count1 != first) {												//Converting the original X-axis location from int to char
				one++;
				count1++;
			}
			
			while(count2 != third) {												//Converting the original Y-axis location from int to char
				three++;
				count2++;
			}
			
			String print = ""+one+second+three+fourth;								//Original-X(char) + Original-Y(int stored as char) + NEW-X(char) + NEW-Y(int stored as char) = MOVE
			System.out.print(print+" ");											
			
		}
		
		System.out.println();
		
	}
	
	public void board(GameBoard game) {
		game.printBoard(game);														//Prints the entire gameboard
	}
	
}
