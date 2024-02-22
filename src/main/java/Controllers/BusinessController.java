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

import Models.Business;
import Models.Category;
import DAO.BusinessDAO;

public class BusinessController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	BusinessDAO businessDAO = null;
	RequestDispatcher dispatcher = null;

    public BusinessController() throws ClassNotFoundException, SQLException {
        super();
        businessDAO = new BusinessDAO();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if(action != null) {
			switch(action) {
			// add business
			case "addBusiness":
				try {
					saveBusiness(request, response);  // save new category
				} catch (ServletException | IOException | SQLException e) {
					e.printStackTrace();
				}
				break;
				
			// update business
			case "updateBusiness":
				try {
					updateBusiness(request, response);  // save new category
				} catch (ServletException | IOException | SQLException e) {
					e.printStackTrace();
				}
				break;
			}
		}
	}
	
	// create new category
	private void saveBusiness(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		boolean flag = false;
		String name = request.getParameter("name");
		
		Business is_duplicated = businessDAO.getByName(name);
		
		if(is_duplicated != null) {
			String error = "Name has already taken";
			String encoded = URLEncoder.encode(error, "UTF-8");
			response.sendRedirect(request.getContextPath() + "/AdminController?page=business&error="+encoded);
			return;
		}
		
		Business newBusiness = new Business();
		newBusiness.setName(name);
		
		if(businessDAO.create(newBusiness)) {
			String success = "Added Business Successfully";
			String encoded = URLEncoder.encode(success, "UTF-8");
			response.sendRedirect(request.getContextPath() + "/AdminController?page=business&success="+encoded);
		}
	}
	
	// update category
		private void updateBusiness(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
			String name = request.getParameter("name");
			String business_id = request.getParameter("business_id");
			
			Business is_duplicated = businessDAO.getByName(name);
			
			if(is_duplicated != null && is_duplicated.getId() != Integer.parseInt(business_id)) {
				String error = "Name has already taken";
				String encoded = URLEncoder.encode(error, "UTF-8");
				response.sendRedirect(request.getContextPath() + "/AdminController?page=business&error="+encoded);
				return;
			}
			
			Business updatedBusiness = new Business();
			updatedBusiness.setName(name);
			if(businessDAO.update(updatedBusiness, Integer.parseInt(business_id))) {
				String success = "Updated Business Successfully";
				String encoded = URLEncoder.encode(success, "UTF-8");
				response.sendRedirect(request.getContextPath() + "/AdminController?page=business&success="+encoded);
			}
		}

}
