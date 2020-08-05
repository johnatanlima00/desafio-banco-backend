package com.johnatanlima.bancocapgemini.services;

import org.springframework.security.core.context.SecurityContextHolder;

import com.johnatanlima.bancocapgemini.security.jwt.UserSS;

public class UserService {
	
	public static UserSS authenticated() {
		try {
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}
		catch (Exception e) {
			return null;
		}
	}
}
