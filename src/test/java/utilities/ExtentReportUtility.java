package utilities;

import java.awt.Desktop;
import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.Baseforallcommonuse_class;

public class ExtentReportUtility implements ITestListener{
	

	public ExtentSparkReporter SparkReporter; // Responsible UI of the report ex: look and feel, color, alignment
	
	public ExtentReports ExtentRep; // Responsible to populate common info on the report ex: who is executing the testcase tester name, which browser executing the test case browser name , which operating executing the testcase operating name,  which environment executing the testcase environment name.
	
	public ExtentTest ExtTest; // Responsible for to create test case entries in the report and  update status of the test methods ex: Updating the results like pass, fail, attaching screenshot of failure or success
	
	String Repname;
	
	
	public void onStart(ITestContext context) 
	{
		/*SimpleDateFormat df=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		Date dt=new Date();
		String currentdatetimestamp=df.format(dt);  
		*/
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());// time stamp (Combined all above 3 steps to single step)
		
		Repname = "Test-Report-" + timeStamp + ".html";
		SparkReporter = new ExtentSparkReporter(".\\reports\\" + Repname);// specify location of the report

		SparkReporter.config().setDocumentTitle("Opencart Automation Report"); // Title of report
		SparkReporter.config().setReportName("Opencart Functional Testing"); //Name of the report
		SparkReporter.config().setTheme(Theme.DARK);// Color of the theme
		
		
		
		ExtentRep = new ExtentReports();
		ExtentRep.attachReporter(SparkReporter);
		
		ExtentRep.setSystemInfo("Application", "opencart");
		ExtentRep.setSystemInfo("Module", "Admin");
		ExtentRep.setSystemInfo("Sub Module", "Customers");
		ExtentRep.setSystemInfo("User Name", System.getProperty("user.name")); // Who will execute the test case I.e To get the name of the Tester (user.name means -> current system user name)
		ExtentRep.setSystemInfo("Environment", "QA"); // Which environment to test

		String os = context.getCurrentXmlTest().getParameter("os"); // Which operating system to test it will get from current xml file. 
		ExtentRep.setSystemInfo("Operating System", os);

		String browser = context.getCurrentXmlTest().getParameter("browser"); // Which browser want to test it will get from current xml file. 
		ExtentRep.setSystemInfo("Browser", browser);
		
		List<String> includedGroups = context.getCurrentXmlTest().getIncludedGroups(); // Will capture the group names that we specified in include section from current xml file, all the groups which we are storing in list collection (variable)
		if(!includedGroups.isEmpty())  // If no groups are available in xml file it will not generate in report, if available it will generate the report
		{
			ExtentRep.setSystemInfo("Groups", includedGroups.toString()); //To printing the groups in report if available using (to string)
		}
		
		
		
	}
	
	public void onTestSuccess(ITestResult result) 
	{
		
		ExtTest = ExtentRep.createTest(result.getTestClass().getName()); // We are getting the class name and creating new entry in the report
		ExtTest.assignCategory(result.getMethod().getGroups()); // We are getting the method name  + group name to display in the report
		ExtTest.log(Status.PASS, result.getName() + " Successfully executed"); // Update status as Pass/Fail/Skip
		
	}
	
	public void onTestFailure(ITestResult result) 
	{
		ExtTest = ExtentRep.createTest(result.getTestClass().getName()); // We are getting the class name and creating new entry in the report
		ExtTest.assignCategory(result.getMethod().getGroups()); // We are getting the method name  + group name to display in the report (assign category means -> group)
		
		ExtTest.log(Status.FAIL, result.getName() + "Test Case FAILED");
		ExtTest.log(Status.INFO, result.getThrowable().getMessage());
		
		try {
			
			Thread.sleep(4000);
			String imgpath = new Baseforallcommonuse_class().captureScreen(result.getName()); // We are getting the class name of failed one and capturing in variable (Screen shot of failure) (Here 2 drivers instance i.base class driver, ii. object related driver are not the same, conflicts will appear for this we have to make webdriver as static) 
			ExtTest.addScreenCaptureFromPath(imgpath); //It will attach failed screenshot in report
			
		}
	    catch(Exception e) {
	    	
	    	e.printStackTrace(); // If the screenshot is not properly taken / not available in this case will get error message I.e file not found exception
	    }
		
		
	}
	
	public void onTestSkipped(ITestResult result) 
	{
		
		
		ExtTest = ExtentRep.createTest(result.getTestClass().getName()); // We are getting the class name and creating new entry in the report
		ExtTest.assignCategory(result.getMethod().getGroups()); // We are getting the method name  + group name to display in the report (assign category means -> group)
		
		ExtTest.log(Status.SKIP, result.getName() + "Test Case SKIPPED");
		ExtTest.log(Status.INFO, result.getThrowable().getMessage()); 
	}
	
	public void onFinish(ITestContext context) 
	{
		
		ExtentRep.flush();// Once all Test method is finished finally update in the report.
		
		String pathofExtenreport = System.getProperty("user.dir")+"\\reports\\"+Repname;  // Once my test execution is done / my report is generated , without manual intervention we can automatically open the report.
		File ExtentRep = new File(pathofExtenreport);
		
		try 
		{
			Desktop.getDesktop().browse(ExtentRep.toURI()); // To open this report on the browser automatically.
		}
		catch(Exception e)
		{
			e.printStackTrace(); // If extent report is not available 
		}
		
		
		
	/*	
		try { 
		    URL url = new URL("file:///" + System.getProperty("user.dir") + "\\reports\\" + Repname); // Convert the url format
		    
		    // Create the email message (Automatically send the email once report generated)
		    ImageHtmlEmail email = new ImageHtmlEmail();
		    email.setDataSourceResolver(new DataSourceUrlResolver(url));
		    
		    email.setHostName("smtp.googlemail.com"); // This will work only for gmail this hostname , port (In company we have to use company email & port)
		    email.setSmtpPort(465);
		    
		    email.setAuthenticator(new DefaultAuthenticator("gundewilson.111@gmail.com", "password"));
		    email.setSSLOnConnect(true);
		    email.setFrom("gundewilson.111@gmail.com"); // Sender
		    email.setSubject("Test Results");
		    email.setMsg("Please Find Attached Report....");
		    email.addTo("pavan.kumar.busya@gmail.com"); // Receiver
		    email.attach(url, "extent report", "please check report....");
		    email.send(); // send the email
		} 
		catch(Exception e) { 
		    e.printStackTrace(); 
		}
					
			*/
		
	
	}
	
	
}
