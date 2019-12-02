package facility_maintenance.selenium;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.junit.*;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;

@RunWith(JUnitParamsRunner.class)
public class UserTest extends facility_maintenance.FMFunctions{
  private String appURL;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  private ChromeOptions options = new ChromeOptions();

  @Before
  public void setUp() throws Exception {
	  System.setProperty("webdriver.chrome.driver","c:/ChromeDriver/chromedriver.exe");
	  options.addArguments("disable-gpu");
	  options.addArguments("--start-fullscreen");
	  options.addArguments("--disable-features=VizDisplayCompositor");
	  
	  driver = new ChromeDriver(options);
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  prop = new Properties();
	  prop.load(new FileInputStream("Configurations/Configuration.properties"));
	  appURL = prop.getProperty("AppURL");
	  prop.load(new FileInputStream(prop.getProperty("SharedUIMap")));
  }
  
  @Test
  @FileParameters("./test/facility_maintenance/selenium/UserRegister.csv")
  public void Registration(int testcaseNum, 
		  	String username,
		  	String pwd,
		  	String role,
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
	FM_Register(driver, username, pwd, role, utaid, firstName, lastName, email, phone, address, city, state, "UserTest" + methodName + testcaseNum);
    Thread.sleep(1000);
	//takeScreenshot(driver, "UserTest" + methodName + testcaseNum);
  }
  
//  @Test
//  @FileParameters("./test/facility_maintenance/selenium/UserUpdate.csv")
//  public void userUpdate(int testcaseNum, 
//        String pwd,
//        String utaid,
//        String firstName,
//        String lastName,
//        String email,
//        String phone,
//        String address,
//        String city,
//    	String state
//        ) throws Exception {
//	  driver.get(appURL); 
//	  String methodName = new Throwable().getStackTrace()[0].getMethodName();
//	  FM_userUpdate(driver,pwd, utaid, firstName, lastName, email, phone, address, city,state, "UserTest" + methodName + testcaseNum);
//	  Thread.sleep(1000);
//  }
  
  @Test
  @FileParameters("./test/facility_maintenance/selenium/UserLogin.csv")
  public void Login(int testcaseNum, String username, String password ) throws Exception
  {
  	driver.get(appURL);
	FM_Login(driver, username, password);
    Thread.sleep(1000);
    
    String methodName = new Throwable().getStackTrace()[0].getMethodName();
    takeScreenshot(driver, "UserTest" + methodName + testcaseNum);
  }

  @Test
  @FileParameters("./test/facility_maintenance/selenium/UserReport.csv")
  public void MarReport(int testcaseNum, String facility, String description) throws Exception {
		driver.get(appURL);
		FM_Login(driver, "Sha", "shan");
		
	    Thread.sleep(1000);
	    driver.findElement(By.linkText(prop.getProperty("Txt_ProblemReport"))).click();
	    new Select(driver.findElement(By.name(prop.getProperty("Lst_Facility")))).selectByVisibleText(facility);
	    driver.findElement(By.id(prop.getProperty("Txt_Description"))).clear();
	    driver.findElement(By.id(prop.getProperty("Txt_Description"))).sendKeys(description);
	    
	    Thread.sleep(1000);
		driver.findElement(By.cssSelector(prop.getProperty("Btn_Report_Problem_Submit"))).click();
	    Thread.sleep(1000);
	    String methodName = new Throwable().getStackTrace()[0].getMethodName();
	    takeScreenshot(driver, "UserTest" + methodName + testcaseNum);
}
  
  @Test
  public void EachLinkCorrect() throws Exception {
	  driver.get(appURL);
	  FM_Login(driver, "Sha", "shan");
	  
	  Thread.sleep(1000);
	  driver.findElement(By.linkText(prop.getProperty("Txt_Home"))).click();
	  Thread.sleep(1000);
	  
	  Thread.sleep(1000);
	  driver.findElement(By.linkText(prop.getProperty("Txt_Update_Profile"))).click();
	  Thread.sleep(1000);
	  driver.navigate().back();
	  
	  Thread.sleep(1000);
	  
	  FM_Logout(driver);
	  
	  Thread.sleep(1000);
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