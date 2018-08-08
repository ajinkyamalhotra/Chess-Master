public class Best {

	String move = null;
	int score = -99999;
				
	public Best(String m, int s) {
		this.move = m;
		this.score = s;
	}
		
	public void setBestMove(String move) {
		this.move = move;
	}
		
	public void setBestScore(int score) {
		this.score = score;
	}
	
	public String getBestMove() {
		return this.move;
	}
		
	public int getBestScore() {
		return this.score;
	}
}