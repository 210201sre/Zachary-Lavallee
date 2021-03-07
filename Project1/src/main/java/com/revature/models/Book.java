package com.revature.models;



import javax.persistence.CascadeType;

import javax.persistence.Entity;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.ManyToOne;

import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(schema="project0",name="books")
@Data @NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(exclude = {"bookId"}) @ToString(exclude = {"bookId"})
public class Book{
	
	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH }, fetch = FetchType.EAGER)
	@JsonBackReference
	private User users;
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int bookId;
	
	private String title;
	private String author;
	
	
	

	

}