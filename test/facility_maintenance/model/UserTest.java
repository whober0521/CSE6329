package test;
import static org.junit.Assert.*;

import java.util.HashMap;

import facility_maintenance.model.User;
import facility_maintenance.model.UserErrorMsgs;
import facility_maintenance.data.UsersDAO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;


@RunWith(JUnitParamsRunner.class)
public class UserTest {
	User users;
	UserErrorMsgs userserr;

	@Before
	public void setUp() throws Exception {
		users = new User();
		userserr = new UserErrorMsgs();
	}

	@Test
	@FileParameters("TestCaseTable_CSV/Users.csv")
	public void test(int testCaseNo, String action, String username, String password, String role, String utaid, String firstName, String LastName,
					String Email, String phoneNum, String address, String city, String state, String Errormsgs, String UserNameErr, String PwdErr, String idErr, String FnErr, String LnErr,
					String EmailErr, String PhoneErr, String adderr, String cityerr) {
		
		users.setUser(username, password, role, utaid, firstName, LastName, Email, phoneNum, address, city, state);
		users.validate(action, users, userserr);
		assertEquals(Errormsgs, userserr.getErrorMsg());
		assertTrue(Errormsgs.equals(userserr.getErrorMsg()));
		assertEquals(UserNameErr, userserr.getUsernameError());
		assertEquals(PwdErr, userserr.getPasswordError());
		assertEquals(idErr, userserr.getUtaidError());
		assertTrue(FnErr.equals(userserr.getFnameError()));
		assertTrue(LnErr.equals(userserr.getLnameError()));
		
		EmailErr = EmailErr.replace("\"", "");
		assertEquals(EmailErr, userserr.getEmailError());
		assertTrue(PhoneErr.equals(userserr.getPhoneError()));
		assertTrue(adderr.equals(userserr.getAddressError()));
		assertTrue(cityerr.equals(userserr.getCityError()));
		assertEquals("selected", users.getRoles(role).get(users.getRole()));
		assertEquals("selected", users.getStates(state).get(users.getState()));
		
		UserErrorMsgs userNaPwdErr  = new UserErrorMsgs();
		assertTrue(userNaPwdErr.getUsernameError().isEmpty());
		assertTrue(userNaPwdErr.getPasswordError().isEmpty());
	}
	
	@Test
	public void testEmptyGetRoles() {
		HashMap<String, String> expect = new HashMap<String, String>();
		expect.put("User", "");
		expect.put("Facility Manager", "");
		expect.put("Admin", "");
		expect.put("Repairer", "");
		assertEquals(expect, users.getRoles(""));
	}
	@Test
	public void testEmptyGetStates() {
		HashMap<String, String> expect = new HashMap<String, String>();
		expect.put("Alabama", "");
		expect.put("Alaska", "");
		expect.put("Arizona", "");
		expect.put("Arkansas", "");
		expect.put("California", "");
		expect.put("Colorado", "");
		expect.put("Connecticut", "");
		expect.put("Delaware", "");
		expect.put("District Of Columbia", "");
		expect.put("Florida", "");
		expect.put("Georgia", "");
		expect.put("Hawaii", "");
		expect.put("Idaho", "");
		expect.put("Illinois", "");
		expect.put("Indiana", "");
		expect.put("Iowa", "");
		expect.put("Kansas", "");
		expect.put("Kentucky", "");
		expect.put("Louisiana", "");
		expect.put("Maine", "");
		expect.put("Maryland", "");
		expect.put("Massachusetts", "");
		expect.put("Michigan", "");
		expect.put("Minnesota", "");
		expect.put("Mississippi", "");
		expect.put("Missouri", "");
		expect.put("Montana", "");
		expect.put("Nebraska", "");
		expect.put("Nevada", "");
		expect.put("New Hampshire", "");
		expect.put("New Jersey", "");
		expect.put("New Mexico", "");
		expect.put("New York", "");
		expect.put("North Carolina", "");
		expect.put("North Dakota", "");
		expect.put("Ohio", "");
		expect.put("Oklahoma", "");
		expect.put("Oregon", "");
		expect.put("Pennsylvania", "");
		expect.put("Rhode Island", "");
		expect.put("South Carolina", "");
		expect.put("South Dakota", "");
		expect.put("Tennessee", "");
		expect.put("Texas", "");
		expect.put("Utah", "");
		expect.put("Vermont", "");
		expect.put("Virginia", "");
		expect.put("Washington", "");
		expect.put("West Virginia", "");
		expect.put("Wisconsin", "");
		expect.put("Wyoming", "");
		assertEquals(expect, users.getStates(""));	
	}
	

}


