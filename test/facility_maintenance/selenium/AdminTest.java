package facility_maintenance.selenium;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.junit.FixMethodOrder;

import org.apache.commons.io.FileUtils;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;

@RunWith(JUnitParamsRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
  public class AdminTest extends facility_maintenance.FMFunctions {
  private static String sAppURL;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
	System.setProperty("webdriver.chrome.driver","c:/ChromeDriver/chromedriver.exe");
	
	driver = new ChromeDriver();
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	
    prop = new Properties();
    prop.load(new FileInputStream("Configurations/Configuration.properties"));
    prop.load(new FileInputStream(prop.getProperty("SharedUIMap")));
    
    sAppURL = prop.getProperty("AppURL");

    System.out.print(prop.getProperty("Txt_Register_Password"));
	System.out.print(prop.getProperty("Txt_Register_UTAID"));
  }

  @Test
  @FileParameters("./test/facility_maintenance/selenium/admin_registration.csv")
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
	  
	  driver.get(sAppURL);
	  
	  String methodName = new Throwable().getStackTrace()[0].getMethodName();
	  
	  //takeScreenshot(driver, "AdminTest" + methodName + testcaseNum);
	  
	  FM_Register(driver, username, pwd, role, utaid, firstName, lastName, email, phone, address, city, state, "AdminTest" + methodName + testcaseNum);
  }

  @Test
  @FileParameters("./test/facility_maintenance/selenium/admin_searchuser.csv")
  public void Search_user (int testcaseNum, String user) throws Exception {
	  driver.get(sAppURL);
	  
	  FM_Login(driver, "admin", "admin");
	  
	  Thread.sleep(1000);
	  
	  driver.findElement(By.linkText(prop.getProperty("Lnk_SearchUser"))).click();
	  
	  try {
		  driver.findElement(By.name(prop.getProperty("Txt_SearchUser_Username"))).clear();
		  driver.findElement(By.name(prop.getProperty("Txt_SearchUser_Username"))).sendKeys(user);
		  }
	  catch( Exception e) { }
	  
	  driver.findElement(By.xpath(prop.getProperty("Btn_SearchUser_Submit"))).click();
	  
	  String methodName = new Throwable().getStackTrace()[0].getMethodName();
	  
	  takeScreenshot(driver, "AdminTest" + methodName + testcaseNum);
	  
	  Thread.sleep(500);
	  
	  FM_Logout(driver);
  }
  
  @Test
  @FileParameters("./test/facility_maintenance/selenium/admin_update.csv")
  public void Update (int testcaseNum, String user) throws Exception {
	  driver.get(sAppURL);
	  
	  FM_Login(driver, "admin", "admin");
	  
	  Thread.sleep(1000);
	  
	  String methodName = new Throwable().getStackTrace()[0].getMethodName();
	  
	  admin_Update(driver, user, "AdminTest" + methodName + testcaseNum);
	  
	  Thread.sleep(500);
	  
	  FM_Logout(driver);
  }
  
  public void admin_Update(WebDriver driver, String user, String screenshotname) throws InterruptedException {
	  driver.findElement(By.linkText(prop.getProperty("Lnk_SearchUser"))).click();
	  driver.findElement(By.name(prop.getProperty("Txt_SearchUser_Username"))).clear();
	  driver.findElement(By.name(prop.getProperty("Txt_SearchUser_Username"))).sendKeys(user);
	  driver.findElement(By.xpath(prop.getProperty("Btn_SearchUser_Submit"))).click();
	  new Select(driver.findElement(By.xpath(prop.getProperty("Lst_Update_Role")))).selectByVisibleText("Repairer");
	  Thread.sleep(1000);
	  driver.findElement(By.xpath(prop.getProperty("Btn_Update_Update"))).click();
	  driver.findElement(By.name(prop.getProperty("Txt_SearchUser_Username"))).clear();
	  driver.findElement(By.name(prop.getProperty("Txt_SearchUser_Username"))).sendKeys(user);
	  driver.findElement(By.xpath(prop.getProperty("Btn_SearchUser_Submit"))).click();
	  takeScreenshot(driver, screenshotname);
	  driver.findElement(By.xpath(prop.getProperty("Btn_Update_Update"))).click();
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
