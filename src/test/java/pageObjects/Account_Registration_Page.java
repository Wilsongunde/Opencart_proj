package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Account_Registration_Page extends Basepage{
	
	
	
		//Constructor
		public Account_Registration_Page(WebDriver driver)
		{
			
			super(driver); //We can Invoke parent class constructor using inheritance concept immediate parent class variable, methods, constructor by using Super keyword.
			
		}
	
	
		//Locators 
			
		@FindBy(xpath="//*[@name='firstname']")
		WebElement txt_Firstname;
		
		@FindBy(xpath="//*[@id='input-lastname']")
		WebElement txt_Lastname;
		
		@FindBy(xpath="//*[@type='email']")
		WebElement txt_Email;
		
		@FindBy(xpath="//*[@id='input-telephone']")
		WebElement txt_Telephone;
		
		@FindBy(xpath="//*[@name='password']")
		WebElement txt_Password;
		
		@FindBy(xpath="//*[@id='input-confirm']")
		WebElement txt_Confirmpassword;
		
		@FindBy(xpath="//*[@name='agree']")
		WebElement btn_Policycheck;
		
		@FindBy(xpath="//*[@value='Continue']")
		WebElement btn_btncontinue;
		
		@FindBy(xpath="//h1[normalize-space()='Your Account has Been Created!']")
		WebElement msg_Confirmation;
		
		
		//Action methods
		
		public void setfirstname(String firstname)
		{
			
			txt_Firstname.sendKeys(firstname);
			
		}
		
		public void setlastname(String lastname)
		{
			
			txt_Lastname.sendKeys(lastname);
				
		}
		
		public void setemail(String email)
		{
			
			txt_Email.sendKeys(email);
			
		}
		
		public void settelephone(String phnnumber)
		{
			
			txt_Telephone.sendKeys(phnnumber);
				
		}
		
		public void setpassword(String password)
		{
			
			txt_Password.sendKeys(password);
			
		}
		
		public void setpasswordconfirm(String confirmpassword)
		{
			
			txt_Confirmpassword.sendKeys(confirmpassword);
				
		}
		
		public void setprivacypolicycheck()
		{
			
			btn_Policycheck.click();
				
		}
		
		public void clickcontinue()
		{
			
			btn_btncontinue.click();
			
		}
		
		public String Getconfirmationmsg()
		
		{
			try{
				
				return(msg_Confirmation.getText());
				
			}
			catch(Exception e)
			{
				return(e.getMessage());//Once we get the confirmation message it will return / if it is not expected will throw an error.
			}
			
		}	

		
}
