package com.revature.models;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

// Should have a field for every column in the table of the DB
// (for the most part, there are some occasional exceptions)

// This is actually the Java Bean Design Pattern
public class Account implements Serializable {

	private static final long serialVersionUID = 7382850360365866259L;

	private int id;
	private double balance;
	private List<Book> books;
	
	public Account() {
		super();
	}

	public Account(int id, double balance, List<Book> books) {
		super();
		this.id = id;
		this.balance = balance;
		this.books = books;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(balance, books, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Account)) {
			return false;
		}
		Account other = (Account) obj;
		return Double.doubleToLongBits(balance) == Double.doubleToLongBits(other.balance)
				&& Objects.equals(books, other.books) && id == other.id;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", balance=" + balance + ", books=" + books + "]";
	}

	

	
	
}

	