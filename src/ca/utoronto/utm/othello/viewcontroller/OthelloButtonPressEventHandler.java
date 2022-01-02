package ca.utoronto.utm.othello.viewcontroller;
import ca.utoronto.utm.othello.model.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.util.ArrayList;
import ca.utoronto.utm.othello.model.*;
import javafx.scene.control.Button;

public class OthelloButtonPressEventHandler implements EventHandler<ActionEvent>{
	
	private Othello othello;
	private UndoOperator moveStack;
	private RedoOperator redoStack;
	private Button redoButton, undoButton;
	
	public OthelloButtonPressEventHandler(Othello othello, UndoOperator moveStack, RedoOperator redoStack, Button redoButton, Button undoButton) {
		this.othello = othello;
		this.moveStack = moveStack;
		this.redoStack = redoStack;
		this.redoButton = redoButton;
		this.undoButton = undoButton;
		
	}
	
	public void setGamemodeController(GamemodeController gamemodeController) {
	}
	
	@Override 
	public void handle(ActionEvent event) {
		
		OthelloButton source = (OthelloButton)event.getSource();
		
		int row = source.getRow();
		int col = source.getCol();
		Othello temp = othello.copy();
		if (temp.move(row, col)) {
			moveStack.acceptMove(new Move(row, col));
			redoStack.reset();
			redoButton.setDisable(true);
			undoButton.setDisable(false);
		}
		othello.move(row, col);
	}
}

