package facility_maintenance.selenium;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.junit.*;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;

@RunWith(JUnitParamsRunner.class)
public class FacilityManagerTest extends facility_maintenance.FMFunctions {
  private String appURL;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  public static String sharedUIMapStr;

  @Before
  public void setUp() throws Exception {
	System.setProperty("webdriver.chrome.driver", "/Users/xaiser/CS/school/SoftwareEngineerAdvance/workplace/chromedriver");
	//System.setProperty("webdriver.chrome.driver","c:/ChromeDriver/chromedriver.exe");
	/*
    driver = new ChromeDriver();
    baseUrl = "http://localhost:225/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    */
    
    driver = new ChromeDriver();
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    prop = new Properties();
    prop.load(new FileInputStream("Configurations/Configuration.properties"));
    appURL = prop.getProperty("FM_AppURL");
    prop.load(new FileInputStream(prop.getProperty("SharedUIMap")));
  }

  /*
  @Test
  @FileParameters("./test/facility_maintenance/selenium/FM_registration.csv")
  public void Registration(int testcaseNum, 
		  	String username,
		  	String pwd,
		  	String utaid,
		  	String firstName,
		  	String lastName,
		  	String email,
		  	String phone,
		  	String address,
		  	String city,
		  	String state) throws Exception {
	driver.get(appURL);
	
    String methodName = new Throwable().getStackTrace()[0].getMethodName();

	FM_Register(driver, username, pwd, "Facility Manager", utaid, firstName, lastName, email, phone, address, city, state, "FacilityManagerTest" + methodName + testcaseNum);
  }

  @Test
  @FileParameters("./test/facility_maintenance/selenium/FM_verifyHomelink.csv")
  public void verifyAllLinks(int testcaseNum, String link, String title) throws Exception {
	  driver.get(appURL);
	  Thread.sleep(1000);
	  FM_Login(driver, "fmfive", "test1");    
	  Thread.sleep(1000);
	  driver.findElement(By.linkText(prop.getProperty(link))).click();
	  try {
		  String methodName = new Throwable().getStackTrace()[0].getMethodName();
		  takeScreenshot(driver, "FacilityManagerTest" + methodName + testcaseNum);
		  assertEquals(title, driver.getTitle());
	  } catch (Error e) {
		  verificationErrors.append(e.toString());
	  }
	  Thread.sleep(1000);
	  FM_Logout(driver);
	  //driver.findElement(By.linkText("Logout")).click();
  }

  @Test
  public void verifyAddNewFacility() throws Exception {
	  driver.get(appURL);
	  Thread.sleep(1000);
	  FM_Login(driver, "fmfive", "test1");    
	  Thread.sleep(1000);
	  driver.findElement(By.linkText(prop.getProperty("Txt_FM_AddNewFacility"))).click();
	  String methodName = new Throwable().getStackTrace()[0].getMethodName();
	  takeScreenshot(driver, "FacilityManagerTest" + methodName);
	  assertTrue(isElementPresent(By.cssSelector("form")));
	  Thread.sleep(1000);
	  //driver.findElement(By.linkText("Logout")).click();
  }

  @Test
  public void verifyUnassignedMAR() throws Exception {
	  driver.get(appURL);
	  Thread.sleep(1000);
	  FM_Login(driver, "fmfive", "test1");    
	  Thread.sleep(1000);
	  driver.findElement(By.linkText(prop.getProperty("Txt_FM_ViewUnassignedMAR"))).click();
	  String methodName = new Throwable().getStackTrace()[0].getMethodName();
	  takeScreenshot(driver, "FacilityManagerTest" + methodName);
	  assertTrue(isElementPresent(By.cssSelector("th")));
	  Thread.sleep(1000);
	  //driver.findElement(By.linkText("Logout")).click();
  }
  */

  /*
  @Test
  @FileParameters("./test/facility_maintenance/selenium/FM_assignMAR.csv")
  public void assignMAR(int testcaseNum, String repairer, String urgency, String estimate) throws Exception {
	driver.get(appURL);
	
	FM_Login(driver, "fmfive", "test1");    

    Thread.sleep(1000);
    driver.findElement(By.linkText(prop.getProperty("Txt_FM_ViewUnassignedMAR"))).click();
    Thread.sleep(1000);
    driver.findElement(By.linkText(prop.getProperty("Txt_UAM_View"))).click();

    try
    {
    	new Select(driver.findElement(By.name(prop.getProperty("Lst_MM_Urgency")))).selectByVisibleText(urgency);
    }
    catch( Exception e)
    { }

    try
    {
    	new Select(driver.findElement(By.name(prop.getProperty("Lst_MM_Repairer")))).selectByVisibleText(repairer);
    }
    catch( Exception e)
    { }

    try
    {
    	new Select(driver.findElement(By.name(prop.getProperty("Lst_MM_Estimate")))).selectByVisibleText(estimate);
    }
    catch( Exception e)
    { }
    Thread.sleep(500);
    driver.findElement(By.cssSelector(prop.getProperty("Btn_MM_Submit"))).click();
    Thread.sleep(500);
    String methodName = new Throwable().getStackTrace()[0].getMethodName();
    takeScreenshot(driver, "FacilityManagerTest" + methodName + testcaseNum);

    FM_Logout(driver);
  }  
  */

