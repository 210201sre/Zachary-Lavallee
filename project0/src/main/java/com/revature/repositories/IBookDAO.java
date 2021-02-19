package com.revature.repositories;

import java.util.List;

import com.revature.models.Author;
import com.revature.models.Book;

public interface IBookDAO {

	public List<Book> findAll();
	public int insert(Book b);
	public int add(Book b);
	public boolean update(Book b);
	public boolean delete(int bookId);
}