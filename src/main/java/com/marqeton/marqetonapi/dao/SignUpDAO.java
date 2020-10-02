package com.marqeton.marqetonapi.dao;

import com.marqeton.marqetonapi.model.User;

public interface SignUpDAO {

	User verifyUsername(String username);

	User normalSignUp(User user);

}
