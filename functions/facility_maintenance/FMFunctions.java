package facility_maintenance;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class FMFunctions {
	public static WebDriver driver;
	public static Properties prop;
	
	public void takeScreenshot(WebDriver driver, String screenshotname) {
		try {
			File source = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(source, new File("./ScreenShots/" + screenshotname +".png"));
		}
		catch(IOException e) {}
		
		try {
//			Change the delay value to 1_000 to insert a 1 second delay after 
//			each screenshot
			Thread.sleep(0);
		}
		catch (InterruptedException e) {}
	}
	
	public void FM_Login(WebDriver driver, String sUserName, String sPassword) throws InterruptedException
	{
	    driver.findElement(By.xpath(prop.getProperty("Txt_Login_Username"))).clear();
	    driver.findElement(By.xpath(prop.getProperty("Txt_Login_Username"))).sendKeys(sUserName);
	    
	    driver.findElement(By.xpath(prop.getProperty("Txt_Login_Password"))).clear();
	    driver.findElement(By.xpath(prop.getProperty("Txt_Login_Password"))).sendKeys(sPassword);
	    
	    Thread.sleep(1000);
	       
	    driver.findElement(By.cssSelector(prop.getProperty("Btn_Login_Login"))).click();
	}
	
	public void FM_Logout(WebDriver driver) throws InterruptedException
	{
		driver.findElement(By.linkText(prop.getProperty("Lnk_Logout"))).click();
	}
	
	public void FM_Register(WebDriver driver, String username, String pwd, String role, String utaid, String firstName, String lastName,
			String email, String phone, String address, String city, String state, String screenshotname) throws InterruptedException
	{
		driver.findElement(By.linkText(prop.getProperty("Lnk_Register_Register"))).click();
		
		driver.findElement(By.xpath(prop.getProperty("Txt_Register_Username"))).clear();
	    driver.findElement(By.xpath(prop.getProperty("Txt_Register_Username"))).sendKeys(username);

	    driver.findElement(By.xpath(prop.getProperty("Txt_Register_Password"))).clear();
	    driver.findElement(By.xpath(prop.getProperty("Txt_Register_Password"))).sendKeys(pwd);

	    new Select(driver.findElement(By.xpath(prop.getProperty("Lst_Register_Role")))).selectByVisibleText(role);
	    
	    driver.findElement(By.xpath(prop.getProperty("Txt_Register_UTAID"))).clear();
	    driver.findElement(By.xpath(prop.getProperty("Txt_Register_UTAID"))).sendKeys(utaid);
	    
	    driver.findElement(By.xpath(prop.getProperty("Txt_Register_FirstName"))).clear();
	    driver.findElement(By.xpath(prop.getProperty("Txt_Register_FirstName"))).sendKeys(firstName);
	    
	    driver.findElement(By.xpath(prop.getProperty("Txt_Register_LastName"))).clear();
	    driver.findElement(By.xpath(prop.getProperty("Txt_Register_LastName"))).sendKeys(lastName);
	    
	    driver.findElement(By.xpath(prop.getProperty("Txt_Register_EMail"))).clear();
	    driver.findElement(By.xpath(prop.getProperty("Txt_Register_EMail"))).sendKeys(email);
	    
	    driver.findElement(By.xpath(prop.getProperty("Txt_Register_Phone"))).clear();
	    driver.findElement(By.xpath(prop.getProperty("Txt_Register_Phone"))).sendKeys(phone);
	    
	    driver.findElement(By.xpath(prop.getProperty("Txt_Register_Address"))).clear();
	    driver.findElement(By.xpath(prop.getProperty("Txt_Register_Address"))).sendKeys(address);
	    
	    driver.findElement(By.xpath(prop.getProperty("Txt_Register_City"))).clear();
	    driver.findElement(By.xpath(prop.getProperty("Txt_Register_City"))).sendKeys(city);
	    
	    new Select(driver.findElement(By.xpath(prop.getProperty("Lst_Register_State")))).selectByVisibleText(state);

	    Thread.sleep(1000);
	    
		takeScreenshot(driver, screenshotname);
	    driver.findElement(By.cssSelector(prop.getProperty("Btn_Register_Submit"))).click();
	}
}
