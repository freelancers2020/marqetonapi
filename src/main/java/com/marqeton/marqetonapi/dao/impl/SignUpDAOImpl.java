package com.marqeton.marqetonapi.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.marqeton.marqetonapi.dao.SignUpDAO;
import com.marqeton.marqetonapi.model.User;
import com.marqeton.marqetonapi.repository.UserRepository;

@Repository("SignUpRepository")
public class SignUpDAOImpl implements SignUpDAO {

	@Autowired
	UserRepository userRepository;

	@Override
	public User verifyUsername(String username) {
		return userRepository.findDistinctByUsernameOrEmailOrMobile(username, username, username);
	}

	@Override
	public User normalSignUp(User user) {
		return userRepository.save(user);
	}

}
