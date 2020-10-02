package com.marqeton.marqetonapi.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.marqeton.marqetonapi.dao.LoginDAO;
import com.marqeton.marqetonapi.model.User;
import com.marqeton.marqetonapi.repository.UserRepository;

@Repository("LoginRepository")
public class LoginDAOImpl implements LoginDAO {

	@Autowired
	UserRepository userRepository;
	
	@Override
	public User authenticate(String username) {
		return userRepository.findDistinctByUsernameOrEmailOrMobile(username, username,username);
	}

	

}
