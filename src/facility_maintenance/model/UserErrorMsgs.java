package facility_maintenance.model;

public class UserErrorMsgs {
	private String errorMsg;
	private String usernameError;
	private String passwordError;
	private String roleError;
	private String utaidError;
	private String fnameError;
	private String lnameError;
	private String emailError;
	private String phoneError;
	private String addressError;
	private String cityError;
	private String stateError;
	
	public UserErrorMsgs() {
		this.errorMsg = "";
		this.usernameError = "";
		this.passwordError = "";
		this.roleError = "";
		this.utaidError = "";
		this.fnameError = "";
		this.lnameError = "";
		this.emailError = "";
		this.phoneError = "";
		this.addressError = "";
		this.cityError = "";
		this.stateError = "";
	}
	
	public void setErrorMsg() {
		if (!usernameError.equals("") || !passwordError.equals("") || !roleError.equals("") || !utaidError.equals("") ||
				!fnameError.equals("") || !lnameError.equals("") || !emailError.equals("") || !phoneError.equals("") ||
				!addressError.equals("") || !cityError.equals("") || !stateError.equals(""))
			this.errorMsg = "Please correct the following errors";
	}
	
	public String getErrorMsg() {
		return errorMsg;
	}
	
	public void setUsernameError(String usernameError) {
		this.usernameError = usernameError;
	}
	
	public String getUsernameError() {
		return usernameError;
	}
	
	public void setPasswordError(String passwordError) {
		this.passwordError = passwordError;
	}
	
	public String getPasswordError() {
		return passwordError;
	}
	
	public void setRoleError(String roleError) {
		this.roleError = roleError;
	}
	
	public String getRoleError() {
		return roleError;
	}
	
	public void setUtaidError(String utaidError) {
		this.utaidError = utaidError;
	}
	
	public String getUtaidError() {
		return utaidError;
	}
	
	public void setFnameError(String fnameError) {
		this.fnameError = fnameError;
	}
	
	public String getFnameError() {
		return fnameError;
	}
	
	public void setLnameError(String lnameError) {
		this.lnameError = lnameError;
	}
	
	public String getLnameError() {
		return lnameError;
	}
	
	public void setEmailError(String emailError) {
		this.emailError = emailError;
	}
	
	public String getEmailError() {
		return emailError;
	}
	
	public void setPhoneError(String phoneError) {
		this.phoneError = phoneError;
	}
	
	public String getPhoneError() {
		return phoneError;
	}
	
	public void setAddressError(String addressError) {
		this.addressError = addressError;
	}
	
	public String getAddressError() {
		return addressError;
	}
	
	public void setCityError(String cityError) {
		this.cityError = cityError;
	}
	
	public String getCityError() {
		return cityError;
	}
	
	public void setStateError(String stateError) {
		this.stateError = stateError;
	}
	
	public String getStateError() {
		return stateError;
	}
}