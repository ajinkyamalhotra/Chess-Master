import java.util.ArrayList;

public class UpdateBoard {

	public static void playMove(String move, GameBoard game, boolean flag) {
			
		int locX = Integer.parseInt(move.charAt(0)+"")-1;
		int locY = Integer.parseInt(move.charAt(1)+"")-1;
		
		int locXnew = Integer.parseInt(move.charAt(2)+"") -1;
		int locYnew = Integer.parseInt(move.charAt(3)+"") -1;
		
		ArrayList<GamePiece> gamePieces = game.getGamePiecesArray();
			
		GamePiece recentlyMoved = null; 
		for(int i=0; i<gamePieces.size(); ++i) {
			recentlyMoved = gamePieces.get(i);
			if(locX == recentlyMoved.getX() && locY == recentlyMoved.getY()) {
				recentlyMoved.setX(locXnew); recentlyMoved.setY(locYnew);
				break;
			}
			
		}
		checkStatus(recentlyMoved, gamePieces, flag);
	}
	
	public static void checkStatus(GamePiece recentlyMoved, ArrayList<GamePiece> gamePieces, boolean playerOrGideonFlag) {
		
		
		
	}
	
	public static void decreaseHealth() {
		
		
		
	}
	
	public static void kill() {
		
		
		
	}
	
	public static void GameOver() {
		
		
		
	}
	
}
