package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Homepage extends Basepage{
	
	
	
	//Constructor
	public Homepage(WebDriver driver)
	{
		
		super(driver); //We can Invoke parent class constructor using inheritance concept immediate parent class variable, methods, constructor by using Super keyword.
		
	}
	
	//Locators 
	
	
	@FindBy(xpath="//span[text()='My Account']")
	WebElement linkMyaccount;
	
	@FindBy(xpath="//*[text()='Register']")
	WebElement linkRegister;
	
	@FindBy(linkText= "Login") // Login link added in steps
	WebElement linkLogin;
	

	//Action methods
	
	public void clickMyaccount()
	{
		
		linkMyaccount.click();		
	}
	
	public void clickRegister()
	{
		
		linkRegister.click();
			
	}
	
	public void clickLogin()
	{
		
		linkLogin.click();
			
	}
	

}
