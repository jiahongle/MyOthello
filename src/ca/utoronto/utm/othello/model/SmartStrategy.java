package ca.utoronto.utm.othello.model;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class SmartStrategy implements OthelloStrategy {
	private PlayerSmart playerSmart;
	private Othello othello;
	private ArrayList<Move> moveList = new ArrayList<Move>();
	
	public SmartStrategy(Othello othello, char player) {
		this.playerSmart = new PlayerSmart(othello, player);
		this.othello = othello;
	}
	public Move getLatest() {
		ArrayList<Move> list = this.moveList;
		return list.get(list.size()-1);
	}
	public void move() {
		Move move = this.playerSmart.getMove();
		this.othello.move(move.getRow(), move.getCol());
		this.moveList.add(move);
	}
}
