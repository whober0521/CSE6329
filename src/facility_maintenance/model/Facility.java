package facility_maintenance.model;

import java.io.Serializable;

public class Facility implements Serializable{
	private String master;
	private int id;
	private int interval;
	private int duration;
	private String venue;
	private String number;

	public void setFacility (String master, int id, int interval, int duration, String venue, String number) {
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
	
	public void setInterval(int interval) {
		this.interval = interval;
	}
	
	public int getInterval() {
		return interval;
	}
	
	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	public int getDuration() {
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