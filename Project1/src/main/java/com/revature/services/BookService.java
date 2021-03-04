package com.revature.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.Book;
import com.revature.repositories.BookDAO;

@Service
public class BookService {
	
	@Autowired
	private BookDAO bookDAO;

	public Book insert(Book b) {
		return bookDAO.save(b);
	}

}
