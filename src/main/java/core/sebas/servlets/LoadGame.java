package core.sebas.servlets;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.sebas.core.Match;

/**
 * Servlet class
 * @author srevuelta
 *
 */
public class LoadGame extends HttpServlet {
	
	private final static Logger log = Logger.getLogger(LoadGame.class);
	private Match match;
	public Match getMatch() { return match; }
	public void setMatch(Match match) { this.match = match; }
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request,final HttpServletResponse response) throws ServletException, IOException {
		
		boolean success = true;
		HttpSession session = request.getSession();

		String xmlfile = request.getParameter("xmlfile");
		
        File xmlFile = new File(xmlfile);
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        /* VIOLATION, vulnerable to XML entity injection and "billion laughs attack" */
        Document doc = null;
		try {
			doc = dbf.newDocumentBuilder().parse( xmlFile );
		} catch (SAXException | ParserConfigurationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        String history = doc.getNodeValue();
        session.setAttribute("history", history);
        
        
        if (history == null) {
        	success = false;
        }
        
        RequestDispatcher requestDispatcher = null;
        if (success) {
			requestDispatcher = request.getRequestDispatcher("/html/index.jsp");
			requestDispatcher.forward(request, response);
		} else {
			session.setAttribute("error", "Error with file: " + xmlfile);
			requestDispatcher = request.getRequestDispatcher("/html/index.jsp");
			requestDispatcher.forward(request, response);
		}
	}
}
