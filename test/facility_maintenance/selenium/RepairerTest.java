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
  
//  @Test
//  @FileParameters("./test/facility_maintenance/selenium/RepairViewAssigned.csv")
//  public void testRepairAssigned(int testcaseNum, String Idx, String Type, String Name, String Urgency, String Description,
//		  String Reported, String Date, String Assigneddate, String Estimate, String Reservation) throws Exception {
//	  driver.get(appURL);
//	  
//	  FM_Login(driver, username, password);
//	  Thread.sleep(1000);
//	  
//	  takeScreenshot(driver, "RepairTest" + "RepairerHome" + testcaseNum);
//	  driver.findElement(By.linkText(prop.getProperty("Lnk_RepairerMenu_View"))).click();
//	  Thread.sleep(1000);
//	  
//	  driver.findElement(By.xpath(prop.getProperty("Txt_RepairerReserved_Date"))).clear();
//	  driver.findElement(By.xpath(prop.getProperty("Txt_RepairerReserved_Date"))).sendKeys("11/25/2019");
//	  new Select(driver.findElement(By.xpath(prop.getProperty("Lst_RepairerReserved_Time")))).selectByVisibleText("00:00");
//	  takeScreenshot(driver, "RepairTest" + "RepairerSearch" + testcaseNum);
//	  driver.findElement(By.cssSelector(prop.getProperty("Btn_Register_Submit"))).click();
//	  Thread.sleep(1000);
//	  
//	  assertEquals(Idx, driver.findElement(By.xpath("/html/body/table/tbody/tr["+(testcaseNum+1)+"]/td[1]")).getText());
//	  driver.findElement(By.xpath("html/body/table/tbody/tr["+(testcaseNum+1)+"]/td[2]/a")).click();
//	  assertEquals(Type, driver.findElement(By.xpath(prop.getProperty("Txt_RepairerReserved_Type"))).getText());
//	  assertEquals(Name, driver.findElement(By.xpath(prop.getProperty("Txt_RepairerReserved_Name"))).getText());
//	  assertEquals(Urgency, driver.findElement(By.xpath(prop.getProperty("Txt_RepairerReserved_Urgency"))).getText());
//	  assertEquals(Description, driver.findElement(By.xpath(prop.getProperty("Txt_RepairerReserved_Description"))).getText());
//	  assertEquals(Reported, driver.findElement(By.xpath(prop.getProperty("Txt_RepairerReserved_Reported"))).getText());
//	  assertEquals(Date, driver.findElement(By.xpath(prop.getProperty("Txt_RepairerReserved_AddDate"))).getText());
//	  assertEquals(Idx, driver.findElement(By.xpath(prop.getProperty("Txt_RepairerReserved_Idx"))).getText());
//	  assertEquals(username, driver.findElement(By.xpath(prop.getProperty("Txt_RepairerReserved_Assigned"))).getText());
//	  assertEquals(Assigneddate, driver.findElement(By.xpath(prop.getProperty("Txt_RepairerReserved_AssignedDate"))).getText());
//	  assertEquals(Estimate, driver.findElement(By.xpath(prop.getProperty("Txt_RepairerReserved_Estimate"))).getText());
//	  String methodName = new Throwable().getStackTrace()[0].getMethodName();
//	  takeScreenshot(driver, "RepairTest" + "RepairerDetail" + testcaseNum);
//	  
//	  driver.findElement(By.linkText(prop.getProperty("Txt_Home"))).click();
//	  Thread.sleep(1000);
//	  driver.findElement(By.linkText(prop.getProperty("Lnk_RepairerMenu_Reservation"))).click();
//	  driver.findElement(By.cssSelector(prop.getProperty("Btn_RepairerRequest_Search"))).click();
//	  Thread.sleep(1000);
//	  takeScreenshot(driver, "RepairTest" + "RepairerFacilityError" + testcaseNum);
//	  new Select(driver.findElement(By.xpath(prop.getProperty("Lst_RepairerRequest_Name")))).selectByVisibleText("BMC1");
//	  driver.findElement(By.xpath(prop.getProperty("Txt_RepairerRequest_Date"))).clear();
//	  driver.findElement(By.xpath(prop.getProperty("Txt_RepairerRequest_Date"))).sendKeys("11/30/2019");
//	  driver.findElement(By.cssSelector(prop.getProperty("Btn_RepairerRequest_Search"))).click();
//	  Thread.sleep(1000);
//	  takeScreenshot(driver, "RepairTest" + "RepairerDateError" + testcaseNum);
//	  driver.findElement(By.xpath(prop.getProperty("Txt_RepairerRequest_Date"))).clear();
//	  driver.findElement(By.xpath(prop.getProperty("Txt_RepairerRequest_Date"))).sendKeys("11/26/2019");
//	  new Select(driver.findElement(By.xpath(prop.getProperty("Lst_RepairerRequest_Time")))).selectByVisibleText("00:00");
//	  driver.findElement(By.cssSelector(prop.getProperty("Btn_RepairerRequest_Search"))).click();
//	  Thread.sleep(1000);
//	  driver.findElement(By.linkText(prop.getProperty("Lnk_Txt_RepairerRequest_Book"))).click();
//	  Thread.sleep(1000);
//	  
//	  driver.findElement(By.linkText(prop.getProperty("Lnk_RepairerMenu_View"))).click();
//	  driver.findElement(By.xpath(prop.getProperty("Txt_RepairerReserved_Date"))).clear();
//	  driver.findElement(By.xpath(prop.getProperty("Txt_RepairerReserved_Date"))).sendKeys("11/25/2019");
//	  new Select(driver.findElement(By.xpath(prop.getProperty("Lst_RepairerReserved_Time")))).selectByVisibleText("00:00");
//	  Thread.sleep(1000);
//	  driver.findElement(By.cssSelector(prop.getProperty("Btn_Register_Submit"))).click();
//	  Thread.sleep(1000);
//	  driver.findElement(By.xpath("html/body/table/tbody/tr["+(testcaseNum+1)+"]/td[2]/a")).click();
//	  Thread.sleep(1000);
//	  assertEquals(Reservation, driver.findElement(By.xpath(prop.getProperty("Txt_RepairerReserved_Reservation"))).getText().replace('\n', ' '));
//	  takeScreenshot(driver, "RepairTest" + "RepairerRequestDetail" + testcaseNum);
//	  FM_Logout(driver);
//	  Thread.sleep(1000);
//	  
//	  
//}

