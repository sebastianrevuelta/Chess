package core.sebas.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.sebas.core.Board;
import com.sebas.core.Match;
import com.sebas.core.Square;

/**
 * Servlet class
 * @author srevuelta
 *
 */
public class MatchServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4530399168686881410L;
	private final static Logger log = Logger.getLogger(MatchServlet.class);


	@Override
	protected void doGet(HttpServletRequest request,final HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String player1 = request.getParameter("player1");
		String player2 = request.getParameter("player2");
		String timer = request.getParameter("timer");
		response.setContentType("text/html;charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		Match match = (Match) session.getAttribute("match");
		if (match == null) { match = new Match("white"); }
		match.setPlayer1(player1);
		match.setPlayer2(player2);
		match.setTimer(timer);

		try {
			StringBuffer html = paintMatch(match,true, "Click play button");
			out.println(html.toString());
		    session.setAttribute("match", match);
		}
		catch(Exception e) {
			log.error("Error in the servlet" + this.getClass().getName());
		}
		finally {out.close();}
	}
	
	@Override
	protected void doPost(HttpServletRequest request,final HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		response.setContentType("text/html;charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		Match match = (Match) session.getAttribute("match");
		if (match == null) { match = new Match("white"); }
		
		boolean shouldMove = (boolean) session.getAttribute("shouldMove");
		String message = (String) session.getAttribute("message");
		
		try {
			StringBuffer html = paintMatch(match,shouldMove, message);
			out.println(html.toString());
		    session.setAttribute("match", match);
		    session.setAttribute("shouldMove",true);
		}
		catch(Exception e) {
			log.error("Error in the servlet" + this.getClass().getName());
		}
		finally {out.close();}
	}
	
	private StringBuffer paintMatch(Match match, boolean shouldMove, String message) {
		
		StringBuffer sb = new StringBuffer();

		String move= "";
		if (shouldMove) {
		  move = match.getMove();
		}

		Board board = match.getBoard();
		String history = match.getHistoryMatch();
		Square[][] squares = board.getSquares();
		
		sb.append("<head>");
		sb.append("<meta http-equiv='content-type' content='text/html; charset=utf-8' />");
		sb.append("<title>Vulnerable Chess Game</title>");
		sb.append("<script src='./js/ajax.js' language='JavaScript'></script>");
		sb.append("<link rel='stylesheet' type='text/css' href='./css/squares.css' media='screen' />");
		sb.append("</head>");
		sb.append("<body>");
		sb.append("<h2>Vulnerable Chess Game</h2>");

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Square square = squares[i][j];
				String casilla = square.getHorizontal()+square.getVertical();
				
				if (!square.isEmpty()) {
					
				 String pieza = square.getPieza().getType();
				 char color = square.getPieza().getColor().charAt(0);
				 sb.append("<div id='" + casilla  + "'><img src='./images/" + pieza + color + ".png'/></div>");
				}
				else {
					sb.append("<div id='" + casilla + "'></div>");
				}
			}
		}			
		sb.append("<div id='match'>" + history + "</div>");
		sb.append("<div id='play'> <a href='./Play'>Play</a></div>");
		sb.append("<div id='new'> <a href='./Game'>New</a></div>");
		sb.append("<div id='save'> <a href='./Save'>Save</a></div>");
		sb.append("<div id='load'> <a href='./Load'>Load</a></div>");
		sb.append("<div id='message'>" + message + "</div>");
		sb.append("</body>");
		sb.append("</html>");
		
		return sb;
	}
}
