package com.aurionpro.model;

public class Cell {
	private MarkType mark;

	public Cell() {
		this.mark = MarkType._;
	}

	public boolean isEmpty() {
		return mark == MarkType._;
	}

	public MarkType getMark() {
		return mark;
	}

	public void setMark(MarkType mark) throws Exception {
		if (!isEmpty()) {
			throw new Exception("Cell is already marked!");
		}
		this.mark = mark;
	}

}