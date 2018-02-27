package by.htp.belavia.driver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import by.htp.belavia.constants.Constants;

public class Driver {
	
	private static WebDriver driver;
	
	public static WebDriver startWebdriver(){
		if (driver == null) {
			System.setProperty(Constants.WEBDRIVER, Constants.WEBDRIVER_EXE_PATH);
			driver = new ChromeDriver();
			driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.manage().window().maximize();
		}
		
		return driver;
	}
	
	public static void stopWebDriver(){
		driver.quit();
		driver = null;
	}

}
