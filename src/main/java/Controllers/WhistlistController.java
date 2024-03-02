package Controllers;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;
import java.util.*;
import DAO.*;
import Models.*;


@WebServlet("/WhistlistController")
public class WhistlistController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	RequestDispatcher dispatcher = null;
	WhistlistDAO whistDAO = null;

    public WhistlistController() throws ClassNotFoundException, SQLException {
        super();
        whistDAO = new WhistlistDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if(action != null) {
			
			switch(action) {
			
			case "addToWhistList":
				do_add_to_whistlist(request, response);
				break;
			
			}
			
		}
	}
	
	
	// add to whistlist
	private void do_add_to_whistlist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String product_id = request.getParameter("product_id");
		String user_id = request.getParameter("user_id");
		boolean flag = whistDAO.create(Integer.parseInt(product_id), Integer.parseInt(user_id));
		if(flag) {
			String success = "Add to whistlist successfully!";
			String encoded = URLEncoder.encode(success, "UTF-8");
			response.sendRedirect(request.getContextPath() + "/UserController?page=main&success="+encoded);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
