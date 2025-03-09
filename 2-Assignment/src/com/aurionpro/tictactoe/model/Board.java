package com.aurionpro.tictactoe.model;

public class Board {
	private Cell[] cells;

	public Board() {
		cells = new Cell[9];
		for (int i = 0; i < 9; i++) {
			cells[i] = new Cell();
		}
	}

	public void displayBoard() {
		for (int i = 0; i < 9; i++) {
			System.out.print(cells[i].getMark() + " ");
			if ((i + 1) % 3 == 0)
				System.out.println();
		}
	}

	public boolean makeMove(int position, MarkType mark) throws Exception {
		if (position < 0 || position >= 9) {
			throw new Exception("Invalid position! Choose between 0-8.");
		}
		cells[position].setMark(mark);
		return checkWin(mark);
	}

	private boolean checkWin(MarkType mark) {
		int[][] winConditions = { { 0, 1, 2 }, { 3, 4, 5 }, { 6, 7, 8 }, // Rows
				{ 0, 3, 6 }, { 1, 4, 7 }, { 2, 5, 8 }, // Columns
				{ 0, 4, 8 }, { 2, 4, 6 } // Diagonals
		};
		for (int[] condition : winConditions) {
			if (cells[condition[0]].getMark() == mark && cells[condition[1]].getMark() == mark
					&& cells[condition[2]].getMark() == mark) {
				return true;
			}
		}
		return false;
	}

	public boolean isBoardFull() {
		for (Cell cell : cells) {
			if (cell.getMark() == MarkType.E) {
				return false;
			}
		}
		return true;
	}

	public void resetBoard() {
		for (Cell cell : cells) {
			cell.reset();
		}
	}

}