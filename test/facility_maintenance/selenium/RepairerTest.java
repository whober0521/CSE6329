package facility_maintenance.selenium;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;

@RunWith(JUnitParamsRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING) // the test methods in this test file can run in any order but I prefer a fixed order
public class RepairerTest extends facility_maintenance.FMFunctions {
  private String appURL;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  public static String sharedUIMapStr;
  private String username;
  private String password;
  private ChromeOptions options = new ChromeOptions();

  
  @Before
  public void setUp() throws Exception {
//	System.setProperty("webdriver.chrome.driver","c:/ChromeDriver/chromedriver.exe");
	System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
	options.addArguments("disable-gpu");
//	options.addArguments("--start-fullscreen");
	options.addArguments("--disable-features=VizDisplayCompositor");
	
	driver = new ChromeDriver(options);
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    
    prop = new Properties();
    prop.load(new FileInputStream("Configurations/Configuration.properties"));
    prop.load(new FileInputStream("Configurations/Login.properties"));
    prop.load(new FileInputStream(prop.getProperty("SharedUIMap")));
    
    appURL = prop.getProperty("WCAppURL");
    username = prop.getProperty("RepairerUserName");
    password = prop.getProperty("RepairerPassword");
  }
  
	  @Test
	  public void testRepairerTC01aRegister() throws Exception {
		driver.get(appURL);
	
		FM_Register(driver, "repairbot", "!123456", "Repairer", "1001161980", "test", "test", "test@uta.edu", "1234567890", "UTA ERB", "Arlington", "Texas", "RepairerTestRegister");
		Thread.sleep(1000);
	  }
	  
	  
	  @Test
	  @FileParameters("./test/facility_maintenance/selenium/RepairViewAssigned.csv")
	  public void testRepairerTC01bAssigned(int testcaseNum, String Idx, String Homeheader, String ViewHeader, String FilterDate, String FilterTime, String Type, String Name, String Urgency, String Description,
			  String Reported, String Date, String Assigneddate, String Estimate) throws Exception {
		  driver.get(appURL);
		  FM_Login(driver, username, password);
		  assertEquals(Homeheader, driver.findElement(By.xpath(prop.getProperty("Lst_RepairerMenu_Header"))).getText());
		  Thread.sleep(1000);
		  
		  takeScreenshot(driver, "RepairTest" + "RepairerHome" + testcaseNum);
		  driver.findElement(By.linkText(prop.getProperty("Lnk_RepairerMenu_View"))).click();
		  Thread.sleep(1000);
		  
		  driver.findElement(By.xpath(prop.getProperty("Txt_RepairerReserved_Date"))).clear();
		  driver.findElement(By.xpath(prop.getProperty("Txt_RepairerReserved_Date"))).sendKeys(FilterDate);
		  new Select(driver.findElement(By.xpath(prop.getProperty("Lst_RepairerReserved_Time")))).selectByVisibleText(FilterTime);
		  takeScreenshot(driver, "RepairTest" + "RepairerSearch" + testcaseNum);
		  driver.findElement(By.cssSelector(prop.getProperty("Btn_Register_Submit"))).click();
		  Thread.sleep(1000);
		  
		  assertEquals(Idx, driver.findElement(By.xpath(prop.getProperty("Lab_RepairerReserved_Idx"))).getText());
		  driver.findElement(By.xpath(prop.getProperty("Lnk_RepairerReserved_View"))).click();
		  assertEquals(Type, driver.findElement(By.xpath(prop.getProperty("Txt_RepairerReserved_Type"))).getText());
		  assertEquals(Name, driver.findElement(By.xpath(prop.getProperty("Txt_RepairerReserved_Name"))).getText());
		  assertEquals(Urgency, driver.findElement(By.xpath(prop.getProperty("Txt_RepairerReserved_Urgency"))).getText());
		  assertEquals(Description, driver.findElement(By.xpath(prop.getProperty("Txt_RepairerReserved_Description"))).getText());
		  assertEquals(Reported, driver.findElement(By.xpath(prop.getProperty("Txt_RepairerReserved_Reported"))).getText());
		  assertEquals(Date, driver.findElement(By.xpath(prop.getProperty("Txt_RepairerReserved_AddDate"))).getText());
		  assertEquals(Idx, driver.findElement(By.xpath(prop.getProperty("Txt_RepairerReserved_Idx"))).getText());
		  assertEquals(username, driver.findElement(By.xpath(prop.getProperty("Txt_RepairerReserved_Assigned"))).getText());
		  assertEquals(Assigneddate, driver.findElement(By.xpath(prop.getProperty("Txt_RepairerReserved_AssignedDate"))).getText());
		  assertEquals(Estimate, driver.findElement(By.xpath(prop.getProperty("Txt_RepairerReserved_Estimate"))).getText());
		  String methodName = new Throwable().getStackTrace()[0].getMethodName();
		  takeScreenshot(driver, "RepairTest" + "RepairerDetail" + testcaseNum);
	
	
	}
  		@Test
  		@FileParameters("./test/facility_maintenance/selenium/RepairReservedError.csv")
  		public void testRepairerTC01cAssigned(int testcaseNum, String Homeheader, String facility, String RequestDate, String RequestTime, String ErrorReminder,
  												String ErrorFound, String ErrorTime) throws Exception{
  		  driver.get(appURL);
  		  FM_Login(driver, username, password);
  		  assertEquals(Homeheader, driver.findElement(By.xpath(prop.getProperty("Lst_RepairerMenu_Header"))).getText());
  		  Thread.sleep(1000);
  		  driver.findElement(By.linkText(prop.getProperty("Lnk_RepairerMenu_Reservation"))).click();
		  new Select(driver.findElement(By.xpath(prop.getProperty("Lst_RepairerRequest_Name")))).selectByVisibleText(facility);
  		  driver.findElement(By.xpath(prop.getProperty("Txt_RepairerRequest_Date"))).clear();
  		  driver.findElement(By.xpath(prop.getProperty("Txt_RepairerRequest_Date"))).sendKeys(RequestDate);
		  new Select(driver.findElement(By.xpath(prop.getProperty("Lst_RepairerRequest_Time")))).selectByVisibleText(RequestTime);
  		  driver.findElement(By.cssSelector(prop.getProperty("Btn_RepairerRequest_Search"))).click();
  		  Thread.sleep(1000);
  		  assertEquals(ErrorReminder, driver.findElement(By.xpath(prop.getProperty("Lst_RepairerRequest_ErrorReminder"))).getAttribute("value"));
  		  assertEquals(ErrorFound, driver.findElement(By.xpath(prop.getProperty("Lst_RepairerRequest_ErrorFound"))).getAttribute("value"));
  		  if(driver.findElement(By.xpath(prop.getProperty("Lst_RepairerRequest_ErrorTime"))).getAttribute("value").length() > 0)
  			  assertEquals(ErrorTime, driver.findElement(By.xpath(prop.getProperty("Lst_RepairerRequest_ErrorTime"))).getAttribute("value").substring(0, 11));
  		  
  		  takeScreenshot(driver, "RepairTest" + "RepairerFullError" + testcaseNum);
  	}
  		@Test
  		@FileParameters("./test/facility_maintenance/selenium/RepairReservedResult.csv")
  		public void testRepairerTC01dAssigned(int testcaseNum, String Homeheader, String RepairSearchPage, String ViewPage,  
  				String facility, String RequestDate, String RequestTime, String ResultDate, String Reservation) throws Exception{
  			  driver.get(appURL);
    		  FM_Login(driver, username, password);
    		  assertEquals(Homeheader, driver.findElement(By.xpath(prop.getProperty("Lst_RepairerMenu_Header"))).getText());
    		  Thread.sleep(1000);
    		  driver.findElement(By.linkText(prop.getProperty("Lnk_RepairerMenu_Reservation"))).click();
    		  Thread.sleep(1000);
    		  new Select(driver.findElement(By.xpath(prop.getProperty("Lst_RepairerRequest_Name")))).selectByVisibleText(facility);
    		  driver.findElement(By.xpath(prop.getProperty("Txt_RepairerRequest_Date"))).clear();
    		  driver.findElement(By.xpath(prop.getProperty("Txt_RepairerRequest_Date"))).sendKeys(RequestDate);
    		  new Select(driver.findElement(By.xpath(prop.getProperty("Lst_RepairerRequest_Time")))).selectByVisibleText(RequestTime);
    		  driver.findElement(By.cssSelector(prop.getProperty("Btn_RepairerRequest_Search"))).click();
    		  Thread.sleep(1000);
    		  takeScreenshot(driver, "RepairTest" + "RepairerRequestResult" + testcaseNum);
    		  assertEquals(facility, driver.findElement(By.xpath(prop.getProperty("Lst_RepairerResult_Facility"))).getText());
    		  assertEquals(ResultDate, driver.findElement(By.xpath(prop.getProperty("Lst_RepairerResult_Date"))).getText());
    		  driver.findElement(By.linkText(prop.getProperty("Lnk_Txt_RepairerRequest_Book"))).click();
    		  Thread.sleep(1000);
    		  driver.findElement(By.linkText(prop.getProperty("Lnk_RepairerMenu_View"))).click();
    		  Thread.sleep(1000);
    		  assertEquals(RepairSearchPage, driver.findElement(By.xpath(prop.getProperty("Lst_RepairerMenu_Header"))).getText());
	  		  driver.findElement(By.xpath(prop.getProperty("Txt_RepairerReserved_Date"))).clear();
	  		  driver.findElement(By.xpath(prop.getProperty("Txt_RepairerReserved_Date"))).sendKeys(RequestDate);
	  		  new Select(driver.findElement(By.xpath(prop.getProperty("Lst_RepairerReserved_Time")))).selectByVisibleText(RequestTime);
	  		  driver.findElement(By.cssSelector(prop.getProperty("Btn_Register_Submit"))).click();
	  		  Thread.sleep(1000);
	  		  assertEquals(ViewPage, driver.findElement(By.xpath(prop.getProperty("Lst_RepairerReserved_MarNumber"))).getText());
	  		  driver.findElement(By.xpath(prop.getProperty("Lnk_RepairerReserved_View"))).click();
    		  assertEquals(Reservation, driver.findElement(By.xpath(prop.getProperty("Txt_RepairerReserved_Reservation"))).getText().replace('\n', ' '));
    		  takeScreenshot(driver, "RepairTest" + "RepairerRequestDetail" + testcaseNum);
    		  FM_Logout(driver);
    		  
  		}
  		
