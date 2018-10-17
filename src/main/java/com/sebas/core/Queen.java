package com.sebas.core;

import java.util.ArrayList;
import java.util.Iterator;
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
		
		List<Square> squares = getSquares(board, movement);
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

	private List<Square> getSquares(Board board, Movement movement) {
		List<Square> squares = new ArrayList<Square>();
		Tower tower = new Tower();
		Bishop bishop = new Bishop();
		List<Square> squaresT = tower.getSquares(board, movement);
		List<Square> squaresB = bishop.getSquares(board, movement);
		squares.addAll(squaresB);
		squares.addAll(squaresT);
		return squares;
	}
}
