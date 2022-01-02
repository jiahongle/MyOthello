package ca.utoronto.utm.othello.viewcontroller;
import javafx.scene.layout.GridPane;
import javafx.animation.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ca.utoronto.utm.util.*;
import javafx.util.Duration;
import javafx.animation.FadeTransition;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.concurrent.TimeUnit;

import ca.utoronto.utm.othello.model.*;


public class VOthello extends GridPane implements Observer {
	GridPane grid;
	Image blackChip;
	Image whiteChip;
	
	public VOthello(GridPane grid) {
		this.grid = grid;
		 
		try {
			blackChip = new Image(new FileInputStream("images/black-chip.png"), 40, 40, true, true);
			whiteChip = new Image(new FileInputStream("images/white-chip.png"),40, 40, true, true);
			
		} catch (FileNotFoundException e) {
			System.out.println("One or both images cannot be found");
		}	
	}
	
	@Override
	public void update(Observable o) {
		Othello othello =  (Othello) o;
		updateBoard(othello);
	}
	
	private void updateBoard(Othello othello) {
		int childCount = 0;
		for (int row = 0; row < Othello.DIMENSION; row++) {
			for (int col = 0; col < Othello.DIMENSION; col++) {
				OthelloButton currentChild = (OthelloButton) this.grid.getChildren().get(childCount);
				char currToken = othello.getToken(row, col);
				if (currToken == OthelloBoard.P1) {
					currentChild.setPlayer(OthelloBoard.P1);
					if (currentChild.changedState()) {
						ImageView imageView = new ImageView(blackChip);
						imageView.setOpacity(0);;
						currentChild.setGraphic(imageView);
						FadeTransition ft = new FadeTransition(Duration.seconds(1), imageView);
						ft.setDelay(Duration.seconds(0.5));
						ft.setFromValue(0);
					    ft.setToValue(1);
					    ft.play();
					    currentChild.setStyle(OthelloButton.ogBtnStyle());
					}
					else {
						currentChild.setStyle(OthelloButton.ogBtnStyle());
						currentChild.setGraphic(new ImageView(blackChip));
					}
					
				}
				else if (currToken == OthelloBoard.P2) {
					currentChild.setPlayer(OthelloBoard.P2);
					if (currentChild.changedState()) {
						ImageView imageView = new ImageView(whiteChip);
						imageView.setOpacity(0);
						currentChild.setGraphic(imageView);
						FadeTransition ft = new FadeTransition(Duration.seconds(1), imageView);
						ft.setDelay(Duration.seconds(0.5));
					    ft.setFromValue(0);
					    ft.setToValue(1);
					    ft.play();
					    currentChild.setStyle(OthelloButton.ogBtnStyle());
					}
					else {
						currentChild.setStyle(OthelloButton.ogBtnStyle());
						currentChild.setGraphic(new ImageView(whiteChip));
					}
				}
				else {
					currentChild.setStyle(OthelloButton.ogBtnStyle());
					currentChild.setGraphic(null);
				}
				
				childCount++;
			}
		}	
	}
}
