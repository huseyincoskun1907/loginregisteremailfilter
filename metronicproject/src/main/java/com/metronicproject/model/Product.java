package com.metronicproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    private String description;
    @Column(nullable = false)
    private BigDecimal price;
    private String image;
    @Transient
    @JsonIgnore
    private MultipartFile upload;
    private Double available;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "seller_id")
    private Seller seller;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "category_id")
    private Category category;

	public Product(String name, String description, BigDecimal price, String image, MultipartFile upload,
			Double available, Seller seller, Category category) {
		super();
		this.name = name;
		this.description = description;
		this.price = price;
		this.image = image;
		this.upload = upload;
		this.available = available;
		this.seller = seller;
		this.category = category;
	}
	public Product() {
		
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

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public MultipartFile getUpload() {
		return upload;
	}

	public void setUpload(MultipartFile upload) {
		this.upload = upload;
	}

	public Double getAvailable() {
		return available;
	}

	public void setAvailable(Double available) {
		this.available = available;
	}

	public Seller getSeller() {
		return seller;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
}
