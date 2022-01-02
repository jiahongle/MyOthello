package ca.utoronto.utm.othello.model;
import java.util.ArrayList;

public class RandomStrategy implements OthelloStrategy{
	private PlayerRandom playerRandom; 
	private Othello othello;
	private ArrayList<Move> moveList = new ArrayList<Move>();
	
	public RandomStrategy(Othello othello, char player) {
		this.playerRandom = new PlayerRandom(othello, player); 
		this.othello = othello;
	}

	public Move getLatest() {
		ArrayList<Move> list = this.moveList;
		return list.get(list.size()-1);
	}
	public void move() {
		Move move = this.playerRandom.getMove();
		this.othello.move(move.getRow(), move.getCol()); 
		this.moveList.add(move);
	}
}
