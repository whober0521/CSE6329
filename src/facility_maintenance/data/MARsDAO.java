package facility_maintenance.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import facility_maintenance.model.MAR;
import facility_maintenance.util.SQLConnection;

public class MARsDAO {
	static SQLConnection DBMgr = SQLConnection.getInstance();
	
	public static void insert(MAR mar) {
		String queryString = "INSERT INTO `mars` (`facilityname`, `urgency`, `description`, `reporter`, `reportdate`, `reporttime`) ";
		Connection conn = SQLConnection.getDBConnection();
		Statement stmt = null;

		try {
			stmt = conn.createStatement();
			queryString += " VALUES ('"
					+ mar.getFacilityname()  + "','"
					+ mar.getUrgency() + "','"
					+ mar.getDescription() + "','"
					+ mar.getReporter() + "', CURDATE(), CURTIME());";
			
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
						mars.getString("reporter"),
						mars.getString("reportdate"),
						mars.getString("reporttime"),
						mars.getString("repairer"));
			} 
		}
		catch (SQLException e) {
			
		}
		
		return mar;
	}
	
	public static ArrayList<MAR> getUnassigned(MAR mar) {
		String queryString = "SELECT * from mars WHERE repairer is NULL ";
		
		if (!mar.getIdx().equals("")) {
			queryString += " AND `idx`='" + mar.getIdx() + "' ";
		}
		else {
			ArrayList<String> where = new ArrayList<String>();
			
			if (!mar.getFacilitytype().equals("")) {
				where.add(" `facilitytype`='" + mar.getFacilitytype() + "' ");
			}
			
			if (!mar.getFacilityname().equals("")) {
				where.add(" `facilityname`='" + mar.getFacilityname() + "' ");
			}
			
			if (!mar.getRepairer().equals("")) {
				where.add(" `repairer`='" + mar.getRepairer() + "' ");
			}
			
			if (!mar.getReportdate().equals("")) {
				where.add(" `reportdate`='" + mar.getReportdate() + "' ");
			}
			
			if (!mar.getReporttime().equals("")) {
				where.add(" `reporttime`>='" + mar.getReporttime() + "' ");
			}
			
			if(where.size()!=0)
				queryString += " AND ("+ String.join(" OR ", where) + ")";
		}

		ArrayList<MAR> result = new ArrayList<MAR>();
		Connection conn = SQLConnection.getDBConnection();
		Statement stmt = null;
		
		try {
			stmt = conn.createStatement();
			ResultSet mars = stmt.executeQuery(queryString);
			
			while (mars.next()) {
				MAR _mar = new MAR();
				
				_mar.setMAR(
						mars.getString("idx"),
						mars.getString("facilitytype"),
						mars.getString("facilityname"),
						mars.getString("urgency"),
						mars.getString("description"),
						mars.getString("reporter"),
						mars.getString("reportdate"),
						mars.getString("reporttime"),
						mars.getString("repairer"));
				
				result.add(_mar);	
			}
		}
		catch (SQLException e) {
			
		}
		
		return result;
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