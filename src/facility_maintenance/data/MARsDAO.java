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
						mars.getString("facility"),
						mars.getString("description"),
						mars.getString("urgency"),
						mars.getString("reporter"),
						mars.getString("reportdate"),
						mars.getString("reporttime"),
						mars.getString("repairer"),
						mars.getString("assigndate"));
			} 
		}
		catch (SQLException e) {
			
		}
		
		return mar;
	}
	
	public static ArrayList<MAR> getUnassigned() {
		String queryString = "SELECT * from mars WHERE repairer is NULL ";
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
						mars.getString("facility"),
						mars.getString("urgency"),
						mars.getString("description"),
						mars.getString("reporter"),
						mars.getString("reportdate"),
						mars.getString("reporttime"),
						mars.getString("repairer"),
						mars.getString("assigndate"));

				result.add(_mar);	
			}
		}
		catch (SQLException e) {
			
		}
		
		return result;
	}
	
	public static ArrayList<MAR> getUnassigned(MAR mar) {
		String queryString = "SELECT * from mars WHERE repairer is NULL ";
		
		if (!mar.getIdx().equals("")) {
			queryString += " AND `idx`='" + mar.getIdx() + "' ";
		}
		else {
			ArrayList<String> where = new ArrayList<String>();
			
//			if (!mar.getFacilitytype().equals("")) {
//				where.add(" `facilitytype`='" + mar.getFacilitytype() + "' ");
//			}
//			
//			if (!mar.getFacilityname().equals("")) {
//				where.add(" `facilityname`='" + mar.getFacilityname() + "' ");
//			}
			
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
						mars.getString("repairer"),
						mars.getString("assigndate"));
				
				result.add(_mar);	
			}
		}
		catch (SQLException e) {
			
		}
		
		return result;
	}
	
	public static ArrayList<MAR> getAssigned(MAR mar) {
		String queryString = "SELECT * from mars WHERE repairer = '" + mar.getRepairer() + "' ";
		ArrayList<String> where = new ArrayList<String>();
		
		if (!mar.getReportdate().equals("")) {
			where.add(" `reportdate`='" + mar.getReportdate() + "' ");
		}
		
		if (!mar.getReporttime().equals("")) {
			where.add(" `reporttime`>='" + mar.getReporttime() + "' ");
		}
		
		if(where.size()!=0)
			queryString += " AND ("+ String.join(" OR ", where) + ")";

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
						mars.getString("facility"),
						mars.getString("description"),
						mars.getString("urgency"),
						mars.getString("reporter"),
						mars.getString("reportdate"),
						mars.getString("reporttime"),
						mars.getString("repairer"),
						mars.getString("assigndate"));
				
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
					+ mar.getFacility()  + "','"
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
		String queryString = "UPDATE `mars` SET `repairer` = '" + mar.getRepairer() + 
				"', `assigndate` = CURDATE() WHERE `idx` = '" + mar.getIdx() + "';";

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