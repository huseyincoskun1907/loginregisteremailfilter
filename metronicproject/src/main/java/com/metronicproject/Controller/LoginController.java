package com.metronicproject.Controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.metronicproject.model.User;

import java.io.Serializable;
import java.security.Principal;

@Controller
public class LoginController implements Serializable {

    @RequestMapping(value = {"/"})
    public String home(){
        return "login";
    }

    @RequestMapping(value = {"/login"})
    public ModelAndView login(@RequestParam(value = "error", required = false) String error,
                       @RequestParam(value = "logout", required = false) String logout) {

        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", "Invalid username and password!");
        }

        if (logout != null) {
            model.addObject("msg", "You've been logged out successfully.");
        }
        model.setViewName("login");

        return model;
    }
    @RequestMapping(value = {"/index"})
    public String indexs() {
    	return "index";
    }
    @GetMapping("/login")
    public String showLoginPage() {
    	Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
    	if(authentication==null || authentication instanceof AnonymousAuthenticationToken ) {
    		return "login";
    	}
    	return "redirect:/index";
    	
    }
   

}