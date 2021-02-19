package com.revature.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.models.Account;
import com.revature.models.Book;
import com.revature.models.Role;
import com.revature.util.ConnectionUtil;

public class AccountDAO implements IAccountDAO {
	
	private static final Logger log = LoggerFactory.getLogger(AccountDAO.class);

	@Override
	public List<Account> findAll() {
		
		List<Account> allAccounts = new ArrayList<>();
		
		try (Connection conn = ConnectionUtil.getConnection()) {
			
			Statement stmt = conn.createStatement();
			String sql = "SELECT * FROM project0.account";
			
			ResultSet rs = stmt.executeQuery(sql);
			stmt.close();
			
			ResultSetMetaData metaData = rs.getMetaData();
		    int columns = metaData.getColumnCount();
		    List<Book> books = new ArrayList<>(columns);
			
			
			while(rs.next()) {

				int id = rs.getInt("id");
				double balance = rs.getDouble("balance");
				for (int i = 1; i <= columns; i++)
					books.add((Book)rs.getObject(i));
			
				Account a = new Account(id, balance, books);	
				allAccounts.add(a);
			}
			
		} catch(SQLException e) {
			log.error("We failed to retrieve all accounts", e);

			return new ArrayList<>();
		}
		
		return allAccounts;
	}
}