	  	@Test
	  	@FileParameters("./test/facility_maintenance/selenium/RepairerModify.csv")
	  	public void testRepairerTC01eModify(int testcaseNum, String Homeheader, String RepairSearchPage, String ViewPage, String hours,
	  			String FilterDate, String FilterTime, String Reservation) throws Exception {
	  	  driver.get(appURL);
		  
	  	  FM_Login(driver, username, password);
	  	  assertEquals(Homeheader, driver.findElement(By.xpath(prop.getProperty("Lst_RepairerMenu_Header"))).getText());
		  Thread.sleep(1000);
	  	  driver.findElement(By.linkText(prop.getProperty("Lnk_RepairerMenu_View"))).click();
	  	  Thread.sleep(1000);
	  	  assertEquals(RepairSearchPage, driver.findElement(By.xpath(prop.getProperty("Lst_RepairerMenu_Header"))).getText());
	  	  driver.findElement(By.xpath(prop.getProperty("Txt_RepairerReserved_Date"))).clear();
	  	  driver.findElement(By.xpath(prop.getProperty("Txt_RepairerReserved_Date"))).sendKeys("11/25/2019");
	  	  new Select(driver.findElement(By.xpath(prop.getProperty("Lst_RepairerReserved_Time")))).selectByVisibleText("00:00");
	  	  driver.findElement(By.cssSelector(prop.getProperty("Btn_Register_Submit"))).click();
	  	  Thread.sleep(1000);
	  	  assertEquals(ViewPage, driver.findElement(By.xpath(prop.getProperty("Lst_RepairerReserved_MarNumber"))).getText());
		  driver.findElement(By.xpath(prop.getProperty("Lnk_RepairerReserved_View"))).click();
		  Thread.sleep(1000);
		  assertEquals(Reservation, driver.findElement(By.xpath(prop.getProperty("Txt_RepairerReserved_Reservation"))).getText().replace('\n', ' '));
	  	  new Select(driver.findElement(By.xpath(prop.getProperty("Lst_RepairerReserved_ChangeTime")))).selectByVisibleText(hours);
		  driver.findElement(By.cssSelector(prop.getProperty("Btn_RepairerReserved_ChangeTime"))).click();
		  
		  String methodName = new Throwable().getStackTrace()[0].getMethodName();
		  takeScreenshot(driver, "RepairTest" + methodName + testcaseNum);
		  
		  FM_Logout(driver);
		  Thread.sleep(1000);
	    }
  	
