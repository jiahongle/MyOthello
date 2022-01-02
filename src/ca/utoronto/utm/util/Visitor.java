	package ca.utoronto.utm.util;

import ca.utoronto.utm.othello.model.Othello;
import ca.utoronto.utm.othello.model.OthelloBoard;

public interface Visitor {
	
	public void visit(Othello othello);
	
	public void visit(OthelloBoard ob);
	
	public void visit(char[][] board);
	
}
