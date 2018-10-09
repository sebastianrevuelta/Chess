package com.sebas.core;

import java.util.List;

public class Piece {

	private String color;
	private String horizontal;
	private String vertical;
	private String type;
	private int value;
	
	
	public int getValue() { return value; }
	public void setValue(int value) { this.value = value; }
	
	public String getType() { return type; }
	public void setType(String type) { this.type = type; }
	
	public String getColor() { return color; }
	public void setColor(String color) { this.color = color; }
	
	public String getHorizontal() { return horizontal; }
	public void setHorizontal(String horizontal) { this.horizontal = horizontal; }
	
	public String getVertical() { return vertical; }
	public void setVertical(String vertical) { this.vertical = vertical; }

	public List<Movement> move(String turn, String from) {
		return null;
	}
	public boolean isRealMove(Movement move, Board board, String turn) {
		String destiny = move.getDestiny();
		Square square = board.getSquare(destiny);
		return square.isEmpty();
		
	}
}
