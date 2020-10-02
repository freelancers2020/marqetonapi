package com.marqeton.marqetonapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.marqeton.marqetonapi.constants.RestUrlConstants;
import com.marqeton.marqetonapi.payload.SignUpPayload;
import com.marqeton.marqetonapi.service.SignUpService;

@RestController
public class SignUpController {

	@Autowired
	SignUpService signUpService;
	
	@CrossOrigin
	@RequestMapping(value = RestUrlConstants.CUST_USERS, method = RequestMethod.HEAD)
	public ResponseEntity verifyUsername(@RequestParam String username, @RequestParam String type ) {
		
		Boolean isExist = signUpService.verifyUsername(username, type);
		
		if(isExist) {
			return new ResponseEntity(HttpStatus.CONFLICT);
		}
		return new ResponseEntity(HttpStatus.OK);
	}
	
	@CrossOrigin
	@RequestMapping(value = RestUrlConstants.CUST_USERS, method = RequestMethod.POST)
	public ResponseEntity<SignUpPayload> normalSignUp(@RequestBody SignUpPayload signUpPayload) {
		
		SignUpPayload savedUser = signUpService.normalSignUp(signUpPayload);
		
		if(savedUser.getIsExist()) {
			return new ResponseEntity<SignUpPayload>(savedUser,HttpStatus.CONFLICT);
		}
		else {
			return new ResponseEntity<SignUpPayload>(savedUser,HttpStatus.CREATED);
		}
		
	}
}
