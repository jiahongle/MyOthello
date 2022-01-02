package ca.utoronto.utm.othello.model;
import ca.utoronto.utm.util.*;
import java.util.ArrayList;
import ca.utoronto.utm.othello.model.*;
import ca.utoronto.utm.othello.viewcontroller.OpponentChooserEventHandler;
import javafx.scene.control.Button;

public class UndoOperator extends Observable{
	
	ArrayList<Move> moveStack;
	GamemodeController gamemodeController;
	
	public UndoOperator() {
		moveStack = new ArrayList<Move>();
	}
	public void setGamemodeController(GamemodeController gamemodeController) {
		this.gamemodeController = gamemodeController;
	}
	public void acceptMove(Move move) {
		this.moveStack.add(move);
	}

	public ArrayList<Move> getMoveStack(){
		return this.moveStack;
	}
	
	public void reset() {
		this.moveStack.clear();
	}

	public void undo(Othello othello, RedoOperator redoStack, Button redoButton, Button undoButton, String gameMode) {
		if (gameMode.equals(OpponentChooserEventHandler.HvH) && this.moveStack.size()>=1) {
			redoStack.acceptMove(this.moveStack.get(this.moveStack.size()-1));
			this.moveStack.remove(this.moveStack.size()-1);
		}
		else if (this.moveStack.size() >= 2) {
			redoStack.acceptMove(this.moveStack.get(this.moveStack.size()-1));
			this.moveStack.remove(this.moveStack.size()-1);
			redoStack.acceptMove(this.moveStack.get(this.moveStack.size()-1));
			this.moveStack.remove(this.moveStack.size()-1);
		}
		String tempGamemode = this.gamemodeController.getGamemode();
		this.gamemodeController.setGamemodeUnchange(OpponentChooserEventHandler.HvH);
		othello.restart();
		for (int i=0; i<this.moveStack.size(); i++) {
			Move move = this.moveStack.get(i);
			othello.move(move.getRow(), move.getCol());
		}
		this.gamemodeController.setGamemodeUnchange(tempGamemode);
		redoButton.setDisable(false);
		
	}
}
