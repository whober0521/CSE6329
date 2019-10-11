package facility_maintenance.model;

public class MARErrorMsgs {
	private String errorMsg;
	private String nameError;
	private String descriptionError;
	private String urgencyError;
	private String repairerError;
	private String estimateError;
	private String datetimeError;
	
	public MARErrorMsgs() {
		this.errorMsg = "";
		this.nameError = "";
		this.descriptionError = "";
		this.urgencyError = "";
		this.repairerError = "";
		this.estimateError = "";
		this.datetimeError = "";
	}
	
	public void setErrorMsg() {
		if (!nameError.equals("") || !descriptionError.equals("") || !urgencyError.equals("") || 
			!repairerError.equals("") || !estimateError.equals("") || !datetimeError.equals(""))
			this.errorMsg = "Please correct the following errors";
	}
	
	public String getErrorMsg() {
		return errorMsg;
	}
	
	public void setNameError(String nameError) {
		this.nameError = nameError;
	}
	
	public String getNameError() {
		return nameError;
	}
	
	public void setDescriptionError(String descriptionError) {
		this.descriptionError = descriptionError;
	}
	
	public String getDescriptionError() {
		return descriptionError;
	}
	
	public void setUrgencyError(String urgencyError) {
		this.urgencyError = urgencyError;
	}
	
	public String getUrgencyError() {
		return urgencyError;
	}
	
	public void setRepairerError(String repairerError) {
		this.repairerError = repairerError;
	}
	
	public String getRepairerError() {
		return repairerError;
	}
	
	public void setEstimateError(String estimateError) {
		this.estimateError = estimateError;
	}
	
	public String getEstimateError() {
		return estimateError;
	}
	
	public void setDateTimeError(String datetimeError) {
		this.datetimeError = datetimeError;
	}
	
	public String getDatetimeError() {
		return datetimeError;
	}
}