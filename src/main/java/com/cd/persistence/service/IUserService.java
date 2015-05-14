package com.cd.persistence.service;

import com.cd.persistence.model.User;
import com.cd.validation.EmailExistsException;

public interface IUserService {
	User registerNewUserAccount(AccountDto accountDto) throws EmailExistsException;
	
	void save(User user);
	
	void delete(Long id);
	void delete(User user);
	
	User findById(Long id);
	User findByEmail(String email);
}
