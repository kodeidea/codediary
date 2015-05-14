package com.cd.persistence.dao;

import org.springframework.data.repository.CrudRepository;

import com.cd.persistence.model.Privilege;

public interface PrivilegeRepository extends CrudRepository<Privilege, Long>{
	public Privilege findByName(String name);
	public void delete(Privilege privilege);
}
