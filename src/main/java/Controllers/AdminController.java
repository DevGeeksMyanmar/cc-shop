package Controllers;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import Models.Admin;
import DAO.AdminDAO;


public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    AdminDAO adminDAO = null;
    RequestDispatcher dispatcher = null;
	
    public AdminController() throws ClassNotFoundException, SQLException {
        super();
        adminDAO = new AdminDAO();
    }

    // post method from lucas branch
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// get the action type
		String action = request.getParameter("action");
		
		// doPost handle many action like register and login and so on.
		if(action != null) {
			switch(action) {
			case "login": // do the login process
				login(request, response); // do the login process
				break;
			case "register": // do the register process
				try { // check unexpected error with try and catch block
					register(request, response);
				} catch (ServletException | IOException | SQLException e) {
					e.printStackTrace();
				}
				break;
			default:
				break;
			}
		}
		
	}
	
	
	// register servlet
	private void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
		// flag 
		boolean flag = false;
		// create new object
		Admin newAdmin = new Admin();
		// Basic validation (customize as needed)
	    Map<String, String> errors = new HashMap<>();
		
		String name = request.getParameter("name"); // get name value
		String email = request.getParameter("email"); // get email value
		String password = request.getParameter("password"); // get password value
		String cpassword = request.getParameter("cpassword"); // get confirm password value
		String phone = request.getParameter("phone"); // get phone number 
		String image = "assets/images/troll.jpg"; // default image
		
		// set values by object ---> to database
		if(password.equals(cpassword)) {
			
		}else {
			dispatcher = request.getRequestDispatcher("/views/admin/register.jsp");
			errors.put("not_match", "Password Doesn't match");
		}
		
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
            RequestDispatcher dispatcher = request.getRequestDispatcher("views/admin/register.jsp");
            dispatcher.forward(request, response);
        }
		
		// Retrieve image using getPart
//	    Part imagePart = request.getPart("image");
//
//	    // Process image if present
//	    if (imagePart != null && imagePart.getSize() > 0) {
//	        String fileName = getFileName(imagePart);
//	        String filePath = "images/" + fileName;
//	        String fileType = imagePart.getContentType();
//	        long fileSize = imagePart.getSize();
//
//	        // Validate image size
//	        if (fileSize > 5 * 1024 * 1024) { // 5 MB limit
//	            errors.put("image", "Image size exceeds 5 MB");
//	        } else {
//	            // Save image to disk
//	            File file = new File(getServletContext().getRealPath("/assets/image/") + filePath);
//	            imagePart.write(file.getAbsolutePath());
//
//	            // Set image path in Admin object
//	            newAdmin.setImage(filePath);
//	        }
//	    }
	}
	
	
	// login servlet
	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
	}
	
	
	
	// get file name of image
//	private String getFileName(Part part) {
//	    String contentDisposition = part.getHeader("content-disposition");
//	    String[] tokens = contentDisposition.split(";");
//	    for (String token : tokens) {
//	        if (token.trim().startsWith("filename")) {
//	            return token.substring(token.indexOf("=") + 2, token.length() - 1);
//	        }
//	    }
//	    return "";
//	}
	
	// register validation
	private boolean isValid(String username, String email, String password, String confirmPassword) {
        // Perform validation logic here
        return !username.equals("") && !email.equals("") && !password.equals("") && password.equals(confirmPassword);
    }

}
