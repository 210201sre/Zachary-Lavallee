package com.revature.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jboss.logging.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.exceptions.UserNotFoundException;
import com.revature.models.Book;
import com.revature.models.User;
import com.revature.repositories.BookDAO;
import com.revature.repositories.UserDAO;

@Service 
public class UserService {
	
	private static final Logger log = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private BookDAO bookDAO;
	
	@Autowired
	private HttpServletRequest req;
	
	public User insert(User u) { //Create User
		MDC.put("insert", u);
		log.info("Inserting new User");
		MDC.clear();
		return userDAO.save(u);
	}
	public List<User> findAll() { //Get All Users
		MDC.put("findAll", "users");
		log.info("Retrieving All Users");
		MDC.clear();
		return userDAO.findAll();
	}
	public User findById(int userId) {
		MDC.put("findById", userId);
		log.info("Finding User by Id");
		MDC.clear();
		return userDAO.findById(userId).orElse(null);
	}
	public boolean deleteById(int userId) {
		Optional<User> u = userDAO.findById(userId);
		MDC.put("delete", userId);
		log.info("Deleting User");
		if(u.isPresent()) {
			try {
				userDAO.deleteById(userId);
				log.info("User Deleted");
				MDC.clear();
				return true;
			} catch(IllegalArgumentException e) {
				log.info("Failed to delete user");
				MDC.clear();
				return false;
			}
		}
		log.info("User Not Found");
		MDC.clear();
		return false;
	}
	public User findByUsername(String username) {
		MDC.put("username", username);
		Optional<User> u = userDAO.findByUsername(username);
		if(u.isPresent()) {
			log.info("Found user");
			MDC.clear();
			return u.get();
		}
		log.error("Unable to find user");
		MDC.clear();
		return null;
	}
	public User checkOut(int id, int bookId) {
		MDC.put("checkout", bookId);
		Book b = bookDAO.findById(bookId).get();
		User u = userDAO.findById(id).get();
		
		if (u.getBooks() == null) {
			List<Book> nl = new ArrayList<>();
			nl.add(b);
			u.setBooks(nl);
		}
		u.getBooks().add(b);
		b.setUsers(u);
		userDAO.save(u);
		bookDAO.save(b);
		log.error("Book successfully checked out");
		MDC.clear();
		return u;
	}
	public User transfer(int id, int id2, int bookId){
		MDC.put("Transfer", bookId);
		Book b = bookDAO.findById(bookId).get();
		User u = userDAO.findById(id).get();
		User u2 = userDAO.findById(id2).get();
		
		u.getBooks().remove(b);
		u2.getBooks().add(b);
		b.setUsers(u2);
		
		userDAO.save(u);
		userDAO.save(u2);
		bookDAO.save(b);
		
		log.error("Book successfully transfered");
		MDC.clear();
		
		return u;
	}
	public User login(String username, String password) {
		MDC.put("login", username);
		User u = userDAO.findByUsername(username)
							.orElseThrow(() -> new UserNotFoundException(String.format("No User with username = %s", username)));
		if(u.getPassword().equals(password)) {
			HttpSession session = req.getSession();
			session.setAttribute("currentUser", u);
			log.info("User successfully logged in");
			MDC.clear();
			return u;
		} 
		MDC.put("wrongPassword", password);
		log.error("Incorrect password");
		MDC.clear();
		return null;
	}
	public void logout() {
		HttpSession session = req.getSession(false);
		if(session == null) {
			return;
		}
		session.invalidate();
		log.info("Logged out");
		MDC.clear();
	}

	
}
