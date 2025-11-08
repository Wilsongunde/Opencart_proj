package testCases;

import static org.testng.Assert.assertTrue;

import java.time.Duration;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.Homepage;
import pageObjects.Loginpage;
import pageObjects.MyAccountPage;
import testBase.Baseforallcommonuse_class;
import utilities.DataProviders;


/* Data is valid -> Login success -> Test Passed - Logout
 * Data is valid > Login failed -> Test failed 
 * 
 * Data is Invalid -> Login success -> Test failed - Logout
 * Data is Invalid -> Login failed -> Test Passed
 */



public class TC003_Datadriven_Test extends Baseforallcommonuse_class{
	
	
	@Test (dataProvider="LoginData", dataProviderClass = DataProviders.class , groups = "Datadriven") // Data provider method is present in different package and different class, so in this case need to pass one more parameter dataproviderclass (Suppose if data provider method this part of class then no need to add additional parameter) 
	public void verify_Datadriven_Test(String email, String pwd, String exp) throws Exception
	{
		
		
		logger.info("++++++ Starting verify_Datadriven_Test ++++++");
		
		try
		{
		
		//Homepage
		Homepage hp= new Homepage(driver);
		
		hp.clickMyaccount();
		logger.info("Clicked on My account link");
		//hp.clickRegister();
		hp.clickLogin();
		logger.info("Clicked on Login link");
		
		//Loginpage
		Loginpage Logpage = new Loginpage(driver);
		
		logger.info("Providing login info");
		
		Logpage.setemail(email);
		Logpage.setpassword(pwd);
		Logpage.clickLogin();
		logger.info("Clicked on continue");
		
		//My account page
		logger.info("Validating expected msg");
		MyAccountPage MyActPage = new MyAccountPage(driver);
		boolean targetpage = MyActPage.IsMyAccountPageExists();
		
		//UN + Pass -> valid data sending -> if login page success -> Test Pass
		//UN + Pass -> Invalid data sending -> if login page success -> Test Fail
		//Blank + Pass -> Invalid data sending -> if login page fail -> Test Pass
		//UN + Pass -> Invalid data sending -> if login page fail -> Test Pass
		
		/*Expected results:
		1 -> Pass
		2 -> Fail
		3 -> Fail
		4 -> Pass
		5 -> Pass
		6 -> Pass
		 */
		
		if(exp.equalsIgnoreCase("Valid")) // Data is valid (equalignorecase used to avoid case sensitive issue I.e uppercase & lowercase letters)
		{
			if(targetpage==true) // Login page success
			{
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
				MyActPage.clicklogout(); // Test Logout
				Assert.assertTrue(true); // Test Passed
				
			}
			else // Login page is failed
			{
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
				Assert.assertTrue(false); // Test Failed
			}
			
		}
		
		if(exp.equalsIgnoreCase("Invalid")) // Data is In valid
		{
			if(targetpage==true) // Login page success
			{
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
				MyActPage.clicklogout(); // Test Logout
				Assert.assertTrue(false); // Test Failed
				
			}
			else // Login page is failed
			{
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
				Assert.assertTrue(true); // Test Passed
			}
			
		}
		
		
		
		
		}
		catch(Exception e)
		{
		
			logger.error(e + "Test Failed");
			logger.debug("Debug logs...");
			Assert.fail();
		}
		
		logger.info("+++ Finished Login & Logout test ++++++ ");
		
		
	}

	
	

}
