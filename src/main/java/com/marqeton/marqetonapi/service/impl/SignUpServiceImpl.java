package com.marqeton.marqetonapi.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.marqeton.marqetonapi.dao.SignUpDAO;
import com.marqeton.marqetonapi.model.User;
import com.marqeton.marqetonapi.payload.SignUpPayload;
import com.marqeton.marqetonapi.service.SignUpService;
import com.marqeton.marqetonapi.utility.EmailUtility;

@Service("SignUpService")
public class SignUpServiceImpl implements SignUpService {


	@Autowired
	SignUpDAO signUpDAO;

	@Autowired
	EmailUtility emailUtility;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public Boolean verifyUsername(String username, String type) {
		User user;

		user = signUpDAO.verifyUsername(username);

		if(user!=null) {
			return true;
		}
		if (type.equalsIgnoreCase("email") ) {
			emailUtility.sendEmail(username,"Araikya", "This is for testing");
		}
		return false;
	}

	@Override
	public SignUpPayload normalSignUp(SignUpPayload signUpPayload) {
		Boolean isExist = verifyUsername(signUpPayload.getUsername());

		if(!isExist) {

			User user = new User();
			user.setUsername(signUpPayload.getUsername());
			user.setPassword(bCryptPasswordEncoder.encode(signUpPayload.getPassword()));
			user.setName(signUpPayload.getName());
			user.setEmail(signUpPayload.getEmail());
			user.setMobile(signUpPayload.getMobile());
			user.setCreatedDate(new Date());
			user.setUpdatedDate(new Date());
			user.setStatus(1);
			User savedUser = signUpDAO.normalSignUp(user);

			if(savedUser != null ) {
				signUpPayload.setPassword(null);
				signUpPayload.setIsExist(isExist);
				signUpPayload.setMessage("user Registered Successfully");
			}
		}
		else {
			signUpPayload.setPassword(null);
			signUpPayload.setIsExist(isExist);
			signUpPayload.setMessage("User Already Exists");
		}

		return signUpPayload;
	}

	@Override
	public Boolean verifyUsername(String username) {
		User user;

		user = signUpDAO.verifyUsername(username);

		if(user!=null) {
			return true;
		}
		return false;
	}

}
