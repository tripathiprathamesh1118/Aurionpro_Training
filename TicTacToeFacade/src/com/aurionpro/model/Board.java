package com.aurionpro.model;

public class Board {
	private Cell[] cells = new Cell[9];

	public Board() {
		for (int i = 0; i < 9; i++) {
			cells[i] = new Cell();
		}
	}

	public void resetBoard() {
		for (int i = 0; i < 9; i++) {
			cells[i] = new Cell();
		}
	}

	public boolean makeMove(int loc, MarkType mark) throws Exception {
		if (loc < 0 || loc >= 9) {
			throw new Exception("Invalid location.");
		}

		if (!cells[loc].isEmpty()) {
			throw new Exception("Cell is already marked.");
		}

		cells[loc].setMark(mark);
		return checkWin(mark);
	}

	private boolean checkWin(MarkType mark) {
		int[][] winPatterns = { { 0, 1, 2 }, { 3, 4, 5 }, { 6, 7, 8 }, { 0, 3, 6 }, { 1, 4, 7 }, { 2, 5, 8 },
				{ 0, 4, 8 }, { 2, 4, 6 } };

		for (int[] pattern : winPatterns) {
			if (cells[pattern[0]].getMark() == mark && cells[pattern[1]].getMark() == mark
					&& cells[pattern[2]].getMark() == mark) {
				return true;
			}
		}
		return false;
	}

	public boolean isBoardFull() {
		for (Cell cell : cells) {
			if (cell.isEmpty()) {
				return false;
			}
		}
		return true;
	}

	public void displayBoard() {
		for (int i = 0; i < 9; i++) {
			System.out.print(cells[i].getMark() + " ");
			if ((i + 1) % 3 == 0)
				System.out.println(); 
		}
	}

	public MarkType getCellMark(int loc) {
		return cells[loc].getMark();
	}
}
