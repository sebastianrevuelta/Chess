package com.sebas.core;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece {

	public King() {
		super.setType("king");
		super.setValue(1000);
	}


	/***
	 * move the king
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

		if (valueRow+1 < 8) {
			int row = new Integer(valueRow+1).intValue();
			rowdestiny = UtilChess.calculateVertical(row);
			if (valueColumn+1 < 8) {
				int column = new Integer(valueColumn+1).intValue();
				columndestiny = UtilChess.calculateHorizontal(column);
				to = columndestiny + rowdestiny;	
				Movement move = new Movement(this,from,to);
				possibleMoves.add(move);				
			}
			if (valueColumn < 8) {
				int column = new Integer(valueColumn).intValue();
				columndestiny = UtilChess.calculateHorizontal(column);
				to = columndestiny + rowdestiny;	
				Movement move = new Movement(this,from,to);
				possibleMoves.add(move);				
			}
			if (valueColumn-1 >= 0) {
				int column = new Integer(valueColumn-1).intValue();
				columndestiny = UtilChess.calculateHorizontal(column);
				to = columndestiny + rowdestiny;	
				Movement move = new Movement(this,from,to);
				possibleMoves.add(move);				
			}
		}

		if (valueRow-1 >= 0) {
			int row = new Integer(valueRow-1).intValue();
			rowdestiny = UtilChess.calculateVertical(row);
			if (valueColumn+1 < 8) {
				int column = new Integer(valueColumn+1).intValue();
				columndestiny = UtilChess.calculateHorizontal(column);
				to = columndestiny + rowdestiny;	
				Movement move = new Movement(this,from,to);
				possibleMoves.add(move);				
			}
			if (valueColumn < 8) {
				int column = new Integer(valueColumn).intValue();
				columndestiny = UtilChess.calculateHorizontal(column);
				to = columndestiny + rowdestiny;	
				Movement move = new Movement(this,from,to);
				possibleMoves.add(move);				
			}
			if (valueColumn-1 >= 0) {
				int column = new Integer(valueColumn-1).intValue();
				columndestiny = UtilChess.calculateHorizontal(column);
				to = columndestiny + rowdestiny;	
				Movement move = new Movement(this,from,to);
				possibleMoves.add(move);				
			}
		}
		if (valueColumn+1 < 8) {
			int row = new Integer(valueRow).intValue();
			rowdestiny = UtilChess.calculateVertical(row);
			int column = new Integer(valueColumn+1).intValue();
			columndestiny = UtilChess.calculateHorizontal(column);
			to = columndestiny + rowdestiny;	
			Movement move = new Movement(this,from,to);
			possibleMoves.add(move);				
		}

		if (valueColumn-1 >= 0) {
			int row = new Integer(valueRow).intValue();
			rowdestiny = UtilChess.calculateVertical(row);
			int column = new Integer(valueColumn-1).intValue();
			columndestiny = UtilChess.calculateHorizontal(column);
			to = columndestiny + rowdestiny;	
			Movement move = new Movement(this,from,to);
			possibleMoves.add(move);				
		}
		return possibleMoves;
	}
}
