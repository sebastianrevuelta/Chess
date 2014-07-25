package com.sebas.core;

public class Board {

	private Square[][] squares;

	public Square[][] getSquares() { return squares; }
	public void setSquares(Square[][] casillas) { this.squares = casillas; }

	public Board() { 
		setSquares(buildInitialPosition());
	}


	/**
	 * build the start position
	 * @return
	 */
	private Square[][] buildInitialPosition() {
		Square[][] casillas = new Square[8][8];

		//fill board
		
		//white pieces
		Square squarea1 = createSquare("white","a","1","tower");
		casillas[0][0] = squarea1;
		
		Square squareb1 = createSquare("white","b","1","knight");
		casillas[1][0] = squareb1;

		Square squarec1 = createSquare("white","c","1","bishop");
		casillas[2][0] = squarec1;

		Square squared1 = createSquare("white","d","1","queen");
		casillas[3][0] = squared1;

		Square squaree1 = createSquare("white","e","1","king");
		casillas[4][0] = squaree1;

		Square squaref1 = createSquare("white","f","1","bishop");
		casillas[5][0] = squaref1;

		Square squareg1 = createSquare("white","g","1","knight");
		casillas[6][0] = squareg1;

		Square squareh1 = createSquare("white","h","1","tower");
		casillas[7][0] = squareh1;
		
		//white pawns
		Square squarea2 = createSquare("white","a","2","pawn");
		casillas[0][1] = squarea2;
		Square squareb2 = createSquare("white","b","2","pawn");
		casillas[1][1] = squareb2;
		Square squarec2 = createSquare("white","c","2","pawn");
		casillas[2][1] = squarec2;
		Square squared2 = createSquare("white","d","2","pawn");
		casillas[3][1] = squared2;
		Square squaree2 = createSquare("white","e","2","pawn");
		casillas[4][1] = squaree2;
		Square squaref2 = createSquare("white","f","2","pawn");
		casillas[5][1] = squaref2;
		Square squareg2 = createSquare("white","g","2","pawn");
		casillas[6][1] = squareg2;
		Square squareh2 = createSquare("white","h","2","pawn");
		casillas[7][1] = squareh2;

		//white pieces
		Square squarea8 = createSquare("black","a","8","tower");
		casillas[0][7] = squarea8;
		
		Square squareb8 = createSquare("black","b","8","knight");
		casillas[1][7] = squareb8;

		Square squarec8 = createSquare("black","c","8","bishop");
		casillas[2][7] = squarec8;

		Square squared8 = createSquare("black","d","8","queen");
		casillas[3][7] = squared8;

		Square squaree8 = createSquare("black","e","8","king");
		casillas[4][7] = squaree8;

		Square squaref8 = createSquare("black","f","8","bishop");
		casillas[5][7] = squaref8;

		Square squareg8 = createSquare("black","g","8","knight");
		casillas[6][7] = squareg8;

		Square squareh8 = createSquare("black","h","8","tower");
		casillas[7][7] = squareh8;

		//black pawns
		Square squarea7 = createSquare("black","a","7","pawn");
		casillas[0][6] = squarea7;
		Square squareb7 = createSquare("black","b","7","pawn");
		casillas[1][6] = squareb7;
		Square squarec7 = createSquare("black","c","7","pawn");
		casillas[2][6] = squarec7;
		Square squared7 = createSquare("black","d","7","pawn");
		casillas[3][6] = squared7;
		Square squaree7 = createSquare("black","e","7","pawn");
		casillas[4][6] = squaree7;
		Square squaref7 = createSquare("black","f","7","pawn");
		casillas[5][6] = squaref7;
		Square squareg7 = createSquare("black","g","7","pawn");
		casillas[6][6] = squareg7;
		Square squareh7 = createSquare("black","h","7","pawn");
		casillas[7][6] = squareh7;
		
		
		//empty board
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				Square square = casillas[i][j];
				if (square == null) {
					square = new Square();
					square.setEmpty(true);
					String horizontal = calculateHorizontal(i);
					square.setHorizontal(horizontal);
					String vertical = calculateVertical(j);
					square.setVertical(vertical);
					casillas[i][j] = square;
				}
			}
		}

		
		return casillas;
	}
	
	

	/**
	 * 
	 * @param color
	 * @param horizontal
	 * @param vertical
	 * @param type
	 * @return
	 */
	private Square createSquare(String color, String horizontal, String vertical, String type) {
		Square square = new Square();
		Piece p = null;
		if ("tower".equals(type)) p = new Tower();
		if ("knight".equals(type)) p = new Knight();
		if ("bishop".equals(type)) p = new Bishop();
		if ("queen".equals(type)) p = new Queen();
		if ("king".equals(type)) p = new King();
		if ("pawn".equals(type)) p = new Pawn();
		
		p.setColor(color);
		square.setHorizontal(horizontal);
		square.setVertical(vertical);
		square.setPieza(p);
		square.setEmpty(false);
		return square;
	}

	
	/**
	 * 
	 * @param j
	 * @return
	 */
	private String calculateVertical(int j) {
		String value = "";

		switch (j) {
		case 0:  value = "1";
		break;
		case 1: value = "2";
		break;
		case 2: value = "3";
		break;
		case 3: value = "4";
		break;
		case 4: value = "5";
		break;
		case 5: value = "6";
		break;
		case 6: value = "7";
		break;
		case 7: value = "8";
		break;
		default: value = "1";
		}
		return value;
	}


	/**
	 * 
	 * @param i
	 * @return
	 */
	private String calculateHorizontal(int i) {
		String value = "";

		switch (i) {
		case 0:  value = "a";
		break;
		case 1: value = "b";
		break;
		case 2: value = "c";
		break;
		case 3: value = "d";
		break;
		case 4: value = "e";
		break;
		case 5: value = "f";
		break;
		case 6: value = "g";
		break;
		case 7: value = "h";
		break;
		default: value = "a";
		}
		return value;
	}


	/**
	 * update movement
	 * @param m
	 * @param turn
	 */
	public void update(Movement m, String turn) {
		String origin = m.getOrigin();
		String destiny = m.getDestiny();
		
		int horOrigin = UtilChess.calculateHorizontal(origin); 
		int verOrigin = UtilChess.calculateVertical(origin);
		squares[horOrigin][verOrigin].setEmpty(true);
		
		int horDestiny = UtilChess.calculateHorizontal(destiny);
		int verDestiny = UtilChess.calculateVertical(destiny);
		
		String horizontal = destiny.substring(0,1);
		String vertical =  destiny.substring(1);
		
		Square square = createSquare(turn,horizontal,vertical, m.getPiece().getType());
		squares[horDestiny][verDestiny] = square;
		
		
	}
	

	

	/**
	 * print the board
	 */
	public void print() {
		System.out.flush();
		System.out.println("***********************");
		for(int i = 7; i >= 0; i--) {
			if (i != 7) System.out.println();
			for(int j = 0; j < 8; j++) {
				if (j == 0) System.out.print(" " + calculateVertical(i) + " ");
				if (squares[i][j].isEmpty()) {
					System.out.print("| ");
				}
				else {
					Piece p = squares[i][j].getPieza();
					String type = p.getType();
					String color = p.getColor();
					if ("tower".equals(type)) {
						if ("white".equals(color)) System.err.print("|T");
						else System.out.print("|T");
					}
					else if ("knight".equals(type)) {
						if ("white".equals(color)) System.err.print("|C");
						else System.out.print("|C");
					}
					else if ("bishop".equals(type)) {
						if ("white".equals(color)) System.err.print("|A");
						else System.out.print("|A");
					}
					else if ("king".equals(type)) {
						if ("white".equals(color)) System.err.print("|R");
						else System.out.print("|R");
					}
					else if ("queen".equals(type)) {
						if ("white".equals(color)) System.err.print("|D");
						else System.out.print("|D");
					}
					else if ("pawn".equals(type)) {
						if ("white".equals(color)) System.err.print("|p");
						else System.out.print("|p");
					}
				}
				if (j == 7) System.out.print("|");
			}
		}
		System.out.println();
		System.out.print("   ");
		for(int j = 0; j < 8; j++) {
		  System.out.print(" " + calculateHorizontal(j));
		}
	}

	public boolean checkMovement(Movement m) {
		return true;	
	}

}
