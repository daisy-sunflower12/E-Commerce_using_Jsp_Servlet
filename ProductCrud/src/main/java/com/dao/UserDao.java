package com.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.model.User;

public class UserDao {

	public static Connection getConnection() {

		/*
		 * They are null by default for objects, 0 for numeric values and false for
		 * booleans. For variables declared in methods - Java requires them to be
		 * initialized. Not initializing them causes a compile time error when they are
		 * accessed.
		 */
		Connection con = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3307/prod_crud?&useSSL=false", "root", "sushet");

		} catch (Exception e) {
			System.out.println("Exception " + e);
		}
		return con;
	}

	// save user
	public static int saveUser(User user) {
		int rows = 0;

		try {
			Connection con = UserDao.getConnection();
			PreparedStatement pst = con.prepareStatement(
					"insert into user(first_name, last_name, email, password, mobile, balance, dob, role_id) values(?,?,?,?,?,?,?,?)");
			pst.setString(1, user.getFirstName());
			pst.setString(2, user.getLastName());
			pst.setString(3, user.getEmail());
			pst.setString(4, user.getPassword());
			pst.setInt(5, user.getMobile());
			pst.setDouble(6, user.getBalance());
			Date ds = Date.valueOf(user.getDob());
			pst.setDate(7, ds);
			pst.setInt(8, user.getRole_id());

			rows = pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println("Exception " + e);
		}
		return rows;
	}

	public static User authenticate(User u) {
		//boolean status = false;
		User user = new User();
		
		try {
			Connection con = UserDao.getConnection();
			PreparedStatement pst = con.prepareStatement("select user.*, user_role.name "
					+ "from user left join user_role on user.role_id = user_role.r_id where user.email=? and user.password=? order by u_id");

			pst.setString(1, u.getEmail());
			pst.setString(2, u.getPassword());
			ResultSet rs = pst.executeQuery();
			
				while (rs.next()) {	
					
					user.setId(rs.getInt("u_id"));
					user.setFirstName(rs.getString("first_name"));
					user.setLastName(rs.getString("last_name"));
					user.setEmail(rs.getString("email"));
					user.setPassword(rs.getString("password"));
					user.setMobile(rs.getInt("mobile"));
					user.setDob(rs.getString("dob"));
					user.setBalance(rs.getDouble("balance"));
					int roleDb = rs.getInt("role_id");
					user.setRole_id(roleDb);
					user.setUserRoleName(rs.getString("name"));

					if (rs.getString("email").equals(user.getEmail())
							&& rs.getString("password").equals(user.getPassword()) && roleDb == 1)
						// from db from form
						return user;
					if (rs.getString("email").equals(user.getEmail())
							&& rs.getString("password").equals(user.getPassword()) && roleDb == 2)
						return user;
				}

		} catch (Exception e) {
			System.out.println("Exception " + e);
		}
		/* return "Invalid credentials...try again"; */
		return user;

	}
}
