package com.revature.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.annotations.Authorized;
import com.revature.models.Role;
import com.revature.models.User;
import com.revature.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Authorized(allowedRoles = {Role.Librarian})
	@GetMapping
	public ResponseEntity<List<User>> findAll(){
		return ResponseEntity.ok(userService.findAll());
	}
	@Authorized(allowedRoles = {Role.Librarian})
	@PostMapping
	public ResponseEntity<User> insert(@RequestBody User u){
		return ResponseEntity.ok(userService.insert(u));
	}
	@Authorized(allowedRoles = {Role.Librarian})
	@GetMapping(path = "/{userId}")
	public ResponseEntity<User> findById(@PathVariable int userId) {
		return ResponseEntity.ok(userService.findById(userId));
	}
	
	@Authorized(allowedRoles = {Role.Librarian})
	@DeleteMapping(path ="/{userId}")
	public ResponseEntity<String> deleteUser(@PathVariable int userId){
		if(userService.deleteById(userId)) {
			return ResponseEntity.ok("User deleted");
		}
		return ResponseEntity.notFound().build();
	}
	
	@Authorized(allowedRoles = {Role.Librarian, Role.Customer})
	@PostMapping(path ="/{userId}/{bookId}")
	public ResponseEntity<User> checkOut(@PathVariable("userId") int userId, @PathVariable("bookId") int bookId){
		return ResponseEntity.ok(userService.checkOut(userId,bookId));
	}
	
	@Authorized(allowedRoles = {Role.Librarian, Role.Customer})
	@PutMapping(path ="/{userId}/{userId2}/{bookId}")
	public ResponseEntity<User> transfer(@PathVariable("userId") int userId,@PathVariable("userId2") int userId2, @PathVariable("bookId") int bookId){
		return ResponseEntity.ok(userService.transfer(userId,userId2,bookId));
	}
	
}
