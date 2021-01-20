package com.learnautomation.testcases;
																													//user can change the browser at run time with jenkins
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.learnautomation.pages.BaseClass;
import com.learnautomation.pages.LoginPage;


public class LoginTestCRM extends BaseClass
{
	//webdriver driver reference is removed, so driver is shown as underlined. Hence we need to extend baseclass now
	//and that driver needs to be made public for all methods to use it
	
	@Test
	public void loginApp()
	{
																													//now we need to create a page object, we have class called page factory class which has method called "initElements" asking which page you want to initialize	
		
		logger=report.createTest("Login To CRM");																	//reports : object of extent reports and this will return the object of extent test which we already created by name of "logger"which will responsible for all the logging activities in side the testcase
		
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);									//it will go to the login page, check all the locators, it will initialize them and once done, it will return object of the same class
																													//this is abstraction-hiding the background details and showing the essential features(what locators we used, actions, strategy etc)				
		logger.info("Starting Application");																											 
	
		loginPage.loginToCRM(excel.getStringData("Login", 0, 0), excel.getStringData("Login", 0, 1));
		
		logger.pass("Login Success");
	}		
	
}
