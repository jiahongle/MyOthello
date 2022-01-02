package ca.utoronto.utm.othello.viewcontroller;

import javafx.event.EventHandler;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.Random;
import ca.utoronto.utm.othello.model.Othello;
import ca.utoronto.utm.util.Observable;
import ca.utoronto.utm.util.Observer;
import javafx.event.ActionEvent;

public class HintEventHandler implements Observer, EventHandler<ActionEvent> {
	private Othello othello;
	private GridPane vOthello;
	private RadioButton greedyHntBtn, randomHintBtn, noHintBtn;
	private ToggleButton allMovesToggle;
	private char player;
	
	
	
	public HintEventHandler (Othello othello, GridPane vOthello, RadioButton greedyHintBtn, 
		RadioButton randomHintBtn, RadioButton noHintBtn, ToggleButton allMovesToggle) {
		this.greedyHntBtn = greedyHintBtn;
		this.randomHintBtn = randomHintBtn;
		this.noHintBtn = noHintBtn;
		this.othello = othello;
		this.vOthello = vOthello;
		this.allMovesToggle =  allMovesToggle;
	}
	@Override
	public void update(Observable o) {
		displayMove();
		
		
	}
	@Override
	public void handle(ActionEvent event) {
		clearHint();
		displayMove();
		
	}
	/**
	 * Displays a greedy or random move on the board as a hint (depending on what the player selects)
	 */
	public void displayMove(){	
		if (!othello.isGameOver()){
			this.player = othello.getWhosTurn();
			if (this.greedyHntBtn.isSelected()) {
				int greedyPos = greedyHint();
				this.vOthello.getChildren().get(greedyPos).setStyle(OthelloButton.hintBtnStyle());
			}
			
			else if (this.randomHintBtn.isSelected()) {
				this.vOthello.getChildren().get(randomHint()).setStyle(OthelloButton.hintBtnStyle());;
			}
			else if (this.noHintBtn.isSelected()) {
				
			}
		}
	
	}
	

	/**
	 * 
	 * @return the position of the button that results in the greatest increase
	 * of the current player's chips 
	 */
	private void clearHint() {
		for (int row = 0; row < Othello.DIMENSION; row++) {
			for (int col = 0; col < Othello.DIMENSION; col++) {
				
				OthelloButton currButton = (OthelloButton)this.vOthello.getChildren()
						.get(row * Othello.DIMENSION + col);
				if (currButton.getStyle() == OthelloButton.hintBtnStyle()  && allMovesToggle.isSelected()) {
					
					currButton.setStyle(OthelloButton.allMovesBtnStyle());
				} else if(currButton.getStyle() == OthelloButton.hintBtnStyle()){
					
					currButton.setStyle(OthelloButton.ogBtnStyle());
				}
			}
		}
		
	}
	
	private int greedyHint () {
		int bestPos = 0;
		int currentPos = 0;
		int maxChipsGained = 0;
		
		for(int row=0;row<Othello.DIMENSION;row++) {
			for(int col=0;col<Othello.DIMENSION;col++) {
				Othello othelloCopy = this.othello.copy();
				if (othelloCopy.move(row, col)) {
					 if (othelloCopy.getCount(this.player) - this.othello.getCount(this.player) 
							 > maxChipsGained) {
						 bestPos = currentPos;
						 maxChipsGained = othelloCopy.getCount(this.player) - this.othello.getCount(this.player);
					 }
				}
				currentPos ++;
			}
		}
		return bestPos;
	}
	
	private int randomHint() {
		ArrayList<Integer> moves = new ArrayList<Integer>();
		for(int row=0;row<Othello.DIMENSION;row++) {
			for(int col=0;col<Othello.DIMENSION;col++) {
				Othello othelloCopy = this.othello.copy();
				if (othelloCopy.move(row, col)) {
					moves.add(row * Othello.DIMENSION + col);
				}
			}
		}
		Random rand = new Random();
		return moves.get(rand.nextInt(moves.size()));
		
	}
}
