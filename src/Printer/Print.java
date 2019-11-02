/**
 * @author Ajinkya Malhotra
 */
package Printer;
import java.util.ArrayList;

import Board.GameBoard;

public class Print {
	
	public Print() {
		
	}

	/**
	 * All the legal Moves are stored in 2534 format, moves() changes the format to E2D3
	 * Where 25(Y-axis, X-axis) represents the location of the piece and 34(Y-axis, X-axis) is the location where the piece will be moved
	 * moves() prints out all the possible legal moves in E2D3 format
	 * Where E2(X-axis, Y-axis) represents the location of the piece and D3(X-axis, Y-axis) is the location where the piece will be moved
	 * 
	 * @param moves - Contains all the possible moves Player or Gideon can make during their turns
	 */
	public static void moves(ArrayList<Integer> moves) {
		
		System.out.print("\n All possible moves: ");
		for(int i=0; i<moves.size(); ++i) {
			
			String move = ""+moves.get(i);
			
			//Getting the original X-axis location of the game piece(int) 
			int first = Integer.parseInt(""+move.charAt(1));
			
			//Getting the original Y-axis location of the game piece(int stored as char)
			char second = move.charAt(0);
			
			//Getting the new X-axis location of the game piece(int)
			int third = Integer.parseInt(""+move.charAt(3));
			
			//Getting the new Y-axis location of the game piece(int stored as char)
			char fourth = move.charAt(2);
			
			char one = 'A'; char three = 'A';
			int count1 = 1; int count2 = 1;
			
			//Converting the original X-axis location from int to char
			while(count1 != first) {
				one++;
				count1++;
			}
			
			//Converting the original Y-axis location from int to char
			while(count2 != third) {
				three++;
				count2++;
			}
			
			//Original-X(char) + Original-Y(int stored as char) + NEW-X(char) + NEW-Y(int stored as char) = MOVE
			String print = ""+one+second+three+fourth;
			System.out.print(print+" ");											
			
		}
		
		System.out.println();
		
	}
	
	//Prints the entire gameboard
	public void board(GameBoard game) {
		game.printBoard();															
	}
	
}
