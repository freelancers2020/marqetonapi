package com.marqeton.marqetonapi.dao;

import com.marqeton.marqetonapi.model.User;

public interface LoginDAO {
	User authenticate(String username);
	
}
