package com.revature.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.annotations.Authorized;
import com.revature.models.Book;
import com.revature.models.Role;
import com.revature.services.BookService;

@RestController
@RequestMapping("/books")
public class BookController {
	
	@Autowired
	private BookService bookService;

	@Authorized(allowedRoles = {Role.Librarian})
	@PostMapping
	public ResponseEntity<Book> insert(@RequestBody Book b){
		return ResponseEntity.ok(bookService.insert(b));
	}
	@Authorized(allowedRoles = {Role.Librarian, Role.Customer})
	@GetMapping
	public ResponseEntity<List<Book>> findAll(){
		return ResponseEntity.ok(bookService.findAll());
	}
	@Authorized(allowedRoles = {Role.Librarian})
	@GetMapping(path = "/{bookId}")
	public ResponseEntity<Book> findById(@PathVariable int bookId) {
		return ResponseEntity.ok(bookService.findById(bookId));
	}
	@Authorized(allowedRoles = {Role.Librarian})
	@DeleteMapping(path ="/{bookId}")
	public ResponseEntity<String> deleteBook(@PathVariable int bookId){
		if(bookService.deleteById(bookId)) {
			return ResponseEntity.ok("Book deleted");
		}
		return ResponseEntity.notFound().build();
	}
}
