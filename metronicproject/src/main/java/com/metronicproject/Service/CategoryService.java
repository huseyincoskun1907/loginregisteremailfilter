package com.metronicproject.Service;

import java.util.List;

import com.metronicproject.model.Category;

public interface CategoryService {
	List < Category > getAllCategory();
    void saveCategory(Category category);
    Category getCategoryById(long id);
    void deleteCategoryById(long id);
}
