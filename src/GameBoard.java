import java.util.ArrayList;

public class GameBoard {

	ArrayList<GamePiece> board = new ArrayList<GamePiece>();
	
	private String[][] gameBoard = new String[8][7];
	
	public GameBoard() {
		board.add(new King(7, 3, true));
		board.add(new Ninja(6, 0, true));
		board.add(new Ninja(6, 1, true));
		board.add(new Ninja(6, 2, true));
		board.add(new Samurai(6, 4, true));
		board.add(new Samurai(6, 5, true));
		board.add(new Samurai(6, 6, true));
		board.add(new MiniSamurai(5, 0, true));
		board.add(new MiniSamurai(5, 1, true));
		board.add(new MiniSamurai(5, 2, true));
		board.add(new MiniNinja(5, 4, true));
		board.add(new MiniNinja(5, 5, true));
		board.add(new MiniNinja(5, 6, true));
		
		
		board.add(new King(0, 3, false));
		board.add(new Ninja(1, 4, false));
		board.add(new Ninja(1, 5, false));
		board.add(new Ninja(1, 6, false));
		board.add(new Samurai(1, 0, false));
		board.add(new Samurai(1, 1, false));
		board.add(new Samurai(1, 2, false));
		board.add(new MiniSamurai(2, 4, false));
		board.add(new MiniSamurai(2, 5, false));
		board.add(new MiniSamurai(2, 6, false));
		board.add(new MiniNinja(2, 0, false));
		board.add(new MiniNinja(2, 1, false));
		board.add(new MiniNinja(2, 2, false));
		
		this.updateGameBoard();
	
	}
	
	public ArrayList<GamePiece> getGamePiecesArray() {
		
		return this.board;
		
	}
	
	public void updateGameBoard() {
		
		this.gameBoard = new String[8][7];
		
		for(int i=0; i<board.size(); ++i) {
			
			this.gameBoard[board.get(i).getX()][board.get(i).getY()] = board.get(i).printPiece();
		
		}
	
	}
	
	public void changePieceToMini(GamePiece piece) {
		int X = piece.getX();
		int Y = piece.getY();
		board.remove(piece);
		
		if(piece instanceof Samurai) {
			
			board.add(new MiniSamurai(X, Y, piece.getComputerPlayerFlag()));
			
		}
		
		else if(piece instanceof Ninja) {
			
			board.add(new MiniNinja(X, Y, piece.getComputerPlayerFlag()));
			
		}
	}
	
	public void removeGamePiece(GamePiece piece) {
		board.remove(piece);
	}
	
	public void printBoard(GameBoard game) {
		
		//game.updateGameBoard(game);
		
		System.out.println("\n   --------------------- Computer");
		
		for(int i=gameBoard.length-1; i>=0; --i) {
			System.out.print(" "+(i+1)+" ");
			
			for(int j=0; j<gameBoard[i].length; ++j) {
			
				if(gameBoard[i][j] == null)
					System.out.print("   ");
				
				else
					System.out.print(gameBoard[i][j]);
			
			}
			
			System.out.println();
		
		}
		
		System.out.println("   --------------------- Player");
		System.out.println("    A  B  C  D  E  F  G");
		
	}
}
