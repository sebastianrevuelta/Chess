package com.sebas.core;

public class Square {
	
	private boolean isEmpty;
	private Piece pieza;
	private String horizontal;
	private String vertical;
	
	public boolean isEmpty() {
		return isEmpty;
	}
	public void setEmpty(boolean isEmpty) {
		this.isEmpty = isEmpty;
	}
	public Piece getPieza() {
		return pieza;
	}
	public void setPieza(Piece pieza) {
		this.pieza = pieza;
	}
	public String getHorizontal() {
		return horizontal;
	}
	public void setHorizontal(String horizontal) {
		this.horizontal = horizontal;
	}
	public String getVertical() {
		return vertical;
	}
	public void setVertical(String vertical) {
		this.vertical = vertical;
	}
	
	

}
