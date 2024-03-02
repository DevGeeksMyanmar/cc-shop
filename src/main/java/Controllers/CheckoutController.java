package Controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.mail.MessagingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;

import DAO.*;
import Models.*;
import java.util.*;
import java.util.Date;

public class CheckoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	RequestDispatcher dispatcher = null;
	ProductDAO productDAO = null;
	CartDAO cartDAO = null;
	CustomerDAO customerDAO = null;
	CategoryDAO categoryDAO = null;
	AddressDAO addressDAO = null;
	OrderDAO orderDAO = null;
	NoteDAO noteDAO = null;
	SellerDAO sellerDAO = null;

    public CheckoutController() throws ClassNotFoundException, SQLException {
        super();
        productDAO = new ProductDAO();
        cartDAO = new CartDAO();
        customerDAO = new CustomerDAO();
        categoryDAO = new CategoryDAO();
        addressDAO = new AddressDAO();
        orderDAO = new OrderDAO();
        noteDAO = new NoteDAO();
        sellerDAO = new SellerDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page = request.getParameter("page");
		if(page != null) {
			switch(page) {
			case "main":
				checkoutPage(request, response);
				break;
			
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if(action != null) {
			switch(action) {
				
			case "order":
				order(request, response);
				break;
			
			}
		}
	}
	
	// checout page
	private void checkoutPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user_id = request.getParameter("user_id");
		String success = request.getParameter("success");
		String error = request.getParameter("error");
		// get cart items
		List<Cart> items = cartDAO.getProductinCartByUserId(Integer.parseInt(user_id));
		double total_price = 0.0;
		for(Cart item : items) {
			double temp = item.getCount() * item.getPrice();
			total_price += temp;
		}
		 try {
				// get all categories
			List<Category> categoires = categoryDAO.get();
			// get all address by user_id
			List<Address> addresses = addressDAO.getByUserID(Integer.parseInt(user_id));
			// setto setattribute
			request.setAttribute("items", items);
			request.setAttribute("total_price", total_price);
			request.setAttribute("categories", categoires);
			request.setAttribute("addresses", addresses);
			if(success != null) request.setAttribute("success", success);
			if(error != null) request.setAttribute("error", error);
			dispatcher = request.getRequestDispatcher("/views/user/cart/checkout.jsp");
			dispatcher.forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	
	// order action
	private void order(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user_id = request.getParameter("userId");
		String address_id = request.getParameter("addressId");
		String note = request.getParameter("note");
		
		
		List<Cart> items = cartDAO.getProductinCartByUserId(Integer.parseInt(user_id)); /// get the cart item by user id
		String order_code = generateOrderCode(20); // get the order code
		 Date currentDate = new Date();
		 
		 // get the customer email
		 Customer customer = null;
		try {
			customer = customerDAO.getById(Integer.parseInt(user_id));
		} catch (NumberFormatException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 String email = null;
		 ArrayList<String> seller_email = new ArrayList<String>();
		 for(Cart item : items) {
			 email = sellerDAO.getEmailByProductID(item.getProduct_id());
			 if(seller_email.size() == 0) {
				 seller_email.add(email);
			 }else {
				 if(!seller_email.contains(email)) {
					 seller_email.add(email);
				 }else {
					 System.out.println("email already exist");
				 }
			 }
		 }
		 for(String mail : seller_email) {
			 String htmlContext = "<h3>A new order received</h3><h5>Date: "+ currentDate.toString() +"</h5>"
			 		+ "<p>Customer : <span style='font-weight: bold;'>"+ customer.getName() + "</span> ordered a bunch of package from your shop. Please check the order and let me know there "
			 				+ "something wrong</p>";
			 try {
				Config.mail.sendEmail(mail, "You have recevied a new order", htmlContext);
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
		 
		 // get the total number
		long total = 0;
		for(Cart item : items) {
			long temp = item.getPrice();
			total += temp;
		}
		// get the address detali
		Address addresss = addressDAO.getById(Integer.parseInt(address_id));
		String address = "<p>Shipping address: "+ addresss.getStreet_address()+", "+ 
							addresss.getCity() + ", " + addresss.getState() + ", " + addresss.getCountry() + "</p><br>";
		// table body
		String table_body = null;
		for(Cart item : items) {
			table_body += "<tr>"
					+ "<td style='padding: .5rem; '>" + item.getProduct_name() + "</td>"
					+ "<td style='padding: .5rem; '> x" + item.getCount() + "</td>"
					+ "<td style='padding: .5rem; '> " + item.getPrice() + "MMKs</td>"
					+ "</tr>";
		}
		String table_total =  "<tr>"
				+ "<td colspans=2 style='padding: .5rem; font-weight: bold;'> Total </td>"
				+ "<td style='padding: .5rem; font-weight: bold;'> " + total + "MMKs</td>"
				+ "</tr>";
		String table_footer = "</tbody>" 
				+ "</thead>"
				+ "</table>";
		// table wrapper
		String html_context = "<h5>Order code : "+ order_code +"</h5>"
				+ "<p style='display: block;'> Date: "+ currentDate.toString() +" </p>"
				+ address
				+ "<table>"
				+ "<thead>"
				+ "<tr>"
				+ "<td style='padding: .5rem; font-weight: bold;''>Name</td>"
				+ "<td style='padding: .5rem; font-weight: bold;''>Count</td>"
				+ "<td style='padding: .5rem; font-weight: bold;''>Price</td>"
				+ "</tr>"
				+ "<tbody>"
				+ table_body
				+ table_total
				+ table_footer;

		// send mail to user
		try {
			Config.mail.sendEmail(customerDAO.getEamilByID(Integer.parseInt(user_id)), "Your order receipt", html_context);
		} catch (NumberFormatException | MessagingException e) {
			e.printStackTrace();
		}
		
	
		int status = 0;
		
		for(Cart item : items) {
			Orders orders = new Orders();
			orders.setCount(item.getCount());
			orders.setCustomer_id(Integer.parseInt(user_id));
			orders.setOrder_code(order_code);
			orders.setPrice(item.getPrice());
			orders.setProduct_id(item.getProduct_id());
			orders.setShipping_id(Integer.parseInt(address_id));
			orders.setStatus(status);
			
			// inserted into order table one by one
			boolean inserted = orderDAO.create(orders);
			if(inserted) System.out.println("saved");
			
			// then update the product count in product table
			boolean update_count = productDAO.updateProductCount(item.getProduct_id(), item.getCount());
			if(update_count) System.out.println("count updated");
		}
		// after all inserton are done then delete the cart
		boolean delete_cart = cartDAO.deleteByUser(Integer.parseInt(user_id));
		if(delete_cart) System.out.println("deleted from cart");

		if(note != null) {
			// save the noted
			Note note_object = new Note();
			note_object.setCustomer_id(Integer.parseInt(user_id));
			note_object.setOrder_code(order_code);
			note_object.setText(note);
//			note_object.
			boolean noted = noteDAO.create(note_object);
			if(noted) System.out.println("added note");
		}else {
			System.out.println("not noted");
		}
		
		
		// Prepare JSON response
	    JsonObject jsonResponse = new JsonObject();
	    jsonResponse.addProperty("status", "true");

	    // Set content type and write JSON response
	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    response.getWriter().write(jsonResponse.toString());
	}
	
	// to generate random number
	 public static String generateOrderCode(int length) {
	        // Define the characters allowed in the order code
	        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	        // Create a StringBuilder to build the order code
	        StringBuilder orderCodeBuilder = new StringBuilder();
	        // Create an instance of Random class
	        Random random = new Random();
	        // Build the order code with random characters
	        for (int i = 0; i < length; i++) {
	            // Get a random index from the characters string
	            int randomIndex = random.nextInt(characters.length());
	            // Append the character at the random index to the order code
	            orderCodeBuilder.append(characters.charAt(randomIndex));
	        }
	        // Convert StringBuilder to String and return the order code
	        return orderCodeBuilder.toString();
	    }
}
