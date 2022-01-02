package ca.utoronto.utm.othello.viewcontroller;
import javafx.event.*;
import javafx.scene.layout.GridPane;
import ca.utoronto.utm.othello.model.*;
import javafx.scene.control.*;

public class GameControlsEventHandler implements EventHandler<ActionEvent>{
	
	private Othello othello;
	private GridPane othelloGrid;
	private UndoOperator undoStack;
	private RedoOperator redoStack;
	
	public GameControlsEventHandler(Othello othello, GridPane othelloGrid, UndoOperator undoStack, RedoOperator redoStack) {
		this.othello = othello;
		this.othelloGrid = othelloGrid;
		this.undoStack = undoStack;
		this.redoStack = redoStack;
	}
	
	@Override
	public void handle(ActionEvent event) {
		String source = ((Button)event.getSource()).getText();
		if (source == "RESTART") {
			this.othello.restart();
			System.out.println("RESTARTING!");
			this.othelloGrid.setDisable(false);
			this.undoStack.reset();
			this.redoStack.reset();
		}
	}
}
