package com.metronicproject.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.metronicproject.Service.CategoryService;
import com.metronicproject.model.Category;

@Controller
public class AdminCategoryController {


	@Autowired
	private CategoryService categoryService;

	@GetMapping("/categoryDashboard")
    public String viewHomePage(Model model) {
        model.addAttribute("listCategories", categoryService.getAllCategory());
        return "categoryDashboard";
    }
	@PostMapping("/saveCategory")
    public String saveProduct(@ModelAttribute("category") Category category) {
        // save employee to database
		categoryService.saveCategory(category);
        return "redirect:/categoryDashboard";
    }
	@GetMapping("/showNewCategoryForm")
    public String showNewBlogForm(Model model) {
        // create model attribute to bind form data
		Category category=new Category(null, null, null);
        model.addAttribute("category", category);
        return "new_category";
    }
	 @GetMapping("/showFormForsUpdate/{id}")
	    public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {

	        // get employee from the service
		 Category category = categoryService.getCategoryById(id);

	        // set employee as a model attribute to pre-populate the form
	        model.addAttribute("category", category);
	        return "update_category";
	    }

	    @GetMapping("/deleteCategory/{id}")
	    public String deleteBlog(@PathVariable(value = "id") long id) {

	        // call delete employee method 
	        this.categoryService.deleteCategoryById(id);
	        return "redirect:/categoryDashboard";
	    }

}
