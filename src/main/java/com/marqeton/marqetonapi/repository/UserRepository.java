package com.marqeton.marqetonapi.repository;

import org.springframework.data.repository.CrudRepository;

import com.marqeton.marqetonapi.model.User;

public interface UserRepository extends CrudRepository<User, Long>  {
	
	User findDistinctByUsernameOrEmailOrMobile(String username, String email, String mobile);

	User findDistinctByUsernameOrEmail(String username, String username2);

	User findDistinctByUsernameOrMobile(String username, String username2);

}
