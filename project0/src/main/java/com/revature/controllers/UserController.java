package com.revature.controllers;

import java.lang.System.Logger;

import org.eclipse.jetty.client.HttpRequest;
import org.omg.CORBA.Environment;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import com.revature.models.Account;
import com.revature.models.Book;
import com.revature.models.User;
import com.revature.services.UserService;

import io.javalin.Javalin;
import io.javalin.http.Handler;

public class UserController implements Controller {

	private UserService userService = new UserService();


	private Handler createUser = (ctx) -> {
		User u = ctx.bodyAsClass(User.class);
		u = userService.createUser(u);

		ctx.json(u);
		ctx.status(201);

		MDC.clear();
	};


	private Handler getAllBooks = (ctx) -> {
		ctx.json(userService.getAllBooks());
		ctx.status(200);

		MDC.clear();
	};

	private Handler getAllUsers = (ctx) -> {
		ctx.json(userService.getAllUsers());
		ctx.status(200);
	
		MDC.clear();
	};
	private Handler createBook = (ctx) -> {
		Book b = ctx.bodyAsClass(Book.class);

		b = userService.register(b);
		ctx.json(b);
	};
	private Handler returnBook = (ctx) -> {
		User u = ctx.bodyAsClass(User.class);

		ctx.json(u);
		ctx.status(201);

		MDC.clear();
	};
	private Handler deleteBook = (ctx) -> {
		

	};

	private Handler RemoveUser = (ctx) -> {
		String idString = ctx.pathParam("id");

		int id = Integer.parseInt(idString);

		MDC.clear();
	};

	private Handler updateDueDate;

	private Handler payBalanace;

	public void addRoutes(Javalin app) {
		app.get("/users", this.getAllUsers);
		
		app.post("/users", this.createUser);
		
		app.get("/books", this.getAllBooks);
		
		app.post("/books", this.createBook);
		
		app.delete("/books:id", this.deleteBook);
		
		app.put("/books", this.returnBook);

//		app.put("/users:dueDate", this.updateDueDate);
		
//		app.put("/account:balance", this.payBalanace);
	};
}