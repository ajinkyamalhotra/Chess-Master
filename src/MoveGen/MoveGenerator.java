/**
 * @author Ajinkya Malhotra
 */
package MoveGen;
import java.util.*;
import GamePieces.*;

public class MoveGenerator {
	
   static ArrayList<GamePiece> gamePieces = null;
   static HashMap<Integer, GamePiece> gamePiecesMap = new HashMap<Integer, GamePiece>();

   public static void MakeMap() {
	   gamePiecesMap = new HashMap<Integer, GamePiece>();
	   for(GamePiece gp : gamePieces) {
	         int locX = gp.getX()+1;
	         int locY = gp.getY()+1;
	         int loc = locX*10 + locY;
	         gamePiecesMap.put(loc, gp);
	   }
   }
   
   /**
    * Generates legal moves for Gideon or Player
    * 
    * @param playerOrGideon - true = Generate Gideon's move | false = Generate Player's move
    * @param gamePiecesA - ArrayList storing all the game pieces
    * @return - return's all the moves stored in an ArrayList of Integers
    */
   public static ArrayList<Integer> Gen(boolean playerOrGideon, ArrayList<GamePiece> gamePiecesA) {
   		
      gamePieces = gamePiecesA;
      
      ArrayList<Integer> moves = new ArrayList<Integer>();
   		
      for(int i=0; i<gamePieces.size(); ++i) {
      		
         GamePiece currentPiece = gamePieces.get(i);
         int locX = currentPiece.getX()+1;
         int locY = currentPiece.getY()+1;
         int loc = locX*10 + locY;

         if(currentPiece.getComputerPlayerFlag() == playerOrGideon)
        	 currentPiece.genMoves(moves, gamePiecesA, currentPiece, loc);

      }
      
      return moves;
   }
	
}
