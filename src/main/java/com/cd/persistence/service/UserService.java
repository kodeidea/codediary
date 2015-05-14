package com.cd.persistence.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.cd.persistence.dao.RoleRepository;
import com.cd.persistence.dao.UserRepository;
import com.cd.persistence.model.User;
import com.cd.validation.EmailExistsException;

@Repository
@Transactional(isolation = Isolation.DEFAULT)
public class UserService implements IUserService {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public void save(User user) {
		userRepository.save(user);
	}

	@Override
	public void delete(User user) {
		userRepository.delete(user);
	}

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public User findById(Long id) {
		return userRepository.findById(id);
	}

	@Override
	public void delete(Long id) {
		userRepository.delete(id);
	}

	public User registerNewUserAccount(final AccountDto accountDto)
			throws EmailExistsException {
		if (emailExist(accountDto.getEmail())) {
			throw new EmailExistsException(
					"There is an account with that email adress: "
							+ accountDto.getEmail());
		}

		final User user = new User();
		user.setPassword(passwordEncoder.encode(accountDto.getPassword()));
		user.setEmail(accountDto.getEmail());
		user.setUsername(accountDto.getUsername());

		user.setRoles(Arrays.asList(roleRepository.findByName("ROLE_USER")));
		return userRepository.save(user);
	}

	private boolean emailExist(final String email) {
		final User user = userRepository.findByEmail(email);
		if (user != null) {
			return true;
		}
		return false;
	}

}
