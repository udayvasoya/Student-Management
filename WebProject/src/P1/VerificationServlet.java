package P1;

import java.io.*;
import java.sql.*;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import javax.mail.*;
import javax.mail.internet.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.util.Date;
import java.util.Properties;
import java.util.Random;

@WebServlet("/VerificationServlet")
public class VerificationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM, yyyy");
        String currentDate = dateFormat.format(new Date());
        
		PrintWriter out = response.getWriter();
		
		 HttpSession session = request.getSession();
		 
		 String sessionEmail = (String) session.getAttribute("Email");
		 String sessionUsername = (String) session.getAttribute("userID");
	     String sessionPassword = (String) session.getAttribute("Password");
	        
		 String messageBody = "<!DOCTYPE html>" +
         	    "<html lang=\"en\">" +
         	    "<head>" +
         	    "<meta charset=\"UTF-8\" />" +
         	    "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />" +
         	    "<meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\" />" +
         	    "<title>Static Template</title>" +
         	    "<link href=\"https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600&display=swap\" rel=\"stylesheet\" />" +
         	    "</head>" +
         	    "<body style=\"margin: 0; font-family: 'Poppins', sans-serif; background: #ffffff; font-size: 14px;\">" +
         	    "<div style=\"max-width: 680px; margin: 0 auto; padding: 45px 30px 60px; background: #f4f7ff; background-image: url(https://archisketch-resources.s3.ap-northeast-2.amazonaws.com/vrstyler/1661497957196_595865/email-template-background-banner); background-repeat: no-repeat; background-size: 800px 452px; background-position: top center; font-size: 14px; color: #434343;\">" +
         	    "<header>" +
         	    "<table style=\"width: 100%\">" +
         	    "<tbody>" +
         	    "<tr style=\"height: 0\">" +
         	    "<td style=\"text-align: right\">" +
         	    "<span style=\"font-size: 16px; line-height: 30px; color: #ffffff\">Current Date : " +currentDate + "</span>" +
         	    "</td>" +
         	    "</tr>" +
         	    "</tbody>" +
         	    "</table>" +
         	    "</header>" +
                 "<main>\n" +
                 "        <div\n" +
                 "          style=\"\n" +
                 "            margin: 0;\n" +
                 "            margin-top: 70px;\n" +
                 "            padding: 22px 10px 30px;\n" +
                 "            border: 5px solid rgb(0, 14, 41);\n" +
                 "            box-shadow: rgba(0, 0, 0, 0.35) 0px 5px 15px;\n" +
                 "            background: #ffffff;\n" +
                 "            border-radius: 30px;\n" +
                 "            text-align: center;\n" +
                 "          \"\n" +
                 "        >\n" +
                 "          <h1\n" +
                 "            id=\"header\"\n" +
                 "            style=\"font-size: 25px; color: #ba3d4f; padding-top: 23px\"\n" +
                 "          >\n" +
                 "            Thank you For Registration\n" +
                 "          </h1>\n" +
                 "          <p\n" +
                 "            style=\"\n" +
                 "              font-size: 15px;\n" +
                 "              font-weight: 500;\n" +
                 "              color: #797979;\n" +
                 "              margin-bottom: 20px;\n" +
                 "            \"\n" +
                 "          >\n" +
                 "            --- Your credentials ---\n" +
                 "          </p>\n" +
                 "          <div style=\"width: 100%; max-width: 489px; margin: 0 auto\">\n" +
                 "            <p\n" +
                 "              style=\"\n" +
                 "                margin: 0;\n" +
                 "                margin-bottom: 7px;\n" +
                 "                font-weight: 700;\n" +
                 "                font-size: 25px;\n" +
                 "              \"\n" +
                 "            >\n" +
                 "              Hey "+ sessionUsername +
                 "            </p>\n" +
                 "\n" +
                 "            <p\n" +
                 "              style=\"\n" +
                 "                margin: 0;\n" +
                 "                margin-top: 30px;\n" +
                 "                font-size: 20px;\n" +
                 "                font-weight: 400;\n" +
                 "                letter-spacing: 1px;\n" +
                 "                color: #ba3d4f;\n" +
                 "              \"\n" +
                 "            >\n" +
                 "              Username : " + sessionUsername + "\n" +
                 "            </p>\n" +
                 "            <p\n" +
                 "              style=\"\n" +
                 "                margin: 0;\n" +
                 "                font-size: 20px;\n" +
                 "                font-weight: 400;\n" +
                 "                letter-spacing: 1px;\n" +
                 "                color: #ba3d4f;\n" +
                 "              \"\n" +
                 "            >\n" +
                 "              Password :  " + sessionPassword +
                 "            </p>\n" +
                 "            <p\n" +
                 "              style=\"\n" +
                 "                margin: 0;\n" +
                 "                margin-top: 30px;\n" +
                 "                font-weight: 500;\n" +
                 "                letter-spacing: 0.56px;\n" +
                 "              \"\n" +
                 "            >\n" +
                 "              Do not share this credentials with others .... &#128274;\n" +
                 "            </p>\n" +
                 "          </div>\n" +
                 "        </div>\n" +
                 "\n" +
                 "        <p\n" +
                 "          style=\"\n" +
                 "            max-width: 400px;\n" +
                 "            margin: 0 auto;\n" +
                 "            margin-top: 90px;\n" +
                 "            text-align: center;\n" +
                 "            font-weight: 500;\n" +
                 "            color: #8c8c8c;\n" +
                 "          \"\n" +
                 "        >\n" +
                 "          Need help? Ask at\n" +
                 "          <a\n" +
                 "            href=\"mailto:shivpatel635272@gmail.com\"\n" +
                 "            style=\"color: #499fb6; text-decoration: none\"\n" +
                 "            >udayvasoya@gmail.com</a\n" +
                 "          >\n" +
                 "          or visit our\n" +
                 "          <a\n" +
                 "            href=\"\"\n" +
                 "            target=\"_blank\"\n" +
                 "            style=\"color: #499fb6; text-decoration: none\"\n" +
                 "            >Help Center</a\n" +
                 "          >\n" +
                 "        </p>\n" +
                 "      </main>\n" +
         	    "<footer style=\"width: 100%; max-width: 490px; margin: 20px auto 0; text-align: center; border-top: 1px solid #e6ebf1;\">" +
         	    "<p style=\"margin: 0; margin-top: 40px; font-size: 16px; font-weight: 600; color: #434343;\">Uday Vasoya</p>" +
         	    "<div style=\"margin: 0; margin-top: 16px\">" +
         	    "<a href=\"\" target=\"_blank\" style=\"display: inline-block\"><img width=\"36px\" alt=\"Facebook\" src=\"https://archisketch-resources.s3.ap-northeast-2.amazonaws.com/vrstyler/1661502815169_682499/email-template-icon-facebook\" /></a>" +
         	    "<a href=\"\" target=\"_blank\" style=\"display: inline-block; margin-left: 8px\"><img width=\"36px\" alt=\"Instagram\" src=\"https://archisketch-resources.s3.ap-northeast-2.amazonaws.com/vrstyler/1661504218208_684135/email-template-icon-instagram\" /></a>" +
         	    "<a href=\"\" target=\"_blank\" style=\"display: inline-block; margin-left: 8px\"><img width=\"36px\" alt=\"Twitter\" src=\"https://archisketch-resources.s3.ap-northeast-2.amazonaws.com/vrstyler/1661503043040_372004/email-template-icon-twitter\" /></a>" +
         	    "<a href=\"\" target=\"_blank\" style=\"display: inline-block; margin-left: 8px\"><img width=\"36px\" alt=\"Youtube\" src=\"https://archisketch-resources.s3.ap-northeast-2.amazonaws.com/vrstyler/1661503195931_210869/email-template-icon-youtube\" /></a>" +
         	    "</div>" +
         	    "<p style=\"margin: 0; margin-top: 16px; color: #434343\">Copyright © 2023 UPVasoya. All rights reserved.</p>" +
         	    "</footer>" +
         	    "</div>" +
         	    "</body>" +
         	    "</html>";
		 
		 	String sessionOtp = (String) session.getAttribute("otp");
		 	
	        String userOtp = request.getParameter("digit1") + 
	        					request.getParameter("digit2") + 
	        					request.getParameter("digit3") + 
	        					request.getParameter("digit4") + 
	        					request.getParameter("digit5") + 
	        					request.getParameter("digit6");
		 
        
        if(sessionOtp != null && sessionOtp.equals(userOtp)) 
        {
            sendEmail(sessionEmail,"Successfully Registration" ,messageBody);
            response.sendRedirect("./login.html");
        } else 
        {
            out.println("<h1>Incorrect OTP. Registration Failed.</h1>");
            response.sendRedirect("./reg.html");
        }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	public void sendEmail(String recipientEmail, String subject, String messageBody) {
	    // Sender's email ID and password need to be mentioned
	    final String username = "vasoyauday808@gmail.com"; // <-- Replace with your Gmail username
	    final String password = "riam tqkd bnms qjtm";

	    // Setting up mail server properties
	    Properties props = new Properties();
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.smtp.host", "smtp.gmail.com");
	    props.put("mail.smtp.port", "587");

	    // Creating a new session with an authenticator
	    Session session = Session.getInstance(props, new javax.mail.Authenticator() {
	        protected PasswordAuthentication getPasswordAuthentication() {
	            return new PasswordAuthentication(username, password);
	        }
	    });

	    try {
	        // Creating a default MimeMessage object
	        Message message = new MimeMessage(session);

	        // Setting From: header field of the header
	        message.setFrom(new InternetAddress(username));

	        // Setting To: header field of the header
	        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail)); // Specified recipient email address

	        // Setting Subject: header field
	        message.setSubject(subject);

	        // Now set the actual message
	        message.setContent(messageBody, "text/html"); // Set content as HTML

	        // Sending the message
	        Transport.send(message);

	        // Writing response
	        System.out.println("Sent message successfully to " + recipientEmail);
	    } catch (MessagingException e) {
	        // Print the stack trace to the console
	        e.printStackTrace();

	        // Write the error message to the response
	        System.out.println("Failed to send the email. Error: " + e.getMessage());
	    }
	}
}
