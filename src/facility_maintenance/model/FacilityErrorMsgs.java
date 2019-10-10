package facility_maintenance.model;

public class FacilityErrorMsgs {
	private String errorMsg;
	private String numberError;
	
	public FacilityErrorMsgs() {
		this.errorMsg = "";
		this.numberError = "";
	}
	
	public void setErrorMsg() {
		if (!numberError.equals(""))
			this.errorMsg = "Please correct the following errors";
	}
	
	public String getErrorMsg() {
		return errorMsg;
	}
	
	public void setNumberError(String numberError) {
		this.numberError = numberError;
	}
	
	public String getNumberError() {
		return numberError;
	}
}