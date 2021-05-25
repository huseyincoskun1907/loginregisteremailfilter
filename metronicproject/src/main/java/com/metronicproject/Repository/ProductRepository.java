package com.metronicproject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.metronicproject.model.Category;
import com.metronicproject.model.Product;
import com.metronicproject.model.Seller;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findProductsByCategory(Category category);

    List<Product> findProductsBySeller(Seller seller);

    @Query("SELECT p FROM Product p WHERE p.name LIKE ?1 ")
    List<Product> findProductsByName(String name);
}
