package br.com.raiadrogasil.api.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.raiadrogasil.api.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {

	public List<User> findAll();
}
