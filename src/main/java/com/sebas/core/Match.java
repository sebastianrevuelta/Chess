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
	private String logger;

	public String getLog() {
		return logger;
	}
	public void setLog(String log) {
		this.logger = log;
	}
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
		String result = "";
		//StringBuilder sb = new StringBuilder();
		Board board = new Board();
		try { 
			while (!checkmate) {
				board.print();
				Movement m = new Movement();
				m = m.makeMovement(board,turn,1);
				checkmate = board.checkMate(m);
				board.update(m,turn);
				
				result += m.getPiece();
				//sb.append(m.getPiece());
				
				if (turn.equals("white")) setTurno("black");
				else setTurno("white");

				Thread.sleep(TIME_OUT_THINKING);  
			}
			System.out.println("Match: " + result);
			//System.out.println("Match: " + sb.toString());
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
		
		m = m.makeMovement(board,turn,1);

		checkmate = board.checkMate(m);
		String descriptionMove;

		//boolean checkMate = board.checkMovement(m);
		board.update(m,turn);

		if ("white".equals(turn)) { 
			setTurno("black"); 
			if ("pawn".equals(m.getPiece().getType())) {
				descriptionMove = getHistoryMatch() + "\n" + movement + "." + m.getDestiny() + " ("+ m.getHeuristicValue() + ")";
			}
			else {
				descriptionMove = getHistoryMatch() + "			" + movement + "." + m.getPiece().getType().toUpperCase().charAt(0) + m.getDestiny() + " ("+ m.getHeuristicValue() + ")";
			}
		}
		else { 
			setTurno("white");
			if ("pawn".equals(m.getPiece().getType())) {
				descriptionMove = getHistoryMatch() + " | " + m.getDestiny() + " ("+ m.getHeuristicValue() + ")";
			}
			else {
				descriptionMove = getHistoryMatch() + " | " + m.getPiece().getType().toUpperCase().charAt(0) + m.getDestiny() + " ("+ m.getHeuristicValue() + ")";
			}
			movement++;
		}
		setHistoryMatch(descriptionMove);
		if (checkmate) {
			descriptionMove += "++";
		}

		return getHistoryMatch();
	}

}
