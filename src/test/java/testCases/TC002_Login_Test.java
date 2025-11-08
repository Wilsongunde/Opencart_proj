package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.Account_Registration_Page;
import pageObjects.Homepage;
import pageObjects.Loginpage;
import pageObjects.MyAccountPage;
import testBase.Baseforallcommonuse_class;

public class TC002_Login_Test extends Baseforallcommonuse_class {
	
	
	@Test(groups = {"Sanity","Master"}) // When we add multiple groups need to place {,} for single group no need to place curly braces.
	public void verify_login() throws Exception
	{
		
		
		logger.info("++++++ Starting verify_login ++++++");
		
		try
		{
		
		//Homepage
		Homepage hp= new Homepage(driver);
		
		hp.clickMyaccount();
		logger.info("Clicked on My account link");
		hp.clickRegister();
		hp.clickLogin();
		logger.info("Clicked on Login link");
		
		//Loginpage
		Loginpage Logpage = new Loginpage(driver);
		
		logger.info("Providing login info");
		
		Logpage.setemail(p.getProperty("email"));
		Logpage.setpassword(p.getProperty("password"));
		Logpage.clickLogin();
		logger.info("Clicked on continue");
		Thread.sleep(3000);
		
		//My account page
		logger.info("Validating expected msg");
		MyAccountPage MyActPage = new MyAccountPage(driver);
		boolean targetpage = MyActPage.IsMyAccountPageExists();
		
		Assert.assertEquals(targetpage, true, "Test Failed");
		
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

	
	


