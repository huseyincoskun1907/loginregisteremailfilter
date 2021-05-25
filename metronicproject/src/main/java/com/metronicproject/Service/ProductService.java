package com.metronicproject.Service;

import java.util.List;

import com.metronicproject.model.Product;


public interface ProductService {

	List < Product > getAllProduct();
    void saveProduct(Product blog);
    Product getProductById(long id);
    void deleteProductById(long id);
}
