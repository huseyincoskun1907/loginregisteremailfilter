package com.metronicproject.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.metronicproject.Service.ProductService;
import com.metronicproject.model.Product;

@Controller
public class AdminProductController {
	

	@Autowired
	private ProductService productService;

	@GetMapping("/dashboard-2")
    public String viewHomePage(Model model) {
        model.addAttribute("listProducts", productService.getAllProduct());
        return "dashboard-2";
    }
	@PostMapping("/saveProduct")
    public String saveProduct(@ModelAttribute("product") Product product) {
        // save employee to database
		productService.saveProduct(product);
        return "redirect:/dashboard-2";
    }
	@GetMapping("/showNewProductForm")
    public String showNewBlogForm(Model model) {
        // create model attribute to bind form data
		Product product=new Product(null, null, null, null, null, null, null, null);
        model.addAttribute("product", product);
        return "new_product";
    }
	 @GetMapping("/showFormForUpdate/{id}")
	    public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {

	        // get employee from the service
		 Product product = productService.getProductById(id);

	        // set employee as a model attribute to pre-populate the form
	        model.addAttribute("product", product);
	        return "update_product";
	    }

	    @GetMapping("/deleteProduct/{id}")
	    public String deleteBlog(@PathVariable(value = "id") long id) {

	        // call delete employee method 
	        this.productService.deleteProductById(id);
	        return "redirect:/dashboard-2";
	    }

}
