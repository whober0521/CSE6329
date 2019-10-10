package facility_maintenance.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import facility_maintenance.model.Facility;
import facility_maintenance.util.SQLConnection;

public class FacilitiesDAO {
	static SQLConnection DBMgr = SQLConnection.getInstance();

	public static HashMap<String, String> getTypes() {
		HashMap<String, String> result = new HashMap<String, String>();
		Connection conn = SQLConnection.getDBConnection();
		Statement stmt = null;
		
		try {
			stmt = conn.createStatement();
			ResultSet facilities = stmt.executeQuery("SELECT * from `facilitymaster`;");
			
			while (facilities.next()) {
				result.put(facilities.getString("id"), facilities.getString("name"));
			}
		}
		catch (SQLException e) {
			
		}
		
		return result;
	}
	
	public static ArrayList<String> getNames() {
		ArrayList<String> result = new ArrayList<String>();
		Connection conn = SQLConnection.getDBConnection();
		Statement stmt = null;
		
		try {
			stmt = conn.createStatement();
			ResultSet details = stmt.executeQuery("SELECT * from `facilitydetail`;");
			
			while (details.next()) {
				result.add(details.getString("master") + details.getString("id"));	
			}
		}
		catch (SQLException e) {
			
		}
		
		return result;
	}
	
	public static void insert(Facility facility) {
		String queryString = "INSERT INTO `facility_maintenance`.`facilitydetail` (`master`, `id`, `interval`, `duration`, `venue`) ";
		Connection conn = SQLConnection.getDBConnection();
		Statement stmt = null;

		try {
			int count = Integer.parseInt(facility.getNumber());
			int id = getMaxId(facility.getMaster()) +1 ;
			
			for (int i = 0; i < count; i++) {
				stmt = conn.createStatement();
				String valueString = " VALUES ('"
						+ facility.getMaster()  + "', '"
						+ (id+i)  + "', '"
						+ facility.getInterval() + "', '"
						+ facility.getDuration() + "', '"
						+ facility.getVenue() + "');";
				
				stmt.executeUpdate(queryString+valueString);
				conn.commit();
			}
		}
		catch (SQLException e) 
		{
			
		}
	} 
	
	public static int getMaxId(String master) {
		Connection conn = SQLConnection.getDBConnection();
		Statement stmt = null;
		
		try {
			stmt = conn.createStatement();
			ResultSet facilities = stmt.executeQuery("SELECT * FROM facilitydetail WHERE `master` = '" + master + "' ORDER BY `id` DESC;");
			
			while (facilities.next()) {
				return facilities.getInt("id");
			}
		}
		catch (SQLException e) {
			
		}
		
		return -1;
	} 
}