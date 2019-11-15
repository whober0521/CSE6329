package test;

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
  public class testadmin extends functions.functions {
  private static String sAppURL;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  public static String sharedUIMapStr;

  @Before
  public void setUp() throws Exception {
	System.setProperty("webdriver.chrome.driver","c:/ChromeDriver/chromedriver.exe");
	driver = new ChromeDriver();
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    prop = new Properties();
    //prop.load(new FileInputStream("./SharedUIMap/SharedUIMap.properties"));
    prop.load(new FileInputStream("./Configuration/Configuration.properties"));
    sAppURL = prop.getProperty("sAppURL");
    sharedUIMapStr = prop.getProperty("SharedUIMap");
    System.out.print(sharedUIMapStr);
    prop.load(new FileInputStream(sharedUIMapStr));
    System.out.print(prop.getProperty("Txt_Register_Password"));
	System.out.print(prop.getProperty("Txt_Register_UTAID"));
  }

  @Test
  @FileParameters("src/test/admin_registration.csv")
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
	
	admin_Register(driver, username, pwd, role, utaid, firstName, lastName, email, phone, address, city, state, "AdminTest" + methodName + testcaseNum);
	
    
  }

  @Test
  @FileParameters("src/test/admin_searchuser.csv")
  public void Search_user (int testcaseNum, String user) throws Exception {
	driver.get(sAppURL);
	
	admin_Login(driver, "admin", "admin");    

    Thread.sleep(1000);
    driver.findElement(By.linkText(prop.getProperty("Lnk_SearchUser"))).click();
    try
    {
    	driver.findElement(By.name(prop.getProperty("Txt_SearchUser_Username"))).clear();
        driver.findElement(By.name(prop.getProperty("Txt_SearchUser_Username"))).sendKeys(user);
    }
    catch( Exception e)
    { }
    driver.findElement(By.xpath(prop.getProperty("Btn_SearchUser_Submit"))).click();
    String methodName = new Throwable().getStackTrace()[0].getMethodName();
    takeScreenshot(driver, "AdminTest" + methodName + testcaseNum);
    Thread.sleep(500);
    admin_Logout(driver); 
  }
  
  @Test
  @FileParameters("src/test/admin_update.csv")
  public void Update (int testcaseNum, String user) throws Exception {
  driver.get(sAppURL);	
  admin_Login(driver, "admin", "admin");
  Thread.sleep(1000);
  String methodName = new Throwable().getStackTrace()[0].getMethodName();
  admin_Update(driver, user, "AdminTest" + methodName + testcaseNum);
  Thread.sleep(500);
  admin_Logout(driver);
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
