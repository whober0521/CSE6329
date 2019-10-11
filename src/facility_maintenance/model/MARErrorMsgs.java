package facility_maintenance.model;

public class MARErrorMsgs {
	private String errorMsg;
	private String descriptionError;
	private String urgencyError;
	private String repairerError;
	private String estimateError;
	
	public MARErrorMsgs() {
		this.errorMsg = "";
		this.descriptionError = "";
	}
	
	public void setErrorMsg() {
		if (!descriptionError.equals("") || !urgencyError.equals("") || !repairerError.equals("") || !estimateError.equals(""))
			this.errorMsg = "Please correct the following errors";
	}
	
	public String getErrorMsg() {
		return errorMsg;
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
}