package com.marqeton.marqetonapi.service;

import com.marqeton.marqetonapi.model.User;
import com.marqeton.marqetonapi.payload.SignUpPayload;

public interface SignUpService {

	Boolean verifyUsername(String username, String type);
	
	Boolean verifyUsername(String username);

	SignUpPayload normalSignUp(SignUpPayload signUpPayload);

}
