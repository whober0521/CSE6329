package facility_maintenance.model;

public class MARErrorMsgs {
	private String errorMsg;
	private String idxError;
	private String descriptionError;
	
	public MARErrorMsgs() {
		this.errorMsg = "";
		this.descriptionError = "";
	}
	
	public void setErrorMsg() {
		if (!descriptionError.equals(""))
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
}