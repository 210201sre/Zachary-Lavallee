package com.revature.services;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jboss.logging.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.User;
import com.revature.repositories.UserDAO;



@Service 
public class UserService {
	
	private static final Logger log = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private HttpServletRequest req;
	
	public User insert(User u) { //Create User
		return userDAO.save(u);
	}
	public List<User> findAll() { //Get All Users
		return userDAO.findAll();
	}
	public User findById(int userId) {
		return userDAO.findById(userId).orElse(null);
	}
	public boolean deleteById(int userId) {
		Optional<User> u = userDAO.findById(userId);
		
		if(u.isPresent()) {
			try {
				userDAO.deleteById(userId);
				return true;
			} catch(IllegalArgumentException e) {
				return false;
			}
		}
		return false;
	}

	
}
