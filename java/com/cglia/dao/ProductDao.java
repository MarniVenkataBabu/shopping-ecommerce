package com.cglia.dao;

import java.sql.*;
import java.util.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.cglia.model.*;

/**
 * 
 * @author venkata.marni
 *@since 22-05-2023
 *@version 1.0
 */
public class ProductDao {
	
	private Connection connection;
	private String query;
    private PreparedStatement pst;
    private ResultSet rs;
    private static final Logger logger = LogManager.getLogger(ProductDao.class);
    private static final String CATEGORY = "category";
    private static final String IMAGE = "image";
    private static final String PRICE = "price";
   /**
    * 
    * @param intialization of connection
    */
	public ProductDao(Connection connection) {
		this.connection = connection;
	}
	/**
	 * 
	 * @return Returns all products present in the products table
	 * @throws -SQLException
	 */
	public List<Product> getAllProducts() {
        List<Product> book = new ArrayList<>();
        try {

            query = "select * from venkat.products";
            pst = connection.prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()) {
            	Product row = new Product();
                row.setId(rs.getInt("id"));
                row.setName(rs.getString("name"));
                row.setCategory(rs.getString(CATEGORY));
                row.setPrice(rs.getDouble(PRICE));
                row.setImage(rs.getString(IMAGE));
                book.add(row);
            }

         } catch (SQLException e) {
            e.printStackTrace();
            logger.error("An error Occured: {}",e.getMessage(), e);
         }
         return book;
    }
     /**
      * 
      * @param id-requires productId to get product details
      * @return returns a single product with details 
      * @throws SQLException
      */
	 public Product getSingleProduct(int id) {
		 Product row = null;
	        try {
	            query = "select * from venkat.products where id=? ";
	            pst =connection.prepareStatement(query);
	            pst.setInt(1, id);
	            rs = pst.executeQuery();

	            while (rs.next()) {
	            	row = new Product();
	                row.setId(rs.getInt("id"));
	                row.setName(rs.getString("name"));
	                row.setCategory(rs.getString(CATEGORY));
	                row.setPrice(rs.getDouble(PRICE));
	                row.setImage(rs.getString(IMAGE));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            logger.error("An error occurred getting products: {}", e.getMessage(), e);
	        }
	        return row;
	 }
	 /**
	  * 
	  * @param searchTerm-required the String value which is required to get products from the product table
	  * @return-returns a list of products which matches with the searchTerm 
	  */
	public List<Product> getSearchProducts(String searchTerm) {
	        List<Product> book = new ArrayList<>();
	        try {
	        	if ("men".equalsIgnoreCase(searchTerm)) {
	        		 query = "SELECT * FROM venkat.products WHERE (category LIKE 'men%' AND name NOT LIKE '%women%');;";
	        	} else {
	        	    query = "SELECT * FROM venkat.products WHERE name LIKE CONCAT('%', ?, '%');";
	        	    pst = connection.prepareStatement(query);
	        	    pst.setString(1, searchTerm);
	        	}
	            rs = pst.executeQuery();

	            while (rs.next()) {
	            	Product row = new Product();
	                row.setId(rs.getInt("id"));
	                row.setName(rs.getString("name"));
	                row.setCategory(rs.getString(CATEGORY));
	                row.setPrice(rs.getDouble(PRICE));
	                row.setImage(rs.getString(IMAGE));
	                book.add(row);
	            }

	         } catch (SQLException e) {
	            e.printStackTrace();
	            logger.error("An error occured: {}",e.getMessage(), e);
	         }
	         return book;
	    }
	/**
	 * 
	 * @param cartList
	 * @return double value which is obtained by adding to the cart
	 */
	 public double getTotalCartPrice(List<Cart> cartList) {
        double sum = 0;
        try {
            if (!cartList.isEmpty()) {
                for (Cart item : cartList) {
                    query = "select price from venkat.products where id=?";
                    pst = connection.prepareStatement(query);
                    pst.setInt(1, item.getId());
                    rs = pst.executeQuery();
                    while (rs.next()) {
                        sum+=rs.getDouble(PRICE)*item.getQuantity();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("An error occurred during login: {}", e.getMessage(), e);
        }
         return sum;
     }
    /**
     * 
     * @param cartList-requires cartList to get Cart products
     * @return List of cart items 
     */
    public List<Cart> getCartProducts(List<Cart> cartList) {
        List<Cart> book = new ArrayList<>();
        try {
            if (!cartList.isEmpty()) {
                for (Cart item : cartList) {
                    query = "select * from venkat.products where id=?";
                    pst = connection.prepareStatement(query);
                    pst.setInt(1, item.getId());
                    rs = pst.executeQuery();
                    while (rs.next()) {
                        Cart row = new Cart();
                        row.setId(rs.getInt("id"));
                        row.setName(rs.getString("name"));
                        row.setCategory(rs.getString(CATEGORY));
                        row.setPrice(rs.getDouble(PRICE)*item.getQuantity());
                        row.setQuantity(item.getQuantity());
                        book.add(row);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("products obtaining from table with userID: {}",e.getMessage(), e);
        }
        return book;
    }
}