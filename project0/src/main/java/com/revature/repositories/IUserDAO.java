package com.revature.repositories;

import java.util.List;

import com.revature.models.Account;
import com.revature.models.Book;
import com.revature.models.User;

// Standard CRUD operations as well as anything you might find useful
public interface IUserDAO {

	public List<User> findAll();
	public int insert(User u); 
	public boolean update(User u); 
	public boolean delete(int userId);
}