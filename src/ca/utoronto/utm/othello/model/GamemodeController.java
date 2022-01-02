package ca.utoronto.utm.othello.model;

import java.util.concurrent.TimeUnit;

import ca.utoronto.utm.othello.model.Othello;
import ca.utoronto.utm.othello.model.OthelloBoard;
import ca.utoronto.utm.othello.viewcontroller.OpponentChooserEventHandler;
import ca.utoronto.utm.util.Observer;
import javafx.concurrent.Task;
import ca.utoronto.utm.util.Observable;

public class GamemodeController implements Observer {
	private Othello othello;
	private String gamemode;
	private UndoOperator moveStack;
	OthelloStrategy greedyStrategyP1, greedyStrategyP2, 
					randomStrategyP1, randomStrategyP2,
					smartStrategyP1, smartStrategyP2;

	public GamemodeController(Othello othello, UndoOperator moveStack) {
		this.othello = othello;
		this.gamemode = OpponentChooserEventHandler.HvH;
		this.moveStack = moveStack;
		this.greedyStrategyP1 = StrategyFactory.create(StrategyFactory.GREEDY, othello, OthelloBoard.P1, moveStack);
		this.greedyStrategyP2 = StrategyFactory.create(StrategyFactory.GREEDY, othello, OthelloBoard.P2, moveStack);
		this.randomStrategyP1 = StrategyFactory.create(StrategyFactory.RANDOM, othello, OthelloBoard.P1, moveStack);
		this.randomStrategyP2 = StrategyFactory.create(StrategyFactory.RANDOM, othello, OthelloBoard.P2, moveStack);
		this.smartStrategyP1 = StrategyFactory.create(StrategyFactory.SMART, othello, OthelloBoard.P1, moveStack);
		this.smartStrategyP2 = StrategyFactory.create(StrategyFactory.SMART, othello, OthelloBoard.P2, moveStack);
	}
	
	public String getGamemode() {
		return this.gamemode;
	}

	public void setGamemode(String gamemode) {
		this.gamemode = gamemode;
		if (this.othello.getWhosTurn() == OthelloBoard.P1) {
			switch (this.gamemode) {
			case OpponentChooserEventHandler.GvH:
				this.randomStrategyP1.move();
				this.moveStack.acceptMove(this.randomStrategyP1.getLatest());
				break;
			case OpponentChooserEventHandler.RvH:
				this.randomStrategyP1.move();
				this.moveStack.acceptMove(this.randomStrategyP1.getLatest());
				break;
			case OpponentChooserEventHandler.SvH:
				this.smartStrategyP1.move();
				this.moveStack.acceptMove(this.smartStrategyP1.getLatest());
				break;
			}
		} else if (this.othello.getWhosTurn() == OthelloBoard.P2) {
			switch (this.gamemode) {
			case OpponentChooserEventHandler.HvG:
				this.greedyStrategyP2.move();
				this.moveStack.acceptMove(this.greedyStrategyP2.getLatest());
				break;
			case OpponentChooserEventHandler.HvR:
				this.randomStrategyP2.move();
				this.moveStack.acceptMove(this.randomStrategyP2.getLatest());
				break;
			case OpponentChooserEventHandler.HvS:
				smartStrategyP2.move();
				this.moveStack.acceptMove(this.smartStrategyP2.getLatest());
				break;
			}
		}
	}
	
	public void setGamemodeUnchange(String gamemode) {
		this.gamemode = gamemode;
	}

	@Override
	public void update(Observable o) {
		switch (this.gamemode) {
		case OpponentChooserEventHandler.HvH:
			break;
		case OpponentChooserEventHandler.HvG:
			if (othello.getWhosTurn() == OthelloBoard.P2) {
				this.greedyStrategyP2.move();
				this.moveStack.acceptMove(this.greedyStrategyP2.getLatest());
			}
			break;
		case OpponentChooserEventHandler.HvR:
			if (othello.getWhosTurn() == OthelloBoard.P2) {
				this.randomStrategyP2.move();
				this.moveStack.acceptMove(this.randomStrategyP2.getLatest());
			}
			break;
		case OpponentChooserEventHandler.HvS:
			if (othello.getWhosTurn() == OthelloBoard.P2) {
				this.smartStrategyP2.move();
				this.moveStack.acceptMove(this.smartStrategyP2.getLatest());
			}
			break;
		case OpponentChooserEventHandler.GvH:
			if (othello.getWhosTurn() == OthelloBoard.P1) {
				this.greedyStrategyP1.move();
				this.moveStack.acceptMove(this.greedyStrategyP1.getLatest());
			}
			break;
		case OpponentChooserEventHandler.RvH:
			if (othello.getWhosTurn() == OthelloBoard.P1) {
				this.randomStrategyP1.move();
				this.moveStack.acceptMove(this.randomStrategyP1.getLatest());
			break;
			}
		case OpponentChooserEventHandler.SvH:
			if (othello.getWhosTurn() == OthelloBoard.P1) {
				this.smartStrategyP1.move();
				this.moveStack.acceptMove(this.smartStrategyP1.getLatest());
			}
			break;
		}  
	}
}
	
	



