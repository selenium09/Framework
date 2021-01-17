package com.learnautomation.utility;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

//Browser factory will take care of set property and browser invoking

public class BrowserFactory 
{

	public static WebDriver startApplication(WebDriver driver,String browserName,String appURL)			//why return "web browser" - once we start the browser, we will return this reference
	{
		if(browserName.equalsIgnoreCase("firefox"))														//inputs - how these class will communicate-how it will understand which driver to use,so ldriver(local driver) and browser name and URL
		{
			System.setProperty("webdriver.gecko.driver", "./Drivers/geckodriver.exe");					//copied all drivers to drivers folder
			driver = new FirefoxDriver();			
		}
		else if(browserName.equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver.exe");
			 driver = new ChromeDriver();
		}
		else if(browserName.equalsIgnoreCase("IE"))
		{
			System.setProperty("webdriver.ie.driver", "./Drivers/IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}
		
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);								//common for all-so pasted here
		driver.manage().window().maximize();
		driver.get(appURL); 																			//get url from user
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);								//variables are loading slowly or not visible immediately
		
		return driver;
		}
	
	public static void quitBrowser(WebDriver driver)				//its better to pass webdriver, so that it closes the respective browser
	{
		driver.quit();
	}
	
	
	
}
