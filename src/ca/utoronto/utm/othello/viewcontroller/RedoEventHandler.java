package ca.utoronto.utm.othello.viewcontroller;
import ca.utoronto.utm.othello.model.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class RedoEventHandler implements EventHandler<ActionEvent>{

	private Othello othello;
	private RedoOperator redoStack;
	private UndoOperator undoStack;
	private GamemodeController gamemodeController;
	private Button redoButton;
	
	public RedoEventHandler(Othello othello, RedoOperator redoStack, UndoOperator undoStack, Button redoButton, GamemodeController gamemodeController) {
		this.othello = othello;
		this.redoStack = redoStack;
		this.undoStack = undoStack;
		this.gamemodeController = gamemodeController;
		this.redoButton = redoButton;
	}
	
	@Override
	public void handle(ActionEvent event) {
		String source = ((Button)event.getSource()).getText();
		if (source == "Redo") {
			this.redoStack.redo(othello, undoStack, redoButton, this.gamemodeController.getGamemode());
		}
	}
}
