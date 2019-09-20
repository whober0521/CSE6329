package facility_maintenance.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import facility_maintenance.model.MAR;
import facility_maintenance.util.SQLConnection;

public class MARsDAO {
	static SQLConnection DBMgr = SQLConnection.getInstance();
	
	public static void insert(MAR mar) {
		String queryString = "INSERT INTO `mars` (`facilityname`, `urgency`, `description`, `reportdate`, `reporttime`) ";
		Connection conn = SQLConnection.getDBConnection();
		Statement stmt = null;

		try {
			stmt = conn.createStatement();
			queryString += " VALUES ('"
					+ mar.getFacilityname()  + "','"
					+ mar.getUrgency() + "','"
					+ mar.getDescription() + "', CURDATE(), CURTIME());";
			
			stmt.executeUpdate(queryString);
			conn.commit();
		}
		catch (SQLException e) 
		{
			
		}
	} 
	
	//search MAR with idx
	public static MAR getMAR (String idx)  {
		String queryString = "SELECT * from `mars` WHERE `idx`='"+idx+"';";
		Connection conn = SQLConnection.getDBConnection();
		Statement stmt = null;
		MAR mar = new MAR(); 
		
		try {
			stmt = conn.createStatement();
			ResultSet mars = stmt.executeQuery(queryString);
			
			while (mars.next()) {
				mar.setMAR(
						mars.getString("idx"),
						mars.getString("facilitytype"),
						mars.getString("facilityname"),
						mars.getString("urgency"),
						mars.getString("description"),
						mars.getString("repairer"),
						mars.getString("reportdate"),
						mars.getString("reporttime"));
			} 
		}
		catch (SQLException e) {
			
		}
		
		return mar;
	}
//		
//
//		
//		public static void update(User user) {
//			String queryString = "UPDATE `users` SET ";
//			Connection conn = SQLConnection.getDBConnection();
//			Statement stmt = null;
//			
//			try {
//				stmt = conn.createStatement();
//				
//				queryString += " `password` = '" + user.getPassword();
//				queryString += "', `role` = '" + user.getRole();
//				queryString += "', `utaid` = '" + user.getUtaid();
//				queryString += "', `fname` = '" + user.getFname();
//				queryString += "', `lname` = '" + user.getLname();
//				queryString += "', `email` = '" + user.getEmail();
//				queryString += "', `phone` = '" + user.getPhone();
//				queryString += "', `address` = '" + user.getAddress();
//				queryString += "', `city` = '" + user.getCity();
//				queryString += "', `state` = '" + user.getState();			
//				queryString += "'  WHERE `username`='" + user.getUsername() + "';";
//				
//				stmt.executeUpdate(queryString);
//				conn.commit();
//			}
//			catch (SQLException e) 
//			{
//				
//			}
//		} 
		
//		private static ArrayList<Company> ReturnMatchingCompaniesList (String queryString) {
//			ArrayList<Company> companyListInDB = new ArrayList<Company>();
//			
//			Statement stmt = null;
//			Connection conn = SQLConnection.getDBConnection();  
//			try {
//				stmt = conn.createStatement();
//				ResultSet companyList = stmt.executeQuery(queryString);
//				while (companyList.next()) {
//					Company company = new Company(); 
//					company.setIdcompany(companyList.getString("idcompany"));
//					company.setCompany_name(companyList.getString("company_name"));
//					company.setPhone(companyList.getString("phone"));
//					company.setEmail(companyList.getString("email"));  
//					companyListInDB.add(company);	
//				}
//			} catch (SQLException e) {}
//			return companyListInDB;
//		}
	//	

	//

	//	
//		public static ArrayList<Company>  listCompanies() {  
//				return ReturnMatchingCompaniesList(" SELECT * from COMPANY ORDER BY company_name");
//		}
	//	
//		//search companies
//		public static ArrayList<Company>  searchCompanies(String companyname)  {  
//				return ReturnMatchingCompaniesList(" SELECT * from COMPANY WHERE company_name LIKE '%"+companyname+"%' ORDER BY idcompany");
//		}
	//	
//		//determine if companyID is unique
//		public static Boolean CompanyIDunique(String idComp)  {  
//				return (ReturnMatchingCompaniesList(" SELECT * from COMPANY WHERE IDCOMPANY = '"+idComp+"' ORDER BY company_name").isEmpty());
//		}
}