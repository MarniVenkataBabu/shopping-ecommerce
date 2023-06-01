package com.cglia.model;

/**
 * 
 * @author venkata.marni
 * @since 22-05-2023
 * @version 1.0
 */
public class Product {

	private int id;
	private String name;
	private String category;
	private Double price;
	private String image;

	/**
	 * constructor
	 */
	public Product() {
	}

	/**
	 * 
	 * @param id
	 * @param name
	 * @param category
	 * @param price
	 * @param image
	 */
	public Product(int id, String name, String category, Double price, String image) {
		this.id = id;
		this.name = name;
		this.category = category;
		this.price = price;
		this.image = image;
	}

	/**
	 * 
	 * @return Integer value of id
	 */
	public int getId() {
		return id;
	}

	/**
	 * 
	 * @param id-accepts Integer value in id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * 
	 * @return String value of name
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @param name-accepts String value in name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 
	 * @return String value in category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * 
	 * @param category-accepts String value in category
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * 
	 * @return Returns Double value in price
	 */
	public Double getPrice() {
		return price;
	}

	/**
	 * 
	 * @param price-Accepts double value in price
	 */
	public void setPrice(Double price) {
		this.price = price;
	}

	/**
	 * 
	 * @return String value in image
	 */
	public String getImage() {
		return image;
	}

	/**
	 * 
	 * @param image-accepts String value in image
	 */
	public void setImage(String image) {
		this.image = image;
	}

	/**
	 * To String method to return product id,name,category,price,image.
	 */
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", category=" + category + ", price=" + price + ", image="
				+ image + "]";
	}
}