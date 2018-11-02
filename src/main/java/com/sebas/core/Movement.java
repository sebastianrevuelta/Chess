package com.sebas.core;

import java.util.ArrayList;
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


	public Piece getPiece() { return piece; }
	public void setPiece(Piece piece) {this.piece = piece;}

	public String getOrigin() { return origin; }
	public void setOrigin(String origin) { this.origin = origin; }

	public String getDestiny() { return destiny; }
	public void setDestiny(String destiny) { this.destiny = destiny; }

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
		this.setPiece(move.getPiece());
		this.setOrigin(move.getOrigin());
		this.setDestiny(move.getDestiny());
		System.out.println("Moving..." + turn + ":" + move.getPiece().getType() + " " + move.getOrigin() + "-" + move.getDestiny());
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
				realMoves.add(move);
			}
		}
		return realMoves; 
	}

	/**
	 * chooseBestMove
	 * @param possiblesMoves
	 * @return
	 */
	private Movement chooseBestMove(List<Movement> possiblesMoves) {
		int totalMoves = possiblesMoves.size();
		Movement move = possiblesMoves.get(UtilChess.getRandomValue(totalMoves));
		return move; //TODO
	}
}
