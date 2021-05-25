package com.metronicproject.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Buyer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer points = 0;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(mappedBy = "buyer", cascade = CascadeType.ALL)
    private List<Order> orders = new ArrayList<Order>();
    @OneToMany(mappedBy = "buyer", cascade = CascadeType.ALL)
    private List<CartItem> cartItems = new ArrayList<CartItem>();
    @ManyToMany
    @JoinTable(name = "following", joinColumns = {@JoinColumn(name = "buyer_id")}, inverseJoinColumns = {@JoinColumn(name = "seller_id")})
    private List<Seller> sellers = new ArrayList<Seller>();

    public void followSeller(Seller seller) {
        sellers.add(seller);
    }

    public void unfollowSeller(Seller seller) {
        sellers.remove(seller);
    }

    public void addCartItem(CartItem item) {
        cartItems.add(item);
    }

    public void removeCartItem(CartItem item) {
        cartItems.remove(item);
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public List<CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(List<CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	public List<Seller> getSellers() {
		return sellers;
	}

	public void setSellers(List<Seller> sellers) {
		this.sellers = sellers;
	}

	public Buyer(Integer points, User user, List<Order> orders, List<CartItem> cartItems, List<Seller> sellers) {
		super();
		this.points = points;
		this.user = user;
		this.orders = orders;
		this.cartItems = cartItems;
		this.sellers = sellers;
	}
    
}
