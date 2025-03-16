package com.aurionpro.model;

public class ResultAnalyzer {
    private Board board;

    public ResultAnalyzer(Board board) {
        this.board = board;
    }

    public String analyzeResult() {
        if (checkWin(MarkType.X))
            return "X Wins!";
        if (checkWin(MarkType.O))
            return "O Wins!";
        if (board.isBoardFull())
            return "It's a Draw!";
        return "Game in Progress...";
    }

    private boolean checkWin(MarkType mark) {
        int[][] winPatterns = { { 0, 1, 2 }, { 3, 4, 5 }, { 6, 7, 8 },
                { 0, 3, 6 }, { 1, 4, 7 }, { 2, 5, 8 },
                { 0, 4, 8 }, { 2, 4, 6 }
        };

        for (int[] pattern : winPatterns) {
            if (board.getCellMark(pattern[0]) == mark && board.getCellMark(pattern[1]) == mark
                    && board.getCellMark(pattern[2]) == mark) {
                return true;
            }
        }
        return false;
    }
}
