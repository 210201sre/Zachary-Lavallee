package com.revature.repositories;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.jetty.util.security.Password;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.models.Author;
import com.revature.models.Book;
import com.revature.models.Role;
import com.revature.models.User;
import com.revature.util.ConnectionUtil;

public class UserDAO implements IUserDAO {

	private static final Logger log = LoggerFactory.getLogger(UserDAO.class);

	@Override
	public List<User> findAll() {
		List<User> allUsers = new ArrayList<>();

		try (Connection conn = ConnectionUtil.getConnection()) {
			
			Statement stmt = conn.createStatement();
			
			String sql = "SELECT * FROM project0.users";
		
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				int id = rs.getInt("id");
				String username = rs.getString("username");
				String password =  rs.getString("password");
				Role role = Role.valueOf(rs.getString("role"));		
				
				allUsers.add(new User(id, username, password, role));
			}
		} catch (SQLException e) {
			log.error("We failed to retrieve all users", e);
			return new ArrayList<>();
		}
		return allUsers;
	}

	@Override
	public int insert(User u) {

		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "INSERT INTO project0.users (username, password, role) VALUES (?, ?, ?) "
					+ "RETURNING project0.users.id";

			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setString(1, u.getUsername());
			stmt.setString(2, u.getPassword());
			stmt.setObject(3, u.getRole(), Types.OTHER);

			ResultSet rs;

			if ((rs = stmt.executeQuery()) != null) {
				rs.next();

				int id = rs.getInt(1);
				stmt.close();

				return id;
			}

		} catch (SQLException e) {
			log.error("We failed to insert a new user", e);
			return -1;
		}
		return -1;
	}

	@Override
	public boolean update(User u) {
		return false;
	}

	@Override
	public boolean delete(int userId) {
		return false;
	}

}