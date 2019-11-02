package GamePieces;

import java.util.ArrayList;

/**
 * @author ajinma
 */
public class Samurai extends GamePiece{
	
	
	/**
	 * Constructor for game piece Samurai
	 * 
	 * @param X - x co-ordinate of the game piece Samurai
	 * @param Y - y co-ordinate of the game piece Samurai
	 * @param computerPlayer - flag to determine if an instance of Samurai is Gideon's or player's
	 */
	public Samurai(int X, int Y, boolean computerPlayer) {
		
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
        
           while(move1+1< 87 || move2-1> 10) {
           	
              move1 += 1;
              if(move1%10 == 0)
                 move1 = 999999;
           	
              if(move1 < 87 && move1Flag) {
            	  
            	  if(checkLegalMove(move1))
            		  moves = negative10(move1, currentPiece, loc, moves);
            	  
            	  else
            		  move1Flag = false;
              }
           	
              move2 -= 1;
              if(move2%10 == 8)
                 move2 = -999999;
           	
              if(move2 > 10 && move2Flag) {
            	  
            	  if(checkLegalMove(move2))
            		  moves = negative10(move2, currentPiece, loc, moves);
              
            	  else
            		  move2Flag = false;
              }
           
           }
        	
        }
        
      //For Generating player's attack moves
        else if(!this.getComputerPlayerFlag()) {
        	
           while(move1+1< 87 || move2-1> 10) {
           	
              move1 += 1;
              if(move1%10 == 0)
                 move1 = 999999;
           	
              if(move1 < 87 && move1Flag) {
            	  
            	  if(checkLegalMove(move1))
            		   moves = plus10(move1, currentPiece, loc, moves);
            	  
            	  else
            		  move1Flag = false;
              }
           	
              move2 -= 1;
              if(move2%10 == 8)
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
		int move1 = loc;
		
		//boolean flags to stop generating illegal moves, once found any
        boolean move1Flag = true;
     	
      //For Generating player's moves
        if(!this.getComputerPlayerFlag()) {
           while(move1+10< 87) {
           	
              move1 += 10;
              if(move1%10 == 0)
                 move1 = 999999;
           	
              if(move1 < 87 && move1Flag) {
              	
                 if(checkLegalMove(move1)) 
                    moves.add((loc*100)+move1);
                 
                 else
                    move1Flag = false;
              
              }
           
           }
        	
        }
        				
      //For Generating Gideon's moves
        else if(this.getComputerPlayerFlag()) {
           while(move1-10>10) {
           	
              move1 -= 10;
              if(move1/10 == 0)
                 move1 = -999999;
           	
              if(move1 > 10 && move1Flag) {
              	
                 if(checkLegalMove(move1)) 
                    moves.add((loc*100)+move1);
                 
                 else
                    move1Flag = false;
              }
           		
           }
        
        }
	}

}
