/**
 * @author ajink
 */
package MiniMax;

public class Best {
	
	String move = null;						//Stores the best move
	int score = -99999;						//Stores the best move's score
	
	/**
	 * @param m - move
	 * @param s - score
	 */
	public Best(String m, int s) {
		this.move = m;
		this.score = s;
	}
}