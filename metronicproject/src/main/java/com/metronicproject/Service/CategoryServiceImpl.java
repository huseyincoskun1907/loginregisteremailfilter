package com.metronicproject.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metronicproject.Repository.CategoryRepository;
import com.metronicproject.model.Category;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
    	
	@Override
	public List<Category> getAllCategory() {
		return categoryRepository.findAll();
	}

	@Override
	public void saveCategory(Category category) {
		this.categoryRepository.save(category);
		
	}

	@Override
	public Category getCategoryById(long id) {
		Optional < Category > optional = categoryRepository.findById(id);
		Category category = null;
        if (optional.isPresent()) {
        	category = optional.get();
        } else {
            throw new RuntimeException(" Blog not found for id :: " + id);
        }
        return category;
		
	}

	@Override
	public void deleteCategoryById(long id) {
		this.categoryRepository.deleteById(id);
		
	}

}
