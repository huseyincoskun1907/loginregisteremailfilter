package com.metronicproject.Controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.metronicproject.Repository.UserRepository;
import com.metronicproject.Service.UserService;
import com.metronicproject.model.User;

@Controller
public class AdminController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private UserRepository userRepository;

	@GetMapping(value="admin")
    public String viewHomePage(Model model) {
		
		 User user = new User();
	     model.addAttribute("user", user);
		  model.addAttribute("listBlogs", userService.getAllUser()); 
			
		  
        return "admin";
    }
	  @RequestMapping(value="dashboard",method = RequestMethod.GET)
	public String dashbord(Model model,User user) {
		  
		  
		 userService.findByCompletedAndUserId(model);
		return "dashboard";
	}
	
	

}
