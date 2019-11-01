package facility_maintenance;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
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
}