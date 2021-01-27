package com.learnautomation.pages;

import java.io.File;
import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.learnautomation.utility.BrowserFactory;
import com.learnautomation.utility.ConfigDataProvider;
import com.learnautomation.utility.ExcelDataProvider;
import com.learnautomation.utility.Helper;


//it will have all the pre condition and post conditions which are required for each and every testcase-copying before and after classes here now
public class BaseClass 
{
	public WebDriver driver;		
	public ExcelDataProvider excel;
	public ConfigDataProvider config;
	public ExtentReports report;														//global variable of extent report
	public ExtentTest logger;
																						//in before suite, i want to initialize my reports parts so that I can use it across my framework/dont keep it in before class
	@BeforeSuite																		//as soon as your test suite will start, it will setup your excel data provider
	public void setUpSuite()
	{
																						//for custom logs-these are for your info only - log4j, reporter.log, sysout statement
		
		Reporter.log("Setting up reports and test is getting ready", true);				//default custom testng report-it will generate the log and true will print the log in your console
		
		
		excel = new ExcelDataProvider();												//creating object of dataprovider to activate excel sheet(constructor) and use it in here
		config = new ConfigDataProvider();												//copied from logintestCRM-this can also be done here
		
		ExtentHtmlReporter extent=new ExtentHtmlReporter(new File(System.getProperty("user.dir")+"/Reports/FreeCRM_"+Helper.getCurrentDateTime() +".html"));	//it asks you where you want to generate the report, metion file path
																																	//user.dir = return current working directly-in this case path of current framework
		report = new ExtentReports();
		report.attachReporter(extent);													//to attach the report			
		
		Reporter.log("Setting done-test can be started", true);
	}																									
	
	
	//@Parameters({"browser","urlToBeTested"})																			//this variable name should match with xml file-for jenkins
	@BeforeClass
	public void setup(String browser, String url)
	{
		Reporter.log("Trying to start browser and getting application ready", true);
		//driver = BrowserFactory.startApplication(driver,config.getBrowser(),config.getStagingURL());					//web driver reference created as global-we are taking browser from config file
		
		driver = BrowserFactory.startApplication(driver,browser,url);								//taking browser input from pom.xml file-but how maven will pass this browser variable to it-so we have annotation called parameters where you can accept no. of parameters such as browser 
		
		//System.out.println(driver.getTitle());																		//since this method return webdriver, lets print what it return and assign its value to driver	
		Reporter.log("Browser and application are up and running", true);
	}	
	
	@AfterClass
	public void teardown()
	{
		BrowserFactory.quitBrowser(driver);
	}
	
	@AfterMethod																			//very helpful when you want to run a piece of code after every testcase e.g i want to run take screenshot even after method is failed
	public void tearDownMethod(ITestResult result) throws IOException						//if you have 50 tc and you want to run an activity 50 times, keep it under after method 
	{																						//Itestresult interface - as soon as your test is complete this result variable will have all the information
		Reporter.log("Test is about to end", true);
		
		if(result.getStatus()==ITestResult.FAILURE)											//add screenshot to the report-media entity any fail test screenshot, to attach it we have class "mediaentitybuilder" +method called "capture screenshot from path-it will ask where screenshot is placed and attaches it			
		{
			//Helper.captureScreenshot(driver);												//not required now, as we added it below     
			logger.fail("Test Failed", MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreenshot(driver)).build());
		}
		else if(result.getStatus()==ITestResult.SUCCESS)
		{
			logger.pass("Test Passed", MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreenshot(driver)).build());
		}
		
		report.flush();  																	//this will keep on adding all tests in a single report
	
		Reporter.log("Test completed >>> Report generated", true);	
	}
	
	
	
}
