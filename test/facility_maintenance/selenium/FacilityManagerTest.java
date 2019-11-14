package facility_maintenance.selenium;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
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
//	System.setProperty("webdriver.chrome.driver", "/Users/xaiser/CS/school/SoftwareEngineerAdvance/workplace/chromedriver");
	System.setProperty("webdriver.chrome.driver","c:/ChromeDriver/chromedriver.exe");
	/*
    driver = new ChromeDriver();
    baseUrl = "http://localhost:225/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    */
    
    driver = new ChromeDriver();
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    prop = new Properties();
    prop.load(new FileInputStream("Configuration/FM_Configuration.properties"));
    appURL = prop.getProperty("AppURL");
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
    driver.findElement(By.linkText(prop.getProperty("Btn_Login_Registration"))).click();
    driver.findElement(By.xpath(prop.getProperty("Txt_Registration_Username"))).clear();
    driver.findElement(By.xpath(prop.getProperty("Txt_Registration_Username"))).sendKeys(username);
    driver.findElement(By.xpath(prop.getProperty("Txt_Registration_Password"))).clear();
    driver.findElement(By.xpath(prop.getProperty("Txt_Registration_Password"))).sendKeys(pwd);
    new Select(driver.findElement(By.id(prop.getProperty("Lst_Registration_Role")))).selectByVisibleText("Facility Manager");
    driver.findElement(By.name(prop.getProperty("Lst_Registration_UTAID"))).clear();
    driver.findElement(By.name(prop.getProperty("Lst_Registration_UTAID"))).sendKeys(utaid);
    driver.findElement(By.name("fname")).clear();
    driver.findElement(By.name("fname")).sendKeys(firstName);
    driver.findElement(By.name("lname")).clear();
    driver.findElement(By.name("lname")).sendKeys(lastName);
    driver.findElement(By.name("email")).clear();
    driver.findElement(By.name("email")).sendKeys(email);
    driver.findElement(By.name("phone")).clear();
    driver.findElement(By.name("phone")).sendKeys(phone);
    driver.findElement(By.name("address")).clear();
    driver.findElement(By.name("address")).sendKeys(address);
    driver.findElement(By.name("city")).clear();
    driver.findElement(By.name("city")).sendKeys(city);
    new Select(driver.findElement(By.name("state"))).selectByVisibleText(state);
    Thread.sleep(1000);
    driver.findElement(By.cssSelector("button[type=\"submit\"]")).click();
    String methodName = new Throwable().getStackTrace()[0].getMethodName();
    takeScreenshot(driver, "FacilityManagerTest" + methodName + testcaseNum);
  }

  @Test
  @FileParameters("./test/facility_maintenance/selenium/FM_assignMAR.csv")
  public void assignMAR(int testcaseNum, String repairer, String urgency, String estimate) throws Exception {
	driver.get(appURL);
	
	FM_Login(driver, "fmfive", "test1");
	
//    driver.findElement(By.xpath(prop.getProperty("Txt_Login_Username"))).clear();
//    driver.findElement(By.xpath(prop.getProperty("Txt_Login_Username"))).sendKeys("fmfive");
//    driver.findElement(By.xpath(prop.getProperty("Txt_Login_Password"))).clear();
//    driver.findElement(By.xpath(prop.getProperty("Txt_Login_Password"))).sendKeys("test1");
//    Thread.sleep(1000);
//    driver.findElement(By.cssSelector("button[type=\"submit\"]")).click();
    

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

  public void takeScreenshot(WebDriver driver, String screenshotname) {
		try
		{
			java.io.File source = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);			
			FileUtils.copyFile(source, new File("./ScreenShots/" + screenshotname +".png"));
		}
		catch(IOException e) {}
		try {
			//				  Change the delay value to 1_000 to insert a 1 second delay after 
			//				  each screenshot
			Thread.sleep(0);
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
