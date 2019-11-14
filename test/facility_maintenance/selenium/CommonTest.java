package facility_maintenance.selenium;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class CommonTest {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://192.168.3.129:8080/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testUser() throws Exception {
    driver.get(baseUrl + "/facility_maintenance/");
    driver.findElement(By.linkText("Sign up")).click();
    driver.findElement(By.name("username")).clear();
    driver.findElement(By.name("username")).sendKeys("whober");
    driver.findElement(By.name("pwd")).clear();
    driver.findElement(By.name("pwd")).sendKeys("chen");
    driver.findElement(By.name("utaid")).clear();
    driver.findElement(By.name("utaid")).sendKeys("1001161980");
    driver.findElement(By.name("fname")).clear();
    driver.findElement(By.name("fname")).sendKeys("whopper");
    driver.findElement(By.name("username")).clear();
    driver.findElement(By.name("username")).sendKeys("whopper");
    driver.findElement(By.name("lname")).clear();
    driver.findElement(By.name("lname")).sendKeys("chen");
    driver.findElement(By.name("email")).clear();
    driver.findElement(By.name("email")).sendKeys("whopper@uta.edu");
    driver.findElement(By.name("phone")).clear();
    driver.findElement(By.name("phone")).sendKeys("1234567890");
    driver.findElement(By.name("address")).clear();
    driver.findElement(By.name("address")).sendKeys("UTA");
    driver.findElement(By.name("city")).clear();
    driver.findElement(By.name("city")).sendKeys("DFW");
    new Select(driver.findElement(By.name("state"))).selectByVisibleText("Texas");
    driver.findElement(By.cssSelector("button[type=\"submit\"]")).click();
    driver.findElement(By.cssSelector("button[type=\"submit\"]")).click();
    driver.findElement(By.linkText("Create Problem Report")).click();
    driver.findElement(By.id("msg")).clear();
    driver.findElement(By.id("msg")).sendKeys("TEST");
    driver.findElement(By.cssSelector("button[type=\"submit\"]")).click();
    driver.findElement(By.id("msg")).clear();
    driver.findElement(By.id("msg")).sendKeys("test");
    driver.findElement(By.cssSelector("button[type=\"submit\"]")).click();
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