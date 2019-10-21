package test;

import static org.junit.Assert.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import facility_maintenance.model.MAR;
import facility_maintenance.model.Facility;
import facility_maintenance.model.MARErrorMsgs;
import facility_maintenance.data.MARsDAO;
import facility_maintenance.data.FacilitiesDAO;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;

/*
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;

import static org.powermock.api.easymock.PowerMock.*;
*/

import java.util.ArrayList;

@RunWith(JUnitParamsRunner.class)
//@PowerMockRunnerDelegate(JUnitParamsRunner.class)
public class MARTest {
	private MAR mar;
	private static final int defaultDuration = 1;

	@Before
	public void setUp() throws Exception {
		mar = new MAR();
		mar.setIdx("");
		mar.setFacilitytype("");
		mar.setFacilityname("");
		mar.setAssigndate("");
		mar.setAssigntime("");
		mar.setEstimate("");
		mar.setUrgency("");
	}

	/*
	@Test
	@FileParameters("TestCaseTable_CSV/MAR_validate.csv")
	public void testValidate(int testcaseNum, String action, String expectMsg) {
		action = action.replace("\"", "");
		expectMsg = expectMsg.replace("\"", "");
		mar.setDescription("111");
		mar.setIdx("");
		mar.setFacilitytype("");
		mar.setFacilityname("BMC1");
		mar.setAssigndate("");
		mar.setAssigntime("");
		mar.setEstimate("");
		mar.setUrgency("");
		mar.setRepairer("r1");
		MARErrorMsgs marErrMsg = new MARErrorMsgs();
		mar.validate(action, mar, marErrMsg);
		assertEquals(expectMsg, marErrMsg.getErrorMsg());
	}
	*/
	@Test
	@FileParameters("TestCaseTable_CSV/MAR_validateReportAction.csv")
	public void testValidateReportAction(int testcaseNum, String desc, String expectMsg) 
	{
		desc = desc.replace("\"", "");
		expectMsg = expectMsg.replace("\"", "");
		mar.setDescription(desc);
		MARErrorMsgs marErrMsg = new MARErrorMsgs();
		mar.validate("report", mar, marErrMsg);
		assertEquals(expectMsg, marErrMsg.getDescriptionError());
	}

	@Test
	@FileParameters("TestCaseTable_CSV/MAR_validateAllPossibleReportStr.csv")
	public void testValidateAllPossibleReportStr(int testcaseNum, String report) 
	{
		report = report.replace("\"", "");
		mar.setDescription("123");
		MARErrorMsgs marErrMsg = new MARErrorMsgs();
		mar.validate(report, mar, marErrMsg);
		assertEquals("Your Description must less than 500 alphabet, ',', space or '.'.", marErrMsg.getDescriptionError());
	}

	@Test
	@FileParameters("TestCaseTable_CSV/MAR_validateAssignAction.csv")
	public void testValidateAssignAction(
			int testcaseNum,
			String urgency,
			String repairer, 
			String estimate,
			String uMsg,
			String rMsg,
			String eMsg) 
	{
		urgency = urgency.replace("\"", "");
		repairer = repairer.replace("\"", "");
		estimate = estimate.replace("\"", "");
		uMsg = uMsg.replace("\"", "");
		rMsg = rMsg.replace("\"", "");
		eMsg = eMsg.replace("\"", "");
		mar.setUrgency(urgency);
		mar.setRepairer(repairer);
		mar.setEstimate(estimate);
		MARErrorMsgs marErrMsg = new MARErrorMsgs();
		mar.validate("assign", mar, marErrMsg);
		assertEquals(uMsg, marErrMsg.getUrgencyError());
		assertEquals(rMsg, marErrMsg.getRepairerError());
		assertEquals(eMsg, marErrMsg.getEstimateError());
	}

	/*
	@Test
	@FileParameters("TestCaseTable_CSV/MAR_validateRequestAction.csv")
	public void testValidateRequestAction(
			int testcaseNum,
			String urgency,
			String repairer, 
			String estimate,
			String uMsg,
			String rMsg,
			String eMsg) 
	{
		// TODO: wait for validateDateTime
	}
	*/
	@Test
	public void testValidateNoAction()
	{
		MARErrorMsgs marErrMsg = new MARErrorMsgs();
		mar.validate("", mar, marErrMsg);
		assertEquals("", marErrMsg.getErrorMsg());
	}

	@Test
	@FileParameters("TestCaseTable_CSV/MAR_validateFacilityName.csv")
	public void testValidateFacilityName(int testcaseNum, String repairer, String expectMsg) {
		expectMsg = expectMsg.replace("\"", "");
		repairer = repairer.replace("\"", "");
		mar.setRepairer(repairer);
		assertEquals(expectMsg, mar.validateFacilityName(mar));
	}
	
	@Test
	@FileParameters("TestCaseTable_CSV/MAR_validateDescription.csv")
	public void testValidateDescription(int testcaseNum, String desc, String expectMsg) {
		expectMsg = expectMsg.replace("\"", "");
		desc = desc.replace("\"", "");
		assertEquals(expectMsg, mar.validateDescription(desc));
	}

