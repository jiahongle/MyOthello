package ca.utoronto.utm.othello.model;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class PlayerSmart extends PlayerGreedy {

	public PlayerSmart(Othello othello, char player) {
		super(othello, player);
	}

	@Override
	public Move getMove() {
		Move pMove = checkCorner();
		if (pMove != null) {
			return pMove;
		}

		pMove = checkSide();
		if (pMove != null) {
			return pMove;
		}

		pMove = checkMiddle();
		if (pMove != null) {
			return pMove;
		}

		return super.getMove();
	}

	/**
	 * Checks if there exists a valid move on a corner
	 * 
	 * @return a move on the corner if it exists, null if it doesn't
	 */
	private Move checkCorner() {
		ArrayList<Move> cornerMoves = new ArrayList<Move>();
		// Check all four corners

		Othello oCopy = this.othello.copy();
		if (oCopy.move(0, 0)) { // Top Left Corner
			cornerMoves.add(new Move(0, 0));
		}
		oCopy = this.othello.copy();
		if (oCopy.move(0, Othello.DIMENSION - 1)) { // Top Right Corner
			cornerMoves.add(new Move(0, Othello.DIMENSION - 1));
		}
		oCopy = this.othello.copy();
		if (oCopy.move(Othello.DIMENSION - 1, 0)) { // Bottom Left Corner
			cornerMoves.add(new Move(Othello.DIMENSION - 1, 0));
		}
		oCopy = this.othello.copy();
		if (oCopy.move(Othello.DIMENSION - 1, Othello.DIMENSION - 1)) { // Bottom Right corner
			cornerMoves.add(new Move(Othello.DIMENSION - 1, Othello.DIMENSION - 1));
		}

		if (cornerMoves.size() == 0) {
//			System.out.println("\n No Corner \n");
			return null;
		} else {
			Random rand = new Random();
			return cornerMoves.get(rand.nextInt(cornerMoves.size()));
		}
	}

	/**
	 * 
	 * @return a valid move that is made on the edge of the board (excluding
	 *         corners), null if there is no valid edge move
	 */
	private Move checkSide() {
		ArrayList<Move> sideMoves = new ArrayList<Move>();
		// Top row
		for (int col = 1; col < Othello.DIMENSION - 1; col++) {
			Othello oCopy = this.othello.copy();
			if (oCopy.move(0, col)) {
				sideMoves.add(new Move(0, col));
			}
		}
		// Bottom row
		for (int col = 1; col < Othello.DIMENSION - 1; col++) {
			Othello oCopy = this.othello.copy();
			if (oCopy.move(Othello.DIMENSION - 1, col)) {
				sideMoves.add(new Move(Othello.DIMENSION - 1, col));
			}
		}

		// Left column
		for (int row = 1; row < Othello.DIMENSION - 1; row++) {
			Othello oCopy = this.othello.copy();
			if (oCopy.move(row, 0)) {
				sideMoves.add(new Move(row, 0));
			}
		}

		// Right Column
		for (int row = 1; row < Othello.DIMENSION - 1; row++) {
			Othello oCopy = this.othello.copy();
			if (oCopy.move(row, Othello.DIMENSION - 1)) {
				sideMoves.add(new Move(row, Othello.DIMENSION - 1));
			}
		}
		if (sideMoves.size() == 0) {
//			System.out.println("\n No Side \n");
			return null;
		} else {
			Random rand = new Random();
			return sideMoves.get(rand.nextInt(sideMoves.size()));
		}
	}

	/**
	 * 
	 * @return a valid move on the board that results in the greatest presence in
	 *         the middle 4x4 grid
	 */
	private Move checkMiddle() {
		Move bestMove = null;
		int bestMoveCount = countFourByFour(this.othello);
		for (int row = 1; row < Othello.DIMENSION - 1; row++) {
			for (int col = 1; col < Othello.DIMENSION - 1; col++) {
				Othello oCopy = this.othello.copy();
				if (oCopy.move(row, col)) {
					if (countFourByFour(oCopy) >= bestMoveCount) {
						bestMove = new Move(row, col);
						bestMoveCount = countFourByFour(oCopy);
					}
				}
			}
		}
		return bestMove;
	}

	private int countFourByFour(Othello othello) {
		int count = 0;
		for (int row = 2; row < 6; row++) {
			for (int col = 2; col < 6; col++) {
				if (othello.getToken(row, col) == this.getPlayer()) {
					count++;
				}
			}
		}
		return count;
	}

}
