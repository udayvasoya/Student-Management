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

@WebServlet("/update")
public class update extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		RequestDispatcher rd;
		
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "uday");
			
			PreparedStatement ps = conn.prepareStatement("update student set name=?, marks=?, result=? where rollno=?");
			
			String name = request.getParameter("name");
			int marks = Integer.parseInt(request.getParameter("marks"));
			String result = request.getParameter("result");
			int no = Integer.parseInt(request.getParameter("rollno"));
			
			ps.setString(1, name);
			ps.setInt(2, marks);
			ps.setString(3, result);
			ps.setInt(4, no);
			
			int i = ps.executeUpdate();
			if(i > 0)
			{
				out.print("<script>alert('Update Successfull');</script>");
				rd = request.getRequestDispatcher("./ShowAllData");
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
