package com.revature.models;

import java.util.Objects;

public class Author {
	
	private int id;
	private String fName;
	private String lName;
	
	public Author() {
		super();
	}

	public Author(int id, String fName, String lName) {
		super();
		this.id = id;
		this.fName = fName;
		this.lName = lName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	@Override
	public int hashCode() {
		return Objects.hash(fName, id, lName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Author)) {
			return false;
		}
		Author other = (Author) obj;
		return Objects.equals(fName, other.fName) && id == other.id && Objects.equals(lName, other.lName);
	}

	@Override
	public String toString() {
		return "Author [id=" + id + ", fName=" + fName + ", lName=" + lName + "]";
	}

	
	
}
