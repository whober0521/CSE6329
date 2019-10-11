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
	
	public static MAR getMAR (String idx)  {
		String queryString = "SELECT * FROM mars " +
							 "LEFT OUTER JOIN facilitymaster ON mars.facility LIKE CONCAT(facilitymaster.id, '%') " + 
							 "WHERE `idx` = '" + idx + "';";
		
		Connection conn = SQLConnection.getDBConnection();
		Statement stmt = null;
		MAR mar = new MAR(); 
		
		try {
			stmt = conn.createStatement();
			ResultSet mars = stmt.executeQuery(queryString);
			
			while (mars.next()) {				
				mar.setMAR(
						mars.getString("idx"),
						mars.getString("name"),
						mars.getString("facility"),
						mars.getString("description"),
						mars.getString("urgency"),
						mars.getString("reporter"),
						mars.getString("reportdate"),
						mars.getString("reporttime"),
						mars.getString("repairer"),
						mars.getString("assigndate"),
						mars.getString("assigntime"),
						mars.getString("estimate"),
						mars.getString("repairdate"));
			} 
		}
		catch (SQLException e) {
			
		}
		
		return mar;
	}
	
	public static ArrayList<MAR> getUnassigned() {
		String queryString = "SELECT * from mars WHERE repairer is NULL;";
		ArrayList<MAR> result = new ArrayList<MAR>();
		Connection conn = SQLConnection.getDBConnection();
		Statement stmt = null;
		
		try {
			stmt = conn.createStatement();
			ResultSet mars = stmt.executeQuery(queryString);
			
			while (mars.next()) {
				MAR _mar = new MAR();
				
				_mar.setMAR(
						mars.getString("idx"), "",
						mars.getString("facility"),
						mars.getString("description"),
						mars.getString("urgency"),
						mars.getString("reporter"),
						mars.getString("reportdate"),
						mars.getString("reporttime"),
						mars.getString("repairer"),
						mars.getString("assigndate"),
						mars.getString("assigntime"),
						mars.getString("estimate"),
						mars.getString("repairdate"));

				result.add(_mar);	
			}
		}
		catch (SQLException e) {
			
		}
		
		return result;
	}
	
	public static ArrayList<MAR> getAssigned(MAR mar) {
		String queryString = "SELECT * from mars WHERE repairer = '" + mar.getRepairer() + "' ";
		
		if (!mar.getIdx().equals(""))
			queryString += " AND `idx`='" + mar.getIdx() + "' ";
		
		if (!mar.getFacilitytype().equals(""))
			queryString += " AND `facility` LIKE '" + mar.getFacilitytype() + "%' ";
		
		if (!mar.getFacilityname().equals(""))
			queryString += " AND `facility`='" + mar.getFacilityname() + "' ";
		
		if (!mar.getAssigndate().equals(""))
			queryString += " AND `assigndate`='" + mar.getAssigndate() + "' ";
		
		if (!mar.getAssigntime().equals(""))
			queryString += " AND `assigntime` >= '" + mar.getAssigntime() + "' ";
		
		ArrayList<MAR> result = new ArrayList<MAR>();
		Connection conn = SQLConnection.getDBConnection();
		Statement stmt = null;
		
		try {
			stmt = conn.createStatement();
			ResultSet mars = stmt.executeQuery(queryString);
			
			while (mars.next()) {
				MAR _mar = new MAR();
				
				_mar.setMAR(
						mars.getString("idx"), "",
						mars.getString("facility"),
						mars.getString("description"),
						mars.getString("urgency"),
						mars.getString("reporter"),
						mars.getString("reportdate"),
						mars.getString("reporttime"),
						mars.getString("repairer"),
						mars.getString("assigndate"),
						mars.getString("assigntime"),
						mars.getString("estimate"),
						mars.getString("repairdate"));
				
				result.add(_mar);
			}
		}
		catch (SQLException e) {
			
		}
		
		return result;
	}
	
	public static void insert(MAR mar) {
		String queryString = "INSERT INTO `mars` (`facility`, `description`, `reporter`, `reportdate`, `reporttime`) ";
		Connection conn = SQLConnection.getDBConnection();
		Statement stmt = null;

		try {
			stmt = conn.createStatement();
			queryString += " VALUES ('"
					+ mar.getFacilityname()  + "','"
					+ mar.getDescription() + "','"
					+ mar.getReporter() + "', CURDATE(), CURTIME());";
			
			stmt.executeUpdate(queryString);
			conn.commit();
		}
		catch (SQLException e) 
		{
			
		}
	} 
	
	public static void assign(MAR mar) {
		String queryString = "UPDATE `mars` SET " +
				"`urgency` = '" + mar.getUrgency() +
				"', `repairer` = '" + mar.getRepairer() +
				"', `estimate` = '" + mar.getEstimate() +
				"', `assigndate` = CURDATE(), `assigntime` = CURTIME() WHERE `idx` = '" + mar.getIdx() + "';";

		Connection conn = SQLConnection.getDBConnection();
		Statement stmt = null;
		
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(queryString);
			conn.commit();
		}
		catch (SQLException e) {
			
		}
	}
	
	public static void estimate(MAR mar) {
		String queryString = "UPDATE `mars` SET `estimate` = '" + mar.getEstimate() + 
							"'  WHERE `idx` = '" + mar.getIdx() + "';";

		Connection conn = SQLConnection.getDBConnection();
		Statement stmt = null;
		
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(queryString);
			conn.commit();
		}
		catch (SQLException e) {
			
		}
	}
	
	public static void delete (String idx)  {
		String queryString = "DELETE FROM mars WHERE `idx` = '" + idx + "';";
		Connection conn = SQLConnection.getDBConnection();
		Statement stmt = null;
		
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(queryString);
			conn.commit();
		}
		catch (SQLException e) {
			
		}
	}
}