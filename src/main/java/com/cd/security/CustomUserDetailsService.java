package com.cd.security;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cd.persistence.dao.RoleRepository;
import com.cd.persistence.dao.UserRepository;
import com.cd.persistence.model.Privilege;
import com.cd.persistence.model.Role;
import com.cd.persistence.model.User;

@Service("userDetailsService")
@Transactional
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {
		try {
			final User user = userRepository.findByEmail(email);
			
			if(user == null) {
				return new  org.springframework.security.core.userdetails.User("", "", true, true, true, true, getAuthorities(Arrays.asList(roleRepository.findByName("ROLE_USER"))));
			}
			return new  org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), true, true, true, true, getAuthorities(Arrays.asList(roleRepository.findByName("ROLE_USER"))));
		} catch(final Exception e) {
			throw new RuntimeException(e);
		}
		
	}

	private Collection<? extends GrantedAuthority> getAuthorities(
			final List<Role> roles) {
		return getGrantedAuthorities(getPrivileges(roles));
	}

	private List<String> getPrivileges(final List<Role> roles) {
		final List<String> privileges = new ArrayList<String>();
		final List<Privilege> collection = new ArrayList<Privilege>();
		
		for (Role role : roles) {
			collection.addAll(role.getPrivileges());
		}
		
		for (final Privilege privilege : collection) {
			privileges.add(privilege.getName());
		}
		
		return privileges;
	}
	
	private Collection<? extends GrantedAuthority> getGrantedAuthorities(
			List<String> privileges) {
		final List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		for (final String privilege : privileges) {
			authorities.add(new SimpleGrantedAuthority(privilege));
		}
		return authorities;
	}	


}
