package com.sebas.core;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {

	public Pawn() {
		super.setType("pawn");
		super.setValue(1);
	}
	
	/**
	 * move Pawn
	 * @return
	 */
	public List<Movement> move(String turn, String from) {

		List<Movement> possiblesMoves = new ArrayList<Movement>();
		String to;

		char column = from.charAt(0);
		char row = from.charAt(1);

		int value = UtilChess.calculateVertical(from);

		String rowdestiny = "";
		if ("white".equals(turn)) { 
			//only one step 
			int rowMove = new Integer(value+1).intValue();
			rowdestiny = UtilChess.calculateVertical(rowMove);
			to = column + rowdestiny;
			Movement move1 = new Movement(this,from,to);
			possiblesMoves.add(move1);
			
			//could be two steps at the beginning
			if (row == '2') {
				rowMove = new Integer(value+2).intValue();
				to = column + rowdestiny;
				Movement move2 = new Movement(this,from,to);
				possiblesMoves.add(move2);
			}
		}
		if ("black".equals(turn)) {
			//only one step
			int rowMove = new Integer(value-1).intValue();
			rowdestiny = UtilChess.calculateVertical(rowMove);
			to = column + rowdestiny;
			Movement move3 = new Movement(this,from,to);
			possiblesMoves.add(move3);			
			//could be two steps at the beginning
			if (row == '7') {
				rowMove = new Integer(value-2).intValue();
				to = column + rowdestiny;
				Movement move4 = new Movement(this,from,to);
				possiblesMoves.add(move4);			
			}
		}
		return possiblesMoves;
	}
	
	/**
	 * check if the move is possible
	 */
	public boolean isRealMove(Movement movement, Board board) {
		
		Square[][] squares = board.getSquares();

		String from = movement.getOrigin();
		String to = movement.getDestiny();
		
		int verticalFrom = UtilChess.calculateVertical(from);

		int horizontalTo = UtilChess.calculateHorizontal(to);
		int verticalTo = UtilChess.calculateVertical(to);
		
		int steps = verticalTo - verticalFrom;
		if (steps == 2) { //it means two steps from the original white square
			Square square = squares[horizontalTo][verticalTo+1];
			if (!square.isEmpty()) return false;
		}
		if (steps == -2) { //it means two steps from the original black square
			Square square = squares[horizontalTo][verticalTo-1];
			if (!square.isEmpty()) return false;
		}		
		
		Square square = squares[horizontalTo][verticalTo];
		if (!square.isEmpty()) return false;
		
		return true;
	}
}
