package com.cglia.model;

/**
 * 
 * @author venkata.marni
 * @since 22-05-2023
 * @version 1.0
 *
 */
public class Order extends Product {

	private int orderId;
	private int userId;
	private int orderQuantity;
	private String orderDate;

	/**
	 * default constructor
	 */
	public Order() {
	}

	/**
	 * Parameterized constructor
	 * 
	 * @param orderId
	 * @param userId
	 * @param orderQuantity
	 * @param orderDate
	 */
	public Order(int orderId, int userId, int orderQuantity, String orderDate) {
		this.orderId = orderId;
		this.userId = userId;
		this.orderQuantity = orderQuantity;
		this.orderDate = orderDate;
	}

	/**
	 * Parameterized Constructor with out orderId
	 * 
	 * @param userId
	 * @param orderQuantity
	 * @param orderedDate
	 */
	public Order(int userId, int orderQuantity, String orderDate) {
		this.userId = userId;
		this.orderQuantity = orderQuantity;
		this.orderDate = orderDate;
	}

	/**
	 * 
	 * @return -Integer value like orderId
	 */
	public int getOrderId() {
		return orderId;
	}

	/**
	 * 
	 * @param orderId- to set orderID to the orders table
	 */
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	/**
	 * 
	 * @return -Integer value like UserId
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * 
	 * @param userId-set userID of int data type
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * 
	 * @return Integer value of orderQuantity
	 */
	public int getOrderQuantity() {
		return orderQuantity;
	}

	/**
	 * 
	 * @param orderQuantity-Integer value to set orderQuantity
	 */
	public void setOrderQuantity(int orderQuantity) {
		this.orderQuantity = orderQuantity;
	}

	/**
	 * 
	 * @return String value of orderDate
	 */
	public String getOrderDate() {
		return orderDate;
	}

	/**
	 * 
	 * @param orderDate -String value of orderDate
	 */
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
}