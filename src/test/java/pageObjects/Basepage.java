package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import org.openqa.selenium.support.PageFactory;

public class Basepage {
	
	
protected WebDriver driver;

	//Constructor
	public Basepage(WebDriver driver)
	{
		
		this.driver=driver;
		PageFactory.initElements(driver, this); // Mandatory
		
	}
	

}
