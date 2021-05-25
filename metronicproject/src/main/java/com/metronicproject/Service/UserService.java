package com.metronicproject.Service;

import java.util.List;

import org.springframework.ui.Model;

import com.metronicproject.model.User;

public interface UserService {

    void save(User user);
	List < User > getAllUser();
	   void findByCompletedAndUserId(Model model);
    User findByUsername(String username);
     public boolean findByverifyKey(String verifyKey); 
    public void updateResetPasswordToken(String token,String username);
    public void updatePassword(String password,User user);
   User getByResetPasswordToken(String resetPasswordToken);

}