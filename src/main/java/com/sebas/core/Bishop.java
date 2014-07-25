package com.sebas.core;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {

	public Bishop() {
		super.setType("bishop");
		super.setValue(3);
	}

	/***
	 * move the bishop
	 * @param turn
	 * @return
	 */
	public List<Movement> move(String turn, String from) {

		List<Movement> possibleMoves = new ArrayList<Movement>();

		String to;

		String rowdestiny = "a";
		String columndestiny = "1";

		int valueColumn = UtilChess.calculateHorizontal(from);
		int valueRow = UtilChess.calculateVertical(from);

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
				Movement move1 = new Movement(this,from,to);
				possibleMoves.add(move1);	
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
				Movement move2 = new Movement(this,from,to);
				possibleMoves.add(move2);	
			}
		}
		return possibleMoves;
	}
	
	/**
	 * check if the move is possible
	 */
	public boolean isRealMove(Movement move, Board board, String turn) {
		String to = move.getDestiny();
		
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
