package facility_maintenance.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import facility_maintenance.model.User;
import facility_maintenance.util.SQLConnection;

public class UsersDAO {
	static SQLConnection DBMgr = SQLConnection.getInstance();
	
	//search user with username
	public static User getUser (String username)  {
		String queryString = "SELECT * from users WHERE `username`='"+username+"';";
		Connection conn = SQLConnection.getDBConnection();
		Statement stmt = null;
		
		User user = new User(); 
		
		try {
			stmt = conn.createStatement();
			ResultSet users = stmt.executeQuery(queryString);
			
			while (users.next()) {
				String _username = users.getString("username");
				String _password = users.getString("password");
				String _role  = users.getString("role");
				
				user.setUsername(_username);
				user.setPassword(_password);
				user.setRole(_role);
			} 
		}
		catch (SQLException e) {
			
		}
		
		return user;
	}
	
	public static void insert(User user) {
		String queryString = "INSERT INTO users (`username`, `password`, `role`, `utaid`, `fname`, `lname`, `email`, `phone`, `address`, `city`, `state`) ";
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
	
//	private static ArrayList<Company> ReturnMatchingCompaniesList (String queryString) {
//		ArrayList<Company> companyListInDB = new ArrayList<Company>();
//		
//		Statement stmt = null;
//		Connection conn = SQLConnection.getDBConnection();  
//		try {
//			stmt = conn.createStatement();
//			ResultSet companyList = stmt.executeQuery(queryString);
//			while (companyList.next()) {
//				Company company = new Company(); 
//				company.setIdcompany(companyList.getString("idcompany"));
//				company.setCompany_name(companyList.getString("company_name"));
//				company.setPhone(companyList.getString("phone"));
//				company.setEmail(companyList.getString("email"));  
//				companyListInDB.add(company);	
//			}
//		} catch (SQLException e) {}
//		return companyListInDB;
//	}
//	

//

//	
//	public static ArrayList<Company>  listCompanies() {  
//			return ReturnMatchingCompaniesList(" SELECT * from COMPANY ORDER BY company_name");
//	}
//	
//	//search companies
//	public static ArrayList<Company>  searchCompanies(String companyname)  {  
//			return ReturnMatchingCompaniesList(" SELECT * from COMPANY WHERE company_name LIKE '%"+companyname+"%' ORDER BY idcompany");
//	}
//	
//	//determine if companyID is unique
//	public static Boolean CompanyIDunique(String idComp)  {  
//			return (ReturnMatchingCompaniesList(" SELECT * from COMPANY WHERE IDCOMPANY = '"+idComp+"' ORDER BY company_name").isEmpty());
//	}
}