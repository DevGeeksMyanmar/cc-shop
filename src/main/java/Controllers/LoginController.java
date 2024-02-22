package Controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Config.Hash;
import Models.*;
import DAO.*;

public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	CustomerDAO customerDAO = null;
	SellerDAO sellerDAO = null;
	AdminDAO adminDAO = null;
	BusinessDAO businessDAO = null;
	RequestDispatcher dispatcher = null;
	HttpSession session = null;
       
    public LoginController() throws ClassNotFoundException, SQLException {
        super();
        customerDAO = new CustomerDAO();
        sellerDAO = new SellerDAO();
        adminDAO = new AdminDAO();
        businessDAO = new BusinessDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page = request.getParameter("page");
		session = request.getSession();
		if(page != null) {
			if(page.equals("sellerForm")) {
				try {
					List<Business> businessList = businessDAO.get();
					request.setAttribute("businessList", businessList);
					dispatcher = request.getRequestDispatcher("views/seller/form.jsp");
					dispatcher.forward(request, response);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if(page.equals("adminLogout")) {
				session.removeAttribute("admin");
				response.sendRedirect(request.getContextPath()  + "/views/admin/form.jsp");
			}
			
			if(page.equals("sellerLogout")) {
				session.removeAttribute("seller");
				response.sendRedirect(request.getContextPath() + "/RegisterController?page=sellerForm");
			}
			
			if(page.equals("userLogout")) {
				session.removeAttribute("customer");
				response.sendRedirect(request.getContextPath() + "/views/user/form.jsp");
			}
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page = request.getParameter("page");
		if(page != null) {
			switch(page) {
			case "userLogin":
				try {
					userLogin(request, response);
				} catch (ServletException | IOException | SQLException e) {
					e.printStackTrace();
				}
				break;
			case "sellerLogin":
				try {
					sellerLogin(request, response);
				} catch (ServletException | IOException | SQLException e) {
					e.printStackTrace();
				}
				break;
			case "adminLogin":
				try {
					adminLogin(request, response);
				} catch (ServletException | IOException | SQLException e) {
					e.printStackTrace();
				}
				break;
			}
		}
	}
	
	// login user
	private void userLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		Customer customer = new Customer();
		HttpSession session = request.getSession();
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		customer = customerDAO.getUserByEmail(email);
		if(customer != null) {
			if(Hash.verifyPassword(password, customer.getPassword())) {
				session.setAttribute("customer", customer);
				response.sendRedirect(request.getContextPath()+"/UserController?page=main");
			}else {
				request.setAttribute("error", "Passwords do not match");
	            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/user/form.jsp");
	            dispatcher.forward(request, response);
			}
		}else {
			request.setAttribute("error", "Email was wrong!");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/user/form.jsp");
            dispatcher.forward(request, response);
		}
	}

	// login servlet
	private void sellerLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
		Seller seller = new Seller();
		HttpSession session = request.getSession();
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		seller = sellerDAO.getSellerByEmail(email);
		if(seller != null) {
			if(Hash.verifyPassword(password, seller.getPassword())) {
				session.setAttribute("seller", seller);
				response.sendRedirect(request.getContextPath()+"/SellerController?page=dashboard");
			}else {
				request.setAttribute("error", "Email or Password was wrong!");
	            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/seller/form.jsp");
	            dispatcher.forward(request, response);
			}
		}else {
			request.setAttribute("error", "Email was wrong!");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/seller/form.jsp");
            dispatcher.forward(request, response);
		}
	}
	
	// login servlet
	private void adminLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
		Admin admin = new Admin();
		HttpSession session = request.getSession();
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		admin = adminDAO.getAdminByEmail(email);
		if(admin != null) {
			if(Hash.verifyPassword(password, admin.getPassword())) {
				session.setAttribute("admin", admin);
				response.sendRedirect(request.getContextPath() + "/AdminController?page=dashboard");
			}else {
				request.setAttribute("error", "Password was wrong!");
	            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/admin/form.jsp");
	            dispatcher.forward(request, response);
			}
		}else {
			request.setAttribute("error", "Email was wrong!");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/admin/form.jsp");
            dispatcher.forward(request, response);
		}
	}
	

}
