/**
 * @author ajink
 */

package MiniMax;
import java.util.ArrayList;
import Board.*;
import GamePieces.*;
import MoveGen.*;


public class MiniMax {
	
	GameBoard game = null;
	ArrayList<GamePiece> gamePieces = null;
	ArrayList<Integer> moves = null;
	ArrayList<Move> movesObj = new ArrayList<Move>();
	private int numberOfNodesVisited = 0;
	
	/////////////////////////////////////////////////////////////////////////////////
	/*/*/									    /*/*/								/*/*/
	/*/*/                     private int MAX_DEPTH = 4;			    /*/*/
	/*/*/									    /*/*/								/*/*/
	/////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * MiniMax constructor to initialize movesObj with all the available moves
	 * @param game - The current GameBoard State
	 * @param moves - All the possible moves according to the current game state
	 */
	public MiniMax(GameBoard game, ArrayList<Integer> moves) {
		this.game = game;
		this.gamePieces = game.getGamePiecesArray();
		this.moves = moves;
		for(int i=0; i<this.moves.size(); ++i) {
			
			movesObj.add(new Move(moves.get(i)));
			
		}
	}
	
	/**
	 * Increments MAX_DEPTH of tree search by 1
	 */
	public void incrementMAX_DEPTH() {
		
		this.MAX_DEPTH++;
		
	}
	
	/**
	 * Decrements MAX_DEPTH of tree search by 1
	 */
	public void decrementMAX_DEPTH() {
		
		this.MAX_DEPTH--;
		
	}
	
	/**
	 * @returns the current MAX_DEPTH
	 */
	public int getMAX_DEPTH() {
		return this.MAX_DEPTH;
	}
	
	/**
	 * Starts the MiniMax Alg. by call the MIN() method
	 * @returns the best move out of all the possible moves
	 */
	public String start() {
		Best best = new Best("", -99999999);
	
		for(int i=0; i<movesObj.size(); ++i) {
			
			Move currentMove = movesObj.get(i);
			UpdateBoard boardUpdater = new UpdateBoard();
			
			//save gamestate here 
			//ComputerMove = true and DummyBoard = true
			boardUpdater.playMove(currentMove.move+"", game, gamePieces, true, true, true);
			
			
			//System.out.println(" "+i+" Doing move at Start and at level 0");
			//Print print = new Print();
			//print.board(game);
			
			numberOfNodesVisited++;
			
			/*if(currentMove.move == 7424) {
				System.out.println();
				Print print = new Print();
				print.board(game);
			}*/
			
			currentMove.score = this.MIN(0, -Integer.MAX_VALUE);
			
			if(currentMove.score > best.score) {
				best.move = ""+currentMove.move;
				best.score = currentMove.score;
			}

			
			//retract currentMove.move from the board and restore the gamestate here
			boardUpdater.retractMove(currentMove.move+"", game, gamePieces, true);
			
			
			//Print print2 = new Print();
			//print2.board(game);
		}
		
		System.out.println("\n Number of nodes visited = " + numberOfNodesVisited);
		
		
		for(int i=0; i<movesObj.size(); ++i) {
			
			Move currentMove = movesObj.get(i);
			
			System.out.println("Moves is "+currentMove.move+" and the score is "+currentMove.score);
			
		}
	
		//make best.move on the board
		return best.move;
	}
	
	/**
	 * Evaluates the best move for Gideon(Computer Player)
	 * 
	 * @param depth - Current level at which MiniMax Alg. is operating
	 * @param bestScore - Current best score of the best move
	 * @return the score of the best move
	 */
	public int MIN(int depth, int bestScore) {
		
		UpdateBoard boardUpdater = new UpdateBoard();
		
		Best best = new Best("", 99999999);
		
		if(!boardUpdater.findKing(gamePieces, true))
			return -99999 - depth;
		
		else if (MAX_DEPTH < depth)
			return EVAL();
		
		else {
			
			ArrayList<Move> movesObjMin = new ArrayList<Move>();
			ArrayList<GamePiece> gamePiecesMin = game.getGamePiecesArray();
			ArrayList<Integer> movesMin = MoveGenerator.Gen(false, gamePiecesMin);
			for(int i=0; i<movesMin.size(); ++i) {
				
				movesObjMin.add(new Move(movesMin.get(i)));
				
			}
			
			depth += 1;
			
			for(int i=0; i<movesObjMin.size(); ++i) {
				
				Move currentMove = movesObjMin.get(i);
				
				//save gamestate here 
				game = boardUpdater.playMove(currentMove.move+"", game, gamePiecesMin, false, true, true);
				
				//System.out.println(" "+i+" Doing move at Min and at level "+depth);
				//Print print = new Print();
				//print.board(game);
				
				numberOfNodesVisited++;
				currentMove.score = this.MAX(depth, best.score);
				
				if(currentMove.score < best.score) {
					best.move = ""+currentMove.move;
					best.score = currentMove.score;
				}
				
				//retract currentMove.move from the board
				boardUpdater.retractMove(currentMove.move+"", game, gamePieces, true);
				
			}
			
		}
			
		return best.score;
	}

	/**
	 * Evaluates the best move for Player
	 * 
	 * @param depth - Current level at which MiniMax Alg. is operating
	 * @param bestScore - Current best score of the best move
	 * @return the score of the best move
	 */
	public int MAX(int depth, int bestScore) {
		
		UpdateBoard boardUpdater = new UpdateBoard();
		
		Best best = new Best("", -999999999);
		
		if(!boardUpdater.findKing(gamePieces, false)) 
			return 99999 - depth;
		
		else if (MAX_DEPTH < depth)
			return EVAL();
		
		else {
			
			ArrayList<Move> movesObjMax = new ArrayList<Move>();;
			ArrayList<GamePiece> gamePiecesMax = game.getGamePiecesArray();
			ArrayList<Integer> movesMax = MoveGenerator.Gen(true, gamePiecesMax);
			for(int i=0; i<movesMax.size(); ++i) {
				
				movesObjMax.add(new Move(movesMax.get(i)));
				
			}

			
			depth += 1;
			
			for(int i=0; i<movesObjMax.size(); ++i) {
				
				Move currentMove = movesObjMax.get(i);
				
				//save gamestate here 
				game = boardUpdater.playMove(currentMove.move+"", game, gamePiecesMax, true, true, true);
				
				
				//System.out.println(" "+i+" Doing move at Max and at level "+depth);
			    //Print print = new Print();
				//print.board(game);
				
				numberOfNodesVisited++;
				
				currentMove.score = this.MIN(depth, best.score);
				
				if(currentMove.score > best.score) {
					best.move = ""+currentMove.move;
					best.score = currentMove.score;
				}
				
				//retract currentMove.move from the board
				boardUpdater.retractMove(currentMove.move+"", game, gamePieces, true);
				
				
			}
			
		}
			
		return best.score;
	}
	
	/**
	 * Evaluates the score of every move made on the virtual board by counting the remaining computer pieces and player pieces
	 * @returns the difference between gideons piece count and player piece count
	 */
	public int EVAL() {
		int playerPieceCount = 0;
		int gideonPieceCount = 0;
		for(int i=0; i<gamePieces.size(); ++i) {
			
			if(gamePieces.get(i).getComputerPlayerFlag())
				gideonPieceCount++;
			
			else
				playerPieceCount++;
		}
		
		return gideonPieceCount - playerPieceCount;
	}
}
