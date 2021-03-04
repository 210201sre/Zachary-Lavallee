package com.revature.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.User;
import com.revature.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping
	public ResponseEntity<List<User>> findAll(){
		return ResponseEntity.ok(userService.findAll());
	}
	@PostMapping
	public ResponseEntity<User> insert(@RequestBody User u){
		return ResponseEntity.ok(userService.insert(u));
	}
	@GetMapping(path = "/{userId}")
	public ResponseEntity<User> findById(@PathVariable int userId) {
		return ResponseEntity.ok(userService.findById(userId));
	}
	@DeleteMapping(path ="/{userId}")
	public ResponseEntity<String> deleteUser(@PathVariable int userId){
		if(userService.deleteById(userId)) {
			return ResponseEntity.ok("User deleted");
		}
		return ResponseEntity.notFound().build();
	}
}
