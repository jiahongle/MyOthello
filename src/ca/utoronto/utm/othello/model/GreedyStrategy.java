package ca.utoronto.utm.othello.model;

import java.util.ArrayList;

import javafx.util.Duration;

public class GreedyStrategy implements OthelloStrategy{
	private PlayerGreedy playerGreedy; 
	private Othello othello;
	private ArrayList<Move> moveList = new ArrayList<Move>();
	
	public GreedyStrategy(Othello othello, char player) {
		this.playerGreedy = new PlayerGreedy(othello, player); 
		this.othello = othello;
	}
	
	public Move getLatest() {
		ArrayList<Move> list = this.moveList;
		return list.get(list.size()-1);
	}
	
	public void move() {
		Move move = this.playerGreedy.getMove();
		this.othello.move(move.getRow(), move.getCol()); 
		this.moveList.add(move);
	}
}
