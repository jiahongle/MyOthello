package ca.utoronto.utm.othello.model;

import ca.utoronto.utm.util.Visitor;

public class OthelloGetTokenVisitor implements Visitor{
	private int row;
	private int col;
	private char targetToken;
	
	public OthelloGetTokenVisitor(int row, int col) {
		this.row = row;
		this.col = col;
	}
	@Override
	public void visit(Othello othello) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void visit(OthelloBoard ob) {
		// TODO Auto-generated method stub
		
		
	}
	
	@Override
	public void visit(char[][] board) {
		if (0 <= row && row < board.length && 0 <= col && col < board.length) {
			targetToken = board[row][col];
		}
		else {
			targetToken = OthelloBoard.EMPTY;
		}
		
	}
	
	public char getToken() {
		return targetToken;
	}

	public static void main (String[] args) {
		
	}
	
	
}
