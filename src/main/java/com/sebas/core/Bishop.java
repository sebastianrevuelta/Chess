package com.sebas.core;

import java.util.ArrayList;
import java.util.Iterator;
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
	
	public boolean isRealMove(Movement move, Board board, String turn) {

		List<Square> squares = getSquares(board, move);
		Iterator<Square> i = squares.iterator();
		while (i.hasNext()) {
			Square square = i.next();
			if (!square.isEmpty()) {
				Piece p = square.getPieza();
				if (p != null) {
					if (turn.equals(square.getPieza().getColor())) {
						return false;
					}
				}
			}
		}

		return true;
	}

	private List<Square> getSquares(Board board, Movement move) {
		List<Square> squares = new ArrayList<Square>();

		String from = move.getOrigin();
		String to = move.getDestiny();

		int horizontalTo = UtilChess.calculateHorizontal(to);
		int verticalTo = UtilChess.calculateVertical(to);

		int horizontalFrom = UtilChess.calculateHorizontal(from);
		int verticalFrom = UtilChess.calculateVertical(from);
		
		
		
		return squares;
		
	}

}
