package ca.utoronto.utm.othello.model;
import java.util.ArrayList;
import javafx.scene.control.Button;

import ca.utoronto.utm.othello.viewcontroller.OpponentChooserEventHandler;

public class RedoOperator {

	ArrayList<Move> moveStack;
	GamemodeController gamemodeController;
	
	public RedoOperator() {
		moveStack = new ArrayList<Move>();
	}
	public void setGamemodeController(GamemodeController gamemodeController) {
		this.gamemodeController = gamemodeController;
	}
	public void acceptMove(Move move) {
		this.moveStack.add(move);
	}
	
	public void reset() {
		this.moveStack.clear();
	}

	public void redo(Othello othello, UndoOperator undoStack, Button redoButton, String gameMode) {
		if (gameMode==OpponentChooserEventHandler.HvH) {
			if(this.moveStack.size()==0) {
				;
			}
			else {
			Move temp = this.moveStack.get(this.moveStack.size()-1);
			othello.move(temp.getRow(), temp.getCol());
			undoStack.acceptMove(temp);
			this.moveStack.remove(this.moveStack.size()-1);
			}
		}
		else {
			this.gamemodeController.setGamemodeUnchange(OpponentChooserEventHandler.HvH);
			
			Move temp = this.moveStack.get(this.moveStack.size()-1);
			othello.move(temp.getRow(), temp.getCol());
			undoStack.acceptMove(temp);
			this.moveStack.remove(this.moveStack.size()-1);
			
			temp = this.moveStack.get(this.moveStack.size()-1);
			othello.move(temp.getRow(), temp.getCol());
			undoStack.acceptMove(temp);
			this.moveStack.remove(this.moveStack.size()-1);
			
		}
		
	}
}
