package com.cd.persistence.dao;

import org.springframework.data.repository.CrudRepository;

import com.cd.persistence.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
	public User findByEmail(String email);
	public User findById(Long id);
}