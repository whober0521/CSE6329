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
public class RepairerTest extends facility_maintenance.FMFunctions {
  private String appURL;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  public static String sharedUIMapStr;
  private String username;
  private String password;

  @Before
  public void setUp() throws Exception {
	System.setProperty("webdriver.chrome.driver","c:/ChromeDriver/chromedriver.exe");
	
    driver = new ChromeDriver();
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    
    prop = new Properties();
    prop.load(new FileInputStream("Configurations/Configuration.properties"));
    prop.load(new FileInputStream("Configurations/Login.properties"));
    prop.load(new FileInputStream(prop.getProperty("SharedUIMap")));
    
    appURL = prop.getProperty("AppURL");
    username = prop.getProperty("RepairerUserName");
    password = prop.getProperty("RepairerPassword");
  }

  @Test
//  @FileParameters("./test/facility_maintenance/selenium/Repairer.csv")
  public void testRepairer() throws Exception {
	driver.get(appURL);

	FM_Register(driver, username, password, "Repairer", "1001161980", "test", "test", "test@uta.edu", "1234567890", "UTA ERB", "Arlington", "Texas", "RepairerTest");
	Thread.sleep(1000);
	
	FM_Login(driver, username, password);
	Thread.sleep(1000);
	
	driver.findElement(By.linkText(prop.getProperty("Lnk_RepairerMenu_View"))).click();
	Thread.sleep(1000);
	
	new Select(driver.findElement(By.xpath(prop.getProperty("Lst_RepairerReserved_Time")))).selectByVisibleText("00:00");
	Thread.sleep(1000);
	driver.findElement(By.cssSelector(prop.getProperty("Btn_Register_Submit"))).click();
	Thread.sleep(1000);
	
	FM_Logout(driver);
	Thread.sleep(1000);
  }
  
  @Test
  @FileParameters("./test/facility_maintenance/selenium/RepairerLink.csv")
  public void testRepairerLink(int testcaseNum, String link, String title) throws Exception {
	  driver.get(appURL);
	  Thread.sleep(1000);
	  
	  FM_Login(driver, username, password);
	  Thread.sleep(1000);
	  
	  driver.findElement(By.linkText(prop.getProperty(link))).click();
	  
	  try {
		  String methodName = new Throwable().getStackTrace()[0].getMethodName();
		  takeScreenshot(driver, "Repairer" + methodName + testcaseNum);
		  assertEquals(title, driver.getTitle());
		  driver.navigate().back();
	  } catch (Error e) {
		  verificationErrors.append(e.toString());
	  }
	  
	  Thread.sleep(1000);
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