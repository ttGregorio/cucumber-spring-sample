package br.com.raiadrogasil.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.raiadrogasil.api.entity.User;
import br.com.raiadrogasil.api.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping
	public ResponseEntity<List<User>> listAll() {
		return ResponseEntity.ok(userService.findAll());
	}

	@GetMapping(value = "{id}")
	public ResponseEntity<User> findById(@PathVariable Long id) {
		Optional<User> userFinded = userService.findById(id);
		if (userFinded.isPresent()) {
			return ResponseEntity.ok(userFinded.get());
		} else {
			return ResponseEntity.ok(null);
		}
	}

	@DeleteMapping(value = "{id}")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		Optional<User> optionalUser = userService.findById(id);

		if (optionalUser.isPresent()) {
			userService.delete(id);
			return ResponseEntity.ok("User ".concat(id.toString()).concat(" deleted"));
		} else {
			return ResponseEntity.ok("User not found");
		}
	}

	@PostMapping
	public ResponseEntity<User> create(@RequestBody User user, BindingResult result) {
		validateCreateItemOptions(user, result);
		if (result.hasErrors()) {
			return ResponseEntity.badRequest().body(null);
		} else {
			return ResponseEntity.ok(userService.createUpdate(user));
		}
	}

	@PutMapping(value = "{id}")
	public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User user, BindingResult result) {
		validateUpdateItemOptions(user, result);
		if (id.equals(user.getId()) && !result.hasErrors()) {
			return ResponseEntity.ok(userService.createUpdate(user));
		} else {
			return ResponseEntity.badRequest().body(null);
		}
	}

	private void validateCreateItemOptions(User user, BindingResult result) {
		if (user.getName() == null || user.getName().isBlank()) {
			result.addError(new ObjectError("User", "nameNotInformed"));
		}
		if (user.getEmail() == null || user.getEmail().isBlank()) {
			result.addError(new ObjectError("User", "emailNotInformed"));
		}
	}

	private void validateUpdateItemOptions(User user, BindingResult result) {
		if (user.getName() == null || user.getName().isBlank()) {
			result.addError(new ObjectError("User", "nameNotInformed"));
		}
		if (user.getEmail() == null || user.getEmail().isBlank()) {
			result.addError(new ObjectError("User", "emailNotInformed"));
		}
		if (user.getId() == null) {
			result.addError(new ObjectError("User", "idNotInformed"));
		}
	}
}
