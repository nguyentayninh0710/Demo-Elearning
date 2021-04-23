package com.myclass.until;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class CustomUserDetails extends User implements UserDetails {
	private static final long serialVersionUID = 1L;
	
	private String fullname ;
	
	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}


	public CustomUserDetails(String fullname, String password, Collection<? extends GrantedAuthority> authorities) {
		super(fullname, password, authorities);
		// TODO Auto-generated constructor stub
	}

}
