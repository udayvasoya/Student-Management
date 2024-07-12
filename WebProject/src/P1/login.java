package P1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;


@WebServlet("/login")
public class login extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		RequestDispatcher rd;
		
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "uday");
			
			String em = request.getParameter("email");
			String psw = request.getParameter("password");
			
			PreparedStatement ps = conn.prepareStatement("select * from registration where email='"+em+"' and password='"+psw+"'");
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				String name = rs.getString("name");
			    out.print("<script>localStorage.setItem('userID', '" + name + "');</script>");
			    
			    // Close resources
			    rs.close();
			    ps.close();
			    conn.close();
			    
			    // Delay redirect to ensure JavaScript execution
			    out.print("<script>window.onload = function() { window.location.href = 'index.html'; }</script>");				out.print("<script>alert('Login Successfull');</script>");
				
			}
			else
			{
				out.print("<script>alert('Login Failes...');</script>");
				rd = request.getRequestDispatcher("./login.html");
				rd.include(request, response);
			}
			
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
