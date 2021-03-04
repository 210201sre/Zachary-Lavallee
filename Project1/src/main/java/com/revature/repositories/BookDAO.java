package com.revature.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.revature.models.Book;

public interface BookDAO extends JpaRepository<Book, Integer> {

}
