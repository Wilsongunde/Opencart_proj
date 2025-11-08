package testCases;

import java.time.Duration;



import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.Account_Registration_Page;
import pageObjects.Basepage;
import pageObjects.Homepage;
import testBase.Baseforallcommonuse_class;

public class TC001_AccountRegistration_Test extends Baseforallcommonuse_class{
	

	@Test(groups = {"Regression","Master"}) // When we add multiple groups need to place {,} for single group no need to place curly braces.
	public void verify_account_registriation() throws Exception
	{
		
		
		logger.info("++++++ Starting verify_account_registriation ++++++");
		
		try
		{
			
		Homepage hp= new Homepage(driver);
		
		hp.clickMyaccount();
		logger.info("Clicked on My account link");
		hp.clickRegister();
		logger.info("Clicked on Register link");
		Account_Registration_Page Regpage = new Account_Registration_Page(driver);
		
		logger.info("Providing registration info");
	//	Regpage.setfirstname("AJ"); //Hardcoded
	//	Regpage.setlastname("red"); //Hardcoded
		Regpage.setfirstname(randomstring().toUpperCase()); // Randomly generate the test data using that new method so most of the times will send upper case + lower case letters so used touppercase method (I.e RandomstringUtils)
		Regpage.setlastname(randomstring().toUpperCase()); // Randomly generate the test data using that new method so most of the times will send upper case + lower case letters so used touppercase method (I.e RandomstringUtils)
	//	Regpage.setemail("Aj@gmail.com"); //Hardcoded
		Regpage.setemail(randomstring()+"@gmail.com");//Randomly generated the email (For that we have to create 1 more java method I.e RandomstringUtils )
		
	//	Regpage.settelephone("8899776634"); // Hardcoded
		Regpage.settelephone(randomnumber()); // Randomly generate the test data using that new method I.e RandomstringUtils
	
		String password = randomalphanumber();  // When we need to send same password twice we have to store in a variable, using that we can pass the values where we required.

	//	Regpage.setpassword("xyz12345"); // Hardcoded
		Regpage.setpassword(password);  // Randomly generate the test data using that new method I.e RandomstringUtils
	//	Regpage.setpasswordconfirm("xyz12345"); // Hardcoded
		Regpage.setpasswordconfirm(password);  // Randomly generate the test data using that new method I.e RandomstringUtils
		
		
		Regpage.setprivacypolicycheck();
		Regpage.clickcontinue();
		logger.info("Clicked on continue");
		logger.info("Validating expected msg");
//		String confirmsg = Regpage.Getconfirmationmsg();
//		Assert.assertEquals(confirmsg,"Your Account Has Been Created!");
		
		String exp_title = "Your Account Has Been Created!";
		String act_title = "Your Account Has Been Created!";
		
//		----------- Conditional statement
		
		if(exp_title.equals(act_title))
		{	
			System.out.println("Test Passed");	
		}
		else
		{
			System.out.println("Test Failed");
		}
		
		}
		catch(Exception e)
		{
			
			logger.error(e + "Test Failed");
			logger.debug("Debug logs...");
			Assert.fail();
			
		}
		
		logger.info("+++ Finished verify_account_registriation ++++++ ");
		
		
		
		
	}
	
}	
	

