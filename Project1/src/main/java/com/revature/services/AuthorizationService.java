package com.revature.services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.exceptions.NotAuthorizedException;
import com.revature.exceptions.NotLoggedInException;
import com.revature.models.Role;
import com.revature.models.User;

@Service
public class AuthorizationService {

	@Autowired
	private HttpServletRequest req;
	
	
	public void guardByUserId(int userId) {
		
		HttpSession session = req.getSession(false);

		if(session == null || session.getAttribute("currentUser") == null) {
			throw new NotLoggedInException("Must be logged in to perform this action");
		}
		
		User currentUser = (User) session.getAttribute("currentUser");
		
		if(userId != currentUser.getId()) {
			if(currentUser.getRole() != Role.Librarian) {
				throw new NotAuthorizedException("You are not permitted to perform this action on this resource");
			}
		}
	}
}