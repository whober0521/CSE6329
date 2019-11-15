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