	@Test
	@FileParameters("TestCaseTable_CSV/MAR_validateUrgency.csv")
	public void testValidateUrgency(int testcaseNum, String urgency, String expectMsg) {
		expectMsg = expectMsg.replace("\"", "");
		urgency = urgency.replace("\"", "");
		assertEquals(expectMsg, mar.validateUrgency(urgency));
	}

	/*
	@Test
	@FileParameters("TestCaseTable_CSV/MAR_validateRepairerAssignedMAR.csv")
	public void testValidateRepairerAssignedMAR(
			int testcaseNum,
			String repairerName,
			String date,
			String expectMsg) {
		//TODO: I need to specify date in getAssignedNumber(repairer)
		expectMsg = expectMsg.replace("\"", "");
		repairerName = repairerName.replace("\"", "");
		date = date.replace("\"", "");
		EasyMock.expect(MAR.getDate()).andReturn(date);
		replayAll();
		assertEquals(expectMsg, mar.validateRepairer(repairerName));
	}
	*/

	@Test
	@FileParameters("TestCaseTable_CSV/MAR_validateEstimate.csv")
	public void testValidateEstimate(int testcaseNum, String estimateStr, String expectMsg) {
		expectMsg = expectMsg.replace("\"", "");
		estimateStr = estimateStr.replace("\"", "");
		assertEquals(expectMsg, mar.validateEstimate(estimateStr));
	}

	/*
	@Test
	@PrepareForTest(FacilitiesDAO.class)
	@FileParameters("TestCaseTable_CSV/MAR_validateDateTime.csv")
	public void testValidateDateTime(int testcaseNum, int duration, int lenOfMsg) {
		//TODO: I need to specify date in validateDatTime
		mockStatic(FacilitiesDAO.class);
		Facility f = new Facility();
		f.setDuration(Integer.toString(defaultDuration) + " 0");
		EasyMock.expect(FacilitiesDAO.getDetail("")).andReturn(f);
		replayAll();

		Date expire = new Date();
		Calendar c = Calendar.getInstance(); 
		c.setTime(expire); 
		c.add(Calendar.DATE, defaultDuration + duration);
		expire = c.getTime();

		String repairdate = new SimpleDateFormat("yyyy-MM-dd").format(expire);
		String starttime = new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime());
		assertEquals(lenOfMsg, mar.validateDateTime("", repairdate, starttime).length());
	}
	*/

	@Test
	@FileParameters("TestCaseTable_CSV/MAR_getUrgencies.csv")
	public void testGetUrgencies(int testcaseNum, String urgency) {
		urgency = urgency.replace("\"", "");
		assertEquals("selected", mar.getUrgencies(urgency).get(urgency));
	}

	@Test
	public void testGetEmptyUrgency() {
		HashMap<String, String> expect = new HashMap<String, String>();
		expect.put("Unusable", "");
		expect.put("Major", "");
		expect.put("Medium", "");
		expect.put("Minor", "");
		assertEquals(expect, mar.getUrgencies(""));
	}

	@Test
	@FileParameters("TestCaseTable_CSV/MAR_getEstimates.csv")
	public void testGetEstimates(int testcaseNum, String estimate) {
		estimate = estimate.replace("\"", "");
		assertEquals("selected", mar.getEstimates(estimate).get(estimate));
	}

	@Test
	public void testGetEstimatesEmpty() {
		HashMap<String, String> expect = new HashMap<String, String>();
		expect.put("30 mins", "");
		expect.put("1 hour", "");
		expect.put("2 hours", "");
		expect.put("4 hours", "");
		expect.put("1 day", "");
		expect.put("2 days", "");
		expect.put("4 days", "");
		expect.put("7 days", "");
		assertEquals(expect, mar.getEstimates(""));
	}

	@Test
	@FileParameters("TestCaseTable_CSV/MAR_getTime.csv")
	public void testGetTime(int testcaseNum, String time) {
		time = time.replace("\"", "");
		assertEquals("selected", mar.getTime(time).get(time));
	}

	@Test
	public void testGetTimeEmpty() {
		String expect = new SimpleDateFormat("HH:00").format(Calendar.getInstance().getTime());

		assertEquals("selected", mar.getTime("").get(expect));
	}

	@Test
	public void testCreateMAR() {
		mar.setMAR(
				"idx",
				"ft",
				"fn",
				"desc",
				"urgy",
				"reporter",
				"rd",
				"rt",
				"repairer",
				"ad",
				"at",
				"estimate",
				"rrd",
				"st",
				"et");
		assertEquals("idx", mar.getIdx());
		assertEquals("ft", mar.getFacilitytype());
		assertEquals("fn", mar.getFacilityname());
		assertEquals("desc", mar.getDescription());
		assertEquals("urgy", mar.getUrgency());
		assertEquals("reporter", mar.getReporter());
		assertEquals("rd", mar.getReportdate());
		assertEquals("rt", mar.getReporttime());
		assertEquals("repairer", mar.getRepairer());
		assertEquals("ad", mar.getAssigndate());
		assertEquals("at", mar.getAssigntime());
		assertEquals("estimate", mar.getEstimate());
		assertEquals("rrd", mar.getRepairdate());
		assertEquals("st", mar.getStarttime());
		assertEquals("et", mar.getEndtime());
	}

	@Test
	public void testGetDate() {
		String expect = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
		assertEquals(expect, mar.getDate());
	}
}
