package com.metronicproject.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.metronicproject.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);
    User findByverifyKey(String verifyKey);
   // User update(User user);
    public User findByResetPasswordToken(String token);
    User findByPhone(String phone);
	  User findByTokensms(String  tokensms);
	  User findByLoginsms(String loginsms);
	  
}