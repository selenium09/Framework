package com.learnautomation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage 										//here we will store all the locators
{
	WebDriver driver;
	
	//new commit from Sumit for Github
	public LoginPage(WebDriver ldriver)  					//first we create a constructor, this will help initialize the webdriver
	  {														//we need to pass the webdriver as reference, otherwise it wont be able to identify the driver 
		  this.driver=ldriver;								//ldriver will come from main testcase, whatever driver reference is passed there will be stored in ldriver
	  }	

	@FindBy(name="email") WebElement uname;
	
	@FindBy(name="password") WebElement pass;
	
	@FindBy(xpath="//*[@class='ui fluid large blue submit button']") WebElement LoginButton;
	
	public void loginToCRM(String usernameApplication, String passwordApplication)
	{
		uname.sendKeys(usernameApplication);
		pass.sendKeys(passwordApplication);
		LoginButton.click();
	}
	
	
}
