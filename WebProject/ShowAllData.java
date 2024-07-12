package P1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ShowAllData")
public class ShowAllData extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "uday");
			
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("select * from student");
			
			out.println("<!DOCTYPE html>");
	        out.println("<html>");
	        out.println("<head>");
	        out.println("<title>Student Details</title>");
	        out.print("<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css' />");
	        out.println("<style>");
	        out.println("body { background-color: black; font-family: Arial, sans-serif; }");
	        out.println("h1 { color: white; text-align: center; }");
	        out.println(".styled-table { background: linear-gradient(white, #212121) padding-box,linear-gradient(145deg, transparent 35%, #e81cff, #40c9ff) border-box;border: 5px solid transparent;padding: 32px 24px;font-size: 14px;font-family: inherit;color: white;display: flex;position: absolute;top: 50%;left: 50%;transform: translate(-50%, -50%);flex-direction: column;gap: 20px;box-sizing: border-box;border-radius: 16px;background-size: 200% 100%;animation: gradient 5s ease infinite; }");
	        out.println(".styled-table th, .styled-table td { padding: 10px; font-size: 20px; border: 1px solid black; text-align: left; }");
	        out.println(".styled-table button { background-color: linear-gradient(white, #212121); /* Green */ border: none; color: black; 	 text-align: center; display: inline-block; font-size: 20px; margin: 4px 2px; transition-duration: 0.4s; cursor: pointer; border-radius: 8px;text-decoration:none }");
	        out.println("</style>");
	        out.println("</head>");
	        out.println("<body>");
	        
	        out.println("<h1>Student Details</h1>");
	        out.println("<table class=\"styled-table\">"); // Apply CSS class to table
			out.print("<tr><th>RollNo</th><th>Name</th><th>Marks</th><th>Result</th><th colspan=2></th></tr>");
			while(rs.next())
			{
				out.print("<tr>");
				out.print("<td>"+rs.getInt(1)+"</td>");
				out.print("<td>"+rs.getString(2)+"</td>");
				out.print("<td>"+rs.getString(3)+"</td>");
				out.print("<td>"+rs.getString(4)+"</td>");
				out.print("<td><a href='updatestud.html'><button><i class='fa fa-pencil' aria-hidden='true'></i></button></a></td>");
				out.print("<td><a href='deletestud.html'><button><i class='fa fa-trash' aria-hidden='true'></i></button></a></td>");
				out.print("</tr>");
			}
			out.print("<tr><td colspan=6 style='text-align:center'><a href='index.html'><Button><i class='fa fa-home' aria-hidden='true'></i></Button></a></td></tr>");
			out.print("</table>");
			out.print("</body>");
			out.print("</html>");
			
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