//  	@Test
//  	@FileParameters("./test/facility_maintenance/selenium/RepairerModify.csv")
//  	public void testRepairerModify(int testcaseNum, String hours) throws Exception {
//  	  driver.get(appURL);
//	  
//  	  FM_Login(driver, username, password);
//  	  Thread.sleep(1000);
//  	  
//  	  driver.findElement(By.linkText(prop.getProperty("Lnk_RepairerMenu_View"))).click();
//  	  Thread.sleep(1000);
//  	  
//  	  driver.findElement(By.xpath(prop.getProperty("Txt_RepairerReserved_Date"))).clear();
//  	  driver.findElement(By.xpath(prop.getProperty("Txt_RepairerReserved_Date"))).sendKeys("11/25/2019");
//  	  new Select(driver.findElement(By.xpath(prop.getProperty("Lst_RepairerReserved_Time")))).selectByVisibleText("00:00");
//  	  driver.findElement(By.cssSelector(prop.getProperty("Btn_Register_Submit"))).click();
//  	  Thread.sleep(1000);
//  	  
//  	  driver.findElement(By.xpath("html/body/table/tbody/tr["+( testcaseNum + 1)+"]/td[2]/a")).click();
//  	  new Select(driver.findElement(By.xpath(prop.getProperty("Lst_RepairerReserved_ChangeTime")))).selectByVisibleText(hours);
//  	  
//	  driver.findElement(By.cssSelector(prop.getProperty("Btn_RepairerReserved_ChangeTime"))).click();
//	  String methodName = new Throwable().getStackTrace()[0].getMethodName();
//	  takeScreenshot(driver, "RepairTest" + methodName + testcaseNum);
//	  
//	  FM_Logout(driver);
//	  Thread.sleep(1000);
//    }
//  	
  	@Test
  	@FileParameters("./test/facility_maintenance/selenium/RepairerCancel.csv")
  	public void testRepairerCancel(int testcaseNum) throws Exception {
    	  driver.get(appURL);
  	  
    	  FM_Login(driver, username, password);
    	  Thread.sleep(1000);
    	  
    	  driver.findElement(By.linkText(prop.getProperty("Lnk_RepairerMenu_View"))).click();
    	  Thread.sleep(1000);
    	  
    	  driver.findElement(By.xpath(prop.getProperty("Txt_RepairerReserved_Date"))).clear();
    	  driver.findElement(By.xpath(prop.getProperty("Txt_RepairerReserved_Date"))).sendKeys("11/25/2019");
    	  new Select(driver.findElement(By.xpath(prop.getProperty("Lst_RepairerReserved_Time")))).selectByVisibleText("00:00");
    	  driver.findElement(By.cssSelector(prop.getProperty("Btn_Register_Submit"))).click();
    	  Thread.sleep(1000);
    	  
    	  String methodName = new Throwable().getStackTrace()[0].getMethodName();
    	  takeScreenshot(driver, "RepairTest" + methodName + testcaseNum);
    	  driver.findElement(By.xpath("html/body/table/tbody/tr["+( testcaseNum + 1)+"]/td[3]/a")).click();
    	  
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