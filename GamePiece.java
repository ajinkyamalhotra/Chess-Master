
public class GamePiece {

	private int X;
	private int Y;
	private boolean computerPlayer;
	
	//X and Y are location co-ordinates and boolean value
	//determines if its a player piece or computer piece
	public GamePiece(int X, int Y, boolean computerPlayer) {
		
		this.X = X;
		this.Y = Y;
		this.computerPlayer = computerPlayer;
		
	}
	
	public void setX(int X) {
		
		this.X = X;
		
	}
	
	public void setY(int Y) {
		
		this.Y = Y;
		
	}
	
	public int getX() {
		
		return this.X;
		
	}
	
	public int getY() {
		
		return this.Y;
		
	}
	
	public boolean getComputerPlayerFlag() {
		
		return this.computerPlayer;
		
	}
	
	public String printPiece() {
		String player = "   ";
		
		if(this instanceof Samurai)
			player = " S";
		
		else if(this instanceof MiniSamurai)
			player = " s";
		
		else if(this instanceof Ninja)
			player = " J";
		
		else if(this instanceof MiniNinja)
			player = " j";
		else if(this instanceof King)
			player = " K";
		
		
		if(!(player.equals("X")) && this.computerPlayer)
			player +="c";
		
		else
			player +=" ";
		
		
		return player;
	}
	
}
