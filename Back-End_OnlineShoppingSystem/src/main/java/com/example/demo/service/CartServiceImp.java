package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.CartDao;
import com.example.demo.dao.UserDao;
import com.example.demo.entity.Cart;
import com.example.demo.entity.Product;
import com.example.demo.entity.User;
import com.example.demo.exception.ResourceNotFoundException;

@Service
public class CartServiceImp implements CartService{

	@org.springframework.beans.factory.annotation.Autowired(required=true)
	public CartDao dao;
	
	@Autowired
	UserDao dao1;
	@Autowired
	public ProductService productService;
	
	@Autowired
	public UserService userService;
	
	public CartServiceImp(CartDao dao) {
		super();
		this.dao = dao;
	}
	
	@Override
	public Cart addCart(Cart cart, int productId, int user_Id) {
		Product product = productService.findProductById(productId);
		User user = userService.findUserById(user_Id);
		   product.setPdt_quantity(product.getPdt_quantity()-cart.getQuantity());
		 productService.updateProduct(product, product.getPdt_quantity()-1);
		cart.setProduct(product);
		cart.setMrpPrice(product.getPdt_cost());
		cart.setUser(user);
		  System.out.println("product"+product);
		  System.out.println("user"+ user);
		  System.out.println("cart"+cart);
		  
			    	
		return dao.save(cart);
	}



	@Override
	public List<Cart> getAllCarts() {
		return dao.findAll();
	}

	@Override
	public Cart getCartById(long cartId) {
		return dao.findById(cartId).orElseThrow(()->new ResourceNotFoundException("Cart"));
	}

	@Override
	public Cart updateCart(Cart cart, long cartId) {
		Cart existingCart=dao.findById(cartId).orElseThrow(()->new ResourceNotFoundException("Cart"));
		existingCart.setQuantity(cart.getQuantity());
		//existingCart.setPrice(cart.getPrice());
		existingCart.setMrpPrice(cart.getMrpPrice());
		//existingCart.setImage(cart.getImage());
		existingCart.setCartId(cart.getCartId());
		existingCart.setProduct(cart.getProduct());
		//existingCart.setCustomerId(cart.getCustomerId());
		existingCart.setUser(cart.getUser());
	    dao.save(existingCart);
	    
		return existingCart;
	}

	@Override
	public void deleteCart(long cartId) {
		dao.findById(cartId).orElseThrow(()->new ResourceNotFoundException("Cart"));
		dao.deleteById(cartId);
		
	}



}
