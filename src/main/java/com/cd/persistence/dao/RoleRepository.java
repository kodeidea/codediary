package com.cd.persistence.dao;

import org.springframework.data.repository.CrudRepository;

import com.cd.persistence.model.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {
	public Role findByName(String name);
	 public void delete(Role role);
}