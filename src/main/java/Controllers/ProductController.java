package Controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import java.util.*;

import Models.Category;
import Models.Image;
import Models.Product;
import DAO.ProductDAO;
import DAO.CategoryDAO;

@MultipartConfig

public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Connection con=null;
	ProductDAO productDAO = null;
	CategoryDAO categoryDAO = null;
	RequestDispatcher dispatcher = null;
       
    public ProductController() throws ClassNotFoundException, SQLException {
        super();
        con = Config.config.getConnections();
        productDAO = new ProductDAO();
        categoryDAO = new CategoryDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page = request.getParameter("page");
		String error = request.getParameter("error");
		String success = request.getParameter("success");
		
		
		if(page != null) {
			switch(page) {
			case "createProductPage":
				try {
					List<Category> categories = categoryDAO.get();
					String seller_id = request.getParameter("seller_id");
					System.out.println(seller_id);
					request.setAttribute("categories", categories);
					request.setAttribute("seller_id", seller_id);
					if(error != null) request.setAttribute("error", error);
					dispatcher = request.getRequestDispatcher("/views/seller/product/create.jsp");
    				dispatcher.forward(request, response);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				break;
				
			// edit page for product
			case "detail":
				try {
					detail(request, response);
				} catch (ServletException | IOException | SQLException e) {
					e.printStackTrace();
				}
				break;
				
			// destory the product
			case "destory":
				try {
					destory(request, response);
				} catch (NumberFormatException | ServletException | IOException | SQLException e) {
					e.printStackTrace();
				}
				break;
			
			// edit the product
			case "edit":
				try {
					edit(request, response);
				} catch (ServletException | IOException | SQLException e) {
					e.printStackTrace();
				}
				break;
				
			// delet image
			case "deleteImage":
				try {
					deleteImage(request, response);
				} catch (ServletException | IOException | SQLException e) {
					e.printStackTrace();
				}
				break;
				
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		if(action != null) {
			switch(action) {
			//save product
			case "store":
				try {
					store(request, response);
				} catch (ServletException | IOException | SQLException e) {
					e.printStackTrace();
				}
				break;
				
			//update product content
			case "update":
				try {
					update(request, response);
				} catch (ServletException | IOException | SQLException e) {
					e.printStackTrace();
				}
				break;
			}
		}
	}
	
	// store the product
		private void store(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
			
			String name = request.getParameter("name"); // get product name
			String description = request.getParameter("description"); // get product description
			String count = request.getParameter("count"); // get product count
			String price = request.getParameter("price"); // get product price
			String category_id = request.getParameter("category"); // get product category
			String seller_id = request.getParameter("seller_id"); // get seller id
			Collection<Part> parts = request.getParts(); // get images parts
			boolean hasFileUpload = false;
			
			System.out.println(seller_id);
			
			// Iterate through the parts
			for (Part part : request.getParts()) {
			    // Check if the part is a file upload
			    if (part.getSubmittedFileName() != null && !part.getSubmittedFileName().isEmpty()) {
			        hasFileUpload = true;
			        break; // No need to check further, we found at least one file upload
			    }
			}
			
			if(name == null || name.equals("")) {  // check name is empty or not
				String error = "Name can't be blank!";
				String encodedError = URLEncoder.encode(error, "UTF-8");
				response.sendRedirect(request.getContextPath() +"/SellerController?page=createProductPage&seller_id="+seller_id+"&error="+encodedError);
			}
			else if(description == null || description.equals("")) { // check description is empty or not
				String error = "Description can't be blank!";
				String encodedError = URLEncoder.encode(error, "UTF-8");
				response.sendRedirect(request.getContextPath() +"/SellerController?page=createProductPage&seller_id="+seller_id+"&error="+encodedError);
			}
			else if(count == null || count.equals("")) { // check count is empty or not
				String error = "Please fill the count";
				String encodedError = URLEncoder.encode(error, "UTF-8");
				response.sendRedirect(request.getContextPath() +"/SellerController?page=createProductPage&seller_id="+seller_id+"&error="+encodedError);
			}
			else if(price == null || price.equals("")) { // check price is empty or not
				String error = "Please insert the price!";
				String encodedError = URLEncoder.encode(error, "UTF-8");
				response.sendRedirect(request.getContextPath() +"/SellerController?page=createProductPage&seller_id="+seller_id+"&error="+encodedError);
			}
			else if(category_id == null || category_id.equals("")) { // check category is empty or not
				String error = "Please choose category!";
				String encodedError = URLEncoder.encode(error, "UTF-8");
				response.sendRedirect(request.getContextPath() +"/SellerController?page=createProductPage&seller_id="+seller_id+"&error="+encodedError);
			}
			else if(!hasFileUpload) { // check file is empty or not
				String error = "Please choose at least one image!";
				String encodedError = URLEncoder.encode(error, "UTF-8");
				response.sendRedirect(request.getContextPath() +"/SellerController?page=createProductPage&seller_id="+seller_id+"&error="+encodedError);
			}
			else { // if all exists;
				boolean is_image_inserted = false; // to check image is inserted or not
				
				Product product = new Product(); // create prodcut object
				product.setName(name);
				product.setPrice(Integer.parseInt(price));
				product.setCount(Integer.parseInt(count));
				product.setDescription(description);
				product.setSeller_id(Integer.parseInt(seller_id));
				product.setCategory_id(Integer.parseInt(category_id));
				
				boolean is_product_inserted = productDAO.createProductContent(product); // then insert into product table
				
				for(Part part : request.getParts()) { // then loop the images from form one by one
					
					List<String> fileNames = new ArrayList<String>(); // to check images is empty or not
					// Skip parts that are not file uploads
				    if (part.getSubmittedFileName() == null) { // if not file, then skip this
				        continue;
				    }else { // else add to filenames array list
				    	fileNames.add(part.getSubmittedFileName());
				    } 
				    
				    
				    	int min = 1000;
					    int max = 10000;
					    int random_number = (int) (Math.random()*(max-min+1)+min);  
					    // Process the file upload
					    String fileName = extractFileName(part);
					    String updated_filename = random_number + "_" + fileName;
					    System.out.println("File Name: " + fileName);

					    //Define destination directory
				        String uploadDir = "C:\\Users\\acer\\Desktop\\cc-shop\\src\\main\\webapp\\assets\\images\\products"; // Example: "C:/eclipse_workspace/upload"
				        
				        // Write file to the destination directory
				        OutputStream out = null;
				        InputStream fileContent = null;
				        try {
				            out = new FileOutputStream(new File(uploadDir + File.separator + updated_filename));
				            fileContent = part.getInputStream();

				            int read;
				            final byte[] bytes = new byte[1024];
				            while ((read = fileContent.read(bytes)) != -1) {
				                out.write(bytes, 0, read);
				            }
				        } catch (FileNotFoundException fne) {
				            // Handle file not found exception
				            fne.printStackTrace();
				        } finally {
				            if (out != null) {
				                out.close();
				            }
				            if (fileContent != null) {
				                fileContent.close();
				            }
				        }
				        
				        // get product id
				        int product_id = 0 ;
				        String query = "SELECT products.id as product_id from products WHERE name=?";
				        PreparedStatement pst = con.prepareStatement(query);
				        pst.setString(1, name);
				        ResultSet rs = pst.executeQuery();
				        while(rs.next()) {
				        	product_id = rs.getInt("product_id");
				        }
				        System.out.println("product id" + product_id);
				        
				        // create image obj
						Image image = new Image();
						image.setName(updated_filename);
						image.setProduct_id(product_id);
				        
				        // insert into image
				        is_image_inserted = productDAO.createProductImage(image);  
				    

		        }
				if(is_image_inserted && is_product_inserted) {
					String success = "Created product successfully!";
					String encoded = URLEncoder.encode(success, "UTF-8");
					response.sendRedirect(request.getContextPath() +"/SellerController?page=product&success="+encoded+"&seller_id="+seller_id);
				}
			}
		}
		
		private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
			String product_id = request.getParameter("product_id");
			String name = request.getParameter("name"); // get product name
			String description = request.getParameter("description"); // get product description
			String count = request.getParameter("count"); // get product count
			String price = request.getParameter("price"); // get product price
			String category_id = request.getParameter("category"); // get product category
			String seller_id = request.getParameter("seller_id");
			Collection<Part> parts = request.getParts(); // get images parts
			boolean hasFileUpload = false;
			
			// Iterate through the parts
			for (Part part : request.getParts()) {
			  // Check if the part is a file upload
			   if (part.getSubmittedFileName() != null && !part.getSubmittedFileName().isEmpty()) {
			        hasFileUpload = true;
			        break; // No need to check further, we found at least one file upload
			   }
			}
			
			if(name == null || name.equals("")) {  // check name is empty or not
				String error = "Name can't be blank!";
				String encodedError = URLEncoder.encode(error, "UTF-8");
				response.sendRedirect(request.getContextPath() +"/ProductController?page=edit&error="+encodedError+"&product_id="+product_id);
			}
			else if(description == null || description.equals("")) { // check description is empty or not
				String error = "Description can't be blank!";
				String encodedError = URLEncoder.encode(error, "UTF-8");
				response.sendRedirect(request.getContextPath() +"/ProductController?page=edit&error="+encodedError+"&product_id="+product_id);
			}
			else if(count == null || count.equals("")) { // check count is empty or not
				String error = "Please fill the count";
				String encodedError = URLEncoder.encode(error, "UTF-8");
				response.sendRedirect(request.getContextPath() +"/ProductController?page=edit&error="+encodedError+"&product_id="+product_id);
			}
			else if(price == null || price.equals("")) { // check price is empty or not
				String error = "Please insert the price!";
				String encodedError = URLEncoder.encode(error, "UTF-8");
				response.sendRedirect(request.getContextPath() +"/ProductController?page=edit&error="+encodedError+"&product_id="+product_id);
			}
			else if(category_id == null || category_id.equals("")) { // check category is empty or not
				String error = "Please choose category!";
				String encodedError = URLEncoder.encode(error, "UTF-8");
				response.sendRedirect(request.getContextPath() +"/ProductController?page=edit&error="+encodedError+"&product_id="+product_id);
			}
			else { // if all exists;
				boolean is_image_inserted = false; // to check image is inserted or not
				
				Product product = new Product(); // create prodcut object
				product.setName(name);
				product.setPrice(Integer.parseInt(price));
				product.setCount(Integer.parseInt(count));
				product.setDescription(description);
				product.setId(Integer.parseInt(product_id));
				product.setCategory_id(Integer.parseInt(category_id));
				
				boolean is_product_updated = productDAO.updateProductContent(product); // then insert into product table
				
				// if has file
				if(hasFileUpload) {
					for(Part part : request.getParts()) { // then loop the images from form one by one
						
						List<String> fileNames = new ArrayList<String>(); // to check images is empty or not
						// Skip parts that are not file uploads
					    if (part.getSubmittedFileName() == null) { // if not file, then skip this
					        continue;
					    }else { // else add to filenames array list
					    	fileNames.add(part.getSubmittedFileName());
					    } 
					    
					    
					    	int min = 1000;
						    int max = 10000;
						    int random_number = (int) (Math.random()*(max-min+1)+min);  
						    // Process the file upload
						    String fileName = extractFileName(part);
						    String updated_filename = random_number + "_" + fileName;
						    System.out.println("File Name: " + fileName);

						    //Define destination directory
					        String uploadDir = "C:\\Users\\acer\\Desktop\\cc-shop\\src\\main\\webapp\\assets\\images\\products"; // Example: "C:/eclipse_workspace/upload"
					        
					        // Write file to the destination directory
					        OutputStream out = null;
					        InputStream fileContent = null;
					        try {
					            out = new FileOutputStream(new File(uploadDir + File.separator + updated_filename));
					            fileContent = part.getInputStream();

					            int read;
					            final byte[] bytes = new byte[1024];
					            while ((read = fileContent.read(bytes)) != -1) {
					                out.write(bytes, 0, read);
					            }
					        } catch (FileNotFoundException fne) {
					            // Handle file not found exception
					            fne.printStackTrace();
					        } finally {
					            if (out != null) {
					                out.close();
					            }
					            if (fileContent != null) {
					                fileContent.close();
					            }
					        }
					        
					        // create image obj
							Image image = new Image();
							image.setName(updated_filename);
							image.setProduct_id(Integer.parseInt(product_id));
					        
					        // insert into image
					        is_image_inserted = productDAO.createProductImage(image);  
			        }
				}
				
				if(is_product_updated) {
					String success = "Updated product successfully!";
					String encoded = URLEncoder.encode(success, "UTF-8");
					response.sendRedirect(request.getContextPath() +"/SellerController?page=product&success="+encoded+"&seller_id="+seller_id);
				}
			}
		}
		
		private String extractFileName(Part part) {
	        String contentDisp = part.getHeader("content-disposition");
	        String[] tokens = contentDisp.split(";");
	        for (String token : tokens) {
	            if (token.trim().startsWith("filename")) {
	                return token.substring(token.indexOf('=') + 1).trim().replace("\"", "");
	            }
	        }
	        return "";
	    }
		
	// destory the product
		private void destory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NumberFormatException, SQLException {
			String product_id = request.getParameter("product_id");
			String seller_id = request.getParameter("seller_id");
			boolean flag = productDAO.delete(Integer.parseInt(product_id));
			if(flag) {
				String success = "Deleted product successfully!";
				String encoded = URLEncoder.encode(success, "UTF-8");
				response.sendRedirect(request.getContextPath() +"/SellerController?page=product&success="+encoded+"&seller_id="+seller_id);
			}else {
				String error = "Can't delete product!";
				String encodedError = URLEncoder.encode(error, "UTF-8");
				response.sendRedirect(request.getContextPath() +"/SellerController?page=product&error="+encodedError+"&seller_id="+seller_id);
			}
		}
		
	// get the product detail
		private void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
			String product_id = request.getParameter("product_id");
			String seller_id = request.getParameter("seller_id");
			// get product detail by seller id
			Product product = (Product) productDAO.getFullDataBySellerId(Integer.parseInt(product_id));
			// get product images by seller id
			List<Image> images = productDAO.getFullImagesBySellerId(Integer.parseInt(product_id));
			
			request.setAttribute("images", images);
			request.setAttribute("product", product);
			request.setAttribute("seller_id", seller_id);
			dispatcher = request.getRequestDispatcher("/views/seller/product/detail.jsp");
			dispatcher.forward(request, response);
		}
		
	// edit page for product
		private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
			String error = request.getParameter("error");
			String success = request.getParameter("success");
			
			List<Category> categories = categoryDAO.get();
			request.setAttribute("categories", categories);
			String product_id = request.getParameter("product_id");
			// get product detail by seller id
			Product product = (Product) productDAO.getFullDataBySellerId(Integer.parseInt(product_id));
			// get product images by seller id
			List<Image> images = productDAO.getFullImagesBySellerId(Integer.parseInt(product_id));
						
			if(error != null) request.setAttribute("error", error);
			if(success != null) request.setAttribute("success", success);
			
			request.setAttribute("images", images);
			request.setAttribute("product", product);
			dispatcher = request.getRequestDispatcher("/views/seller/product/edit.jsp");
			dispatcher.forward(request, response);
		}
	
		// delete images
		private void deleteImage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
			String image_id = request.getParameter("image_id");
			String product_id = request.getParameter("product_id");
			
			System.out.println( "image id " + image_id);
			System.out.println( "product_id " + product_id);
			
			boolean is_deleted = productDAO.deleteImageById(Integer.parseInt(image_id));
			
			if(is_deleted) {
				String success = "Deleted image successfully!";
				String encoded = URLEncoder.encode(success, "UTF-8");
				response.sendRedirect(request.getContextPath() + "/ProductController?page=edit&success="+encoded+"&product_id="+product_id);
			}else {
				String error = "Can't delete image!";
				String encoded = URLEncoder.encode(error, "UTF-8");
				response.sendRedirect(request.getContextPath() + "/ProductController?page=edit&error="+encoded+"&product_id="+product_id);
			}
		}

}
