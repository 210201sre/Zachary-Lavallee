package com.revature.models;

import java.util.List;
import java.util.Objects;

import org.eclipse.jetty.util.security.Password;

public class User {
	
	private int id;
	private String username;
	private String password;
	private Role role;
	private List<Account> books;
	
	public User() {
		super();
	}

	
	public User(int id, String username, String password, Role role) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.role = role;
	}


	public User(int id, String username, String password, Role role, List<Account> books) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.role = role;
		this.books = books;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<Account> getBooks() {
		return books;
	}

	public void setBooks(List<Account> books) {
		this.books = books;
	}

	@Override
	public int hashCode() {
		return Objects.hash(books, id, password, role, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof User)) {
			return false;
		}
		User other = (User) obj;
		return Objects.equals(books, other.books) && id == other.id && Objects.equals(password, other.password)
				&& role == other.role && Objects.equals(username, other.username);
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", role=" + role + ", books="
				+ books + "]";
	}


	
}
