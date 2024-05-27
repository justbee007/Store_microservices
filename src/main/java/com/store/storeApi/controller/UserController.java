package com.store.storeApi.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.store.storeApi.user.User;
//import com.store.storeApi.user.UserDao;
import com.store.storeApi.user.UserNotFoundException;
import com.store.storeApi.user.UserRepository;

import jakarta.validation.Valid;

@RestController
public class UserController {

//	private UserDao service;
	private UserRepository userrepository;
	
	public UserController(UserRepository userrepository)
	{
//		this.service = service;
		this.userrepository = userrepository;
	}
	
	@GetMapping("/users")
	public List<User> retrieveAllUsers()
	
	{
		List<User> users = userrepository.findAll();
		return users;
		
	}
	
	@GetMapping("/users/{id}")
	public User retrieveUsers(@PathVariable int id)
	
	{
		Optional<User> user = userrepository.findById(id);
//		User user = service.findOne(id);
		if(user== null)
		{
			throw new UserNotFoundException("Id:"+id);
		}
		return user.get();
	}
	
	@PostMapping("/users")
	public ResponseEntity<User> adduser(@Valid @RequestBody User user)
	{
		
		User savedUser = userrepository.save(user);
//		User savedUser = service.addUser(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id)
	{
		userrepository.deleteById(id);
	}
}
