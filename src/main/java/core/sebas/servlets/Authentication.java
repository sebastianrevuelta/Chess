package core.sebas.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Authentication extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (Exception e) {}
	}

	@Override
	protected void doPost(HttpServletRequest request,final HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		boolean success = false;

		String query = "select * from user where username='" + username + "' and password = '" + password + "'";
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/chess?serverTimezone=UTC", "root", "root");
			stmt = conn.createStatement();
			
			//SQL injection. No validation of malicious input
			ResultSet rs = stmt.executeQuery(query); 

			//Fix SQLi with Prepared Statement
/*		    PreparedStatement sqlStatement = conn.prepareStatement("select * from user where username=? and password=?");
		    sqlStatement.setString(1, username);
		    sqlStatement.setString(2, password);
		    ResultSet rsFix = sqlStatement.executeQuery();*/
			
			List<String> list = new ArrayList<String>();							
			while (rs.next()) {
				// Login Successful if match is found
				success = true;

				list.add(rs.getString("username"));
				list.add(rs.getString("password"));
				session.setAttribute("user", list);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (Exception e) {}
		}
		
		RequestDispatcher requestDispatcher = null;

		if (success) {
			requestDispatcher = request.getRequestDispatcher("/html/index.jsp");
			requestDispatcher.forward(request, response);
		} else {
			session.setAttribute("error", "Username or Password incorrect");
			requestDispatcher = request.getRequestDispatcher("/html/login.jsp");
			requestDispatcher.forward(request, response);
		}
	}
}
