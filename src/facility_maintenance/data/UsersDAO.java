package facility_maintenance.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import facility_maintenance.model.User;
import facility_maintenance.util.SQLConnection;

public class UsersDAO {
	static SQLConnection DBMgr = SQLConnection.getInstance();
	
	//search user with username
	public static User getUser (String username)  {
		String queryString = "SELECT * from `users` WHERE `username`='"+username+"';";
		Connection conn = SQLConnection.getDBConnection();
		Statement stmt = null;
		
		User user = new User(); 
		
		try {
			stmt = conn.createStatement();
			ResultSet users = stmt.executeQuery(queryString);
			
			while (users.next()) {
				user.setUser(users.getString("username"),
				users.getString("password"),
				users.getString("role"),
				users.getString("utaid"),
				users.getString("fname"),
				users.getString("lname"),
				users.getString("email"),
				users.getString("phone"),
				users.getString("address"),
				users.getString("city"),
				users.getString("state"));
			} 
		}
		catch (SQLException e) {
			
		}
		
		return user;
	}
	
	public static HashMap<String, String> getRepairers (String repairer)  {
		HashMap<String, String> result = new HashMap<String, String>();
		String queryString = "SELECT * from `users` WHERE `role`='Repairer'";
		Connection conn = SQLConnection.getDBConnection();
		Statement stmt = null;
		
		try {
			stmt = conn.createStatement();
			ResultSet users = stmt.executeQuery(queryString);
			
			while (users.next()) {
				String r = users.getString("username");
				result.put(r, (repairer.equals(r)) ? "selected" : "");
			} 
		}
		catch (SQLException e) {
			
		}
		
		return result;
	}
	
	public static void insert(User user) {
		String queryString = "INSERT INTO `users` (`username`, `password`, `role`, `utaid`, `fname`, `lname`, `email`, `phone`, `address`, `city`, `state`) ";
		Connection conn = SQLConnection.getDBConnection();
		Statement stmt = null;
		
		try {
			stmt = conn.createStatement();
			queryString += " VALUES ('"
					+ user.getUsername()  + "','"
					+ user.getPassword() + "','"		
					+ user.getRole() + "','"
					+ user.getUtaid()  + "','"
					+ user.getFname() + "','"
					+ user.getLname() + "','"
					+ user.getEmail()  + "','"
					+ user.getPhone() + "','"
					+ user.getAddress() + "','"
					+ user.getCity()  + "','"
					+ user.getState() + "')";
			
			stmt.executeUpdate(queryString);
			conn.commit();
		}
		catch (SQLException e) 
		{
			
		}
	} 
	
	public static void update(User user) {
		String queryString = "UPDATE `users` SET ";
		Connection conn = SQLConnection.getDBConnection();
		Statement stmt = null;
		
		try {
			stmt = conn.createStatement();
			
			queryString += " `password` = '" + user.getPassword();
			queryString += "', `role` = '" + user.getRole();
			queryString += "', `utaid` = '" + user.getUtaid();
			queryString += "', `fname` = '" + user.getFname();
			queryString += "', `lname` = '" + user.getLname();
			queryString += "', `email` = '" + user.getEmail();
			queryString += "', `phone` = '" + user.getPhone();
			queryString += "', `address` = '" + user.getAddress();
			queryString += "', `city` = '" + user.getCity();
			queryString += "', `state` = '" + user.getState();			
			queryString += "'  WHERE `username`='" + user.getUsername() + "';";
			
			stmt.executeUpdate(queryString);
			conn.commit();
		}
		catch (SQLException e) 
		{
			
		}
	}
}