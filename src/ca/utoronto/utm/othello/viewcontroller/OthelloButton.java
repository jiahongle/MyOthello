package ca.utoronto.utm.othello.viewcontroller;
import javafx.scene.control.Button;
import javafx.scene.image.*;
import ca.utoronto.utm.util.*;


import ca.utoronto.utm.othello.model.*;

public class OthelloButton extends Button implements Observer {
	
	protected int row;
	protected int col;
	protected char previousPlayer;
	protected char currPlayer;
	
	public OthelloButton(int row, int col, char player) {
		super();
		this.row = row;
		this.col = col;
		this.previousPlayer = player;
		this.currPlayer = player;
		this.setStyle(OthelloButton.ogBtnStyle());
		
		
	}
	public void setPlayer(char player) {
		this.previousPlayer = this.currPlayer;
		this.currPlayer = player;
	}
	
	public boolean changedState() {
		if (this.previousPlayer != this.currPlayer) {
			return true;
		}
		else {
			return false;
		}
	}
		
	
	
	public int getRow() {
		return this.row;
	}
	
	public int getCol() {
		return this.col;
	}
	
	public static String ogBtnStyle() {
		return "-fx-border-color: black;"
				+ "-fx-border-width: 1px;"
				+ "-fx-background-color: #4f9635;";		
	}
	
	public static String hintBtnStyle() {
		return "-fx-border-color: #ffe945;"
				+ "-fx-border-width: 2px;"
				+ "-fx-background-color: #4f9635;";		
	}
	
	public static String allMovesBtnStyle() {
		return "-fx-border-color: #66ff00;"
				+ "-fx-border-width: 2px;"
				+ "-fx-background-color: #4f9635;";	
	}
	
	public void update(Observable o) {
		Othello othello = (Othello)o;
	}
	
}

