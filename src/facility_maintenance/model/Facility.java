package facility_maintenance.model;

import java.io.Serializable;

public class Facility implements Serializable{
	private String master;
	private int id;
	private String interval;
	private String duration;
	private String venue;
	private String number;

	public void setFacility (String master, int id, String interval, String duration, String venue, String number) {
		setMaster(master);
		setId(id);
		setInterval(interval);
		setDuration(duration);
		setVenue(venue);
		setNumber(number);
	}
	
	public void setMaster(String master) {
		this.master = master;
	}
	
	public String getMaster() {
		return master;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setInterval(String interval) {
		this.interval = interval;
	}
	
	public String getInterval() {
		return interval;
	}
	
	public void setDuration(String duration) {
		this.duration = duration;
	}
	
	public String getDuration() {
		return duration;
	}
	
	public void setVenue(String venue) {
		this.venue = venue;
	}
	
	public String getVenue() {
		return venue;
	}
	
	public void setNumber(String number) {
		this.number = number;
	}
	
	public String getNumber() {
		return number;
	}
	
	public void validate (String action, Facility facility, FacilityErrorMsgs errorMsgs) {
		if (action.equalsIgnoreCase("add")) {
			errorMsgs.setNumberError(validateNumber(facility.getNumber()));
		}
		
		errorMsgs.setErrorMsg();
	}
	
	private String validateNumber(String number) {
		String result="";
		
		if(!number.matches("\\d{1,3}"))
			result="Must be a number.";
		
		return result;
	}
}