package ca.utoronto.utm.othello.model;

public interface OthelloStrategy {
	public void move();
	public Move getLatest();
}
