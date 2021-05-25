package com.metronicproject.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.metronicproject.Service.SecurityService;
import com.metronicproject.Service.UserService;
import com.metronicproject.model.User;

import javax.validation.Valid;
import java.io.Serializable;

@Controller
public class RegisterController implements Serializable {

	
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private PasswordEncoder passwordEncoder;
   

    @RequestMapping("/register")
    public String register(Model model) {

        model.addAttribute("userForm", new User());

        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@ModelAttribute("userForm") @Valid User userForm, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "register";
        }
        userForm.setPassword(passwordEncoder.encode(userForm.getPassword()));
        userService.save(userForm);
        return "redirect:/sms";
    } 
	@RequestMapping(value = "register/verifyKey/{key}", method = RequestMethod.GET)
	public String regOk(@PathVariable("key") String verifyKey) {
		if (userService.findByverifyKey(verifyKey)) {
			return "redirect:/login";
		}
		return "redirect:/login";
	}

}