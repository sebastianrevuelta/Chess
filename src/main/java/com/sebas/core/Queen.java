package com.sebas.core;

import java.util.ArrayList;
import java.util.List;
/**
 * This is the Queen class 
 * @author srevuelta
 *
 */
public class Queen extends Piece {
	
	public Queen() {
		 super.setType("queen");
		 super.setValue(9);
	}
	
	/***
	 * move the bishop
	 * @param turn
	 * @return
	 */
	public List<Movement> move(String from) {

		List<Movement> possibleMoves = new ArrayList<Movement>();

		String to;

		String rowdestiny = "a";
		String columndestiny = "1";

		int valueColumn = UtilChess.calculateHorizontal(from);
		int valueRow = UtilChess.calculateVertical(from);
		
		//Tower Moves
		for (int i=1; i < 8; i++) {
			if (valueRow+i < 8) {
				int row = new Integer(valueRow+i).intValue();
				rowdestiny = UtilChess.calculateVertical(row);
				int column = new Integer(valueColumn).intValue();
				columndestiny = UtilChess.calculateHorizontal(column);
				to = columndestiny + rowdestiny;	
				Movement move = new Movement(this,from,to);
				possibleMoves.add(move);				
			}
			
			if (valueRow-i >=0) {
				int row = new Integer(valueRow+i).intValue();
				rowdestiny = UtilChess.calculateVertical(row);
				int column = new Integer(valueColumn).intValue();
				columndestiny = UtilChess.calculateHorizontal(column);
				to = columndestiny + rowdestiny;	
				Movement move = new Movement(this,from,to);
				possibleMoves.add(move);				
			}
			
			if (valueColumn+i < 8) {
				int row = new Integer(valueRow).intValue();
				rowdestiny = UtilChess.calculateVertical(row);
				int column = new Integer(valueColumn+i).intValue();
				columndestiny = UtilChess.calculateHorizontal(column);
				to = columndestiny + rowdestiny;	
				Movement move = new Movement(this,from,to);
				possibleMoves.add(move);				
			}
			if (valueColumn-i >=0) {
				int row = new Integer(valueRow).intValue();
				rowdestiny = UtilChess.calculateVertical(row);
				int column = new Integer(valueColumn-i).intValue();
				columndestiny = UtilChess.calculateHorizontal(column);
				to = columndestiny + rowdestiny;	
				Movement move = new Movement(this,from,to);
				possibleMoves.add(move);				
			}
		}
		
		//Bishop moves
		for (int i=1; i < 8; i++) {
			if (valueRow+i < 8) {
				int row = new Integer(valueRow+i).intValue();
				rowdestiny = UtilChess.calculateVertical(row);
				if (valueColumn+i < 8) {
					int column = new Integer(valueColumn+i).intValue();
					columndestiny = UtilChess.calculateHorizontal(column);
				}
				else if (valueColumn-i >= 0) {
					int column = new Integer(valueColumn-i).intValue();
					columndestiny = UtilChess.calculateHorizontal(column);
				}
				to = columndestiny + rowdestiny;	
				Movement move = new Movement(this,from,to);
				possibleMoves.add(move);				
			}
			
			if (valueRow-i >=0) {
				int row = new Integer(valueRow-i).intValue();
				rowdestiny = UtilChess.calculateVertical(row);
				if (valueColumn+i < 8) {
					int column = new Integer(valueColumn+i).intValue();
					columndestiny = UtilChess.calculateHorizontal(column);
				}
				else if (valueColumn-i >= 0) {
					int column = new Integer(valueColumn-i).intValue();
					columndestiny = UtilChess.calculateHorizontal(column);
				}
				to = columndestiny + rowdestiny;	
				Movement move = new Movement(this,from,to);
				possibleMoves.add(move);				
			}
		}
		return possibleMoves;
	}
	
	/**
	 * TODO: check if the move is possible
	 */
	public boolean isRealMove(Movement movement, Board board, String turn) {
		String to = movement.getDestiny();
		
		int horizontalTo = UtilChess.calculateHorizontal(to);
		int verticalTo = UtilChess.calculateVertical(to);

		Square[][] squares = board.getSquares();
		Square square = squares[horizontalTo][verticalTo];
		if (!square.isEmpty()) {
			if (turn.equals(square.getPieza().getColor())) return false;
		}
		
		return true;

	}
}
