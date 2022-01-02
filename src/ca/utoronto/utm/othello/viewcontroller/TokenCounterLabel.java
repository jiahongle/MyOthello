package ca.utoronto.utm.othello.viewcontroller;
import javafx.scene.control.Label;
import ca.utoronto.utm.util.*;
import ca.utoronto.utm.othello.model.*; 

public class TokenCounterLabel extends Label implements Observer{
	private char player;
	
	public TokenCounterLabel(String s, char player) {
		super(s);
		this.player = player;
	}
	public void update(Observable o) {
		int count = ((Othello)o).getCount(this.player);
		this.setText(this.player + ": " + count);
		
	}

}
