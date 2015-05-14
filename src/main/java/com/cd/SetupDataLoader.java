package com.cd;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.cd.persistence.dao.PrivilegeRepository;
import com.cd.persistence.dao.RoleRepository;
import com.cd.persistence.dao.UserRepository;
import com.cd.persistence.model.Privilege;
import com.cd.persistence.model.Role;
import com.cd.persistence.model.User;

public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {
	
	private boolean alreadySetup  = false;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PrivilegeRepository privilegRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		if(alreadySetup) {
			return;
		}
		
		final Privilege readPrivilege = createPrivilegeIfNotFound("READ_PRIVILEGE");
		final Privilege writePrivilege = createPrivilegeIfNotFound("WRITE_PRIVILEGE");
		final List<Privilege> adminPrivileges = Arrays.asList(readPrivilege, writePrivilege);
		
		final Role adminRole = createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
		createRoleIfNotFound("ROLE_USER", Arrays.asList(readPrivilege));
		
		User user = new User();
		user.setUsername("Test");
		user.setEmail("test@test.com");
		user.setPassword("test");
		user.setRoles(Arrays.asList(adminRole));
		userRepository.save(user);
		
		alreadySetup = true;
	}

	private Role createRoleIfNotFound(String name,
			List<Privilege> privileges) {
		Role role = roleRepository.findByName(name);
		if(role == null) {
			role = new Role(name);
			role.setPrivileges(privileges);
			roleRepository.save(role);
		}
		
		return role;
	}

	private Privilege createPrivilegeIfNotFound(String name) {
		Privilege privilege = privilegRepository.findByName(name);
		if(privilege == null) {
			privilege = new Privilege(name);
			privilegRepository.save(privilege);
		}
		return privilege;
	}
	
}
