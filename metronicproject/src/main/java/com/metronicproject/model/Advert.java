package com.metronicproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
public class Advert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String image;
    @Transient
    @JsonIgnore
    private MultipartFile imageUpload;
    private String url;
    private Integer count;
    private LocalDateTime creationDateTime;
    private LocalDateTime expirationDateTime;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public MultipartFile getImageUpload() {
		return imageUpload;
	}
	public void setImageUpload(MultipartFile imageUpload) {
		this.imageUpload = imageUpload;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public LocalDateTime getCreationDateTime() {
		return creationDateTime;
	}
	public void setCreationDateTime(LocalDateTime creationDateTime) {
		this.creationDateTime = creationDateTime;
	}
	public LocalDateTime getExpirationDateTime() {
		return expirationDateTime;
	}
	public void setExpirationDateTime(LocalDateTime expirationDateTime) {
		this.expirationDateTime = expirationDateTime;
	}
	public Advert(String title, String description, String image, MultipartFile imageUpload, String url, Integer count,
			LocalDateTime creationDateTime, LocalDateTime expirationDateTime) {
		super();
		this.title = title;
		this.description = description;
		this.image = image;
		this.imageUpload = imageUpload;
		this.url = url;
		this.count = count;
		this.creationDateTime = creationDateTime;
		this.expirationDateTime = expirationDateTime;
	}
    
}
