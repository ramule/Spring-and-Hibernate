package com.niit.serviceimpl;

import javax.transaction.Transactional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.stereotype.Service;

import com.niit.Model.User;
import com.niit.Operations.UserOperations;

@Service("userDetailServices")
public class UserDetailsServiceIMpL implements UserDetailsService {
	UserOperations uo = new UserOperations();

	@Transactional()
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user1 = uo.displaybyName(username);
		UserBuilder builder = null;
		
		if(user1 != null) {
			builder = org.springframework.security.core.userdetails.User.withUsername(username);
			builder.disabled(!user1.isStatus());
		    builder.password(user1.getPassword());
		    String[] authorities = new String[]{user1.getAuthority()};
		    
		    builder.authorities(authorities);
		}
		
		else {
			throw new UsernameNotFoundException("User not found");
		}
		return builder.build();
	}
	
	
}
