package com.example.demo.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cart")
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cart_id")
	private long cartId;

//	@Column(name="image")
//	private String image;
	
	@Column(name = "quantity")
	private int quantity;

	@Column(name = "mrp_price", nullable = false)
	private double mrpPrice;

//	// @Size(min = 2, max = 10)
//	@Column(name = "price", nullable = false)
//	private double price;
	
	private int user_Id;
	
	@Column(name = "pdt_Id")
	private int pdt_Id;
	@ManyToOne( cascade=CascadeType.MERGE)
	@JoinColumn(name="user_Id", insertable = false, updatable = false)
	//@JsonIgnore
    private User user;
	
	
	@ManyToOne( cascade=CascadeType.MERGE)
	@JoinColumn(name="pdt_Id",insertable = false, updatable = false)
	//@JsonIgnore
    private Product product;
	
	public Cart() {
		
	}
	public Cart(long cartId, int quantity, double mrpPrice, int user_Id, User user) {
		super();
		this.cartId = cartId;
		this.quantity = quantity;
		this.mrpPrice = mrpPrice;
		this.user_Id = user_Id;
		this.user = user;
	}
	public long getCartId() {
		return cartId;
	}
	public void setCartId(long cartId) {
		this.cartId = cartId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getMrpPrice() {
		return mrpPrice;
	}
	public void setMrpPrice(double mrpPrice) {
		this.mrpPrice = mrpPrice;
	}
	public int getUser_Id() {
		return user_Id;
	}
	public void setUser_Id(int user_Id) {
		this.user_Id = user_Id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public int getPdt_Id() {
		return pdt_Id;
	}
	public void setPdt_Id(int pdt_Id) {
		this.pdt_Id = pdt_Id;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	
}
