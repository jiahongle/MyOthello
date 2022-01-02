package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.Othello;
import ca.utoronto.utm.othello.model.OthelloBoard;
import ca.utoronto.utm.util.Observable;
import ca.utoronto.utm.util.Observer;
import javafx.scene.control.Label;

public class GameStatusLabel extends Label implements Observer{
	
	public GameStatusLabel(String gameStatus) {
		super(gameStatus);
	}
	public void update(Observable o) {
		
		Othello othello = (Othello) o;
		if (othello.isGameOver()) {
			switch (othello.getWinner()) {
			case OthelloBoard.P1:
				this.setText("Player 1 WINS!");
				break;
			case OthelloBoard.P2:
				this.setText("Player 2 WINS!");
				break;
			default:
				this.setText("DRAW!");
				break;
			}
		}
		else {
			this.setText("In Progress");
		}
	}
}
