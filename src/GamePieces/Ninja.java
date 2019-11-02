/**
 * @author ajinma
 */

package GamePieces;

import java.util.ArrayList;

public class Ninja extends GamePiece{
	
	
	
	/**
	 * Constructor for game piece Ninja
	 * 
	 * @param X - x co-ordinate of the game piece Ninja
	 * @param Y - y co-ordinate of the game piece Ninja
	 * @param computerPlayer - flag to determine if an instance of Ninja is Gideon's or player's
	 */
	public Ninja(int X, int Y, boolean computerPlayer) {
		
		super(X, Y, computerPlayer);
		
	}
	
	/**
	 * Generate moves for a particular Game Piece
	 * @return - ArrayList with moves for this player
	 */
	public ArrayList<Integer> genMoves(ArrayList<Integer> moves, ArrayList<GamePiece> gamePieces, GamePiece currentPiece, int loc) { 
	    MakeMap(gamePieces);
	    generateAttackMoves(moves, gamePieces, currentPiece, loc);
	    generateNormalMoves(moves, gamePieces, currentPiece, loc);
		return moves;
	}
	
	/**
	    * addAttackMove() adds the attack move for the current game piece
	    * 
	    * @param moves - ArrayList containing all the moves
	    * @param gamePieces - ArrayList containing all the game pieces
	    * @param currentPiece - GamePiece for which we are adding attack move
	    * @param loc - Location of the current gamePiece
	    * @param playerOrGideon - Flag determining if current GamePiece is player's or Gideon's
	    * @return - returning the moves ArrayList after adding all the attack moves for the current GamePiece
	    */
	private void generateAttackMoves(ArrayList<Integer> moves, ArrayList<GamePiece> gamePieces, GamePiece currentPiece, int loc) {
		boolean move1Flag = true; boolean move2Flag = true;
		int move1 = loc; int move2 = loc;
        
		//For Generating Gideon's attack moves
        if(this.getComputerPlayerFlag()) {
           while(move1+9< 87 || move2+11< 87) {
           	
              move1 += 9;
              if(move1%10 == 0)
                 move1 = 999999;
           	
              if(move1 < 87 && move1Flag) {

                 if(this.checkLegalMove(move1)) 
                	 moves = negative10(move1, currentPiece, loc, moves);
              	
                 else
                     move1Flag = false;
              }
           	
              move2 += 11;
              if(move2%10 == 8)
                 move2 = 999999;
           	
              if(move2< 87 && move2Flag) {
              	
            	  if(this.checkLegalMove(move2))
            		  moves = negative10(move2, currentPiece, loc, moves);
              
            	  else
            		  move2Flag = false;
              }
           
           }
        	
        }
        
      //For Generating player's attack moves
        else if(!this.getComputerPlayerFlag()) {																		
           while(move1-9>10 || move2-11>10) {
           	
              move1 -= 9;
              if(move1%10 == 8)
                 move1 = -999999;
           	
              if(move1 > 10 && move1Flag) {
            	  
            	  if(this.checkLegalMove(move1))
            		  moves = plus10(move1, currentPiece, loc, moves);
              
            	  else
            		  move1Flag = false;
              }
           	
              move2 -= 11;
              if(move2%10 == 0)
                 move2 = -999999;
           	
              if(move2 > 10 && move2Flag) {
                 
            	  if(this.checkLegalMove(move2))
            		  moves = plus10(move2, currentPiece, loc, moves);
              
            	  else
            		  move2Flag = false;
              }
           		
           }
        
        }
	}

	/**
	    * Generates legal moves for Gideon or Player
	    * 
	    * @param playerOrGideon - true = Generate Gideon's move | false = Generate Player's move
	    * @param gamePiecesA - ArrayList storing all the game pieces
	    * @return - return's all the moves stored in an ArrayList of Integers
	    */
	private void generateNormalMoves(ArrayList<Integer> moves, ArrayList<GamePiece> gamePieces, GamePiece currentPiece, int loc) {
		
		//Calculating 2 different moves in 1 step since the Ninja piece can move in left or right direction
		int move1 = loc; int move2 = loc;
		
		//boolean flags to stop generating illegal moves, once found any
        boolean move1Flag = true; boolean move2Flag = true;
     	
      //For Generating player's moves
        if(!this.getComputerPlayerFlag()) {
     	   
        	//add move1 and move2 flag condition in the while loop and add for the other while loop's
           while(move1+9< 87 || move2+11< 87) {
           	
              move1 += 9;
              if(move1%10 == 0)
                 move1 = 999999;
           		
              if(move1 < 87 && move1Flag) {
              	
                 if(checkLegalMove(move1)) 
                    moves.add((loc*100)+move1);
                 
                 else
                    move1Flag = false;
              
              }
           	
              move2 += 11;
              if(move2%10 == 8)
                 move2 = 999999;
           	
              if(move2< 87 && move2Flag) {
              	
                 if(checkLegalMove(move2))
                    moves.add((loc*100)+move2);	
                 	
                 else
                    move2Flag = false;
              
              }
           
           }
        	
        }
        
      //For Generating Gideon's moves
        else if(this.getComputerPlayerFlag()) {
           while(move1-9>10 || move2-11>10) {
           	
              move1 -= 9;
              if(move1%10 == 8)
                 move1 = -999999;
           	
              if(move1 > 10 && move1Flag) {
              	
                 if(checkLegalMove(move1)) 
                    moves.add((loc*100)+move1);
                 
                 else
                    move1Flag = false;
              }
           	
              move2 -= 11;
              if(move2%10 == 0)
                 move2 = -999999;
           	
              if(move2 > 10 && move2Flag) {
              	
                 if(checkLegalMove(move2)) 
                    moves.add((loc*100)+move2);
                 
                 else
                    move2Flag = false;
              	
              }
           }
        }
	}
}
