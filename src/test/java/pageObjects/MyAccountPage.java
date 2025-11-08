package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends Basepage {
	
	
	
	//Constructor
		public MyAccountPage(WebDriver driver)
			{
				
				super(driver); //We can Invoke parent class constructor using inheritance concept immediate parent class variable, methods, constructor by using Super keyword.
				
			}
			
	//Locators
		
		
		@FindBy(xpath="//h2[text()='My Account']") // My Account page heading
		WebElement msg_heading;
				
		@FindBy(linkText = "Logout") // Logout
		WebElement linklogout;
		
		public boolean IsMyAccountPageExists()
		{
			
			try 
			{
				
			return (msg_heading.isDisplayed());
			}
			catch(Exception e)
			{
				return false;
			}
			
			
		}
		
		//Action methods
		public void clicklogout()
		{
			
			linklogout.click();
				
		}

				

}
