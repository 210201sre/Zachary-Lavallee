package com.revature.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.revature.models.Book;

public interface BookDAO extends JpaRepository<Book, Integer> {
	public Optional<Book> findById(int bookId);
}
