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
	private int materialValue;
	private int squaresControlled;
	private double heuristicValue;
	
	
	public Piece getPiece() { return piece; }
	public void setPiece(Piece piece) {this.piece = piece;}

	public String getOrigin() { return origin; }
	public void setOrigin(String origin) { this.origin = origin; }

	public String getDestiny() { return destiny; }
	public void setDestiny(String destiny) { this.destiny = destiny; }
	
	public int getValue() { return materialValue; }
	public void setValue(int value) { this.materialValue = value; }
	
	public int getSquaresControlled() { return squaresControlled; }
	public void setSquaresControlled(int squaresControlled) { this.squaresControlled = squaresControlled; }
	
	public double getHeuristicValue() { return heuristicValue; }
	public void setHeuristicValue(double heuristicValue) { this.heuristicValue = heuristicValue; }
	
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
		List<Movement> realMoves = filterMoves(board,possiblesMoves,turn);
		List<Movement> realEvaluatedMoves = evaluatedMoves(board,realMoves,turn);
		Movement move = chooseBestMoveMaterial(realEvaluatedMoves);
		if (move != null) {
			this.setPiece(move.getPiece());
			this.setOrigin(move.getOrigin());
			this.setDestiny(move.getDestiny());
			System.out.println("Moving..." + turn + ":" + move.getPiece().getType() + " " + move.getOrigin() + "-" + move.getDestiny());
		}
		return this;
	}

	private Movement evaluateNumberSquares(Board copyBoard, String turn) {
		copyBoard.update(this, turn);
		List<Movement> list = getPossiblesMoves(copyBoard,turn);
		this.setSquaresControlled(list.size());
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
	 * evaluatedMoves
	 * @param board
	 * @param realMoves
	 * @param turn
	 * @return
	 */
	private List<Movement> evaluatedMoves(Board board,	List<Movement> realMoves, String turn) {

		List<Movement> evaluatedMoves = new ArrayList<Movement>();
		Board boardcopy = copy(board);
		
		Iterator<Movement> i = realMoves.iterator();
		while (i.hasNext()) {
			Movement move = i.next();
			move.evaluateMaterial(boardcopy, turn);
			move.evaluateNumberSquares(boardcopy, turn);
			if (move.getSquaresControlled() > 0) {
				double finalValue = (double)move.getValue() + (double)100/(double)move.getSquaresControlled();
				move.setHeuristicValue(finalValue);
			}
			else {
				move.setHeuristicValue(0);
			}
			
			evaluatedMoves.add(move);
		}
		
		return evaluatedMoves; 
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
	private void evaluateMaterial(Board b, String turn) {
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
	private Movement chooseBestMoveMaterial(List<Movement> possiblesMoves) {
		
		Collections.sort(possiblesMoves, new Comparator<Movement>() {
		    @Override
		    public int compare(Movement o1, Movement o2) {
		        return new Double(o1.getHeuristicValue()).compareTo(new Double(o2.getHeuristicValue()));
		    }
		});
		
		if (possiblesMoves.size() > 0) {
		  return possiblesMoves.get(possiblesMoves.size()-1);
		}
		return null;
	}
}
