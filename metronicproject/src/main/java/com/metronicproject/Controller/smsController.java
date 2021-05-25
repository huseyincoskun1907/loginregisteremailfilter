package com.metronicproject.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.metronicproject.Repository.UserRepository;
import com.metronicproject.Service.SmsService;
import com.metronicproject.Service.UserService;
import com.metronicproject.model.User;

import java.io.Serializable;

@Controller
public class smsController implements Serializable {


	@Autowired
	private SmsService smsService;
@Autowired
private UserService userService;
	
    @Autowired
    private UserRepository userRepository;



    
    @RequestMapping(value = {"/sms"})
    public String home(){
    	return "sms";
    }

    @RequestMapping(value = "/sms", method = RequestMethod.POST)
	public String regsOk(String tokensms) {
		User usergelen=userRepository.findByTokensms(tokensms);
		if(usergelen.getTokensms().equals(tokensms)) {
			usergelen.setSmsEnabled(true);
			usergelen.setTokensms(null);
			userRepository.save(usergelen);
		}
		return "redirect:/login";
		
	}
    
//    @RequestMapping(value = {"/sms-login"})
//    public String homess(){
//    	return "sms-login";
//    }

    
    @RequestMapping(value = "/sms-login",method = RequestMethod.GET)
    public String homes(Model model){
    	
		
		return "/sms-login";
    }
    @RequestMapping(value = "/sendOtp", method = RequestMethod.POST)
   	public String sendOtp() {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user=userRepository.findByUsername(auth.getName());
        String token;
   		try {
   			token = smsService.smsSenderFunction(user.getPhone());
   			  user.setLoginsms(token);
   			  userRepository.save(user);
   		} catch (Exception e) {
   			// TODO Auto-generated catch block
   			e.printStackTrace();
       }
		return "/sms-login";
   		
   		
   	}

    @RequestMapping(value = "/sms-login", method = RequestMethod.POST)
	public String regsOks(String tokensms) {
		User usergelen=userRepository.findByLoginsms(tokensms);
		if(usergelen.getLoginsms().equals(tokensms)) {
			usergelen.setLoginsmsEnabled(true);
			usergelen.setLoginsms(null);
			userRepository.save(usergelen);
		}
		return "redirect:/index";
		
	}
//    @RequestMapping(value = "/default",method = RequestMethod.GET)
//    public String deaults(Model model){
//    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//     User user=   userRepository.findByUsername(auth.getName());
//   if(user.isOtpActive()) {
//	   return "sms-login";
//   }
//		
//		return "/sms-login";
//    }
//    
}