  /*
  @Test
  @FileParameters("./test/facility_maintenance/selenium/FM_assignMARToOther.csv")
//  public void assignMARToOther(int testcaseNum, String time, String facilityName, String newRepairer) throws Exception {
  public void assignMARToOther(int testcaseNum, String MARIdx, String facilityType, 
		  String facilityName, String oldRepairer, String date, String time, String newRepairer) throws Exception {
	  driver.get(appURL);
	  FM_Login(driver, "fmfive", "test1");    

	  driver.findElement(By.linkText(prop.getProperty("Txt_FM_ViewAssignedMAR"))).click();

	  driver.findElement(By.name(prop.getProperty("Txt_FAM_Idx"))).clear();
	  driver.findElement(By.name(prop.getProperty("Txt_FAM_Idx"))).sendKeys(MARIdx);

	  Thread.sleep(1000);
	  driver.findElement(By.name(prop.getProperty("Txt_FAM_AssignedDate"))).clear();
	  Thread.sleep(1000);
	  driver.findElement(By.name(prop.getProperty("Txt_FAM_AssignedDate"))).sendKeys(date);
	  //driver.findElement(By.name(prop.getProperty("Txt_FAM_AssignedDate"))).sendKeys("11/25/2019");
	  System.out.println(date);
	  Thread.sleep(2000);

//	  new Select(driver.findElement(By.name("assigntime"))).selectByVisibleText(time);
	  new Select(driver.findElement(By.name(prop.getProperty("Txt_FAM_AssignedTime")))).selectByVisibleText(time);
//	  new Select(driver.findElement(By.name("facilitytype"))).selectByVisibleText(facilityName);
	  new Select(driver.findElement(By.name(prop.getProperty("Txt_FAM_FacilityType")))).selectByVisibleText(facilityType);
	  new Select(driver.findElement(By.name(prop.getProperty("Txt_FAM_FacilityName")))).selectByVisibleText(facilityName);

	  new Select(driver.findElement(By.name(prop.getProperty("Txt_FAM_OldRepairer")))).selectByVisibleText(oldRepairer);
	  
	   //For unknown reason, I have to give it 2 click with 500ms delay or the selenium won't click the button.
	  WebDriverWait wait = new WebDriverWait(driver, 5);
//	  wait.until(ExpectedConditions.elementToBeClickable(By.xpath(prop.getProperty("Btn_FAM_Submit")))).click();
	  Thread.sleep(500);
	  wait.until(ExpectedConditions.elementToBeClickable(By.xpath(prop.getProperty("Btn_FAM_Submit"))));
	  Thread.sleep(500);
	  driver.findElement(By.xpath(prop.getProperty("Btn_FAM_Submit"))).click();

	  wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(prop.getProperty("Txt_UAM_View"))));
	  driver.findElement(By.linkText(prop.getProperty("Txt_UAM_View"))).click();

	  new Select(driver.findElement(By.name(prop.getProperty("Lst_MM_Repairer")))).selectByVisibleText(newRepairer);
	  driver.findElement(By.cssSelector(prop.getProperty("Btn_MM_Submit"))).click();

	  FM_Logout(driver);
  }
  */

  @Test
  @FileParameters("./test/facility_maintenance/selenium/FM_searchFacility.csv")
  public void searchFacility(int testcaseNum, String facilityName) throws Exception {
	  driver.get(appURL);
	  FM_Login(driver, "fmfive", "test1");    

	  driver.findElement(By.linkText(prop.getProperty("Txt_FM_SearchFacility"))).click();
//	  new Select(driver.findElement(By.name("facilityname"))).selectByVisibleText(facilityName);
	  new Select(driver.findElement(By.name(prop.getProperty("Txt_SF_FacilityName")))).selectByVisibleText(facilityName);
	  new Select(driver.findElement(By.name("starttime"))).selectByVisibleText("11:00");
//	  driver.findElement(By.cssSelector("button[type=\"submit\"]")).click();
	  Thread.sleep(500);
	  driver.findElement(By.cssSelector(prop.getProperty("Btn_SF_Submit"))).click();
	  Thread.sleep(500);
	  driver.navigate().back();
	  //driver.findElement(By.linkText("Logout")).click();
	  FM_Logout(driver);
  }

  /*
  @Test
  @FileParameters("./test/facility_maintenance/selenium/FM_addFacility.csv")
  public void addFacility(int testcaseNum, String facilityName, String number, String interval, String duration) throws Exception {
	  driver.get(appURL);
	  FM_Login(driver, "fmfive", "test1");    

	  driver.findElement(By.linkText(prop.getProperty("Txt_FM_AddNewFacility"))).click();
//	  new Select(driver.findElement(By.name("master"))).selectByVisibleText("Volleyball courts");
	  new Select(driver.findElement(By.name(prop.getProperty("Txt_ANF_Master")))).selectByVisibleText(facilityName);
	  driver.findElement(By.name(prop.getProperty("Txt_ANF_Number"))).clear();
//	  driver.findElement(By.name("number")).sendKeys("2");
	  driver.findElement(By.name(prop.getProperty("Txt_ANF_Number"))).sendKeys(number);
//	  new Select(driver.findElement(By.name("interval"))).selectByVisibleText("1 hour");
	  new Select(driver.findElement(By.name(prop.getProperty("Txt_ANF_Interval")))).selectByVisibleText(interval);
//	  new Select(driver.findElement(By.name("duration"))).selectByVisibleText("4 days");
	  new Select(driver.findElement(By.name(prop.getProperty("Txt_ANF_Duration")))).selectByVisibleText(duration);
	  driver.findElement(By.cssSelector(prop.getProperty("Btn_ANF_Submit"))).click();

	  driver.navigate().back();
	  driver.navigate().back();
	  Thread.sleep(100);
	  FM_Logout(driver);
  }
  */

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
