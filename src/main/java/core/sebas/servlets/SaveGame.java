package core.sebas.servlets;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.sebas.core.Match;

/**
 * Servlet class
 * @author srevuelta
 *
 */
public class SaveGame extends HttpServlet {
	
	private final static Logger log = Logger.getLogger(SaveGame.class);
	private Match match;
	public Match getMatch() { return match; }
	public void setMatch(Match match) { this.match = match; }

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		
		Match match = (Match) session.getAttribute("match");
		
		String file = req.getParameter("file");
		File f = new File(file);
		f.createNewFile();

		BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
		bw.write("Match"); 
		bw.newLine();
		bw.write("player1: " + match.getPlayer1()); 
		bw.write("player2: " + match.getPlayer2()); 
		bw.newLine();
		bw.write("time: " + match.getTimer() + " minutes"); 
		bw.newLine();
		bw.write(match.getHistoryMatch());
		bw.newLine();
		bw.flush();
		bw.close();
		out.println("<h4>Match correctly save to: " + f.getAbsolutePath() + "</h4>");
		session.setAttribute("shouldMove",false);
		session.setAttribute("message","Match correctly save to: " + f.getAbsolutePath());
		RequestDispatcher requestDispatcher = req.getRequestDispatcher("./Play");
		requestDispatcher.forward(req, resp);
	}
}
