package DAO;

import java.io.File;
import java.sql.*;
import java.util.*;

import Models.Image;
import Models.Product;
import Models.Seller;

public class ProductDAO {
	
	Connection con = null;
	Statement statement = null;
	PreparedStatement stmt = null;
	ResultSet resultSet = null;
	private int noOfRecords;
	
	// constructor
	public ProductDAO() throws ClassNotFoundException, SQLException{
		con = Config.config.getConnections();
	}
	
	// get all products
	public List<Product> get(){
	    List<Product> products = new ArrayList<Product>();
	    String query = "SELECT products.*, categories.name as category_name, sellers.name as seller_name FROM products LEFT JOIN categories ON products.category_id = categories.id LEFT JOIN sellers ON products.seller_id = sellers.id";
	    try {
	        statement = con.createStatement();
	        resultSet = statement.executeQuery(query);
	        while(resultSet.next()) {
	            Product product = new Product();
	            product.setId(resultSet.getInt("id"));
	            product.setName(resultSet.getString("name"));
	            product.setPrice(resultSet.getInt("price"));
	            product.setDescription(resultSet.getString("description"));
	            product.setCount(resultSet.getInt("count"));
	            product.setRating(resultSet.getInt("rating"));
	            product.setCategory_id(resultSet.getInt("category_id"));
	            product.setSeller_id(resultSet.getInt("seller_id"));
	            product.setSeller_name(resultSet.getString("seller_name"));
	            product.setCategory_name(resultSet.getString("category_name"));
	            // Add the product to the list
	            products.add(product);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        // Close resources in the finally block to ensure they are always closed
	        try {
	            if(resultSet != null) resultSet.close();
	            if(statement != null) statement.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return products;
	}
	
	// get product count by seller id
	public List<Product> getProductBySellerId(int id) throws SQLException {
		List<Product> products = new ArrayList<Product>();
		String query = "SELECT products.name, MAX(products.rating) as rating FROM products LEFT JOIN sellers ON sellers.id = products.seller_id WHERE sellers.id=" + id + " GROUP BY products.name";
		statement = con.createStatement();
		resultSet = statement.executeQuery(query);
		Product product = null;
		while(resultSet.next()) {
			product = new Product();
			product.setName(resultSet.getString("name"));
			product.setRating(resultSet.getInt("rating"));
			products.add(product);
		}
		return products;
	}
	
	// get product by product Id
	public Product getProductById(int id) throws SQLException{
		Product product = null;
		String query = "select products.*, categories.name as category_name, sellers.name as seller_name FROM products LEFT JOIN categories ON products.category_id = categories.id LEFT JOIN sellers ON products.seller_id = sellers.id WHERE products.id="+id;
		statement = con.createStatement();
		resultSet = statement.executeQuery(query);
        while(resultSet.next()) {
            product = new Product();
           product.setId(resultSet.getInt("id"));
           product.setName(resultSet.getString("name"));
           product.setPrice(resultSet.getInt("price"));
           product.setDescription(resultSet.getString("description"));
           product.setCount(resultSet.getInt("count"));
           product.setRating(resultSet.getInt("rating"));
           product.setCategory_id(resultSet.getInt("category_id"));
           product.setSeller_id(resultSet.getInt("seller_id"));
           product.setSeller_name(resultSet.getString("seller_name"));
           product.setCategory_name(resultSet.getString("category_name"));
        }
        return product;
	}
	
	// create product content
	public boolean createProductContent(Product product) throws SQLException {
		boolean flag = false;
		String query = "INSERT INTO products (name, price, description, count, category_id, seller_id) VALUES (?,?,?,?,?,?)";
		stmt = con.prepareStatement(query);
		stmt.setString(1, product.getName());
		stmt.setInt(2, product.getPrice());
		stmt.setString(3, product.getDescription());
		stmt.setInt(4, product.getCount());
		stmt.setInt(5, product.getCategory_id());
		stmt.setInt(6, product.getSeller_id());
		int insertedRow = stmt.executeUpdate();
		if(insertedRow > 0) flag = true;
		return flag;
	}
	
	// update product
	public boolean updateProductContent(Product product) throws SQLException {
		boolean flag = false;
		String query = "UPDATE products SET name=?, price=?, description=?, count=?, category_id=? WHERE id=?";
		stmt = con.prepareStatement(query);
		stmt.setString(1, product.getName());
		stmt.setInt(2, product.getPrice());
		stmt.setString(3, product.getDescription());
		stmt.setInt(4, product.getCount());
		stmt.setInt(5, product.getCategory_id());
		stmt.setInt(6, product.getId());
		int updatedRow = stmt.executeUpdate();
		if(updatedRow > 0) flag = true;
		return flag;
	}
	
	// create product image
	public boolean createProductImage(Image image) throws SQLException {
		boolean flag= false;
		// query for inserting image
		String query = "INSERT INTO images (name, product_id) VALUES (?,?)";
		stmt = con.prepareStatement(query);
		stmt.setString(1, image.getName());
		stmt.setInt(2, image.getProduct_id());
		int insertedImage = stmt.executeUpdate(); // insert into image table
		stmt.close();
		if(insertedImage > 0) {
			flag = true;
		}
		return flag;
	}
	
	// update product image
//	public boolean updateProductImage(Image image, int seller_id) throws SQLException {
//		boolean flag = false;
//	}
	
	// get all product
			public List<Product> getAll(int offset, int noOfRecords) throws SQLException{
				List<Product> products = new ArrayList<Product>();  // create empty admin list to store admins
				Product product = null; // create admin object which is from model
				String query = "select SQL_CALC_FOUND_ROWS products.*, categories.name as category_name, MIN(images.name) AS image_name, sellers.name as seller_name FROM products "
						+ "LEFT JOIN categories ON products.category_id = categories.id "
						+ "LEFT JOIN sellers ON products.seller_id = sellers.id "
						+ "LEFT JOIN images ON images.product_id = products.id GROUP BY products.id "
						+ "ORDER BY updated_at DESC limit " + offset + ", " + noOfRecords;
				statement = con.createStatement();
		        resultSet = statement.executeQuery(query);
		        while(resultSet.next()) {
		             product = new Product();
		            product.setId(resultSet.getInt("id"));
		            product.setName(resultSet.getString("name"));
		            product.setPrice(resultSet.getInt("price"));
		            product.setDescription(resultSet.getString("description"));
		            product.setCount(resultSet.getInt("count"));
		            product.setRating(resultSet.getInt("rating"));
		            product.setCategory_id(resultSet.getInt("category_id"));
		            product.setSeller_id(resultSet.getInt("seller_id"));
		            product.setSeller_name(resultSet.getString("seller_name"));
		            product.setCategory_name(resultSet.getString("category_name"));
		            product.setImage(resultSet.getString("image_name"));
		            // Add the product to the list
		            products.add(product);
		        }
				resultSet.close();
				resultSet = statement.executeQuery("SELECT FOUND_ROWS()");
		        if(resultSet.next()) {
		        	 this.noOfRecords = resultSet.getInt(1);
		        }
				return products; // return that list
			}
			
			
			// get product by category id
			public List<Product> getByCategoryID(int offset, int noOfRecords, int category_id) throws SQLException{
				List<Product> products = new ArrayList<Product>();  // create empty admin list to store admins
				Product product = null; // create admin object which is from model
				String query = "select SQL_CALC_FOUND_ROWS products.*, categories.name as category_name, MIN(images.name) AS image_name, sellers.name as seller_name FROM products "
						+ "LEFT JOIN categories ON products.category_id = categories.id "
						+ "LEFT JOIN sellers ON products.seller_id = sellers.id "
						+ "LEFT JOIN images ON images.product_id = products.id WHERE products.category_id="+category_id+" GROUP BY products.id "
						+ "ORDER BY updated_at DESC limit " + offset + ", " + noOfRecords;
				statement = con.createStatement();
		        resultSet = statement.executeQuery(query);
		        while(resultSet.next()) {
		             product = new Product();
		            product.setId(resultSet.getInt("id"));
		            product.setName(resultSet.getString("name"));
		            product.setPrice(resultSet.getInt("price"));
		            product.setDescription(resultSet.getString("description"));
		            product.setCount(resultSet.getInt("count"));
		            product.setRating(resultSet.getInt("rating"));
		            product.setCategory_id(resultSet.getInt("category_id"));
		            product.setSeller_id(resultSet.getInt("seller_id"));
		            product.setSeller_name(resultSet.getString("seller_name"));
		            product.setCategory_name(resultSet.getString("category_name"));
		            product.setImage(resultSet.getString("image_name"));
		            // Add the product to the list
		            products.add(product);
		        }
				resultSet.close();
				resultSet = statement.executeQuery("SELECT FOUND_ROWS()");
		        if(resultSet.next()) {
		        	 this.noOfRecords = resultSet.getInt(1);
		        }
				return products; // return that list
			}
			
			// get by search term
			public List<Product> getBySearching(String searchTerm) throws SQLException{
				List<Product> products = new ArrayList<Product>();  // create empty admin list to store admins
				Product product = null; // create admin object which is from model
				String query = "SELECT SQL_CALC_FOUND_ROWS products.*, " + 
					    "categories.name AS category_name, " + 
					    "MIN(images.name) AS image_name, " + 
					    "sellers.name AS seller_name " + 
					    "FROM products " + 
					    "LEFT JOIN categories ON products.category_id = categories.id " + 
					    "LEFT JOIN sellers ON products.seller_id = sellers.id " + 
					    "LEFT JOIN images ON images.product_id = products.id " + 
					    "WHERE products.name LIKE '%" + searchTerm + "%' " + 
					    "OR categories.name LIKE '%" + searchTerm + "%' " + 
					    "OR sellers.name LIKE '%" + searchTerm + "%' " + 
					    "GROUP BY products.id";

				statement = con.createStatement();
		        resultSet = statement.executeQuery(query);
		        while(resultSet.next()) {
		             product = new Product();
		            product.setId(resultSet.getInt("id"));
		            product.setName(resultSet.getString("name"));
		            product.setPrice(resultSet.getInt("price"));
		            product.setDescription(resultSet.getString("description"));
		            product.setCount(resultSet.getInt("count"));
		            product.setRating(resultSet.getInt("rating"));
		            product.setCategory_id(resultSet.getInt("category_id"));
		            product.setSeller_id(resultSet.getInt("seller_id"));
		            product.setSeller_name(resultSet.getString("seller_name"));
		            product.setCategory_name(resultSet.getString("category_name"));
		            product.setImage(resultSet.getString("image_name"));
		            // Add the product to the list
		            products.add(product);
		        }
				return products; // return that list
			}
			
			
			// get product count by Seller id
			public int getProductCountBySellerID(int seller_id) throws SQLException {
				int count = 0;
				String query = "SELECT count(*) as product_count FROM products WHERE products.seller_id= " + seller_id;
				statement = con.createStatement();
				resultSet = statement.executeQuery(query);
				if(resultSet.next()) {
					count = resultSet.getInt("product_count");
				}
				return count;
			}
			
			
			
			// get all product by seller Id
						public List<Product> getAllBySellerID(int offset, int noOfRecords, int seller_id) throws SQLException{
							List<Product> products = new ArrayList<Product>();  // create empty admin list to store admins
							Product product = null; // create admin object which is from model
							String query = "select SQL_CALC_FOUND_ROWS products.*, categories.name as category_name, MIN(images.name) AS image_name, sellers.name as seller_name FROM products "
									+ "LEFT JOIN categories ON products.category_id = categories.id "
									+ "LEFT JOIN sellers ON products.seller_id = sellers.id "
									+ "LEFT JOIN images ON images.product_id = products.id  WHERE sellers.id="+seller_id+" GROUP BY "
									+ "products.id "
									+ "ORDER BY updated_at DESC limit " + offset + ", " + noOfRecords ;
							statement = con.createStatement();
					        resultSet = statement.executeQuery(query);
					        while(resultSet.next()) {
					             product = new Product();
					            product.setId(resultSet.getInt("id"));
					            product.setName(resultSet.getString("name"));
					            product.setPrice(resultSet.getInt("price"));
					            product.setDescription(resultSet.getString("description"));
					            product.setCount(resultSet.getInt("count"));
					            product.setRating(resultSet.getInt("rating"));
					            product.setCategory_id(resultSet.getInt("category_id"));
					            product.setSeller_id(resultSet.getInt("seller_id"));
					            product.setSeller_name(resultSet.getString("seller_name"));
					            product.setCategory_name(resultSet.getString("category_name"));
					            product.setImage(resultSet.getString("image_name"));
					            // Add the product to the list
					            products.add(product);
					        }
							resultSet.close();
							resultSet = statement.executeQuery("SELECT FOUND_ROWS()");
					        if(resultSet.next()) {
					        	 this.noOfRecords = resultSet.getInt(1);
					        }
							return products; // return that list
						}
			
			
			public Product getFullDataBySellerId(int id) throws SQLException{
				Product product = new Product(); // create admin object which is from model
				String query = "select products.*, categories.name as category_name, sellers.name as seller_name FROM products LEFT JOIN categories ON products.category_id = categories.id LEFT JOIN sellers ON products.seller_id = sellers.id WHERE products.id=" + id;
				statement = con.createStatement();
		        resultSet = statement.executeQuery(query);
		        while(resultSet.next()) {
		            product.setId(resultSet.getInt("id"));
		            product.setName(resultSet.getString("name"));
		            product.setPrice(resultSet.getInt("price"));
		            product.setDescription(resultSet.getString("description"));
		            product.setCount(resultSet.getInt("count"));
		            product.setRating(resultSet.getInt("rating"));
		            product.setCategory_id(resultSet.getInt("category_id"));
		            product.setSeller_id(resultSet.getInt("seller_id"));
		            product.setSeller_name(resultSet.getString("seller_name"));
		            product.setCategory_name(resultSet.getString("category_name"));
		        }
				return product; // return that list
			}
			
			
			
			public int getProductCountBySellerId(int id) {
				int count = 0;
				String query = "SELECT count(products.name) as product_count from products WHERE seller_id = " + id;
				try {
					statement = con.createStatement();
					 resultSet = statement.executeQuery(query);
				     if (resultSet.next()) {
				        count = resultSet.getInt("product_count");
				     }
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return count;
			}
			
			public Product getFullDataByProductId(int id) throws SQLException{
				Product product = new Product(); // create admin object which is from model
				String query = "select products.*, categories.name as category_name, sellers.name as seller_name FROM products LEFT JOIN categories ON products.category_id = categories.id LEFT JOIN sellers ON products.seller_id = sellers.id WHERE products.id=" + id;
				statement = con.createStatement();
		        resultSet = statement.executeQuery(query);
		        while(resultSet.next()) {
		            product.setId(resultSet.getInt("id"));
		            product.setName(resultSet.getString("name"));
		            product.setPrice(resultSet.getInt("price"));
		            product.setDescription(resultSet.getString("description"));
		            product.setCount(resultSet.getInt("count"));
		            product.setRating(resultSet.getInt("rating"));
		            product.setCategory_id(resultSet.getInt("category_id"));
		            product.setSeller_id(resultSet.getInt("seller_id"));
		            product.setSeller_name(resultSet.getString("seller_name"));
		            product.setCategory_name(resultSet.getString("category_name"));
		        }
				return product; // return that list
			}
			
			
			// get product by category id with limit 5 in product
			public List<Product> getSomeProductByCategoryId(int id) {
				List<Product> products = new ArrayList<Product>();
				String query = "SELECT products.*, categories.name AS category_name, MIN(images.name) AS image_name, sellers.name AS seller_name " +
			               "FROM products " +
			               "LEFT JOIN categories ON products.category_id = categories.id " +
			               "LEFT JOIN sellers ON products.seller_id = sellers.id " +
			               "LEFT JOIN images ON images.product_id = products.id " +
			               "WHERE products.category_id = " + id + " " +
			               "GROUP BY products.id " +
			               "ORDER BY RAND() " +
			               "LIMIT 4";

				try {
					statement = con.createStatement();
					resultSet = statement.executeQuery(query);
			        while(resultSet.next()) {
			        	Product product = new Product();
			            product.setId(resultSet.getInt("id"));
			            product.setName(resultSet.getString("name"));
			            product.setPrice(resultSet.getInt("price"));
			            product.setDescription(resultSet.getString("description"));
			            product.setCount(resultSet.getInt("count"));
			            product.setRating(resultSet.getInt("rating"));
			            product.setCategory_id(resultSet.getInt("category_id"));
			            product.setSeller_id(resultSet.getInt("seller_id"));
			            product.setSeller_name(resultSet.getString("seller_name"));
			            product.setCategory_name(resultSet.getString("category_name"));
			            product.setImage(resultSet.getString("image_name"));
			            products.add(product);
			        }
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return products;
			}
			
			
			
			// delete image by id
			public boolean deleteImageById(int id) {
				boolean flag = false;
				// delete from local devicce
				String image_name = null;
				String query = "SELECT * FROM images WHERE id = " + id;

				// delete from table
				String query2 = "DELETE FROM images WHERE id = " + id;
				try {
					statement = con.createStatement();
					resultSet = statement.executeQuery(query);
					while(resultSet.next()) {
						image_name = resultSet.getString("name");
					}
					String filePath = "C:\\Users\\acer\\Desktop\\cc-shop\\src\\main\\webapp\\assets\\images\\products\\" + image_name;

					// Create a File object with the specified file path
					File fileToDelete = new File(filePath);

					// Check if the file exists before attempting to delete it
					if (fileToDelete.exists()) {
					    // Attempt to delete the file
					    if (fileToDelete.delete()) {
					        System.out.println("File deleted successfully.");
					    } else {
					        System.out.println("Failed to delete the file.");
					    }
					} else {
					    System.out.println("File does not exist.");
					}
					statement.close();
					
					statement = con.createStatement();
					int deletedRow = statement.executeUpdate(query2);
					statement.close();
					if(deletedRow > 0) flag = true;
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return flag;
			}
			
			// get the full image by seller id
			public List<Image> getFullImagesBySellerId(int id) throws SQLException{
				List<Image> images = new ArrayList<Image>(); // create admin object which is from model
				String query = "select * from images WHERE product_id=" + id;
				statement = con.createStatement();
		        resultSet = statement.executeQuery(query);
		        while(resultSet.next()) {
		        	Image image = new Image();
		        	image.setId(resultSet.getInt("id"));
		        	image.setName(resultSet.getString("name"));
		        	image.setProduct_id(resultSet.getInt("product_id"));
		        	images.add(image);
		        }
				return images; // return that list
			}
			
			// get the full image by seller id
						public List<Image> getFullImagesByProductId(int id) throws SQLException{
							List<Image> images = new ArrayList<Image>(); // create admin object which is from model
							String query = "select * from images WHERE product_id=" + id;
							statement = con.createStatement();
					        resultSet = statement.executeQuery(query);
					        while(resultSet.next()) {
					        	Image image = new Image();
					        	image.setId(resultSet.getInt("id"));
					        	image.setName(resultSet.getString("name"));
					        	image.setProduct_id(resultSet.getInt("product_id"));
					        	images.add(image);
					        }
							return images; // return that list
						}

		// get number of records
		public int getNoOfRecords() {
	        return noOfRecords;
	    }
		
		// delete product
		public boolean delete(int id) throws SQLException {
				boolean delete_product_flag = false;
				boolean delete_image_flag = false;
				boolean is_all_deleted = false;
				
				List<String> images = new ArrayList<String>();
				
				// frist select the images
				String select_product = "SELECT * FROM images WHERE product_id = " +id;
				statement = con.createStatement();
				resultSet = statement.executeQuery(select_product);
				while(resultSet.next()) {
					String image = resultSet.getString("name");
					images.add(image);
				}
				statement.close();
				
				
				// delete from product table
				String query = "DELETE FROM images WHERE product_id = " + id;
				statement = con.createStatement();
				int delete_image = statement.executeUpdate(query);
				if(delete_image > 0) delete_image_flag = true;
				statement.close();
				
				// delete image from local images
				for(String i : images) {
					// Provide the path of the file you want to delete
					String filePath = "C:\\Users\\acer\\Desktop\\cc-shop\\src\\main\\webapp\\assets\\images\\products\\" + i;

					// Create a File object with the specified file path
					File fileToDelete = new File(filePath);

					// Check if the file exists before attempting to delete it
					if (fileToDelete.exists()) {
					    // Attempt to delete the file
					    if (fileToDelete.delete()) {
					        System.out.println("File deleted successfully.");
					    } else {
					        System.out.println("Failed to delete the file.");
					    }
					} else {
					    System.out.println("File does not exist.");
					}
				}
				System.out.println("Deleted file successfullt");
				
				if(delete_image_flag) {
					System.out.println("delete from image table");
					// then delete product image
					String query2 = "DELETE FROM products WHERE id = " +id;
					statement = con.createStatement();
					int deleted_product = statement.executeUpdate(query2);
					if(deleted_product > 0) {
						delete_product_flag = true;
						System.out.println("delete from  product table");
					}
				}
				if(delete_product_flag && delete_image_flag) is_all_deleted = true;
				
				return is_all_deleted;
		}
		
		
	
	
	// testing method
	public static void main(String args[]) throws ClassNotFoundException, SQLException {
		ProductDAO dao = new ProductDAO();
		System.out.println(dao.get());
	}
}
