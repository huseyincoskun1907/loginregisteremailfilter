package com.metronicproject.Service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.metronicproject.Repository.RoleRepository;
import com.metronicproject.Repository.UserRepository;
import com.metronicproject.model.Role;
import com.metronicproject.model.User;



@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private SmsService smsService;
	@Autowired
	private EmailService emailService;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void save(User user) {
        user.setPassword(user.getPassword());
        user.setRoles(roleRepository.findAll());
        
        String uuid=UUID.randomUUID().toString();
    	user.setVerifyKey(uuid);
    	
		try {
		String	token = smsService.smsSenderFunction(user.getPhone());
			  user.setTokensms(token);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
    	emailService.mailSenderFunction(user.getUsername(), user.getVerifyKey());
    	user.setOtpActive(true);
    
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    
    public boolean findByverifyKey(String verifyKey) {
    	User user = userRepository.findByverifyKey(verifyKey);
    	
    	if(user!=null) {
    		user.setActive(true);
    		user.setVerifyKey(null);
    		userRepository.save(user);
    		return true;
    	}else {
    		return false;
    	}
    }
    public void updateResetPasswordToken(String token,String username) throws UsernameNotFoundException {
    	User user=userRepository.findByUsername(username);
    	
    	if(user!= null) {
    		user.setResetPasswordToken(token);
    		userRepository.save(user);
    	}else 
    	{
    		throw new UsernameNotFoundException("Olmadı be" +username);
    	}
    }
    public User getByResetPasswordToken(String resetPasswordToken) {
    	return userRepository.findByResetPasswordToken(resetPasswordToken);
    }
    public void updatePassword(String newPassword, User user) {
    	BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
    	String encodePassword=passwordEncoder.encode(newPassword);
    	
    	user.setPassword(encodePassword);
    	user.setResetPasswordToken(null);
    	
    	userRepository.save(user);
    	
    }
    @Override
	public List<User> getAllUser() {
		return (List<User>) userRepository.findAll();
	}
    @Override
    public void findByCompletedAndUserId(Model model) {

        User currentUser = getCurrentUser();

      

        model.addAttribute("firstName", currentUser.getFirstName());
    }
    public User getCurrentUser() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        return userRepository.findByUsername(auth.getName());
    }

}