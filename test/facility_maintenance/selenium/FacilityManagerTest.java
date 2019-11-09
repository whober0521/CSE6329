package facility_maintenance.selenium;

import java.util.regex.Pattern;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import junitparams.JUnitParamsRunner;

@RunWith(JUnitParamsRunner.class)
public class FacilityManagerTest {
  private WebDriver driver;
  private String appURL;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  public static String sharedUIMapStr;
  public static Properties prop;

  @Before
  public void setUp() throws Exception {
	System.setProperty("webdriver.chrome.driver", "/Users/xaiser/CS/school/SoftwareEngineerAdvance/workplace/chromedriver");
	/*
    driver = new ChromeDriver();
    baseUrl = "http://localhost:225/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    */
    
    driver = new ChromeDriver();
    //baseUrl = "http://www.adactin.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    prop = new Properties();
    prop.load(new FileInputStream("Configuration/FM_Configuration.properties"));
    appURL = prop.getProperty("AppURL");
    prop.load(new FileInputStream(prop.getProperty("SharedUIMap")));
  }

  @Test
  public void testA5V0Junit() throws Exception {
	driver.get(appURL);
    //driver.get(baseUrl + "/CSE6329/UserController?action=logout");
	/*
    driver.findElement(By.linkText(prop.getProperty("Btn_Login_Registration"))).click();
    driver.findElement(By.xpath(prop.getProperty("Lxt_Registration_Username"))).clear();
    driver.findElement(By.xpath(prop.getProperty("Lxt_Registration_Username"))).sendKeys("fmsix");
    driver.findElement(By.xpath(prop.getProperty("Lxt_Registration_Password"))).clear();
    driver.findElement(By.xpath(prop.getProperty("Lxt_Registration_Password"))).sendKeys("test1");
    new Select(driver.findElement(By.id(prop.getProperty("Lst_Registration_Role")))).selectByVisibleText("Facility Manager");
    driver.findElement(By.name(prop.getProperty("Lst_Registration_UTAID"))).clear();
    driver.findElement(By.name(prop.getProperty("Lst_Registration_UTAID"))).sendKeys("1111222222");
    driver.findElement(By.name("fname")).clear();
    driver.findElement(By.name("fname")).sendKeys("fn");
    driver.findElement(By.name("lname")).clear();
    driver.findElement(By.name("lname")).sendKeys("ln");
    driver.findElement(By.name("email")).clear();
    driver.findElement(By.name("email")).sendKeys("fm@mavs.uta.edu");
    driver.findElement(By.name("phone")).clear();
    driver.findElement(By.name("phone")).sendKeys("1234567890");
    driver.findElement(By.name("address")).clear();
    driver.findElement(By.name("address")).sendKeys("UTA");
    driver.findElement(By.name("city")).clear();
    driver.findElement(By.name("city")).sendKeys("Arlington");
    new Select(driver.findElement(By.name("state"))).selectByVisibleText("Texas");
    driver.findElement(By.cssSelector("button[type=\"submit\"]")).click();
    */
    driver.findElement(By.xpath(prop.getProperty("Txt_Login_Username"))).clear();
    driver.findElement(By.xpath(prop.getProperty("Txt_Login_Username"))).sendKeys("fmfive");
    driver.findElement(By.xpath(prop.getProperty("Txt_Login_Password"))).clear();
    driver.findElement(By.xpath(prop.getProperty("Txt_Login_Password"))).sendKeys("test1");
    driver.findElement(By.cssSelector("button[type=\"submit\"]")).click();

    driver.findElement(By.linkText(prop.getProperty("Txt_FM_ViewUnassignedMAR"))).click();
    driver.findElement(By.linkText(prop.getProperty("Txt_UAM_View"))).click();
    driver.findElement(By.cssSelector(prop.getProperty("Btn_MM_Submit"))).click();
    new Select(driver.findElement(By.name(prop.getProperty("Lst_MM_Urgency")))).selectByVisibleText("Unusable");
    new Select(driver.findElement(By.name(prop.getProperty("Lst_MM_Repairer")))).selectByVisibleText("rOneDay5");
    new Select(driver.findElement(By.name(prop.getProperty("Lst_MM_Estimate")))).selectByVisibleText("2 days");
    /*
    driver.findElement(By.cssSelector("button[type=\"submit\"]")).click();
    new Select(driver.findElement(By.name("repairer"))).selectByVisibleText("rOneWeek10");
    driver.findElement(By.cssSelector("button[type=\"submit\"]")).click();
    new Select(driver.findElement(By.name("repairer"))).selectByVisibleText("r1");
    driver.findElement(By.cssSelector("button[type=\"submit\"]")).click();
    */
    driver.findElement(By.linkText("Logout")).click();
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
