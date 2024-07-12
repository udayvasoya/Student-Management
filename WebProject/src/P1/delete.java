package P1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/delete")
public class delete extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		RequestDispatcher rd;
		
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "uday");
			
			PreparedStatement ps = conn.prepareStatement("delete from student where rollno=?");
			
			int no = Integer.parseInt(request.getParameter("rollno"));
			
			ps.setInt(1, no);
			
			int i = ps.executeUpdate();
			if(i > 0)
			{
				out.print("<script>alert('Delete Successfull');</script>");
				rd = request.getRequestDispatcher("./index.html");
				rd.include(request, response);
//				response.sendRedirect("./login.html");
			}
			else
			{
				out.print("<script>alert('Please try again');</script>");
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
