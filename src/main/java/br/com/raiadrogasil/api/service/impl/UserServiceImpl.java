package br.com.raiadrogasil.api.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.raiadrogasil.api.entity.User;
import br.com.raiadrogasil.api.repository.UserRepository;
import br.com.raiadrogasil.api.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public Optional<User> findById(Long id) {
		return userRepository.findById(id);
	}

	@Override
	public User createUpdate(User user) {
		return userRepository.save(user);
	}

	@Override
	public void delete(Long id) {
		userRepository.deleteById(id);
	}
}
