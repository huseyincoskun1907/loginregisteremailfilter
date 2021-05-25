package com.metronicproject.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.metronicproject.Repository.UserRepository;
import com.metronicproject.model.Role;
import com.metronicproject.model.User;

import java.util.List;
import java.util.stream.Collectors;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) {

    boolean active = true;
    boolean accountNonExpired = true;
    boolean credentialsNonExpired = true;
    boolean accountNonLocked = true;
    boolean smsEnabled=true;
    try {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(
              "No user found with username: " + username);
        }
        
        return new org.springframework.security.core.userdetails.User(
          user.getUsername(), 
          user.getPassword(),
          user.getActive(),
          user.isSmsEnabled(),
          accountNonExpired, 
          credentialsNonExpired, 
          buildUserAuthority(user.getRoles()));
    } catch (Exception e) {
        throw new RuntimeException(e);
    }
    }

    private List<GrantedAuthority> buildUserAuthority(List<Role> userRoles) {

        List<GrantedAuthority> authorities = userRoles.stream().map(userRole -> new SimpleGrantedAuthority(userRole.getRolename())).collect(Collectors.toList());

        return authorities;
    }

}