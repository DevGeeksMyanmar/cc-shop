package Controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Config.mail;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

/**
 * Servlet implementation class EmailServlet
 */
@WebServlet("/EmailServlet")
public class EmailServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    }
    
    public static void main(String args[]) {
    	 String sender = "hein37905@gmail.com";
    	 String receiver = "heinhtetsan33455@gmail.com";
    	 String subject = "Test";
    	 String message = "Testing email";
    	
    	 try {
			mail.sendEmail(receiver, subject, message);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}

