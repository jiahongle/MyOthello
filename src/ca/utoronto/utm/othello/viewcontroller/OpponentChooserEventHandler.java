package ca.utoronto.utm.othello.viewcontroller;
import javafx.event.*;
import javafx.scene.control.*;
import ca.utoronto.utm.othello.model.*;
import ca.utoronto.utm.othello.model.GamemodeController;
import javafx.scene.layout.*;

public class OpponentChooserEventHandler implements EventHandler<ActionEvent> {
	
	private Label p1Mode;
	private Label p2Mode;
	private ComboBox<String> selectP1;
	private ComboBox<String> selectP2;
	private GamemodeController gamemodeController;
	public final static String HvH = "HumanHuman";
	public final static String HvG = "HumanGreedy";
	public final static String HvR = "HumanRandom";
	public final static String HvS = "HumanSmart";
	public final static String GvH = "GreedyHuman";
	public final static String RvH = "RandomHuman";
	public final static String SvH = "SmartHuman";
	
	
	public OpponentChooserEventHandler(Label p1Mode, Label p2Mode,
			ComboBox<String> selectP1, ComboBox<String> selectP2, GamemodeController gamemodeController) {
		this.p1Mode = p1Mode;
		this.p2Mode = p2Mode;
		this.gamemodeController = gamemodeController;
		this.selectP1 = selectP1;
		this.selectP2 = selectP2;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void handle(ActionEvent event) {
		
		ComboBox<String> source = (ComboBox<String>)event.getSource();
		String currPlayer = source.getValue();
		
		switch (source.getId()) {
		case "X":
			if (currPlayer != "Human" && this.p2Mode.getText() != "Human") {
				this.p1Mode.setText("Invalid Player Combination");
			}
			else {
				this.p1Mode.setText(currPlayer);
				this.p2Mode.setText(this.selectP2.getValue());
			}
			break;
		case "O":
			if (currPlayer != "Human" && this.p1Mode.getText() != "Human") {
				this.p2Mode.setText("Invalid Player Combination");
			}
			else {
				this.p1Mode.setText(this.selectP1.getValue());
				this.p2Mode.setText(currPlayer);
			}
			break;
		}
		
		String gamemode = p1Mode.getText() + p2Mode.getText();		
		this.gamemodeController.setGamemode(gamemode);
	}
}
