package com.revature.models;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Book{
	private int id;
	private String title;
	private List<Author> authors;
	private LocalDate checkOutDate;
	private LocalDate dueDate;
	
	public Book() {
		super();
	}

	public Book(int bookId, String title) {
		super();
		this.id = bookId;
		this.title = title;
	}
	
	public Book(int bookId, String title,List<Author> authors) {
		super();
		this.id = bookId;
		this.title = title;
		this.authors = authors;
	}

	public Book(int bookId, String title, List<Author> authors, LocalDate checkOutDate, LocalDate dueDate) {
		super();
		this.id = bookId;
		this.title = title;
		this.authors = authors;
		this.checkOutDate = checkOutDate;
		this.dueDate = dueDate;
	}
	
	public int getBookId() {
		return id;
	}

	public void setBookId(int bookId) {
		this.id = bookId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}

	public LocalDate getCheckOutDate() {
		return checkOutDate;
	}

	public void setCheckOutDate(LocalDate checkOutDate) {
		this.checkOutDate = checkOutDate;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	@Override
	public int hashCode() {
		return Objects.hash(authors, id, checkOutDate, dueDate, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Book)) {
			return false;
		}
		Book other = (Book) obj;
		return Objects.equals(authors, other.authors) && id == other.id
				&& Objects.equals(checkOutDate, other.checkOutDate) && Objects.equals(dueDate, other.dueDate)
				&& Objects.equals(title, other.title);
	}

	@Override
	public String toString() {
		return "Book [bookId=" + id + ", title=" + title + ", authors=" + authors + ", checkOutDate=" + checkOutDate
				+ ", dueDate=" + dueDate + "]";
	}



	
	
}