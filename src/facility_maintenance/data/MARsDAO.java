package facility_maintenance.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import facility_maintenance.model.Facility;
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
						mars.getString("repairdate"),
						mars.getString("starttime"),
						mars.getString("endtime"));
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
						mars.getString("repairdate"),
						mars.getString("starttime"),
						mars.getString("endtime"));

				result.add(_mar);	
			}
		}
		catch (SQLException e) {
			
		}
		
		return result;
	}
	
	public static ArrayList<MAR> getAssigned(MAR mar) {
		String queryString = "SELECT * from mars WHERE 1 ";
		
		if (!mar.getIdx().equals(""))
			queryString += " AND `idx`='" + mar.getIdx() + "' ";
		
		if (!mar.getFacilitytype().equals(""))
			queryString += " AND `facility` LIKE '" + mar.getFacilitytype() + "%' ";
		
		if (!mar.getFacilityname().equals(""))
			queryString += " AND `facility`='" + mar.getFacilityname() + "' ";
		
		if (!mar.getRepairer().equals(""))
			queryString += " AND `repairer`='" + mar.getRepairer() + "' ";
		
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
						mars.getString("repairdate"),
						mars.getString("starttime"),
						mars.getString("endtime"));
				
				result.add(_mar);
			}
		}
		catch (SQLException e) {
			
		}
		
		return result;
	}
	
	public static ArrayList<MAR> getDateTime(String facilityname, String startdate, String starttime){
		Facility facility = FacilitiesDAO.getDetail(facilityname);
		ArrayList<MAR> result = new ArrayList<MAR>();
		
		int duration = Integer.parseInt(facility.getDuration().split(" ")[0]);
		int interval = 0;
		
		if(facility.getInterval().equals("30 minutes"))
			interval = 30;
		else
			interval = Integer.parseInt(facility.getInterval().split(" ")[0]) * 60;
		
		Date end = new Date();

		Calendar c = Calendar.getInstance(); 
		
		c.setTime(end); 
		c.add(Calendar.DATE, duration);
		
		end = c.getTime();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date start = new Date();
		
		try {
			start = sdf.parse(startdate + " " + starttime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String idx = MARsDAO.getIdx(facilityname);
		
		while(start.before(end)) {
			MAR _mar = new MAR();
			
			String d = new SimpleDateFormat("yyyy-MM-dd").format(start);
			String s = new SimpleDateFormat("HH:mm").format(start);
			
			c.setTime(start); 
			c.add(Calendar.MINUTE, interval);
			start = c.getTime();
			
			_mar.setMAR(idx, "", "", "", "", "", "", "", "", "", "", "",
						d, s, new SimpleDateFormat("HH:mm").format(start));
			
			result.add(_mar);
		}
		
		String queryString = "SELECT * FROM mars WHERE `facility` = '" + facilityname + "' AND `repairdate` IS NOT NULL ORDER BY `repairdate`, `starttime`;";
		Connection conn = SQLConnection.getDBConnection();
		Statement stmt = null;
		int i = 0;
		
		try {
			stmt = conn.createStatement();
			ResultSet mars = stmt.executeQuery(queryString);
			
			while (mars.next()) {
				if(i>=result.size()) break;
				
				if(mars.getString("repairdate").compareTo(result.get(i).getRepairdate()) < 0)
					continue;
				
				while(mars.getString("repairdate").compareTo(result.get(i).getRepairdate()) > 0 && i<result.size()) {
					i += 1;
				}
				
				if(i>=result.size()) break;
				
				if(mars.getString("endtime").compareTo(result.get(i).getStarttime()) < 0)
					break;
				
				while(mars.getString("starttime").compareTo(result.get(i).getEndtime()) >= 0 && i<result.size()) {
					i += 1;
				}
				
				if(i>=result.size()) break;
				
				if(mars.getString("starttime").compareTo(result.get(i).getStarttime()) >= 0 && 
				   mars.getString("endtime").compareTo(result.get(i).getEndtime()) <= 0)
					result.remove(i);		
			} 
		}
		catch (SQLException e) {
			
		}

		return result;
	}
	
	private static String getIdx (String facility)  {
		String queryString = "SELECT * FROM mars WHERE `facility` = '" + facility + "';";
		Connection conn = SQLConnection.getDBConnection();
		Statement stmt = null;
		
		try {
			stmt = conn.createStatement();
			ResultSet mars = stmt.executeQuery(queryString);
			
			while (mars.next()) {
				return mars.getString("idx");
			} 
		}
		catch (SQLException e) {
			
		}
		
		return "";
	}
	
	public static int getAssignedNumber (String repairer, String date)  {
		String queryString = "SELECT * FROM mars WHERE `repairer` = '" + repairer + "' AND `assigndate` = '" + date + "';";
		Connection conn = SQLConnection.getDBConnection();
		Statement stmt = null;
		int result = 0;
		
		try {
			stmt = conn.createStatement();
			ResultSet mars = stmt.executeQuery(queryString);
			
			while (mars.next()) {
				result += 1;
			} 
		}
		catch (SQLException e) {
			
		}
		
		return result;
	}
	
	public static int getAssignedNumber(String repairer)  {
		String queryString = "SELECT * FROM mars WHERE `repairer` = '" + repairer + "' AND `assigndate` >= '";
		Calendar c = Calendar.getInstance(); 
		
		c.setTime(new Date()); 
		
		while(c.get(Calendar.DAY_OF_WEEK)!=1)
			c.add(Calendar.DATE, -1);
		
		queryString += new SimpleDateFormat("yyyy-MM-dd").format(c.getTime()) + "' AND `assigndate` <= '";
		c.add(Calendar.DATE, 6);		
		queryString +=  new SimpleDateFormat("yyyy-MM-dd").format(c.getTime()) + "';";
		
		Connection conn = SQLConnection.getDBConnection();
		Statement stmt = null;
		int result = 0;
		
		try {
			stmt = conn.createStatement();
			ResultSet mars = stmt.executeQuery(queryString);
			
			while (mars.next()) {
				result += 1;
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
	
	public static void reserve(String idx, String repairdate, String starttime, String endtime) {
		String queryString = "UPDATE `mars` SET " +
								"`repairdate` = '" + repairdate +
								"', `starttime` = '" +starttime +
								"', `endtime` = '" + endtime + "' WHERE `idx` = '" + idx + "';";

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