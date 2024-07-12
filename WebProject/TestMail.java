package P1;

import java.io.*;
import java.sql.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Random;

@WebServlet("/TestMail")
public class TestMail extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM, yyyy");
        String currentDate = dateFormat.format(new Date());

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "uday");

            PreparedStatement ps = conn.prepareStatement("insert into registration values(?,?,?,?)");

            String nm = request.getParameter("name");
            String em = request.getParameter("email");
            String phone = request.getParameter("phone");
            String psw = request.getParameter("password");

            ps.setString(1, nm);
            ps.setString(2, em);
            ps.setString(3, phone);
            ps.setString(4, psw);


            if (ps.executeUpdate() > 0) {
                // Send OTP to the provided email
//              sendEmail(em, "OTP Verification", "Successfully Sent");

                // Redirect to OTP verification page
                response.sendRedirect("otp.html");
            } else {
                out.print("<script>alert('Registration Not Successful');</script>");
                RequestDispatcher rd = request.getRequestDispatcher("./LoginRegistration.html");
                rd.include(request, response);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    private void sendEmail(String toEmail, String subject, String body) {
        final String fromEmail = "shivlab2023@gmail.com";
        final String password = "poxi dwze fbqz edzm";

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Authenticator auth = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        };

        Session session = Session.getInstance(props, auth);

        try {
            MimeMessage msg = new MimeMessage(session);
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.setHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");

            msg.setFrom(new InternetAddress(fromEmail));
            msg.setReplyTo(InternetAddress.parse(fromEmail, false));
            msg.setSubject(subject, "UTF-8");
            msg.setContent(body, "text/html");
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
            Transport.send(msg);
            System.out.println("Email sent successfully to: " + toEmail);
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("Failed to send the email to: " + toEmail);
        }
    }
}
