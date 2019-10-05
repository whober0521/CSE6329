package facility_maintenance.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import facility_maintenance.util.SQLConnection;

public class FacilitiesDAO {
	static SQLConnection DBMgr = SQLConnection.getInstance();

	public static ArrayList<String> getNames() {
		ArrayList<String> result = new ArrayList<String>();
		Connection conn = SQLConnection.getDBConnection();
		Statement stmt = null;
		
		try {
			stmt = conn.createStatement();
			ResultSet facilities = stmt.executeQuery("SELECT * from `facilities`;");
			
			while (facilities.next()) {
				for (int i = 1; i <= facilities.getInt("amount"); i++)
					result.add(facilities.getString("id") + i);	
			}
		}
		catch (SQLException e) {
			
		}
		
		return result;
	}
}