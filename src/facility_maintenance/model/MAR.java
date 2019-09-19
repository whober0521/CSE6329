package facility_maintenance.model;

import java.io.Serializable;

import facility_maintenance.data.UsersDAO;

public class MAR implements Serializable{
	private int idx;
	private String facility;
	private String urgency;
	private String description;
	
	public void setMAR (int idx, String facility, String urgency, String description) {
		setIdx(idx);
		setFacility(facility);
		setUrgency(urgency);
		setDescription(description);
	}
	
	public void setIdx(int idx) {
		this.idx = idx;
	}
	
	public int getIdx() {
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
	
	public void validate (String action, MAR mar, MARErrorMsgs errorMsgs) {
		errorMsgs.setFacilityError(validateFacility(mar.getFacility()));
		errorMsgs.setUrgencyError(validateUrgency(mar.getUrgency()));
		errorMsgs.setDescriptionError(validateDescription(mar.getDescription()));
		
		errorMsgs.setErrorMsg();
	}
	
	private String validateFacility(String facility) {
		String result="";
		
		if(facility.length()==0)
			result="Please select a Facility.";
		
		return result;
	}
	
	private String validateUrgency(String urgency) {
		String result="";
		
		if(urgency.length()==0)
			result="Please select Urgency.";
		
		return result;
	}
	
	private String validateDescription(String description) {
		String result="";
		
		if(!description.matches("[a-z,\\.\\s]{0,500}"))
			result="Your Description must less than 500 alphabet, ',', space or '.'.";
		
		return result;
	}
//	
//	private String validateUserName(String action, String username) {
//		String result="";
//		
//		if(!username.matches("[a-zA-Z]{3,20}"))
//			result="Your User Name must between 3 and 20 alphabets.";
//		else {
//			User _user = UsersDAO.getUser(username);
//			
//			if (action.equalsIgnoreCase("register") && _user.getUsername()!=null) {
//				result="User Name already in database";
//			}
//			else if (action.equalsIgnoreCase("login") || action.equalsIgnoreCase("search")) {
//				if(_user.getUsername()==null) {
//					result="User Name not in database";
//				}
//			}
//		}
//		
//		return result;
//	}
//	
//	private String validatePassWord(String password) {
//		String result="";
//		
//		if(!password.matches("[^\\d]{1}\\w{1,19}"))
//			result="Your Password must between 1 and 20 alphabets or numbers and cannot start with digit.";
//		
//		return result;
//	}
//	
//	private String validateRole(String role) {
//		String result="";
//		
//		if(role.length()==0)
//			result="Please select a Role.";
//		
//		return result;
//	}
//	
//	private String validateUTAid(String utaid) {
//		String result="";
//		
//		if(!utaid.matches("\\d{10,10}"))
//			result="Your UTA Id must be 10 numbers.";
//		
//		return result;
//	}
//	
//	private String validateFirstName(String fname) {
//		String result="";
//		
//		if(!fname.matches("[a-zA-Z]{1,40}"))
//			result="Your First Name must between 1 and 40 alphabets.";
//		
//		return result;
//	}
//	
//	private String validateLastName(String lname) {
//		String result="";
//		
//		if(!lname.matches("[a-zA-Z]{1,20}"))
//			result="Your Last Name must between 1 and 20 alphabets.";
//		
//		return result;
//	}
//	
//	private String validateEmail(String email) {
//		String result="";
//		
//		if(!email.matches("[\\w\\.]+@[\\w\\.]+"))
//			result="Your Email must between 2 and 100 alphabets or '.', and must have @ .";
//		
//		return result;
//	}
//	
//	private String validatePhone(String phone) {
//		String result="";
//		
//		if(!phone.matches("\\d{10,10}"))
//			result="Your Phone must be 10 numbers.";
//		
//		return result;
//	}
//	

//	
//	private String validateCity(String city) {
//		String result="";
//		
//		if(city.length()==0)
//			result="Please select a City.";
//		
//		return result;
//	}

}