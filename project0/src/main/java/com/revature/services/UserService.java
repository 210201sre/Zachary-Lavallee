package com.revature.services;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import com.revature.exceptions.RegisterUserFailedException;
import com.revature.models.Book;
import com.revature.models.User;
import com.revature.repositories.IUserDAO;
import com.revature.repositories.UserDAO;
import com.revature.repositories.BookDAO;
import com.revature.repositories.IBookDAO;

public class UserService {

	private IUserDAO userDAO = new UserDAO();
	private IBookDAO bookDAO = new BookDAO();
	private static final Logger log = LoggerFactory.getLogger(UserService.class);
	
	public User createUser(User u) {
		
		MDC.put("event", "Register");
		log.info("Registering new User");
		
		if(u.getId() != 0) {
			throw new RegisterUserFailedException("Received User object did not have ID = 0");
		}

		int generatedId = userDAO.insert(u);
		
		if(generatedId != -1 && generatedId != u.getId()) {
			u.setId(generatedId);
		} else {
			throw new RegisterUserFailedException("Failed to insert the User record");
		}
		
		
		MDC.put("userId", Integer.toString(u.getId()));
		log.info("Successfully registered User");
		
		return u;
	}
	
	public Book register(Book b) {

		MDC.put("event", "Register");
		log.info("Registering new Book");
		
		if(b.getBookId() != 0) {
			throw new RegisterUserFailedException("Received User object did not have ID = 0");
		}

		int generatedBookId = bookDAO.insert(b);
		
		if(generatedBookId != -1 && generatedBookId != b.getBookId()) {
			b.setBookId(generatedBookId);
		} else {
			throw new RegisterUserFailedException("Failed to insert the User record");
		}
		
		
		MDC.put("userId", Integer.toString(b.getBookId()));
		log.info("Successfully registered User");
		
		return b;
	}
	
	public List<User> getAllUsers() {
		MDC.put("event", "Retrieving All Users...");
		log.info("Retrieving All Users");
		return userDAO.findAll();
	}
	
	public int insert(User u){
		return userDAO.insert(u);
		
	}

	public List<Book> getAllBooks() {
		return getAllBooks();
	}
	
	public boolean deleteBook(int id) {
		return false;
	}
	public LocalDate returnBook() {
		return returnBook();
		
	}
//	public LocalDate updateDueDate() {
//		return updateDueDate();
//		
//	}
//	public double payBalance() {
//		return payBalance();
//	}
//	
}