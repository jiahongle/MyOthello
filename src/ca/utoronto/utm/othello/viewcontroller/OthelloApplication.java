package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.*;
import ca.utoronto.utm.othello.model.GamemodeController;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.control.*;
import javafx.animation.Animation;
import javafx.animation.Timeline;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.animation.KeyFrame;

public class OthelloApplication extends Application {
	// REMEMBER: To run this in the lab put 
	// --module-path "/usr/share/openjfx/lib" --add-modules javafx.controls,javafx.fxml
	// in the run configuration under VM arguments.
	// You can import the JavaFX.prototype launch configuration and use it as well.
	
	@Override
	public void start(Stage stage) throws Exception {
		// Create and hook up the Model, View and the controller
		
		// MODEL
		Othello othello = new Othello();
		
		// OthelloGrid
		GridPane othelloGrid = new GridPane();
		
		// Undo Stack
		UndoOperator undoStack = new UndoOperator();
		
		// Redo Stack
		RedoOperator redoStack = new RedoOperator();
		
		// Game mode Controls
		GamemodeController gamemodeController = new GamemodeController(othello, undoStack);
		
		// Add GamemodeController to Undo/Redo Stack
		undoStack.setGamemodeController(gamemodeController);
		redoStack.setGamemodeController(gamemodeController);
		
		// Game Controls (Undo, Redo, Pause/Play, Restart etc.)
		Button redoButton = new Button("Redo");
		redoButton.setDisable(true);
		redoButton.setOnAction(new RedoEventHandler(othello, redoStack, undoStack, redoButton, gamemodeController));
		
		Button undoButton = new Button("Undo");
		undoButton.setDisable(true);
		undoButton.setOnAction(new UndoEventHandler(othello, undoStack, redoStack, undoButton, redoButton, gamemodeController));
		
		// Restart Button GOOD IDEA: have a restart image maybe? (A E S T H E T I C S)
		Button restartButton = new Button("RESTART");
		restartButton.setOnAction(new GameControlsEventHandler(othello, othelloGrid, undoStack, redoStack));
		
		// Labels for hBoxBottom
		WhosNextLabel whosNextLabel = new WhosNextLabel("Player 1's Turn");
		GameStatusLabel gameStatusLabel = new GameStatusLabel("In Progress");
		
		// Token counter for both player (hBoxTop)
		TokenCounterLabel tokenCounterP1 = new TokenCounterLabel("X: 2", OthelloBoard.P1);
		TokenCounterLabel tokenCounterP2 = new TokenCounterLabel("O: 2", OthelloBoard.P2);
		
		// AllMoves Toggle 
		ToggleButton allMovesToggle = new ToggleButton("Show All Available Moves");
		AllMovesToggleEventHandler allMovesToggleEventHandler = 
				new AllMovesToggleEventHandler(othello, othelloGrid, allMovesToggle);
		allMovesToggle.setOnAction(allMovesToggleEventHandler);
		
		
		//VIEW 
		// Timer Components
		// Timer input Label + Spinner + Spinner Factory
		Label timeP1 = new Label("Player 1 Time Setting:");
		Label minuteLabelP1 = new Label("min: ");
		Label secondLabelP1 = new Label("sec: ");
		
		Label timeP2 = new Label("Player 2 Time Setting:");
		Label minuteLabelP2 = new Label("min: ");
		Label secondLabelP2 = new Label("sec: ");
				
		Spinner<Integer> minuteSpinnerP1 = new Spinner<Integer>();
		minuteSpinnerP1.setPrefWidth(80);
		Spinner<Integer> secondSpinnerP1 = new Spinner<Integer>();
		secondSpinnerP1.setPrefWidth(80);
		
		Spinner<Integer> minuteSpinnerP2 = new Spinner<Integer>();
		minuteSpinnerP2.setPrefWidth(80);
		Spinner<Integer> secondSpinnerP2 = new Spinner<Integer>();
		secondSpinnerP2.setPrefWidth(80);
		
		SpinnerValueFactory<Integer> minFactoryP1 = 
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 60, 5);
		SpinnerValueFactory<Integer> secFactoryP1 =
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, 0);
		
		SpinnerValueFactory<Integer> minFactoryP2 = 
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 60, 5);
		SpinnerValueFactory<Integer> secFactoryP2 =
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, 0);
		
		minuteSpinnerP1.setValueFactory(minFactoryP1);
		secondSpinnerP1.setValueFactory(secFactoryP1);
		
		minuteSpinnerP2.setValueFactory(minFactoryP2);
		secondSpinnerP2.setValueFactory(secFactoryP2);
		
		// Timer Label
		Label timerP1 = new Label("Time Remaining: ");
		Label timerP2 = new Label("Time Remaining: ");
		
		// TimerEventHandler
		TimerEventHandler timerEventHandlerP1 = 
				new TimerEventHandler(timerP1, minuteSpinnerP1, secondSpinnerP1, othello, OthelloBoard.P1,
						 othelloGrid, gameStatusLabel, whosNextLabel, undoButton, redoButton);
		TimerEventHandler timerEventHandlerP2 = 
				new TimerEventHandler(timerP2, minuteSpinnerP2, secondSpinnerP2, othello, OthelloBoard.P2,
						 othelloGrid, gameStatusLabel, whosNextLabel, undoButton, redoButton);
		
		// Timeline EventHandler hookup
		Timeline countdownP1 = new Timeline(new KeyFrame(Duration.seconds(1), timerEventHandlerP1));
		countdownP1.setCycleCount(Animation.INDEFINITE);
		countdownP1.play();
		
		Timeline countdownP2 = new Timeline(new KeyFrame(Duration.seconds(1), timerEventHandlerP2));
		countdownP2.setCycleCount(Animation.INDEFINITE);
		countdownP2.play();
		
		
		// Timer Settings Panel P1
		GridPane timerPaneP1 = new GridPane();
		timerPaneP1.add(timeP1, 0, 0);
		HBox timeSpinnerP1 = new HBox(minuteLabelP1, minuteSpinnerP1, secondLabelP1, secondSpinnerP1);
		timerPaneP1.add(timeSpinnerP1, 0, 1);

		// Timer Settings Panel P2
		GridPane timerPaneP2 = new GridPane();
		timerPaneP2.add(timeP2, 0, 0);
		HBox timeSpinnerP2 = new HBox(minuteLabelP2, minuteSpinnerP2, secondLabelP2, secondSpinnerP2);
		timerPaneP2.add(timeSpinnerP2, 0, 1);

		// Labels for Current Players
		Label p1Label = new Label("P1: ");
		Label p2Label = new Label("P2: ");
		Label p1Mode = new Label("Human");
		Label p2Mode = new Label("Human");
		
		//Hint Buttons
		final ToggleGroup hintBtnGroup = new ToggleGroup();
		
		RadioButton greedyHintBtn = new RadioButton("Greedy Hint");
		RadioButton randomHintBtn = new RadioButton("Random Hint");
		RadioButton noHintBtn = new RadioButton("No Hint");
		
		greedyHintBtn.setToggleGroup(hintBtnGroup);
		randomHintBtn.setToggleGroup(hintBtnGroup);
		noHintBtn.setToggleGroup(hintBtnGroup);
		
		HintEventHandler hintHandler = new HintEventHandler(othello, othelloGrid, greedyHintBtn, randomHintBtn, 
				noHintBtn, allMovesToggle);
		noHintBtn.setSelected(true);
		
		greedyHintBtn.setOnAction(hintHandler);
		randomHintBtn.setOnAction(hintHandler);
		noHintBtn.setOnAction(hintHandler);
		
		
		
		//Grid Layout
		GridPane gridTop = new GridPane();

		gridTop.addRow(0, tokenCounterP1, timerP1, timerP2, tokenCounterP2);

		gridTop.setAlignment(Pos.CENTER);
		gridTop.setHgap(50);
		
		GridPane gridBottom = new GridPane();
		gridBottom.addRow(0, whosNextLabel, gameStatusLabel);
		gridBottom.setAlignment(Pos.CENTER);
		gridBottom.setHgap(50);
		
		//Choosing Opponent Buttons
		final ComboBox<String> selectP1 = new ComboBox<String>();
		final ComboBox<String> selectP2 = new ComboBox<String>();
		selectP1.getItems().addAll("Human", "Random", "Greedy", "Smart");
		selectP1.getSelectionModel().selectFirst();
		selectP1.setId(Character.toString(OthelloBoard.P1));
		selectP2.getItems().addAll("Human", "Random", "Greedy", "Smart");
		selectP2.getSelectionModel().selectFirst();
		selectP2.setId(Character.toString(OthelloBoard.P2));
		
		// SettingsPanelP1
		GridPane settingsPanelP1 = new GridPane();
		settingsPanelP1.addColumn(0, p1Label, timerPaneP1, selectP1);
		settingsPanelP1.add(p1Mode, 1, 0);
		
		settingsPanelP1.setAlignment(Pos.TOP_CENTER);
		settingsPanelP1.setPadding(new Insets(10));
		
		// SettingsPanelP2
		GridPane settingsPanelP2 = new GridPane();
		settingsPanelP2.addColumn(0, p2Label, timerPaneP2, selectP2);
		settingsPanelP2.add(p2Mode, 1, 0);
		
		settingsPanelP2.setAlignment(Pos.TOP_CENTER);
		settingsPanelP2.setPadding(new Insets(10));
		
		// Menu Panel
		GridPane menuGrid = new GridPane();
		menuGrid.addRow(0, restartButton, undoButton, redoButton, greedyHintBtn, randomHintBtn, noHintBtn, allMovesToggle);
		menuGrid.setPadding(new Insets(10));
		menuGrid.setHgap(10);
		
		VBox gamePanel = new VBox(menuGrid, gridTop, othelloGrid, gridBottom);
		HBox base = new HBox(settingsPanelP1, gamePanel, settingsPanelP2);
		
		
		// set up basic layout 
		VOthello vOthello = new VOthello(othelloGrid);
		
		// CONTROLLER
		OthelloButtonPressEventHandler opresshandler = new OthelloButtonPressEventHandler(othello, undoStack, redoStack, redoButton, undoButton);
		OpponentChooserEventHandler oppChooseHandler = new OpponentChooserEventHandler(p1Mode, p2Mode,
																						selectP1, selectP2, gamemodeController);
	
		// CONTROLLER->MODEL hook up
		selectP1.setOnAction(oppChooseHandler);
		selectP2.setOnAction(oppChooseHandler);
		
		// VIEW->CONTROLLER hookup
		// CONTROLLER->MODEL hookup
		
		for (int row = 0; row < Othello.DIMENSION; row++) {
			for (int col = 0; col < Othello.DIMENSION; col++) {
				OthelloButton othelloButton = new OthelloButton(row, col, OthelloBoard.EMPTY);
				othelloButton.setMinSize(60, 60);
				othelloGrid.add(othelloButton, col, row);
				othelloButton.setOnAction(opresshandler);
				othello.attach(othelloButton);
			}
		}
		/****Board controller and view instantiated together***/
		

		// MODEL->VIEW hookup
		othello.attach(vOthello);
		othello.attach(whosNextLabel);
		othello.attach(gameStatusLabel);
		othello.attach(tokenCounterP1);
		othello.attach(tokenCounterP2);
		othello.attach(allMovesToggleEventHandler);
		othello.attach(hintHandler);
		othello.attach(gamemodeController);

		// VIEW SCENE
		Scene scene = new Scene(base);
		stage.setTitle("Othello");
		stage.setScene(scene);

		//Load up initial game setup 
		vOthello.update(othello);
		
		
		// VIEW STAGE 
		stage.setTitle("Othello");
		stage.setScene(scene);
		
		// LAUNCH THE GUI
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
