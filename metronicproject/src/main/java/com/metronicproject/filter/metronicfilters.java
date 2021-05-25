package com.metronicproject.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.metronicproject.Repository.RoleRepository;
import com.metronicproject.Repository.UserRepository;
import com.metronicproject.model.Role;
import com.metronicproject.model.User;

@Component
public class metronicfilters  implements Filter {

	@Autowired
	UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest) request;
		HttpServletResponse res=(HttpServletResponse) response;
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		User user=userRepository.findByUsername(auth.getName());
		Role role=roleRepository.findByRolename(auth.getName());
		
		if(req.getRequestURI().contains("sms")) {
			
			chain.doFilter(request, response);
			return;
		}
		
		if(req.getRequestURI().contains("sms-login")) {
			
				chain.doFilter(request, response);
				return;
			
		}
		if(req.getRequestURI().contains("/")) {
			
			
			chain.doFilter(request, response);
			return;
		
	}
		if(req.getRequestURI().contains("default")) {
			chain.doFilter(request, response);
			return;
	
		}
		if(req.getRequestURI().contains("admin")) {
			chain.doFilter(request, response);
			return;
	
		}
		if(req.getRequestURI().contains("sms")) {
			chain.doFilter(request, response);
			return;
	
		}
		
		if(req.getRequestURI().contains("index")) {
			
				chain.doFilter(request, response);
				return;
			
		}
		if(req.getRequestURI().contains("register")) {
			chain.doFilter(request, response);
			return;
		}
		if(req.getRequestURI().contains("forgot_password_form")) {
			chain.doFilter(request, response);
			return;
 
		}
		if(req.getRequestURI().contains("forgot_password")) {
			chain.doFilter(request, response);
			return;
 
		}
		if(req.getRequestURI().contains("reset_password_form")) {
			chain.doFilter(request, response);
			return;
		}
		if(req.getRequestURI().contains("new_product")) {
			chain.doFilter(request, response);
			return;
		}
		if(req.getRequestURI().contains("dashboard-2")) {
			chain.doFilter(request, response);
			return;
		}
		if(req.getRequestURI().contains("reset_password")) {
			chain.doFilter(request, response);
			return;
		}
		if(req.getRequestURI().contains("categoryDashboard")) {
			chain.doFilter(request, response);
			return;
		}
		if(req.getRequestURI().contains("update_category")) {
			chain.doFilter(request, response);
			return;
		}
		
		if(req.getRequestURI().contains("login")) {
			
				chain.doFilter(request, response);
				return;
			
		}
		if(req.getRequestURI().contains("assets")) {
			
			chain.doFilter(request, response);
			return;
		
	}
		if(!user.isOtpActive() || !user.isLoginsmsEnabled()) {
			res.sendRedirect("index");
			return;
		}
//		if(role.getRolename()=="ROLE_ADMİN") {
//			
//			res.sendRedirect("dashboard");
//			return;
//			
//		}
//		if(req.getRequestURI().contains("")) {
//			chain.doFilter(request, response);
//			return;
//		}
		
		
		
		
	     
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
