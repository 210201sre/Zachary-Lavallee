package com.revature.services;

import java.util.List;
import java.util.Optional;

import org.jboss.logging.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.Book;
import com.revature.repositories.BookDAO;

@Service
public class BookService {
	
	private static final Logger log = LoggerFactory.getLogger(BookService.class);
	
	@Autowired
	private BookDAO bookDAO;

	public Book insert(Book b) {
		MDC.put("insert", b);
		log.info("Inserting new Book");
		MDC.clear();
		return bookDAO.save(b);
	}
	public List<Book> findAll() { 
		MDC.put("findAll", "books");
		log.info("Retrieving All Books");
		MDC.clear();
		return bookDAO.findAll();
	}
	public Book findById(int bookId) {
		MDC.put("findById", bookId);
		log.info("Finding Book by Id");
		MDC.clear();
		return bookDAO.findById(bookId).orElse(null);
	}
	public boolean deleteById(int bookId) {
		Optional<Book> b = bookDAO.findById(bookId);
		MDC.put("delete", bookId);
		log.info("Deleting Book");
		if(b.isPresent()) {
			try {
				bookDAO.deleteById(bookId);
				log.info("Book Deleted");
				MDC.clear();
				return true;
			} catch(IllegalArgumentException e) {
				log.info("Failed to delete book");
				MDC.clear();
				return false;
			}
		}
		log.info("Book Not Found");
		MDC.clear();
		return false;
	}

}
