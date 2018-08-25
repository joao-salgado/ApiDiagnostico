package com.diagnostico.api.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diagnostico.api.model.UserType;
import com.diagnostico.api.repository.UserTypeRepository;

@RestController
@RequestMapping("/user-types")
public class UserTypeResource {
	
	@Autowired
	private UserTypeRepository typeRepository;
	
	@GetMapping
	public List<UserType> findAll() {
		return typeRepository.findAll();
	}

}
