package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Loginpage extends Basepage {
	
	
	//Constructor
		public Loginpage(WebDriver driver)
		{
			
			super(driver); //We can Invoke parent class constructor using inheritance concept immediate parent class variable, methods, constructor by using Super keyword.
			
		}
		
		//Locators 
		
		
		@FindBy(xpath="//*[@id='input-email']")
		WebElement txt_email;
		
		@FindBy(xpath="//*[@name='password']")
		WebElement txt_password;
		
		@FindBy(xpath="//*[@value='Login']")
		WebElement btn_Login;
		

		//Action methods
		
		public void setemail(String email)
		{
			
			txt_email.sendKeys(email);
		}
		
		public void setpassword(String pwd)
		{
			
			txt_password.sendKeys(pwd);
				
		}
		
		public void clickLogin()
		{
			
			btn_Login.click();
				
		}

	
	

}
