package com.sebas.core;

import org.apache.log4j.Logger;
/**
 * This is the Match class
 * @author srevuelta
 *
 */
public class Match {

	private static final long TIME_OUT_THINKING = 100;
	private final static Logger log = Logger.getLogger(Match.class);

	private Board board;
	private String turn;
	private boolean checkmate;
	private int movement;
	private String historyMatch;

	public Board getBoard() { return board; }
	public final void setBoard(Board board) { this.board = board; }

	public String getTurno() { return turn; }
	public final void setTurno(String turno) { this.turn = turno; }

	public String getHistoryMatch() { return historyMatch; }
	public final void setHistoryMatch(String historyMatch) { this.historyMatch = historyMatch; }

	public Match(String turno) {
		setBoard(new Board());
		setTurno(turno);
		movement = 1;
		setHistoryMatch("");
	}

	/**
	 * start the game
	 */
	public void startGame() {

		Board board = new Board();
		try { 
			while (!checkmate && movement != 6) {
				board.print();
				Movement m = new Movement();
				boolean checkMate = false;
				while (!checkMate) {
					m = m.makeMovement(board,turn);
					checkMate = board.checkMovement(m);
				}
				board.update(m,turn);

				if ("white".equals(turn)) setTurno("black");
				else setTurno("white");

				Thread.sleep(TIME_OUT_THINKING);  

			}
		}
		catch (InterruptedException e) { 
			log.error("Error in the thread of the match");
		}
	}

	/**
	 * move!
	 * @return
	 */
	public String getMove() {

		Movement m = new Movement();
		m = m.makeMovement(board,turn);
		String descriptionMove;

		//boolean checkMate = board.checkMovement(m);
		board.update(m,turn);

		if ("white".equals(turn)) { 
			setTurno("black"); 
			if ("pawn".equals(m.getPiece())) {
				descriptionMove = getHistoryMatch() + "\n" + movement + "." + m.getDestiny();
			}
			else {
				descriptionMove = getHistoryMatch() + "			" + movement + "." + m.getPiece().getType().toUpperCase().charAt(0) + m.getDestiny();
			}
			setHistoryMatch(descriptionMove);
		}
		else { 
			setTurno("white");
			if ("pawn".equals(m.getPiece())) {
				descriptionMove = getHistoryMatch() + " | " + m.getDestiny();
			}
			else {
				descriptionMove = getHistoryMatch() + " | " + m.getPiece().getType().toUpperCase().charAt(0) + m.getDestiny();
			}
			setHistoryMatch(descriptionMove);
			movement++;
		}

		return getHistoryMatch();
	}

}
