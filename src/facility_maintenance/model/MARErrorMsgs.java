package facility_maintenance.model;

public class MARErrorMsgs {
	private String errorMsg;
	private String idxError;
	private String facilityTypeError;
	private String facilityNameError;
	private String urgencyError;
	private String descriptionError;
	private String repairerError;
	private String reportDateError;
	private String reportTimeError;
	
	public MARErrorMsgs() {
		this.errorMsg = "";
		this.idxError = "";
		this.facilityTypeError = "";
		this.facilityNameError = "";
		this.urgencyError = "";
		this.descriptionError = "";
		this.repairerError = "";
		this.reportDateError = "";
		this.reportTimeError = "";
	}
	
	public void setErrorMsg() {
		if (!idxError.equals("") || !facilityTypeError.equals("") || !facilityNameError.equals("") ||
				!urgencyError.equals("") || !descriptionError.equals("") || !repairerError.equals("") ||
				!reportDateError.equals("") || !reportTimeError.equals(""))
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
	
	public void setFacilityTypeError(String facilityTypeError) {
		this.facilityTypeError = facilityTypeError;
	}
	
	public String getFacilityTypeError() {
		return facilityTypeError;
	}
	
	public void setFacilityNameError(String facilityNameError) {
		this.facilityNameError = facilityNameError;
	}
	
	public String getFacilityNameError() {
		return facilityNameError;
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
	
	public void setRepairerError(String repairerError) {
		this.repairerError = repairerError;
	}
	
	public String getRepairerError() {
		return repairerError;
	}
	
	public void setReportDateError(String reportDateError) {
		this.reportDateError = reportDateError;
	}
	
	public String getReportDateError() {
		return reportDateError;
	}
	
	public void setReportTimeError(String reportTimeError) {
		this.reportTimeError = reportTimeError;
	}
	
	public String getReportTimeError() {
		return reportTimeError;
	}
}