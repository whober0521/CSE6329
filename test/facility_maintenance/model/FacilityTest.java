package facility_maintenance.model;
import static org.junit.Assert.*;

import java.io.Serializable;
import java.util.HashMap;
import facility_maintenance.model.Facility;
import facility_maintenance.model.FacilityErrorMsgs;
import facility_maintenance.data.FacilitiesDAO;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
import java.util.ArrayList;



@RunWith(JUnitParamsRunner.class)

public class FacilityTest{
    Facility fac;
    FacilityErrorMsgs facerr;

    @Before
	public void setUp() throws Exception {
		fac = new Facility();
		facerr = new FacilityErrorMsgs();
	}

	@Test
	@FileParameters("./test/facility_maintenance/model/Facility.csv")
	public void test(String action, String master,int id, String interval, String duration, String venue, String number, String Errormsgs, String numberErr){
		fac.setFacility(master, id,  interval,  duration,  venue,  number);
		fac.validate(action, fac, facerr);
		assertEquals(master, fac.getMaster());
		assertEquals(id, fac.getId());
		assertEquals(interval, fac.getInterval());
		assertEquals(duration, fac.getDuration());
		assertEquals(venue, fac.getVenue());
		assertEquals(Errormsgs, facerr.getErrorMsg());
		assertTrue(Errormsgs.equals(facerr.getErrorMsg()));
		assertEquals(numberErr, facerr.getNumberError());
		assertEquals("selected", fac.getIntervals(interval).get(fac.getInterval()));
		assertEquals("selected", fac.getDurations(duration).get(fac.getDuration()));
		assertEquals("selected", fac.getVenues(venue).get(fac.getVenue()));
	}
	
	
	
	@Test
	public void testEmptyGetIntervals(){
		HashMap<String, String> expect = new HashMap<String, String>();
		expect.put("30 minutes", "");
		expect.put("1 hour", "");
		expect.put("2 hours", "");
	    assertEquals(expect, fac.getIntervals(""));
	}
	
	@Test
	public void testEmptyGetDurations(){
		HashMap<String, String> expect = new HashMap<String, String>();
		expect.put("1 day", "");
		expect.put("2 days", "");
		expect.put("4 days", "");
		expect.put("7 days", "");
		assertEquals(expect, fac.getDurations(""));
	}
	
	@Test
	public void testEmptyGetVenues(){
		HashMap<String, String> expect = new HashMap<String, String>();
		expect.put("Indoor", "");
		expect.put("Outdoor", "");
		assertEquals(expect, fac.getVenues(""));
	}
}