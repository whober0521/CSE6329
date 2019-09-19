package facility_maintenance.model;

import java.io.Serializable;
import facility_maintenance.data.UsersDAO;

public class User implements Serializable{
	private String username;
	private String password;
	private String role;
	private String utaid;
	private String fname;
	private String lname;
	private String email;
	private String phone;
	private String address;
	private String city;
	private String state;
	
	public void setUser (String username, String password, String role, String utaid, 
			String fname, String lname, String email, String phone, String address, String city, String state) {
		setUsername(username);
		setPassword(password);
		setRole(role);
		setUtaid(utaid);
		setFname(fname);
		setLname(lname);
		setEmail(email);
		setPhone(phone);
		setAddress(address);
		setCity(city);
		setState(state);
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
	
	public String getRole() {
		return role;
	}
	
	public void setUtaid(String utaid) {
		this.utaid = utaid;
	}
	
	public String getUtaid() {
		return utaid;
	}
	
	public void setFname(String fname) {
		this.fname = fname;
	}
	
	public String getFname() {
		return fname;
	}
	
	public void setLname(String lname) {
		this.lname = lname;
	}
	
	public String getLname() {
		return lname;
	}	
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getEmail() {
		return email;
	}	
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getPhone() {
		return phone;
	}	
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getAddress() {
		return address;
	}	
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getCity() {
		return city;
	}	
	
	public void setState(String state) {
		this.state = state;
	}
	
	public String getState() {
		return state;
	}
	
	public void validate (String action, User user, UserErrorMsgs errorMsgs) {
		errorMsgs.setUsernameError(validateUserName(action, user.getUsername()));
		
		if (action.equalsIgnoreCase("register") || action.equalsIgnoreCase("update")) {
			errorMsgs.setPasswordError(validatePassWord(user.getPassword()));
			errorMsgs.setRoleError(validateRole(user.getRole()));
			errorMsgs.setUtaidError(validateUTAid(user.getUtaid()));
			errorMsgs.setFnameError(validateFirstName(user.getFname()));
			errorMsgs.setLnameError(validateLastName(user.getLname()));
			errorMsgs.setEmailError(validateEmail(user.getEmail()));
			errorMsgs.setPhoneError(validatePhone(user.getPhone()));
			errorMsgs.setAddressError(validateAddress(user.getAddress()));
			errorMsgs.setCityError(validateCity(user.getCity()));
			errorMsgs.setStateError(validateState(user.getState()));
		}
		else if (action.equalsIgnoreCase("login")) {
			errorMsgs.setPasswordError(validatePassWord(user.getPassword()));
			
			if(errorMsgs.getUsernameError().equals("") && errorMsgs.getPasswordError().equals("")) {
				User _user = UsersDAO.getUser(user.getUsername());
				
				if(!_user.getPassword().equals(user.getPassword()))
					errorMsgs.setPasswordError("Incorrect Password");			
			}
		}
		
		errorMsgs.setErrorMsg();
	}
	
	private String validateUserName(String action, String username) {
		String result="";
		
		if(!username.matches("[a-zA-Z]{3,20}"))
			result="Your User Name must between 3 and 20 alphabets.";
		else {
			User _user = UsersDAO.getUser(username);
			
			if (action.equalsIgnoreCase("register") && _user.getUsername()!=null) {
				result="User Name already in database";
			}
			else if (action.equalsIgnoreCase("login") || action.equalsIgnoreCase("search")) {
				if(_user.getUsername()==null) {
					result="User Name not in database";
				}
			}
		}
		
		return result;
	}
	
	private String validatePassWord(String password) {
		String result="";
		
		if(!password.matches("[^\\d]{1}\\w{1,19}"))
			result="Your Password must between 1 and 20 alphabets or numbers and cannot start with digit.";
		
		return result;
	}
	
	private String validateRole(String role) {
		String result="";
		
		if(role.length()==0)
			result="Please select a Role.";
		
		return result;
	}
	
	private String validateUTAid(String utaid) {
		String result="";
		
		if(!utaid.matches("\\d{10,10}"))
			result="Your UTA Id must be 10 numbers.";
		
		return result;
	}
	
	private String validateFirstName(String fname) {
		String result="";
		
		if(!fname.matches("[a-zA-Z]{1,40}"))
			result="Your First Name must between 1 and 40 alphabets.";
		
		return result;
	}
	
	private String validateLastName(String lname) {
		String result="";
		
		if(!lname.matches("[a-zA-Z]{1,20}"))
			result="Your Last Name must between 1 and 20 alphabets.";
		
		return result;
	}
	
	private String validateEmail(String email) {
		String result="";
		
		if(!email.matches("[\\w\\.]+@[\\w\\.]+"))
			result="Your Email must between 2 and 100 alphabets or '.', and must have @ .";
		
		return result;
	}
	
	private String validatePhone(String phone) {
		String result="";
		
		if(!phone.matches("\\d{10,10}"))
			result="Your Phone must be 10 numbers.";
		
		return result;
	}
	
	private String validateAddress(String address) {
		String result="";
		
		if(!address.matches("[\\w\\s\\.]{1,200}"))
			result="Your Address must between 1 and 200 alphabets or spaces.";
		
		return result;
	}
	
	private String validateCity(String city) {
		String result="";
		
		if(city.length()==0)
			result="Please select a City.";
		
		return result;
	}
	
	private String validateState(String state) {
		String result="";
		
		if(state.length()==0)
			result="Please select a State.";
		
		return result;
	}
}