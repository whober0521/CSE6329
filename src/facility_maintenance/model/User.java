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
		setUserName(username);
		setPassWord(password);
		setRole(role);
		setUTAid(utaid);
		setFirstName(fname);
		setLastName(lname);
		setEmail(email);
		setPhone(phone);
		setAddress(address);
		setCity(city);
		setState(state);
	}
	
	public void setUserName(String username) {
		this.username = username;
	}
	
	public String getUserName() {
		return username;
	}
	
	public void setPassWord(String password) {
		this.password = password;
	}
	
	public String getPassWord() {
		return password;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
	
	public String getRole() {
		return role;
	}
	
	public void setUTAid(String utaid) {
		this.utaid = utaid;
	}
	
	public String getUTAid() {
		return utaid;
	}
	
	public void setFirstName(String fname) {
		this.fname = fname;
	}
	
	public String getFirstName() {
		return fname;
	}
	
	public void setLastName(String lname) {
		this.lname = lname;
	}
	
	public String getLastName() {
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
	
	

//	public void validateCompany (String action, Company company, CompanyErrorMsgs errorMsgs) {
//		if (action.equals("saveCompany")) {
//			errorMsgs.setCompanyIDerror(validateIdcompany(action,company.getIdcompany()));
//			errorMsgs.setCompanyNameError(validateCompany_name(company.getCompany_name()));
//			errorMsgs.setPhoneError(validatePhone(company.getPhone()));
//			errorMsgs.setEmailError(validateEmail(company.getEmail()));
//			errorMsgs.setErrorMsg();
//		}
//		else
//			if (action.equals("searchCompany")) {
//				if (company_name.equals("") && idcompany.equals("")) 
//					errorMsgs.setCompanyNameError("Both Company Name and Company ID cannot be blank");
//				else
//					if (!idcompany.equals(""))
//						errorMsgs.setCompanyIDerror(validateIdcompany(action, idcompany));
//				errorMsgs.setErrorMsg();				
//			}
//			else { //action=searchEmployee
//				if (idcompany.equals("")) 
//					errorMsgs.setCompanyIDerror("Company ID cannot be blank");
//				else
//					errorMsgs.setCompanyIDerror(validateIdcompany(action,idcompany));
//				errorMsgs.setErrorMsg();
//			}
//	}
//
//	private String validateIdcompany(String action, String idcompany) {
//		String result="";
//		if (!isTextAnInteger(idcompany))
//			result="Your company ID must be a number";
//		else
//			if (action.equals("saveCompany")) {
//				if (!stringSize(idcompany,3,16))
//					result= "Your Company Id must between 3 and 16 digits";
//				else
//					if (!CompanyDAO.CompanyIDunique(idcompany))
//						result="Company ID already in database";
//			}
//		return result;
//	}
//	
//	private String validateCompany_name(String company_name) {
//		String result="";
//		if (!stringSize(company_name,3,45))
//			result= "Your Company Name must between 3 and 45 digits";
//		else
//			if (Character.isLowerCase(company_name.charAt(0)))
//				result="Your company name must start with a capital letter";
//		return result;		
//	}
//	
//	private String validatePhone(String phone) {
//		String result="";
//		if (phone.length()!=10)
//			result="Phone number must be 10 digits in length";
//		else
//			if (!isTextAnInteger(phone))
//				result="Phone number must be a number";
//		return result;		
//	}
//	
//	private String validateEmail(String email) {
//		String result="",extension="";
//		if (!email.contains("@"))
//			result = "Email address needs to contain @";
//		else
//			if (!stringSize(email,7,45))
//				result="Email address must be between 7 and 45 characters long";
//			else {
//				extension = email.substring(email.length()-4, email.length());
//				if (!extension.equals(".org") && !extension.equals(".edu") && !extension.equals(".com") 
//						&& !extension.equals(".net") && !extension.equals(".gov") && !extension.equals(".mil"))
//					result = "Invalid domain name";				
//			}
//		return result;		
//	}
//
////	This section is for general purpose methods used internally in this class
//	
//	private boolean stringSize(String string, int min, int max) {
//		return string.length()>=min && string.length()<=max;
//	}
//	private boolean isTextAnInteger (String string) {
//        boolean result;
//		try
//        {
//            Long.parseLong(string);
//            result=true;
//        } 
//        catch (NumberFormatException e) 
//        {
//            result=false;
//        }
//		return result;
//	}
}