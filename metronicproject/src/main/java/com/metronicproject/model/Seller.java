package com.metronicproject.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    private String picture;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    @Valid
    private User user;

    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL)
    private List<Product> products = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToMany(mappedBy = "sellers")
    private List<Buyer> buyers = new ArrayList<>();

    public void addBuyer(Buyer buyer) {
        buyers.add(buyer);
    }

    public void removeBuyer(Buyer buyer) {
        buyers.remove(buyer);
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public List<Buyer> getBuyers() {
		return buyers;
	}

	public void setBuyers(List<Buyer> buyers) {
		this.buyers = buyers;
	}
    
}
