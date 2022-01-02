package ca.utoronto.utm.othello.viewcontroller;
import javafx.event.*;
import ca.utoronto.utm.othello.model.*;
import javafx.scene.layout.*;
import java.util.*;
import javafx.scene.control.ToggleButton;
import ca.utoronto.utm.util.Observer;
import ca.utoronto.utm.util.Observable;

public class AllMovesToggleEventHandler implements EventHandler<ActionEvent>, Observer {
	
	private Othello othello;
	private GridPane othelloGrid;
	private ToggleButton button;
	
	public AllMovesToggleEventHandler(Othello othello, GridPane othelloGrid, 
										ToggleButton button) {
		this.othello = othello;
		this.othelloGrid = othelloGrid;
		this.button = button;
	}
	
	@Override
	public void handle(ActionEvent event) {
		
		ToggleButton source = (ToggleButton)event.getSource();
		
		if (source.isSelected()) {
			this.showAllMoves(othello, othello.getWhosTurn(), true);
		}
		else {
			this.showAllMoves(othello, othello.getWhosTurn(), false);
		}
		
	}
	
	private void showAllMoves(Othello othello, char player, boolean toggle) {
		
		for (int row = 0; row < Othello.DIMENSION; row++) {
			for (int col = 0; col < Othello.DIMENSION; col++) {
				
				Othello othelloCopy = othello.copy();

				if (othelloCopy.move(row, col)) {
					OthelloButton currButton = (OthelloButton)othelloGrid.getChildren()
												.get(row * Othello.DIMENSION + col);
					if (toggle) {
						if (currButton.getStyle() == OthelloButton.ogBtnStyle()) {
							currButton.setStyle(OthelloButton.allMovesBtnStyle());
						}
					}
					else {
						if (currButton.getStyle() != OthelloButton.hintBtnStyle()) {
							currButton.setStyle(OthelloButton.ogBtnStyle());
						}
					}
					
				}
				
			}
		}
	}
	@Override
	public void update(Observable o) {
		Othello othello = (Othello)o;
		if (this.button.isSelected()) {
			this.showAllMoves(othello, othello.getWhosTurn(), true);
		}
	}
}
