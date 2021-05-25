package com.metronicproject.Service;

import javax.servlet.http.HttpServletRequest;

public class Utility {
	
	public static String getSiteURL(HttpServletRequest request) {
		String siteURL=request.getRequestURI().toString();
		return siteURL.replace(request.getServletPath(),"" );
	}

}
