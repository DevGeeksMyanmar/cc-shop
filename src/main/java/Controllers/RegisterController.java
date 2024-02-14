package Controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Models.*;
import DAO.*;

public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CustomerDAO customerDAO = null;
	SellerDAO sellerDAO = null;
	AdminDAO adminDAO = null;
	RequestDispatcher dispatcher = null;

    public RegisterController() throws ClassNotFoundException, SQLException {
        super();
        customerDAO = new CustomerDAO();
        sellerDAO = new SellerDAO();
        adminDAO = new AdminDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page = request.getParameter("page");
		if(page != null) {
			switch(page) {
			case "userRegister":
				try {
					userRegister(request, response);
				} catch (ServletException | IOException | SQLException e) {
					e.printStackTrace();
				}
				break;
			case "sellerRegister":
				try {
					sellerReigster(request, response);
				} catch (ServletException | IOException | SQLException e) {
					e.printStackTrace();
				}
				break;
			case "adminRegister":
				try {
					adminRegister(request, response);
				} catch (ServletException | IOException | SQLException e) {
					e.printStackTrace();
				}
				break;
			}
		}
	}
	
	// register admin
	private void adminRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		
			// flag 
			boolean flag = false;
			// create new object
			Admin newAdmin = new Admin();
			
			String name = request.getParameter("name"); // get name value
			String email = request.getParameter("email"); // get email value
			String password = request.getParameter("password"); // get password value
			String cpassword = request.getParameter("cpassword"); // get confirm password value
			String phone = request.getParameter("phone"); // get phone number 
			String image = "assets/images/troll.jpg"; // default image
			
			
			// Perform server-side validation
	        if (isValid(name, email, password, cpassword)) {
	        	newAdmin.setName(name);
				newAdmin.setEmail(email);
				newAdmin.setPhone(phone);
				newAdmin.setPassword(cpassword);
				newAdmin.setImage(image);
				
				// get return 
				flag = adminDAO.create(newAdmin);
				
				if(flag) {  // if flag true, then go dashboard.
					dispatcher = request.getRequestDispatcher("/views/admin/dashboard.jsp");
				    dispatcher.forward(request, response);
				}
	        } else {
	            // Set error message and forward back to registration form
	            request.setAttribute("error", "Passwords do not match");
	            RequestDispatcher dispatcher = request.getRequestDispatcher("views/admin/form.jsp");
	            dispatcher.forward(request, response);
	        }

		
	}
	
	// register seller
	private void sellerReigster(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		// flag 
				boolean flag = false;
				// create new object
				Seller newSeller = new Seller();
				
				String name = request.getParameter("name"); // get name value
				String email = request.getParameter("email"); // get email value
				String password = request.getParameter("password"); // get password value
				String cpassword = request.getParameter("cpassword"); // get confirm password value
				String phone = request.getParameter("phone"); // get phone number 
				String image = "assets/images/troll.jpg"; // default image
				String company = request.getParameter("company");
				String business = request.getParameter("business");
				String address = request.getParameter("address");
				int business_id = Integer.parseInt(business);
				
				// Perform server-side validation
		        if (isValid(name, email, password, cpassword)) {
		        	newSeller.setName(name);
		        	newSeller.setEmail(email);
		        	newSeller.setPhone(phone);
		        	newSeller.setPassword(cpassword);
		        	newSeller.setImage(image);
		        	newSeller.setCompany(company);
		        	newSeller.setBusiness_id(business_id);
		        	newSeller.setAddress(address);
		        	
					// get return 
					flag = sellerDAO.create(newSeller);
					
					if(flag) {  // if flag true, then go dashboard.
						dispatcher = request.getRequestDispatcher("views/seller/dashboard.jsp");
					    dispatcher.forward(request, response);
					}
		        } else {
		            // Set error message and forward back to registration form
		            request.setAttribute("error", "Passwords do not match");
		            RequestDispatcher dispatcher = request.getRequestDispatcher("views/seller/form.jsp");
		            dispatcher.forward(request, response);
		        }
	}
	
	// register user
	private void userRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
			boolean flag = false;
			
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String cpassword = request.getParameter("cpassword");
			String image = "assets/image/troll.jpg";
			String phone = request.getParameter("phone");
			String address = request.getParameter("address");
			
			if(isValid(name, email, password, cpassword)) {
				Customer newCustomer = new Customer();
				
				newCustomer.setName(name);
				newCustomer.setEmail(email);
				newCustomer.setPassword(cpassword);
				newCustomer.setPhone(phone);
				newCustomer.setAddress(address);
				newCustomer.setImage(image);
				
				flag = customerDAO.create(newCustomer);
				
				if(flag) {
					dispatcher = request.getRequestDispatcher("/views/user/dashboard.jsp");
					dispatcher.forward(request, response);
				}
				
			}else {
				dispatcher = request.getRequestDispatcher("/views/user/form.jsp");
				dispatcher.forward(request, response);
			}
			
		}
		
		// custom validation
	private boolean isValid(String name, String email, String password, String cpassword) {
		return !name.equals("") && !email.equals("")  && password.equals(cpassword);
	}

}
