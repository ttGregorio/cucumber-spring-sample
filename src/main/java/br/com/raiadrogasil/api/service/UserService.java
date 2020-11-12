package br.com.raiadrogasil.api.service;

import java.util.List;
import java.util.Optional;

import br.com.raiadrogasil.api.entity.User;

public interface UserService {

	List<User> findAll();

	Optional<User> findById(Long id);

	User createUpdate(User user);

	void delete(Long id);

}