package ca.utoronto.utm.othello.viewcontroller;
import javafx.event.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Spinner;
import ca.utoronto.utm.othello.model.*;
import javafx.scene.layout.*;
import javafx.scene.control.Button;

public class TimerEventHandler implements EventHandler<ActionEvent> {
	
	private int minInitial;
	private int secInitial;
	private Spinner<Integer> minSpinner;
	private Spinner<Integer> secSpinner;
	private Label timer;
	private int min;
	private int sec;
	private Othello othello;
	private char currPlayer;
	private GridPane othelloGrid;
	private Label gameStatus, whosNext;
	private Button undoButton, redoButton;
	
	public TimerEventHandler(Label timer, Spinner<Integer> min, Spinner<Integer> sec,
Othello othello, char player, GridPane othelloGrid, Label gameStatus, Label whosNext,
Button undoButton, Button redoButton) {
		this.timer = timer;
		this.minInitial = (int)min.getValue();
		this.secInitial = (int)sec.getValue();
		this.min = (int)min.getValue();
		this.sec = (int)sec.getValue();
		this.othello = othello;
		this.currPlayer = player;
		this.minSpinner = min;
		this.secSpinner = sec;
		this.othelloGrid = othelloGrid;
		this.gameStatus = gameStatus;
		this.whosNext = whosNext;
		this.undoButton = undoButton;
		this.redoButton = redoButton;
	}
	
	public void handle(ActionEvent event) {
		if (this.othello.numMoves() == 0) {
			this.minInitial = (int)minSpinner.getValue();
			this.secInitial = (int)secSpinner.getValue();
			this.min = this.minInitial;
			this.sec = this.secInitial;
			
			if (this.secInitial < 10) {
				timer.setText("Time Remaining: " + this.min + ":0" + this.sec);
			}
			else {
				timer.setText("Time Remaining: " + this.min + ":" + this.sec);
			}
		}
		if (this.currPlayer == this.othello.getWhosTurn()) {
			if (this.sec > 0) {
				this.sec -= 1;
				if ((Integer.toString(this.sec).length()) == 1) {
					this.timer.setText("Time Remaining: " + this.min + ":0" + this.sec);
				}
				else {
					this.timer.setText("Time Remaining: " + this.min + ":" + this.sec);
				}
			}
			else {
				if (this.min > 0) {
					this.min -= 1;
					this.sec = 59;
					this.timer.setText("Time Remaining: " + this.min + ":" + this.sec);
				}
				else {
					this.timer.setText("Time Remaining: 00:00");
				}
			}
		}
		if (this.timer.getText() == "Time Remaining: 00:00" && this.othello.numMoves() > 0) {
			this.othelloGrid.setDisable(true);
			this.undoButton.setDisable(true);
			this.redoButton.setDisable(true);
			this.whosNext.setText("Game Over!");
			
			switch (this.currPlayer) {
			case OthelloBoard.P1:
				this.gameStatus.setText("Player 2 WINS!");
				break;
			case OthelloBoard.P2:
				this.gameStatus.setText("Player 1 WINS!");
				break;
			}
		}
	}
}
