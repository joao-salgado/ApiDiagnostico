package com.diagnostico.api.resource;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diagnostico.api.event.ResourceCreatedEvent;
import com.diagnostico.api.model.UserAccount;
import com.diagnostico.api.repository.UserAccountRepository;
import com.diagnostico.api.service.UserAccountService;

@RestController
@RequestMapping("/users")
public class UserAccountResource {
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private UserAccountService userService;
	
	@Autowired
	private UserAccountRepository userRepository;

	@GetMapping
	public List<UserAccount> findAll() {
		return userRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserAccount> findByid(@PathVariable UUID id) {
		
		Optional<UserAccount> user = userRepository.findById(id);
		if (!user.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(user.get());
	}
	
	@PostMapping
	public ResponseEntity<?> create(@Valid @RequestBody UserAccount user, HttpServletRequest request,
			HttpServletResponse response) {
		
		UserAccount userSaved = userService.createByCode(user);
		publisher.publishEvent(new ResourceCreatedEvent(this, response, userSaved.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(userSaved);

	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable UUID id, @Valid @RequestBody UserAccount user) {
		
		UserAccount userSaved = userService.update(id, user);
		return ResponseEntity.ok(userSaved);
	}

}
