package facility_maintenance.model;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import facility_maintenance.data.UsersDAO;
import facility_maintenance.data.MARsDAO;

public class MAR implements Serializable{
	private String idx;
	private String facilitytype;
	private String facilityname;
	private String description;
	private String urgency;
	private String reporter;
	private String reportdate;
	private String reporttime;
	private String repairer;
	private String assigndate;
	private String assigntime;
	private String estimate;

	public void setMAR (String idx, String facilitytype, String facilityname, String description, String urgency, 
			String reporter, String reportdate, String reporttime,
			String repairer, String assigndate, String assigntime, String estimate) {
		setIdx(idx);
		setFacilitytype(facilitytype);
		setFacilityname(facilityname);
		setDescription(description);
		setUrgency(urgency);
		setReporter(reporter);
		setReportdate(reportdate);
		setReporttime(reporttime);
		setRepairer(repairer);
		setAssigndate(assigndate);
		setAssigntime(assigntime);
		setEstimate(estimate);
	}
	
	public void setIdx(String idx) {
		this.idx = idx;
	}
	
	public String getIdx() {
		return idx;
	}
	
	public void setFacilitytype(String facilitytype) {
		this.facilitytype = facilitytype;
	}
	
	public String getFacilitytype() {
		return facilitytype;
	}
	
	public void setFacilityname(String facilityname) {
		this.facilityname = facilityname;
	}
	
	public String getFacilityname() {
		return facilityname;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setUrgency(String urgency) {
		this.urgency = urgency;
	}
	
	public String getUrgency() {
		return urgency;
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
	
	public void setAssigntime(String assigntime) {
		this.assigntime = assigntime;
	}
	
	public String getAssigntime() {
		return assigntime;
	}
	
	public void setEstimate(String estimate) {
		this.estimate = estimate;
	}
	
	public String getEstimate() {
		return estimate;
	}
	
	public void validate (String action, MAR mar, MARErrorMsgs errorMsgs) {
		if (action.equalsIgnoreCase("report")) {
			errorMsgs.setDescriptionError(validateDescription(mar.getDescription()));
		}
		else if (action.equalsIgnoreCase("assign")) {
			errorMsgs.setUrgencyError(validateUrgency(mar.getUrgency()));
			errorMsgs.setRepairerError(validateRepairer(mar.getRepairer()));
			errorMsgs.setEstimateError(validateEstimate(mar.getEstimate()));
		}

		errorMsgs.setErrorMsg();
	}
	
	private String validateDescription(String description) {
		String result="";
		
		if(!description.matches("[a-z,\\.\\s]{0,500}"))
			result="Your Description must less than 500 alphabet, ',', space or '.'.";
		
		return result;
	}
	
	private String validateUrgency(String urgency) {
		String result="";
		
		if(urgency.equals(""))
			result="'Urgency' is required";
		
		return result;
	}
	
	private String validateRepairer(String repairer) {
		String result="";
		
		if(repairer.equals(""))
			result="'Assigned to' is required";
		
		return result;
	}
	
	private String validateEstimate(String estimate) {
		String result="";
		
		if(estimate.equals(""))
			result="'Estimate of repair' is required";
		
		return result;
	}
	
	public HashMap<String, String> getUrgencies(String urgency) {
		HashMap<String, String> result = new HashMap<String, String>();
		String[] urgencies = {
				"Unusable", 
				"Major", 
				"Medium", 
				"Minor"};

		for (String u : urgencies) {
			result.put(u, (urgency.equals(u)) ? "selected" : "");
		}
		
		return result;
	}
	
	public HashMap<String, String> getEstimates(String estimate) {
		HashMap<String, String> result = new HashMap<String, String>();
		String[] estimates = {
				"30 mins", 
				"1 hour", 
				"2 hours",
				"4 hours", 
				"1 day", 
				"2 days",
				"4 days",
				"7 days"};
		
		for (String e : estimates) {
			result.put(e, (estimate.equals(e)) ? "selected" : "");
		}
		
		return result;
	}
}