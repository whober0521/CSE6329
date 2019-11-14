package facility_maintenance.selenium;

import java.util.regex.Pattern;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.*;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;

@RunWith(JUnitParamsRunner.class)
public class UserTest {
  private WebDriver driver;
  private String appURL;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  public static String sharedUIMapStr;
  public static Properties prop;
  private ChromeOptions options = new ChromeOptions();

  @Before
  public void setUp() throws Exception {
	System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");;
	ChromeOptions options = new ChromeOptions();
	options.addArguments("disable-gpu");
	options.addArguments("--disable-features=VizDisplayCompositor");
	options.addArguments("--start-fullscreen");
	
	driver = new ChromeDriver(options);
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    prop = new Properties();
    prop.load(new FileInputStream("Configuration/FM_Configuration.properties"));
    appURL = prop.getProperty("WCAppURL");
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
    driver.findElement(By.linkText(prop.getProperty("Btn_Login_Registration"))).click();
    driver.findElement(By.xpath(prop.getProperty("Txt_Registration_Username"))).clear();
    driver.findElement(By.xpath(prop.getProperty("Txt_Registration_Username"))).sendKeys(username);
    driver.findElement(By.xpath(prop.getProperty("Txt_Registration_Password"))).clear();
    driver.findElement(By.xpath(prop.getProperty("Txt_Registration_Password"))).sendKeys(pwd);
    new Select(driver.findElement(By.id(prop.getProperty("Lst_Registration_Role")))).selectByVisibleText(role);
    driver.findElement(By.name(prop.getProperty("Lst_Registration_UTAID"))).clear();
    driver.findElement(By.name(prop.getProperty("Lst_Registration_UTAID"))).sendKeys(utaid);
    driver.findElement(By.name(prop.getProperty("Lst_Registration_Firstname"))).clear();
    driver.findElement(By.name(prop.getProperty("Lst_Registration_Firstname"))).sendKeys(firstName);
    driver.findElement(By.name(prop.getProperty("Lst_Registration_Lastname"))).clear();
    driver.findElement(By.name(prop.getProperty("Lst_Registration_Lastname"))).sendKeys(lastName);
    driver.findElement(By.name(prop.getProperty("Lst_Registration_EMail"))).clear();
    driver.findElement(By.name(prop.getProperty("Lst_Registration_EMail"))).sendKeys(email);
    driver.findElement(By.name(prop.getProperty("Lst_Registration_Phone"))).clear();
    driver.findElement(By.name(prop.getProperty("Lst_Registration_Phone"))).sendKeys(phone);
    driver.findElement(By.name(prop.getProperty("Lst_Registration_Address"))).clear();
    driver.findElement(By.name(prop.getProperty("Lst_Registration_Address"))).sendKeys(address);
    driver.findElement(By.name(prop.getProperty("Lst_Registration_City"))).clear();
    driver.findElement(By.name(prop.getProperty("Lst_Registration_City"))).sendKeys(city);
    new Select(driver.findElement(By.name(prop.getProperty("Lst_Registration_State")))).selectByVisibleText(state);
	driver.findElement(By.cssSelector(prop.getProperty("Btn_Registration_Submit"))).click();
    
	
    String methodName = new Throwable().getStackTrace()[0].getMethodName();
    takeScreenshot(driver, "UserTest" + methodName + testcaseNum);
  }
  
  @Test
  @FileParameters("./test/facility_maintenance/selenium/UserLogin.csv")
  public void Login(int testcaseNum, String username, String password ) throws Exception
  {
  	driver.get(appURL);
    driver.findElement(By.xpath(prop.getProperty("Txt_Login_Username"))).clear();
    driver.findElement(By.xpath(prop.getProperty("Txt_Login_Username"))).sendKeys(username);
    driver.findElement(By.xpath(prop.getProperty("Txt_Login_Password"))).clear();
    driver.findElement(By.xpath(prop.getProperty("Txt_Login_Password"))).sendKeys(password);
    Thread.sleep(1000);
	driver.findElement(By.cssSelector(prop.getProperty("Btn_Login"))).click();
    Thread.sleep(1000);
    String methodName = new Throwable().getStackTrace()[0].getMethodName();
    takeScreenshot(driver, "UserTest" + methodName + testcaseNum);
  }

  @Test
  @FileParameters("./test/facility_maintenance/selenium/UserReport.csv")
  public void MarReport(int testcaseNum, String facility, String description) throws Exception {
		driver.get(appURL);
	    driver.findElement(By.xpath(prop.getProperty("Txt_Login_Username"))).clear();
	    driver.findElement(By.xpath(prop.getProperty("Txt_Login_Username"))).sendKeys("wei");
	    driver.findElement(By.xpath(prop.getProperty("Txt_Login_Password"))).clear();
	    driver.findElement(By.xpath(prop.getProperty("Txt_Login_Password"))).sendKeys("!123462");
	    driver.findElement(By.cssSelector(prop.getProperty("Btn_Login"))).click();
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
  public void HomePage() throws Exception {
		driver.get(appURL);
	    driver.findElement(By.xpath(prop.getProperty("Txt_Login_Username"))).clear();
	    driver.findElement(By.xpath(prop.getProperty("Txt_Login_Username"))).sendKeys("wei");
	    driver.findElement(By.xpath(prop.getProperty("Txt_Login_Password"))).clear();
	    driver.findElement(By.xpath(prop.getProperty("Txt_Login_Password"))).sendKeys("!123462");
	    driver.findElement(By.cssSelector(prop.getProperty("Btn_Login"))).click();
	    
	    Thread.sleep(1000);
	    driver.findElement(By.linkText(prop.getProperty("Txt_Home"))).click();
	    Thread.sleep(1000);
	    
	    Thread.sleep(1000);
	    driver.findElement(By.linkText(prop.getProperty("Txt_Logout"))).click();
	    Thread.sleep(1000);
  }
  
  public void takeScreenshot(WebDriver driver, String screenshotname) {
	  try
	  {
		  File source = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);			
		  FileUtils.copyFile(source, new File("./ScreenShots/" + screenshotname +".png"));
	  }
	  catch(IOException e) {}
	  try {
//		  Change the delay value to 1_000 to insert a 1 second delay after 
//		  each screenshot
		  Thread.sleep(1000);
	} catch (InterruptedException e) {}
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