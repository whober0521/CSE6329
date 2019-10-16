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
//@RunWith(JUnitParamsRunner.class)
public class MARTest {
	private MAR mar;

	@Before
	public void setUp() throws Exception {
		mar = new MAR();
	}

	@Test
	@PrepareForTest({MARsDAO.class, FacilitiesDAO.class})
	@FileParameters("TestCaseTable_CSV/MAR.csv")
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
		//assertEquals(1, 1);
	}

}
