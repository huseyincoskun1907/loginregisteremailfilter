package com.metronicproject.config;



import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.web.AuthorizationRequestRepository;
import org.springframework.security.oauth2.client.web.HttpSessionOAuth2AuthorizationRequestRepository;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import com.metronicproject.Repository.UserRepository;
import com.metronicproject.model.User;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    @Qualifier("customUserDetailsService")
    UserDetailsService userDetailsService;
    @Autowired
    private OidcUserService oidcUserService;
    
//    @Autowired
//    private CustomLogoutSuccesHandler logoutSuccessHandler;
   @Autowired
   UserRepository userRepository;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
        auth.authenticationProvider(authProvider());
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**");
        web.ignoring().antMatchers("/js/**");
        web.ignoring().antMatchers("/images/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                    .antMatchers("/", "/register", "/login","/forgot_password","/dashboard-2","/categoryDashboard").permitAll()
                    .antMatchers("/index").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
                    .antMatchers("/dashboard").access("hasRole('ROLE_ADMIN') ")
                    .antMatchers("/sms-login").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
                   
                .and()
                    .formLogin()
                    .loginPage("/login")
                    .defaultSuccessUrl("/sms-login",true).permitAll()
                    .and()
                    .logout()
                    .logoutSuccessHandler(new LogoutSuccessHandler() {
						
						@Override
						public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
								throws IOException, ServletException {
							User user=userRepository.findByUsername(authentication.getName());
							user.setLoginsmsEnabled(false);
							userRepository.save(user);
							  System.out.println("The user " + user + " has logged out.");
							  
			                    response.sendRedirect(request.getContextPath());
							
						}
					})
                    .permitAll()
                    .and()
                    .csrf().disable();
        http.rememberMe().userDetailsService(userDetailsService);   	
//		http.authorizeRequests().anyRequest().authenticated()
//        .and()
//        .oauth2Login().userInfoEndpoint().oidcUserService(oidcUserService).and().authorizationEndpoint()
//        .baseUri("/oauth2/authorize").authorizationRequestRepository(customAuthorizationRequestRepository());     
    }
    @Bean
	public AuthorizationRequestRepository customAuthorizationRequestRepository() {
		return new HttpSessionOAuth2AuthorizationRequestRepository();
	}
}