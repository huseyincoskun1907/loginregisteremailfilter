package com.metronicproject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.metronicproject.model.Category;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
