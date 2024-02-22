package Controllers;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Models.*;
import DAO.*;
import Config.Hash;

public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CustomerDAO customerDAO = null;
	SellerDAO sellerDAO = null;
	AdminDAO adminDAO = null;
	BusinessDAO businessDAO = null;
	RequestDispatcher dispatcher = null;
	

    public RegisterController() throws ClassNotFoundException, SQLException {
        super();
        customerDAO = new CustomerDAO();
        sellerDAO = new SellerDAO();
        adminDAO = new AdminDAO();
        businessDAO = new BusinessDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page = request.getParameter("page");
		String error = request.getParameter("error");
		String success = request.getParameter("success");
		
		if(page != null) {
			if(page.equals("sellerForm")) {
				try {
					List<Business> businessList = businessDAO.get();
					request.setAttribute("businesses", businessList);
					if(error != null) request.setAttribute("error", error);
					if(success != null) request.setAttribute("success", success);
					dispatcher = request.getRequestDispatcher("views/seller/form.jsp");
					dispatcher.forward(request, response);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
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
			HttpSession session = request.getSession();
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
	        	
	        	Admin is_still_exist_email = adminDAO.getAdminByEmail(email);
	        	if(is_still_exist_email == null) {
	        		String hashed_password = Hash.hashPassword(password);
		        	
		        	newAdmin.setName(name);
					newAdmin.setEmail(email);
					newAdmin.setPhone(phone);
					newAdmin.setPassword(hashed_password);
					newAdmin.setImage(image);
					
					// get return 
					flag = adminDAO.create(newAdmin);
					if(flag) {  // if flag true, then go dashboard.
						Admin retrieveAdmin = adminDAO.getAdminByEmail(email);
						session.setAttribute("admin", retrieveAdmin);
						response.sendRedirect(request.getContextPath() + "/AdminController?page=dashboard");
					}
	        	}else {
	        		String success = "Created product successfully!";
					String encoded = URLEncoder.encode(success, "UTF-8");
	        		dispatcher = request.getRequestDispatcher("/views/admin/form.jsp");
	        		dispatcher.forward(request, response);
	        	}
	        } else {
	            // Set error message and forward back to registration form
	            request.setAttribute("error", "Passwords do not match");
	            RequestDispatcher dispatcher = request.getRequestDispatcher("/RegisterController?page=sellerForm");
	            dispatcher.forward(request, response);
	        }

		
	}
	
	// register seller
	private void sellerReigster(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		// flag 
				boolean flag = false;
				HttpSession session = request.getSession();
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
		        if (isValid(name, email, password, cpassword, address, phone, company, business)) {
		        	
		        	Seller is_still_exist_email = sellerDAO.getSellerByEmail(email);
		        	
		        	if(is_still_exist_email == null) {
		        		String hashed_password = Hash.hashPassword(password);
			        	
			        	newSeller.setName(name);
			        	newSeller.setEmail(email);
			        	newSeller.setPhone(phone);
			        	newSeller.setPassword(hashed_password);
			        	newSeller.setImage(image);
			        	newSeller.setCompany(company);
			        	newSeller.setBusiness_id(business_id);
			        	newSeller.setAddress(address);
			        	
						// get return 
						flag = sellerDAO.create(newSeller);
						
						if(flag) {  // if flag true, then go dashboard
							Seller retrieveSeller = sellerDAO.getSellerByEmail(email);
							session.setAttribute("seller", retrieveSeller);
							response.sendRedirect(request.getContextPath() + "/SellerController?page=dashboard");					}
		        	}else {
		        		String error = "Email has already taken!";
						String encoded = URLEncoder.encode(error, "UTF-8");
		        		response.sendRedirect(request.getContextPath() + "/RegisterController?page=sellerForm&error="+encoded);
		        	}
		        	
		        } else {
		            // Set error message and forward back to registration form
		        	String error = "Please fill the fields first!";
					String encoded = URLEncoder.encode(error, "UTF-8");
	        		response.sendRedirect(request.getContextPath() + "/RegisterController?page=sellerForm&error="+encoded);
		        }
	}
	
	// register user
	private void userRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
			boolean flag = false;
			HttpSession session = request.getSession();
			Customer newCustomer = new Customer();
			
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String cpassword = request.getParameter("cpassword");
			String image = "assets/image/troll.jpg";
			String phone = request.getParameter("phone");
			String address = request.getParameter("address");
			
			if(isValid(name, email, password, cpassword, address, phone)) {
				
				Customer is_still_exist_email = customerDAO.getUserByEmail(email);
				
				if(is_still_exist_email == null) {
					String hashed_password = Hash.hashPassword(password);
					
					newCustomer.setName(name);
					newCustomer.setEmail(email);
					newCustomer.setPassword(hashed_password);
					newCustomer.setPhone(phone);
					newCustomer.setAddress(address);
					newCustomer.setImage(image);
					
					flag = customerDAO.create(newCustomer);
					
					if(flag) {
						Customer retrieveCustomer = customerDAO.getUserByEmail(email);
						session.setAttribute("customer", retrieveCustomer);
						response.sendRedirect(request.getContextPath() + "/UserController?page=main");
					}
				}else {
					request.setAttribute("error", "Email has already taken");
	        		dispatcher = request.getRequestDispatcher("/views/user/form.jsp");
	        		dispatcher.forward(request, response);
				}
			}else {
				request.setAttribute("error", "Fill out all the fields");
				dispatcher = request.getRequestDispatcher("/views/user/form.jsp");
				dispatcher.forward(request, response);
			}
			
		}
		
		// custom validation
	private boolean isValid(String name, String email, String password, String cpassword) {
		return !name.equals("") && !email.equals("")  && password.equals(cpassword);
	}
	
	private boolean isValid(String name, String email, String password, String cpassword, String address, String phone) {
		return !name.equals("") && !email.equals("")  && password.equals(cpassword) && !address.equals("") && !phone.equals("");
	}
	
	private boolean isValid(String name, String email, String password, String cpassword, String address, String phone, String company, String business) {
		return !name.equals("") && !email.equals("")  && password.equals(cpassword) && !address.equals("") 
				&& !phone.equals("") && !company.equals("") && !business.equals("");
	}

}
