/**
 * @author Ajinkya Malhotra
 */

package Board;
import java.util.ArrayList;
import GamePieces.*;
import Printer.*;

public class UpdateBoard {
	
	GameBoard game = null;
	
	/**
	 * action1Moved, action2DecreaseHealth and action3Killed flags are used to
	 * store the action that was performed due to the recently moved game piece
	 * appropriate flag is copied to actionPerformed variable and this is 
	 * used by the MiniMax algorithm to retract move from the game board,
	 * once the score calculations are done. 
	 * 
	 * action1Moved Flag - Represents no impact was caused by the recently moved piece
	 * action2DecreasedHealth Flag - Represents decrease Health/change game piece to mini action was performed
	 * action3Killed  Flag - Represents that a piece was killed/removed from the game board
	 */
	private static final String action1Moved = "OnlyMoved";
	private static final String action2DecreasedHealth = "DecreasedHealth";
	private static final String action3Killed = "Killed";
		
	private GamePiece spare = null;
	private String actionPerformed = null;
	
	/**
	 * playMove() method executes the move on the gameBoard
	 * 
	 * @param move - The move that has to be executed
	 * @param gameB - Copy of the gameBoard where we want to make the move
	 * @param gamePieces - Copy of the gamePieces arrayList where we have all the game pieces stored
	 * @param flag - To determine if player or Gideon is making the move
	 * @param dummyBoard - To determine if MiniMax if calling the method or if Gideon and Player are
	 * @param checkStatus - To determine if there is any need to call the checkStatus method
	 * @return - returns the updated game state
	 */
	public GameBoard playMove(String move, GameBoard gameB, ArrayList<GamePiece> gamePieces, boolean flag, boolean dummyBoard, boolean checkStatus) {
		
		this.game = gameB;
		
		//Getting the x Co-ordinate of the game piece
		int locX = Integer.parseInt(move.charAt(0)+"")-1;
		
		//Getting the y Co-ordinate of the game piece
		int locY = Integer.parseInt(move.charAt(1)+"")-1;
		
		//Getting the new x-axis location where the game piece will be moved
		int locXnew = Integer.parseInt(move.charAt(2)+"") -1;
		
		//Getting the new y-axis location where the game piece will be moved
		int locYnew = Integer.parseInt(move.charAt(3)+"") -1;
			
		GamePiece recentlyMoved = null; 
		for(int i=0; i<gamePieces.size(); ++i) {
			recentlyMoved = gamePieces.get(i);
			
			//Finding the gamePiece that needs to be moved
			if(locX == recentlyMoved.getX() && locY == recentlyMoved.getY()) {
				
				//Changing the game piece location to the new x and y co-ordinates
				recentlyMoved.setX(locXnew); recentlyMoved.setY(locYnew);
				break;
			}
			
		}
		
		if(checkStatus) {
			//Checking for impact on the game board caused by the recently moved game piece
			checkStatus(recentlyMoved, locXnew, locYnew, gamePieces, flag, dummyBoard);
		}
		
		return game;
	}
	
	
	/**
	 * checkStatus() checks for the impact on the gameBoard made by the recently moved gamePiece
	 * @scenario1 - By the recent move, no other piece was effected
	 * @scenario2 - By the recent move, other player's piece's health was decreased(changed to mini piece)
	 * @scenario3 - By the recent move, other player's piece was killed
	 * @scenario4 - By the recent move, other player's King was attacked and Game Over
	 * 
	 * @param recentlyMoved - the piece that was recently moved by the playMove(....) method
	 * @param newX - X co-ordinate of the recently moved game piece
	 * @param newY - Y co-ordinate of the recently moved game piece
	 * @param gamePieces - ArrayList of all the game pieces
	 * @param playerOrGideonFlag - To determine if player or Gideon is making the move
	 * @param dummyBoard - To determine if MiniMax if calling the method or if Gideon and Player are
	 */
	public void checkStatus(GamePiece recentlyMoved, int newX, int newY, ArrayList<GamePiece> gamePieces, boolean playerOrGideonFlag, boolean dummyBoard) {
		
		//@scenario1
		actionPerformed = action1Moved;
		
		for(int i=0; i<gamePieces.size(); ++i) {
			GamePiece current = gamePieces.get(i);
			int currX = current.getX();
			int currY = current.getY();
			
			//current location of the game piece
			int location = (currX)*10 + (currY);
	        
			int invertor = 1;
			
			if(playerOrGideonFlag) {
				//if Gideon's making a move on game board reserve the condition check parameters
				invertor = -1;
	        }
			
			//Finding if there is a game piece that is straight above / straight below the recently
	        if((newX*10) + newY + 10*invertor == location &&
	        		recentlyMoved.getComputerPlayerFlag() != current.getComputerPlayerFlag()) {
	        																								
	        	//saving the game piece
	        	spare = current;
	        	
	        	//@scenario2
	        	if(current instanceof Samurai || current instanceof Ninja) {								
	        		
	        		
	        		actionPerformed = action2DecreasedHealth;												
	        		decreaseHealth(current);
	        		break;
	        		
	        	}
	        	
	        	//@scenario3
	        	else if(current instanceof MiniSamurai || current instanceof MiniNinja) {
	        		
	        		actionPerformed = action3Killed;														
	        		kill(current);
	        		break;
	        		
	        	}
	        	
	        	//@scenario4
	        	else if(current instanceof King) {
	        		
	        		actionPerformed = action3Killed;														
	        		GameOver(current, dummyBoard);
	        		break;
	        		
	        	}
	        	
	        }

		}
			
	}
	
	
	/**
	 * Calls changePieceToMini() to decrease the health of the game piece/change the piece to mini
	 * 
	 * @param piece - The game piece whose health is getting decreased or is getting changed to mini
	 */
	public void decreaseHealth(GamePiece piece) {
		game.changePieceToMini(piece);	
	}
	
	
	/**
	 * Calls removeGamePiece() to remove/delete the game piece from the game board
	 * 
	 * @param piece - The game piece that is getting deleted from the game board
	 */
	public void kill(GamePiece piece) {
		game.removeGamePiece(piece);	
	}
	
	
	/**
	 * GameOver() gets called to end the game as king was attacked
	 * This method check's whose King was attacked and remove's it from the game board
	 * Print's out the winner and the final state of game board after the king was attacked and exit's the game
	 * 
	 * @param piece - The game piece that was attacked by the recently moved piece
	 * @param dummyBoard - To check if the MiniMax method is calling the GameOver method, if dummyBoard = true, then
	 * 					 - Method does NOT exit's the game and does not print's the winner and final game state
	 */
	public void GameOver(GamePiece piece, boolean dummyBoard) {
		
		game.removeGamePiece(piece);
		
		if(!dummyBoard) {
			//updating the String copy of the game board
			game.updateGameBoard();																			
			Print print = new Print();
			
			//Printing the entire game board
			print.board(game);																				
		
			if(piece.getComputerPlayerFlag())
				System.out.println("\nYou win.");
		
			else if(!piece.getComputerPlayerFlag())
				System.out.println("\nGideon wins.");

			//Game Over since King got attacked
			System.exit(1);
		
		}
	
	}
	
	
	/**
	 * findKing() finds the king for player or Gideon depending on the playerOrGideon flag
	 * This method is used by MiniMax algorithm, to check if the opposite players king is still alive or not
	 * 
	 * @param gamePieces - Arraylist that contains all the game pieces that are currently in the game
	 * @param playerOrGideon - Specifies if we want to find player's or Gideon's King
	 * @return - returns boolean value that represents if we found the king or not
	 */
	public boolean findKing(ArrayList<GamePiece> gamePieces, boolean playerOrGideon) {

		boolean kingFound = false;
		
		for(int i=0; i<gamePieces.size(); ++i) {
			
			//Finding the king piece in the game pieces arraylist
			if(gamePieces.get(i) instanceof King) {
				
				//Making sure that is the king we want to find
				if(gamePieces.get(i).getComputerPlayerFlag() == playerOrGideon) {
					
					//Setting the kingFound flag to true
					kingFound = true;
					break;
				}
				
			}
			
		}
		
		return kingFound;
		
	}
	
	
	/**
	 * retractMove() method undoes the previous move, this method is used by MiniMax algorithm
	 * This method helps MiniMax algorithm to maintain the previous game board state
	 * @scenario1 - previous action performed was decreaseHealth and hence this scenario will restore the health that was lost
	 * @scenario2 - previous action performed was Kill and hence this scenario will restore the game piece that was killed
	 * 
	 * @param move - Stores the move that was recently executed on the board
	 * @param gameB - The current GameBoard state
	 * @param gamePieces - The current gamePieces arrayList
	 * @param flag - To determine if player or Gideon is making the move
	 * @return - returns the gameState after retracting the current move
	 */
	public GameBoard retractMove(String move, GameBoard gameB, ArrayList<GamePiece> gamePieces, boolean flag) {
		
		//@scenario1
		if(actionPerformed.equals(action2DecreasedHealth)) {
			
			int locX = spare.getX();
			int locY = spare.getY();
			for(int i=0; i<gamePieces.size(); ++i) {
				
				GamePiece current = gamePieces.get(i);
				
				//Finding the piece whose health was decreased in the previous move
				if(locX == current.getX() && locY == current.getY()) {
					
					//Removing the piece
					gamePieces.remove(i);
					
					//Re-storing the game piece 
					gamePieces.add(spare);																	
					break;
					
				}
				
			}
			
		}
		
		//@scenarrio2
		else if(actionPerformed.equals(action3Killed)) {
			//adding the piece that was removed in the previous move
			gamePieces.add(spare);
		}
		
		String a = move.substring(0, 2);
		String b = move.substring(2, 4);
		
		//reversing the recently performed move
		move = b+a;
	
		//calling the play move method with the reversed move and returning the game state
		return playMove(move, gameB,  gamePieces,  flag, true, false);
	}
	
	
	/**
	 * @returns - the action was performed by the previous move
	 */
	public String getActionPerformed() {
		return this.actionPerformed;
	}
	
}