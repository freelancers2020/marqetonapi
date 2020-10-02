package com.marqeton.marqetonapi.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.marqeton.marqetonapi.dao.LoginDAO;
import com.marqeton.marqetonapi.model.User;

@Service("UserDetailsService")
@Qualifier("customer")
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	LoginDAO loginDAO;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = null;
		user = loginDAO.authenticate(username);
		String[] userRoles = user.getRoles().stream().map((role) -> role.getName()).filter(role -> "ROLE_CUSTOMER".equals(role)).toArray(String[]::new);
		
		if(user == null) {
			throw new UsernameNotFoundException(username);
		}
		
		if(userRoles == null) {
			throw new UsernameNotFoundException(username);
		}
		else if(userRoles.length == 0) {
			throw new UsernameNotFoundException(username);
		}
		return new org.springframework.security.core.userdetails.User(user.getMobile(),user.getPassword(), getAuthorities(user));
	}
	
	private static Collection<? extends GrantedAuthority> getAuthorities(User user) {
        String[] userRoles = user.getRoles().stream().map((role) -> role.getName()).toArray(String[]::new);
        Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(userRoles);
        return authorities;
    }

}
