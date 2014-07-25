package core.sebas.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HolaMundo extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		try {
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Hola mundo</title>");
			out.println("</head>");out.println("<body>");
			out.println("<h1>Hola mundo!</h1>");
			out.println("</body>");
			out.println("</html>");
		} 
		catch(Exception e) {System.err.println(e.getMessage());}
		finally {out.close();}
	}
}
