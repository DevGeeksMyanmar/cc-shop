package Controllers;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Config.Hash;
import DAO.*;
import Models.*;


public class PasswordController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	AdminDAO adminDAO = null;
	RequestDispatcher dispatcher = null;
       
    public PasswordController() throws ClassNotFoundException, SQLException {
        super();
        adminDAO = new AdminDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String page = request.getParameter("page");
		if(page != null) {
			switch(page) {
			case "adminPasswordChange": // admin password change
				String admin_id = request.getParameter("admin_id");
				Admin get_admin = adminDAO.getById(Integer.parseInt(admin_id));
				request.setAttribute("admin", get_admin);
				request.getRequestDispatcher("/views/admin/profile/changePassword.jsp").forward(request, response);
				break;
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if(action != null) {
			switch(action) {
			// update admin password
			case "updateAdminPassword":
				HttpSession session = request.getSession();
				String admin_id = request.getParameter("admin_id");
				
				Admin fetch_admin = adminDAO.getById(Integer.parseInt(admin_id));
				String current_password = request.getParameter("current_password");
				String new_password = request.getParameter("new_password");
				String confirm_new_password = request.getParameter("confirm_new_password");
				
				if(!current_password.isEmpty() && !new_password.isEmpty() && !confirm_new_password.isEmpty()) {
					boolean is_verified = Hash.verifyPassword(current_password, fetch_admin.getPassword());
					System.out.println(is_verified);
					// first check the current password
					if(is_verified) {
						// and then check new passowrd
						System.out.println("success");
						if(new_password.equals(confirm_new_password)) {
							String hashed_password = Hash.hashPassword(new_password);
							System.out.println("got hashed");
							try {
								if(adminDAO.updatePassword(hashed_password, Integer.parseInt(admin_id))) {
									System.out.println("got updated");
									String success = "Updated Password successfully";
									String encodedSuccess = URLEncoder.encode(success, "UTF-8");
									response.sendRedirect(request.getContextPath() +"/AdminController?page=dashboard&success="+encodedSuccess);
								}
							} catch (NumberFormatException | SQLException e) {
								e.printStackTrace();
							}
						}else {
							String error = "Password Don't Match";
							String encodedError = URLEncoder.encode(error, "UTF-8");
							response.sendRedirect(request.getContextPath() +"/PasswordController?page=adminPasswordChange&admin_id="+admin_id+"&error="+encodedError);
						}
					}else {
						String error = "Wrong Password!";
						String encodedError = URLEncoder.encode(error, "UTF-8");
						response.sendRedirect(request.getContextPath() +"/PasswordController?page=adminPasswordChange&admin_id="+admin_id+"&error="+encodedError);
					}
				}else {
					String error = "Don't miss, fill the feilds!";
					String encodedError = URLEncoder.encode(error, "UTF-8");
					response.sendRedirect(request.getContextPath() +"/PasswordController?page=adminPasswordChange&admin_id="+admin_id+"&error="+encodedError);
				}
				break;
			}
		}
	}

}