package ca.utoronto.utm.othello.model;

public class StrategyFactory {
	
	public static final String RANDOM = "Random";
	public static final String GREEDY = "Greedy";
	public static final String SMART = "Smart";
	
	public static OthelloStrategy create(String strategyName, Othello othello, 
			char player, UndoOperator moveStack) {
		OthelloStrategy strategy = null;
		
		switch (strategyName) {
		case RANDOM:
			strategy = new RandomStrategy(othello, player);
			break;
		case GREEDY:
			strategy = new GreedyStrategy(othello, player);
			break;
		case SMART:
			strategy = new SmartStrategy(othello, player);
			break;
		}
		return strategy;
	}
}
