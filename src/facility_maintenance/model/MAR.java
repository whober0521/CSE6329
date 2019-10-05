package facility_maintenance.model;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import facility_maintenance.data.UsersDAO;
import facility_maintenance.data.MARsDAO;

public class MAR implements Serializable{
	private String idx;
	private String facility;
	private String urgency;
	private String description;
	private String reporter;
	private String reportdate;
	private String reporttime;
	private String repairer;
	private String assigndate;

	public void setMAR (String idx, String facility, String description, String urgency, 
			String reporter, String reportdate, String reporttime, String repairer, String assigndate) {
		setIdx(idx);
		setFacility(facility);
		setDescription(description);
		setUrgency(urgency);
		setReporter(reporter);
		setReportdate(reportdate);
		setReporttime(reporttime);
		setRepairer(repairer);
		setAssigndate(assigndate);
	}
	
	public void setIdx(String idx) {
		this.idx = idx;
	}
	
	public String getIdx() {
		return idx;
	}
	
	public void setFacility(String facility) {
		this.facility = facility;
	}
	
	public String getFacility() {
		return facility;
	}
	
	public void setUrgency(String urgency) {
		this.urgency = urgency;
	}
	
	public String getUrgency() {
		return urgency;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setReporter(String reporter) {
		this.reporter = reporter;
	}
	
	public String getReporter() {
		return reporter;
	}
	
	public void setReportdate(String reportdate) {
		this.reportdate = reportdate;
	}
	
	public String getReportdate() {
		return reportdate;
	}
	
	public void setReporttime(String reporttime) {
		this.reporttime = reporttime;
	}
	
	public String getReporttime() {
		return reporttime;
	}
	
	public void setRepairer(String repairer) {
		this.repairer = repairer;
	}
	
	public String getRepairer() {
		return repairer;
	}

	public void setAssigndate(String assigndate) {
		this.assigndate = assigndate;
	}
	
	public String getAssigndate() {
		return assigndate;
	}
	
	public void validate (String action, MAR mar, MARErrorMsgs errorMsgs) {
		if (action.equalsIgnoreCase("report")) {
			errorMsgs.setDescriptionError(validateDescription(mar.getDescription()));
		}
		else if(action.equalsIgnoreCase("search_fm")) {
			errorMsgs.setIdxError(validateIdx(mar.getIdx()));
			errorMsgs.setRepairerError(validateRepairer(mar.getRepairer()));
			errorMsgs.setReportDateError(validateReportDate(mar.getReportdate()));
		}
		else if(action.equalsIgnoreCase("assign")) {
			errorMsgs.setRepairerError(validateRepairer(mar.getRepairer()));
		}

		errorMsgs.setErrorMsg();
	}
	
	private String validateIdx(String idx) {
		String result="";
		
		if(idx.length()!=0)
		{
			MAR mar = MARsDAO.getMAR(idx);
			
			if(mar.getIdx()==null) 
				result="MAR Number not in database";
		}
		
		return result;
	}
	
	private String validateDescription(String description) {
		String result="";
		
		if(!description.matches("[a-z,\\.\\s]{0,500}"))
			result="Your Description must less than 500 alphabet, ',', space or '.'.";
		
		return result;
	}
	
	private String validateRepairer(String repairer) {
		String result="";
		
		if(repairer.length()!=0)
		{
			User user = UsersDAO.getUser(repairer);
			
			if(user.getUsername()==null || !user.getRole().equalsIgnoreCase("R"))
				result="Repairer (" + repairer + ") not in database";
		}

		return result;
	}
	
	private String validateReportDate(String date) {
		String result="";
		
		if(date.length()!=0)
		{
			try {
				Date d = new SimpleDateFormat("MM/dd/yyyy").parse(date);  
			}
			catch (Exception e) {
				result="Incorrect date format, should MM/dd/yyyy";
			}
		}
		
		return result;
	}	
}