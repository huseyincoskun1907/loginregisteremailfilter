package com.metronicproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantity;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;


    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "buyer_id")
    @JsonIgnore
    private Buyer buyer;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public Product getProduct() {
		return product;
	}


	public void setProduct(Product product) {
		this.product = product;
	}


	public Buyer getBuyer() {
		return buyer;
	}


	public void setBuyer(Buyer buyer) {
		this.buyer = buyer;
	}


	public CartItem(int quantity, Product product, Buyer buyer) {
		super();
		this.quantity = quantity;
		this.product = product;
		this.buyer = buyer;
	}
    
}
