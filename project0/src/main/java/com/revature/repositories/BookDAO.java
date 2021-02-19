package com.revature.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import com.revature.exceptions.RegisterUserFailedException;
import com.revature.models.Author;
import com.revature.models.Book;
import com.revature.util.ConnectionUtil;

public class BookDAO implements IBookDAO {
	
	private static final Logger log = LoggerFactory.getLogger(BookDAO.class);

	@Override
	public List<Book> findAll() {
		
		List<Book> allBooks = new ArrayList<>();

		try (Connection conn = ConnectionUtil.getConnection()) {
			
			Statement stmt = conn.createStatement();
			String sql = "SELECT * FROM project0.Books";
			
			ResultSet rs = stmt.executeQuery(sql);
			stmt.close();
			
			ResultSetMetaData metaData = rs.getMetaData();
		    int columns = metaData.getColumnCount();
		    List<Author> authors = new ArrayList<>(columns);
			
			while(rs.next()) {
				
				int id = rs.getInt("bookId");
				String title = rs.getString("title");
				for (int i = 1; i <= columns; i++)
					authors.add((Author)rs.getObject(i));
				LocalDate checkOutDate = (LocalDate) rs.getObject("checkOutDate");
				LocalDate dueDate = (LocalDate) rs.getObject("dueDate");
				
				if(id == 0) {
					allBooks.add(new Book(id, title, authors, checkOutDate, dueDate));
				}else { 
					Book b = new Book(id, title, authors, checkOutDate, dueDate);
					allBooks.add(b);
				}	
			}
		} catch(SQLException e) {
			log.error("We failed to retrieve all Books", e);
			return new ArrayList<>(); 
		}
		return allBooks;
	}

	
	@Override
	public int insert(Book b) {
		
		try(Connection conn = ConnectionUtil.getConnection()) {
			
			String sql = "INSERT INTO project0.books (title, authors, checkOutDate, dueDate) "
					+ "VALUES (?, ?, ?, ?) RETURNING project0.books.id";
			
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setString(1, b.getTitle());
			stmt.setObject(2, b.getAuthors());
			stmt.setObject(3, b.getCheckOutDate());
			stmt.setObject(4, b.getDueDate());
			
			ResultSet rs;
			
			if( (rs = stmt.executeQuery()) != null) {
				rs.next();
				int id = rs.getInt(1);
				
				return id;
			}
			
		} catch (SQLException e) {
			log.error("We failed to insert a new user", e);
			return -1;
		}
		return -1;
	}
	@Override
	public int add(Book a) {
		return 0;
	}

	@Override
	public boolean update(Book a) {
		return false;
	}

	@Override
	public boolean delete(int bookId) {
		return false;
	}
	
	public void returnBook() {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "select project0.books.dueDate, project0.books.checkOutDate FROM project0.books";

			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setString(1, "");

			ResultSet rs;

			if ((rs = stmt.executeQuery()) != null) {
				rs.next();

			}
		} catch (SQLException e) {
			log.error("We failed to update dueDate", e);
		}
		
	}

}