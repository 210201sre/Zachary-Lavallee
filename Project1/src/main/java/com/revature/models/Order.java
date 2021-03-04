package com.revature.models;

import java.util.Objects;

public class Order {
	private int orderId;
	private int userId;
	private String title;
	private String author;
	
	
	public Order() {
		super();
	}

	public Order(int orderId, int userId, String title, String author) {
		super();
		this.orderId = orderId;
		this.userId = userId;
		this.title = title;
		this.author = author;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Override
	public int hashCode() {
		return Objects.hash(author, orderId, title, userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Order)) {
			return false;
		}
		Order other = (Order) obj;
		return Objects.equals(author, other.author) && orderId == other.orderId && Objects.equals(title, other.title)
				&& userId == other.userId;
	}

	@Override
	public String toString() {
		return "order [orderId=" + orderId + ", userId=" + userId + ", title=" + title + ", author=" + author + "]";
	}
	
	
}
