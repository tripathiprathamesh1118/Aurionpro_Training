package com.aurionpro.tictactoe.model;

public class Cell {
	private MarkType mark;

    public Cell() {
        this.mark = MarkType.E;
    }

    public MarkType getMark() {
        return mark;
    }

    public void setMark(MarkType mark) throws Exception {
        if (this.mark != MarkType.E) {
            throw new Exception("Cells are full!");
        }
        this.mark = mark;
    }

    public void reset() {
        this.mark = MarkType.E;
    }

}