package com.sebas.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * Movement class.
 * It propose a movement for the piece from origin to destiny
 * @author srevuelta
 *
 */
public class Movement {

	private Piece piece;
	private String origin;
	private String destiny;
	private int value;

	public Piece getPiece() { return piece; }
	public void setPiece(Piece piece) {this.piece = piece;}

	public String getOrigin() { return origin; }
	public void setOrigin(String origin) { this.origin = origin; }

	public String getDestiny() { return destiny; }
	public void setDestiny(String destiny) { this.destiny = destiny; }
	
	public int getValue() { return value; }
	public void setValue(int value) { this.value = value; }
	
	Movement() {}

	Movement(Piece piece, String origin, String destiny) {
		this.piece = piece;
		this.origin = origin;
		this.destiny = destiny;
	}


	/**
	 * make the movement
	 * @param board
	 * @param turn
	 * @return
	 */
	public Movement makeMovement(Board board, String turn) {

		List<Movement> possiblesMoves = getPossiblesMoves(board,turn);
		//System.out.println("choosing between ..." + possiblesMoves.size());
		List<Movement> realMoves = filterMoves(board,possiblesMoves,turn);
		Movement move = chooseBestMove(realMoves);
		if (move != null) {
			this.setPiece(move.getPiece());
			this.setOrigin(move.getOrigin());
			this.setDestiny(move.getDestiny());
			//System.out.println("Moving..." + turn + ":" + move.getPiece().getType() + " " + move.getOrigin() + "-" + move.getDestiny());
		}
		else {
			//System.out.println("No more possible moves to do");
			if ("black".equals(turn)) {
				//System.out.println("White wins");
			}
			else {
				//System.out.println("Black wins");
			}
		}
		return this;
	}

	/**
	 * getPossiblesMoves
	 * @param board
	 * @param turn
	 * @return
	 */
	private List<Movement> getPossiblesMoves(Board board, String turn) {

		List<Movement> possiblesMoves = new ArrayList<Movement>();

		Square[][] squares = board.getSquares();
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Square square = squares[i][j];
				if (!square.isEmpty()){
					Piece p = square.getPieza();
					if (p.getColor().equals(turn)) {
						String from = square.getHorizontal()+square.getVertical();
						List<Movement> moves = p.move(from,turn,p.getType());
						possiblesMoves.addAll(moves);
					}
				}
			}
		}
		return possiblesMoves;
	}

	/**
	 * filterMoves
	 * @param board
	 * @param turn
	 * @param possiblesMoves
	 * @return
	 */
	private List<Movement> filterMoves(Board board,	List<Movement> possiblesMoves, String turn) {

		List<Movement> realMoves = new ArrayList<Movement>();

		Iterator<Movement> i = possiblesMoves.iterator();
		while (i.hasNext()) {
			Movement move = i.next();
			Piece p = move.getPiece();
			if (p.isRealMove(move,board,turn)) {
				
				Board boardcopy = copy(board);
				
				move.evaluate(boardcopy, turn);
				realMoves.add(move);
			}
			
		}
		return realMoves; 
	}

	private Board copy(Board board) {
		Board b = new Board();
		Square[][] squares = board.getSquares();
		Square[][] newSquares = new Square[8][8];
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Square square = squares[i][j];
				Square newSquare = new Square();
				newSquare.setEmpty(square.isEmpty());
				newSquare.setPieza(square.getPieza());
				newSquare.setHorizontal(square.getHorizontal());
				newSquare.setVertical(square.getVertical());
				newSquares[i][j] = newSquare;
			}
		}
		b.setSquares(newSquares);
		return b;
		
	}
	/**
	 * setValue
	 * @param b
	 * @param turn
	 */
	private void evaluate(Board b, String turn) {
		int value = 0;

		b.update(this, turn);
		Square[][] squares = b.getSquares();
		
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Square s = squares[i][j];
				if (!s.isEmpty()) {
					if (s.getPieza().getColor().equals(turn)) {
						value += s.getPieza().getValue();
					}
					else {
						value -= s.getPieza().getValue();
					}
				}
			}
			
		}
		this.setValue(value);
	}
	
	
	/**
	 * chooseBestMove
	 * @param possiblesMoves
	 * @return
	 */
	private Movement chooseBestMove(List<Movement> possiblesMoves) {
		
		Collections.sort(possiblesMoves, new Comparator<Movement>() {
		    @Override
		    public int compare(Movement o1, Movement o2) {
		        return new Integer(o1.getValue()).compareTo(new Integer(o2.getValue()));
		    }
		});
		
		if (possiblesMoves.size() > 0) {
		  return possiblesMoves.get(possiblesMoves.size()-1);
		}
		return null;
	}
}
