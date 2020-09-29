package core.sebas.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Game extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	@Override
	protected void doGet(HttpServletRequest request,final HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String player1 = request.getParameter("player1");
		String player2 = request.getParameter("player2");
		
		Cookie cookie1 = new Cookie("player1", player1);
		Cookie cookie2 = new Cookie("player2", player2);
		
		String timer = request.getParameter("timer");
		session.setAttribute("timer", timer); 
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/html/game.jsp");
		requestDispatcher.forward(request, response);
	}
	
}
