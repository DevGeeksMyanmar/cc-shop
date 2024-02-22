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

import Models.Category;
import DAO.CategoryDAO;

public class CategoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	CategoryDAO categoryDAO = null;
	RequestDispatcher dispatcher = null;

    public CategoryController() throws ClassNotFoundException, SQLException {
        super();
        categoryDAO = new CategoryDAO();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		// add category
		if(action != null) {
			switch(action) {
			case "addCategory":
				try {
					saveCategory(request, response);  // save new category
				
				} catch (ServletException | IOException | SQLException e) {
					e.printStackTrace();
				}
				break;
				
			case "updateCategory":
				try {
					updateCategory(request, response);
				
				} catch (ServletException | IOException | SQLException e) {
					e.printStackTrace();
				}
				break;
			}
		}
	}
	
	// create new category
	private void saveCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		boolean flag = false;
		String name = request.getParameter("name");
		
		Category is_duplicated = categoryDAO.getByName(name);
		
		if(is_duplicated == null) {
			Category newCategory = new Category();
			newCategory.setName(name);
			
			if(categoryDAO.create(newCategory)) {
				String success = "Added Category Successfully";
				String encoded = URLEncoder.encode(success, "UTF-8");
				response.sendRedirect(request.getContextPath() + "/AdminController?page=category&success="+encoded);
			}
		}else {
			System.out.println("duplicated");
			String error = "Name has already taken";
			String encoded = URLEncoder.encode(error, "UTF-8");
			response.sendRedirect(request.getContextPath() + "/AdminController?page=category&error="+encoded);
			return;
		}
	}
	
	// update category
	private void updateCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		String name = request.getParameter("name");
		String category_id = request.getParameter("category_id");
		
		Category is_duplicated = categoryDAO.getByName(name);
		
		if(is_duplicated != null && is_duplicated.getId() != Integer.parseInt(category_id)) {
			String error = "Name has already taken";
			String encoded = URLEncoder.encode(error, "UTF-8");
			response.sendRedirect(request.getContextPath() + "/AdminController?page=category&error="+encoded);
			return;
		}
		
		Category updatedCategory = new Category();
		updatedCategory.setName(name);
		if(categoryDAO.update(updatedCategory, Integer.parseInt(category_id))) {
			String success = "Updated Category Successfully";
			String encoded = URLEncoder.encode(success, "UTF-8");
			response.sendRedirect(request.getContextPath() + "/AdminController?page=category&success="+encoded);
		}
	}

}
