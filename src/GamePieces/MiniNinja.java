/**
 * @author ajinma
 */

package GamePieces;

import java.util.ArrayList;

public class MiniNinja extends GamePiece{

	/**
	 * Constructor for game piece MiniNinja
	 * 
	 * @param X - x co-ordinate of the game piece MiniNinja
	 * @param Y - y co-ordinate of the game piece MiniNinja
	 * @param computerPlayer - flag to determine if an instance of MiniNinja is Gideon's or player's
	 */
	public MiniNinja(int X, int Y, boolean computerPlayer) {
		
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
	
	private void generateAttackMoves(ArrayList<Integer> moves, ArrayList<GamePiece> gamePieces, GamePiece currentPiece, int loc) {
		int move1 = loc; int move2 = loc;
		boolean move1Flag = true; boolean move2Flag = true;
		
		//For Generating Gideon's attack moves
        if(this.getComputerPlayerFlag()) {
           if(move1+9< 87 || move2+11< 87) {
           	
              move1 += 9;
              if(move1%10 == 0)
                 move1 = 999999;
           	
              if(move1 < 87 && move1Flag) {
            	  
            	  if(checkLegalMove(move1))
            		  moves = negative10(move1, currentPiece, loc, moves);
            	  
            	  else
            		  move1Flag = false;
              }
           	
              move2 += 11;
              if(move2%10 == 8)
                 move2 = 999999;
           	
              if(move2< 87 && move2Flag) {
            	  
            	  if(checkLegalMove(move2))
            		  moves = negative10(move2, currentPiece, loc, moves);
              
            	  else 
            		  move2Flag = false;
              }
           
           }
        	
        }
        
      //For Generating player's attack moves
        else if(!this.getComputerPlayerFlag()) {
           if(move1-9>10 || move2-11>10) {
           	
              move1 -= 9;
              if(move1%10 == 8)
                 move1 = -999999;
           	
              if(move1 > 10 && move1Flag) {
            	  
            	  if(checkLegalMove(move1))
            		  moves = plus10(move1, currentPiece, loc, moves);
              
            	  else
            		  move1Flag = false;
              }
           	
              move2 -= 11;
              if(move2%10 == 0)
                 move2 = -999999;
           	
              if(move2 > 10 && move2Flag) {
            	  
            	  if(checkLegalMove(move2))
            		  moves = plus10(move2, currentPiece, loc, moves);
              
            	  else
            		  move2Flag = false;
              }
           		
           }
        
        }
	}

	private void generateNormalMoves(ArrayList<Integer> moves, ArrayList<GamePiece> gamePieces, GamePiece currentPiece, int loc) {
		int move1 = loc; int move2 = loc;
		boolean move1Flag = true; boolean move2Flag = true;
    	
		//For Generating player's moves
        if(!this.getComputerPlayerFlag()) {
           if(move1+9< 87 || move2+11< 87) {
           	
              move1 += 9;
              if(move1%10 == 0)
                 move1 = 999999;
           	
              if(move1 < 87)
                 if(checkLegalMove(move1))
                    moves.add((loc*100)+move1);
           	
           	
              move2 += 11;
              if(move2%10 == 8)
                 move2 = 999999;
           	
              if(move2< 87)
                 if(checkLegalMove(move2))
                    moves.add((loc*100)+move2);
           
           }
        }
        
      //For Generating Gideon's moves
        else if(this.getComputerPlayerFlag()) {
           if(move1-9>10 || move2-11>10) {
           	
              move1 -= 9;
              if(move1%10 == 8)
                 move1 = -999999;
           	
              if(move1 > 10)
                 if(checkLegalMove(move1))
                    moves.add((loc*100)+move1);
           	
           	
              move2 -= 11;
              if(move2%10 == 0)
                 move2 = -999999;
           	
              if(move2 > 10)
                 if(checkLegalMove(move2))
                    moves.add((loc*100)+move2);
           }
        
        }
	}
}
