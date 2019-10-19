package test;

import static org.junit.Assert.*;
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
}
