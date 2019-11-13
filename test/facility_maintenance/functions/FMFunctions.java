package facility_maintenance.functions;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

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
}