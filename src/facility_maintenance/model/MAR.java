package facility_maintenance.model;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import facility_maintenance.data.UsersDAO;
import facility_maintenance.data.FacilitiesDAO;
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
	private String repairdate;
	private String starttime;
	private String endtime;

	public void setMAR (String idx, String facilitytype, String facilityname, String description, String urgency, 
			String reporter, String reportdate, String reporttime,
			String repairer, String assigndate, String assigntime, String estimate,
			String repairdate, String starttime, String endtime) {
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
		setRepairdate(repairdate);
		setStarttime(starttime);
		setEndtime(endtime);
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
	
	public void setRepairdate(String repairdate) {
		this.repairdate = repairdate;
	}
	
	public String getRepairdate() {
		return repairdate;
	}
	
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	
	public String getStarttime() {
		return starttime;
	}
	
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	
	public String getEndtime() {
		return endtime;
	}
	
	public void validate (String action, MAR mar, MARErrorMsgs errorMsgs) {
		//if (action.equals("report")) {
		if (action.compareTo("report") == 0) {
			errorMsgs.setDescriptionError(validateDescription(mar.getDescription()));
		}
		//else if (action.equals("assign")) {
		else if (action.compareTo("assign") == 0) {
			errorMsgs.setUrgencyError(validateUrgency(mar.getUrgency()));
			errorMsgs.setRepairerError(validateRepairer(mar.getRepairer()));
			errorMsgs.setEstimateError(validateEstimate(mar.getEstimate()));
		}
		//else if (action.equals("request")) {
		else if (action.compareTo("request") == 0) {
			errorMsgs.setNameError(validateFacilityName(mar));
			//errorMsgs.setDateTimeError(validateDateTime(mar.getFacilityname(), mar.getRepairdate(), mar.getStarttime()));
			errorMsgs.setDateTimeError(validateDateTime(mar.getFacilityname(), mar.getRepairdate()));
		}

		errorMsgs.setErrorMsg();
	}
	
	public String validateFacilityName(MAR mar) {
		String result="";
		
		ArrayList<MAR> mars = MARsDAO.getAssigned(mar);
		
		if(mars.size()==0)
			result="No assigned MAR found";
		
		return result;
	}
	
	public String validateDescription(String description) {
		String result="";
		
		if(!description.matches("[a-z,\\.\\s]{0,500}"))
			result="Your Description must less than 500 alphabet, ',', space or '.'.";
		
		return result;
	}
	
	public String validateUrgency(String urgency) {
		String result="";
		
		if(urgency.equals(""))
			result="'Urgency' is required";
		
		return result;
	}
	
	public String validateRepairer(String repairer) {
		String result="";
		
		if(repairer.equals(""))
			result="'Assigned to' is required";
		else if(MARsDAO.getAssignedNumber(repairer, getDate()) >= 5)
			result="'Each repairer may only be assigned  5 repairs per day";
		else if(MARsDAO.getAssignedNumber(repairer) >= 10)
			result="'Each repairer may only be assigned  10 repairs per week (Monday - Sunday)";

		return result;
	}
	
	public String validateEstimate(String estimate) {
		String result="";
		
		if(estimate.equals(""))
			result="'Estimate of repair' is required";
		
		return result;
	}
	
	//TODO : why we need time here?
	/*
	public String validateDateTime(String facilityname, String repairdate, String starttime) {
		String result="";
		
		Facility facility = FacilitiesDAO.getDetail(facilityname);
		int duration = Integer.parseInt(facility.getDuration().split(" ")[0]);

		Date expire = new Date();

		Calendar c = Calendar.getInstance(); 
		
		c.setTime(expire); 
		c.add(Calendar.DATE, duration);
		
		expire = c.getTime();
		
		String expiretime = new SimpleDateFormat("yyyy-MM-dd").format(expire) +
							new SimpleDateFormat(" HH:mm").format(Calendar.getInstance().getTime());

		if((repairdate + " " + starttime).compareTo(expiretime) > 0)
			result="Latest time: " + expiretime;

		return result;
	}
	*/
	public String validateDateTime(String facilityname, String repairdate) {
		String result="";
		
		if ( false == facilityname.isEmpty() )
		{
			Facility facility = FacilitiesDAO.getDetail(facilityname);
			int duration = Integer.parseInt(facility.getDuration().split(" ")[0]);

			Date expire = new Date();

			Calendar c = Calendar.getInstance(); 

			c.setTime(expire); 
			c.add(Calendar.DATE, duration);

			expire = c.getTime();

			String expiretime = new SimpleDateFormat("yyyy-MM-dd").format(expire);

			if(repairdate.compareTo(expiretime) > 0)
				result="Latest time: " + expiretime;
		}
		else
		{
			result = "Empty facility name";
		}

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
			result.put(u, (u.equals(urgency)) ? "selected" : "");
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
			result.put(e, (e.equals(estimate)) ? "selected" : "");
		}
		
		return result;
	}
	
	public String getDate() {
		return new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
	}
	
	public HashMap<String, String> getTime(String time) {
		HashMap<String, String> result = new HashMap<String, String>();
		String[] times = {
				"00:00",
				"01:00",
				"02:00",
				"03:00",
				"04:00",
				"05:00",
				"06:00",
				"07:00",
				"08:00",
				"09:00",
				"10:00",
				"11:00",
				"12:00",
				"13:00",
				"14:00",
				"15:00",
				"16:00",
				"17:00",
				"18:00",
				"19:00",
				"20:00",
				"21:00",
				"22:00",
				"23:00"};

		if(time.equals(""))
			time = new SimpleDateFormat("HH:00").format(Calendar.getInstance().getTime());

		for (String t : times) {
			result.put(t, (t.equals(time)) ? "selected" : "");
		}
		
		return result;
	}
}
