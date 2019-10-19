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

import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;

import static org.powermock.api.easymock.PowerMock.*;

import java.util.ArrayList;

@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(JUnitParamsRunner.class)
public class MARTest {
	private MAR mar;
	private static final int defaultDuration = 1;

	@Before
	public void setUp() throws Exception {
		mar = new MAR();
	}

	@Test
	@PrepareForTest({MARsDAO.class, FacilitiesDAO.class})
	@FileParameters("TestCaseTable_CSV/MAR_validate.csv")
	public void testValidate(int testcaseNum, String action, String expectMsg) {
		action = action.replace("\"", "");
		expectMsg = expectMsg.replace("\"", "");
		mockStatic(MARsDAO.class);
		mockStatic(FacilitiesDAO.class);
		ArrayList<MAR> mars = new ArrayList<MAR>();
		mars.add(mar);
		mar.setIdx("");
		EasyMock.expect(MARsDAO.getAssigned(mar)).andReturn(mars);
		Facility emptyFacility = new Facility();
		emptyFacility.setDuration("1 2");
		EasyMock.expect(FacilitiesDAO.getDetail("ff")).andReturn(emptyFacility);
		mar.setDescription("111");
		mar.setUrgency("notEmpty");
		mar.setRepairer("");
		mar.setEstimate("");
		mar.setFacilityname("ff");
		replayAll();
		MARErrorMsgs marErrMsg = new MARErrorMsgs();
		mar.validate(action, mar, marErrMsg);
		assertEquals(expectMsg, marErrMsg.getErrorMsg());
	}

	@Test
	@PrepareForTest(MARsDAO.class)
	@FileParameters("TestCaseTable_CSV/MAR_validateFacilityName.csv")
	public void testValidateFacilityName(int testcaseNum, int numMar, String expectMsg) {
		expectMsg = expectMsg.replace("\"", "");
		mockStatic(MARsDAO.class);
		ArrayList<MAR> mars = new ArrayList<MAR>();
		for (int i  = 0; i < numMar; i++) {
			mars.add(mar);
		}
		EasyMock.expect(MARsDAO.getAssigned(mar)).andReturn(mars);
		replayAll();
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

	@Test
	@PrepareForTest(MARsDAO.class)
	@FileParameters("TestCaseTable_CSV/MAR_validateRepairerAssignedMAR.csv")
	public void testValidateRepairerAssignedMAR(
			int testcaseNum,
			String repairerName,
			int numDayAssignedMAR,
			int numWeekAssignedMAR,
			String expectMsg) {
		expectMsg = expectMsg.replace("\"", "");
		repairerName = repairerName.replace("\"", "");
		mockStatic(MARsDAO.class);
		EasyMock.expect(MARsDAO.getAssignedNumber(repairerName,mar.getDate())).andReturn(numDayAssignedMAR);
		EasyMock.expect(MARsDAO.getAssignedNumber(repairerName)).andReturn(numWeekAssignedMAR);
		replayAll();
		assertEquals(expectMsg, mar.validateRepairer(repairerName));
	}

	@Test
	@FileParameters("TestCaseTable_CSV/MAR_validateEstimate.csv")
	public void testValidateEstimate(int testcaseNum, String estimateStr, String expectMsg) {
		expectMsg = expectMsg.replace("\"", "");
		estimateStr = estimateStr.replace("\"", "");
		assertEquals(expectMsg, mar.validateEstimate(estimateStr));
	}

	@Test
	@PrepareForTest(FacilitiesDAO.class)
	@FileParameters("TestCaseTable_CSV/MAR_validateDateTime.csv")
	public void testValidateDateTime(int testcaseNum, int duration, int lenOfMsg) {
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
}
