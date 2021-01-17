package com.learnautomation.utility;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class Helper 
{

	//Screenshot, alerts, frames, windows, sync issues, javascript executor
			
	public static String captureScreenshot(WebDriver driver)												//setting to static so that you can call it by classname or you need to create an object of class
	{
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);							//we have a separate interface named takescreenshot which has method getscreenshotAs which will return the object as file type.
		String screenshotPath = System.getProperty("user.dir")+"/Screenshots/FreeCRM_"+getCurrentDateTime()+".png";	//to get path where screenshot is placed-used in base class
		
		try {
			FileHandler.copy(src, new File(screenshotPath));			//"declared above"- this has to be in file format so folder name and login.png, directly called getcurrentdate time () as we are in same class
			
			System.out.println("Screenshot captured");
			
		} catch (IOException e) {
			System.out.println("Unable to capture screenshot"+e.getMessage());
		}				
		
		return screenshotPath;											//return screenshot path
	}
	
	public static String getCurrentDateTime()										//as every screenshot will have nomenclature as login.png, we are creating a different class to address this issue 
	{
		DateFormat customFormat = new SimpleDateFormat("MM_dd_yyyy_HH_mm_ss");
		Date currentDate = new Date();										//to capture the date we have date class
		return customFormat.format(currentDate);							//format is the method-we format the current date	
		
	}
	
	
	
	
	
	
}
