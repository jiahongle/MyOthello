package ca.utoronto.utm.othello.viewcontroller;
import ca.utoronto.utm.othello.model.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class UndoEventHandler implements EventHandler<ActionEvent> {
	
	private Othello othello;
	private UndoOperator undoStack;
	private RedoOperator redoStack;
	private GamemodeController gamemodeController;
	private Button undoButton, redoButton;
	
	public UndoEventHandler(Othello othello, UndoOperator undoStack, RedoOperator redoStack, Button undoButton, Button redoButton, GamemodeController gamemodeController) {
		this.othello = othello;
		this.undoStack = undoStack;
		this.redoStack = redoStack;
		this.gamemodeController = gamemodeController;
		this.undoButton = undoButton;
		this.redoButton = redoButton;
	}
	
	@Override
	public void handle(ActionEvent event) {
		String source = ((Button)event.getSource()).getText();
		if (source.equals("Undo")) {
			this.undoStack.undo(othello, redoStack, redoButton, undoButton, this.gamemodeController.getGamemode());
		}
	}
}