  	@Test
  	@FileParameters("./test/facility_maintenance/selenium/RepairerCancel.csv")
  	public void testRepairerTC01fCancel(int testcaseNum, String Homeheader, String RepairSearchPage, 
  			String ViewPage, String FilterDate, String FilterTime) throws Exception {
    	  driver.get(appURL);
    	  FM_Login(driver, username, password);
    	  assertEquals(Homeheader, driver.findElement(By.xpath(prop.getProperty("Lst_RepairerMenu_Header"))).getText());
		  Thread.sleep(1000);
    	  driver.findElement(By.linkText(prop.getProperty("Lnk_RepairerMenu_View"))).click();
    	  Thread.sleep(1000);
    	  assertEquals(RepairSearchPage, driver.findElement(By.xpath(prop.getProperty("Lst_RepairerMenu_Header"))).getText());
    	  driver.findElement(By.xpath(prop.getProperty("Txt_RepairerReserved_Date"))).clear();
    	  driver.findElement(By.xpath(prop.getProperty("Txt_RepairerReserved_Date"))).sendKeys(FilterDate);
    	  new Select(driver.findElement(By.xpath(prop.getProperty("Lst_RepairerReserved_Time")))).selectByVisibleText(FilterTime);
    	  driver.findElement(By.cssSelector(prop.getProperty("Btn_Register_Submit"))).click();
    	  Thread.sleep(1000);
    	  assertEquals(ViewPage, driver.findElement(By.xpath(prop.getProperty("Lst_RepairerReserved_MarNumber"))).getText());
    	  String methodName = new Throwable().getStackTrace()[0].getMethodName();
    	  takeScreenshot(driver, "RepairTest" + methodName + testcaseNum);
    	  driver.findElement(By.xpath(prop.getProperty("Lnk_RepairerReserved_Cancel"))).click();
    	  
    	  FM_Logout(driver);
    	  Thread.sleep(1000);
      }
	  
  
//  @Test
//  @FileParameters("./test/facility_maintenance/selenium/RepairerLink.csv")
//  public void testRepairerLink(int testcaseNum, String link, String title) throws Exception {
//	  driver.get(appURL);
//	  Thread.sleep(1000);
//	  
//	  FM_Login(driver, username, password);
//	  Thread.sleep(1000);
//	  
//	  driver.findElement(By.linkText(prop.getProperty(link))).click();
//	  
//	  try {
//		  String methodName = new Throwable().getStackTrace()[0].getMethodName();
//		  takeScreenshot(driver, "Repairer" + methodName + testcaseNum);
//		  assertEquals(title, driver.getTitle());
//		  driver.navigate().back();
//	  } catch (Error e) {
//		  verificationErrors.append(e.toString());
//	  }
//	  
//	  Thread.sleep(1000);
//	  FM_Logout(driver);
//  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}