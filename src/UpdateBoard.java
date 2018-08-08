import java.util.ArrayList;

public class UpdateBoard {
	
	GameBoard game = null;
	
	/**
	 * 
	 * action1Moved, action2DecreaseHealth and action3Killed flags are used to
	 * store the action that was performed due to the recently moved game piece
	 * appropriate flag is copied to actionPerformed variable and this is 
	 * used by the MiniMax algorithm to retract move from the game board,
	 * once the score calculations are done. 
	 * 
	 */
	private static final String action1Moved = "OnlyMoved";													//This Flag represents no impact was caused by the recently moved piece
	private static final String action2DecreasedHealth = "DecreasedHealth";									//This Flag represents decrease Health/change game piece to mini action was performed
	private static final String action3Killed = "Killed";													//This Flag represents that a piece was killed/removed from the game board
		
	private GamePiece spare = null;
	private String actionPerformed = null;
	
	/***************************************************************************************************
	 * 																								   *
	 * playMove(....) method executes the move on the gameBoard	   									   *
	 * 																								   *
	 * @param move - The move that has to be executed												   *
	 * @param gameB - Copy of the gameBoard where we want to make the move							   *
	 * @param gamePieces - Copy of the gamePieces arrayList where we have all the game pieces stored   *
	 * @param flag - To determine if player or Gideon is making the move							   *
	 * @param dummyBoard - To determine if MiniMax if calling the method or if Gideon and Player are   *
	 * @param checkStatus - To determine if there is any need to call the checkStatus method		   *
	 * @return - returns the updated game state														   *
	 * 																								   *
	 ***************************************************************************************************
	 */
	public GameBoard playMove(String move, GameBoard gameB, ArrayList<GamePiece> gamePieces, boolean flag, boolean dummyBoard, boolean checkStatus) {
		
		this.game = gameB;
		
		int locX = Integer.parseInt(move.charAt(0)+"")-1;													//Getting the x Co-ordinate of the game piece
		int locY = Integer.parseInt(move.charAt(1)+"")-1;													//Getting the y Co-ordinate of the game piece
		
		int locXnew = Integer.parseInt(move.charAt(2)+"") -1;												//Getting the new x-axis location where the game piece will be moved
		int locYnew = Integer.parseInt(move.charAt(3)+"") -1;												//Getting the new y-axis location where the game piece will be moved
			
		GamePiece recentlyMoved = null; 
		for(int i=0; i<gamePieces.size(); ++i) {
			recentlyMoved = gamePieces.get(i);
			if(locX == recentlyMoved.getX() && locY == recentlyMoved.getY()) {								//Finding the gamePiece that needs to be moved
				recentlyMoved.setX(locXnew); recentlyMoved.setY(locYnew);									//Changing the game piece location to the new x and y co-ordinates
				break;
			}
			
		}
		
		if(checkStatus)
			checkStatus(recentlyMoved, locXnew, locYnew, gamePieces, flag, dummyBoard);						//Checking for impact on the game board caused by the recently moved game piece
		
		return game;
	}
	
	
	/********************************************************************************************************
	 * 																										*
	 * checkStatus(....) checks for the impact on the gameBoard made by the recently moved gamePiece		*
	 * @scenario1 - By the recent move, no other piece was effected											*
	 * @scenario2 - By the recent move, other player's piece's health was decreased(changed to mini piece)	*
	 * @scenario3 - By the recent move, other player's piece was killed										*
	 * @scenario4 - By the recent move, other player's King was attacked and Game Over						*
	 * 																										*
	 * @param recentlyMoved - the piece that was recently moved by the playMove(....) method				*
	 * @param newX - X co-ordinate of the recently moved game piece											*
	 * @param newY - Y co-ordinate of the recently moved game piece											*
	 * @param gamePieces - ArrayList of all the game pieces													*
	 * @param playerOrGideonFlag - To determine if player or Gideon is making the move						*
	 * @param dummyBoard - To determine if MiniMax if calling the method or if Gideon and Player are		*
	 * 																										*
	 ********************************************************************************************************
	 */
	public void checkStatus(GamePiece recentlyMoved, int newX, int newY, ArrayList<GamePiece> gamePieces, boolean playerOrGideonFlag, boolean dummyBoard) {
		
		actionPerformed = action1Moved;																		//@scenario1
		
		for(int i=0; i<gamePieces.size(); ++i) {
			GamePiece current = gamePieces.get(i);
			int currX = current.getX();
			int currY = current.getY();
			
			int location = (currX)*10 + (currY);															//current location of the game piece
	        
			int invertor = 1;
			
			if(playerOrGideonFlag) {
				invertor = -1;																				//if Gideon's making a move on game board reserve the condition check parameters
	        }
			
	        if((newX*10) + newY + 10*invertor == location &&												//Finding if there is a game piece that is straight above / straight below the recently
	        		recentlyMoved.getComputerPlayerFlag() != current.getComputerPlayerFlag()) {				//moved game piece
	        																								
	        	//saving the game piece
	        	spare = current;
	        	
	        	if(current instanceof Samurai || current instanceof Ninja) {								
	        		
	        		actionPerformed = action2DecreasedHealth;												//@scenario2
	        		decreaseHealth(current);
	        		break;
	        		
	        	}
	        	else if(current instanceof MiniSamurai || current instanceof MiniNinja) {
	        		
	        		actionPerformed = action3Killed;														//@scenario3
	        		kill(current);
	        		break;
	        		
	        	}
	        	else if(current instanceof King) {
	        		
	        		actionPerformed = action3Killed;														//@scenario4
	        		GameOver(current, dummyBoard);
	        		break;
	        		
	        	}
	        	
	        }

		}
			
	}
	
	
	/********************************************************************************************************
	 * 																										*
	 * Calls changePieceToMini(...) to decrease the health of the game piece/change the piece to mini		*
	 * 																										*
	 * @param piece - The game piece whose health is getting decreased or is getting changed to mini		*
	 * 																										*
	 * ******************************************************************************************************
	 */
	public void decreaseHealth(GamePiece piece) {
		
		game.changePieceToMini(piece);																		//changing game piece to mini
		
	}
	
	
	/************************************************************************************
	 * 																					*
	 * Calls removeGamePiece(...) to remove/delete the game piece from the game board	*
	 * 																					*
	 * @param piece - The game piece that is getting deleted from the game board		*
	 * 																					*
	 * **********************************************************************************
	 */
	public void kill(GamePiece piece) {
		
		game.removeGamePiece(piece);																		//Removing piece from game board
			
	}
	
	
	/************************************************************************************************************************
	 * 																														*
	 * GameOver(...) gets called to end the game as king was attacked														*
	 * This method check's whose King was attacked and remove's it from the game board										*
	 * Print's out the winner and the final state of game board after the king was attacked and exit's the game				*
	 * 																														*
	 * @param piece - The game piece that was attacked by the recently moved piece											*
	 * @param dummyBoard - To check if the MiniMax method is calling the GameOver method, if dummyBoard = true, then		*
	 * 					 - Method does NOT exit's the game and does not print's the winner and final game state				*
	 * 																														*
	 * **********************************************************************************************************************
	 */
	public void GameOver(GamePiece piece, boolean dummyBoard) {
		
		game.removeGamePiece(piece);																		//Removing piece from game board
		
		if(!dummyBoard) {
			game.updateGameBoard();																			//updating the String copy of the game board
			Print print = new Print();																		
			print.board(game);																				//Printing the entire game board
		
			if(piece.getComputerPlayerFlag())																//If true, then Gideon's King is getting attacked, hence player wins.
				System.out.println("\nYou win.");
		
			else if(!piece.getComputerPlayerFlag())															//If false, then player's King is getting attacked, hence Gideon wins.
				System.out.println("\nGideon wins.");

			System.exit(1);																					//Game Over since King got attacked
		
		}
	
	}
	
	
	/************************************************************************************************************
	 * 																											*	
	 * findKing(...) finds the king for player or Gideon depending on the playerOrGideon flag					*
	 * This method is used by MiniMax algorithm, to check if the opposite players king is still alive or not	*
	 * 																											*
	 * @param gamePieces - Arraylist that contains all the game pieces that are currently in the game			*
	 * @param playerOrGideon - Specifies if we want to find player's or Gideon's King							*
	 * @return - returns boolean value that represents if we found the king or not								*
	 * 																											*
	 * **********************************************************************************************************
	 */
	public boolean findKing(ArrayList<GamePiece> gamePieces, boolean playerOrGideon) {

		boolean kingFound = false;
		
		for(int i=0; i<gamePieces.size(); ++i) {
			
			if(gamePieces.get(i) instanceof King) {															//Finding the king piece in the game pieces arraylist
				
				if(gamePieces.get(i).getComputerPlayerFlag() == playerOrGideon) {							//Making sure that is the king we want to find
					
					kingFound = true;																		//Setting the kingFound flag to true
					break;
				}
				
			}
			
		}
		
		return kingFound;
		
	}
	
	
	/****************************************************************************************************************************
	 * 																															*
	 * retractMove(...) method undoes the previous move, this method is used by MiniMax algorithm								*
	 * This method helps MiniMax algorithm to maintain the previous game board state											*
	 * @scenario1 - previous action performed was decreaseHealth and hence this scenario will restore the health that was lost	*
	 * @scenario2 - previous action performed was Kill and hence this scenario will restore the game piece that was killed		*
	 * 																															*
	 * @param move - Stores the move that was recently executed on the board													*
	 * @param gameB - The current GameBoard state																				*
	 * @param gamePieces - The current gamePieces arrayList																		*
	 * @param flag - To determine if player or Gideon is making the move														*
	 * @return - returns the gameState after retracting the current move														*
	 * 																															*
	 * **************************************************************************************************************************
	 */
	public GameBoard retractMove(String move, GameBoard gameB, ArrayList<GamePiece> gamePieces, boolean flag) {
		
		if(actionPerformed.equals(action2DecreasedHealth)) {												//@scenario1
			
			int locX = spare.getX();
			int locY = spare.getY();
			for(int i=0; i<gamePieces.size(); ++i) {
				
				GamePiece current = gamePieces.get(i);
				
				if(locX == current.getX() && locY == current.getY()) {										//Finding the piece whose health was decreased in the previous move
					
					gamePieces.remove(i);																	//Removing the piece
					gamePieces.add(spare);																	//Re-storing the game piece 
					break;
					
				}
				
			}
			
		}
		
		else if(actionPerformed.equals(action3Killed)) {													//@scenarrio2
			gamePieces.add(spare);																			//adding the piece that was removed in the previous move
		}
		
		String a = move.substring(0, 2);
		String b = move.substring(2, 4);
		
		move = b+a;																							//reversing the recently performed move
	
		return playMove(move, gameB,  gamePieces,  flag, true, false);										//calling the play move method with the reversed move and returning the game state
	}
	
	
	/********************************************************************
	 * 																	*
	 * @returns - the action was performed by the previous move			*
	 * 																	*
	 * ******************************************************************
	 */
	public String getActionPerformed() {
		return this.actionPerformed;
	}
	
}