package facility_maintenance.model;

public class MARErrorMsgs {
	private String errorMsg;
	private String idxError;
	private String facilityError;
	private String urgencyError;
	private String descriptionError;
	
	public MARErrorMsgs() {
		this.errorMsg = "";
		this.idxError = "";
		this.facilityError = "";
		this.urgencyError = "";
		this.descriptionError = "";
	}
	
	public void setErrorMsg() {
		if (!idxError.equals("") || !facilityError.equals("") || !urgencyError.equals("") || !descriptionError.equals(""))
			this.errorMsg = "Please correct the following errors";
	}
	
	public String getErrorMsg() {
		return errorMsg;
	}
	
	public void setIdxError(String idxError) {
		this.idxError = idxError;
	}
	
	public String getIdxError() {
		return idxError;
	}
	
	public void setFacilityError(String facilityError) {
		this.facilityError = facilityError;
	}
	
	public String getFacilityError() {
		return facilityError;
	}
	
	public void setUrgencyError(String urgencyError) {
		this.urgencyError = urgencyError;
	}
	
	public String getUrgencyError() {
		return urgencyError;
	}
	
	public void setDescriptionError(String descriptionError) {
		this.descriptionError = descriptionError;
	}
	
	public String getDescriptionError() {
		return descriptionError;
	}
}