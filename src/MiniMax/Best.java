/**
 * @author ajink
 */
package MiniMax;

public class Best {
	
	//Stores the best move
	String move = null;
	
	//Stores the best move's score
	int score = -99999;						
	
	/**
	 * @param m - move
	 * @param s - score
	 */
	public Best(String m, int s) {
		this.move = m;
		this.score = s;
	}
}