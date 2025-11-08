package testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import javax.annotation.processing.SupportedOptions;

import org.apache.logging.log4j.LogManager; // Log4j
import org.apache.logging.log4j.Logger; // Log4j
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.beust.jcommander.Parameter;

public class Baseforallcommonuse_class {
	
	
//Base class is the parent class of all the test cases classes.(Reusability)
	
public static WebDriver driver;
public Logger logger; // Log4j
public Properties p;
	
	@BeforeClass (groups = {"Sanity", "Regression", "Master" })
	@Parameters({"os","browser"})
	public void setup(@Optional("windows")String os,@Optional("chrome")String br) throws Exception
	{
		
		//Loading config.properties file
		FileReader file = new FileReader("./src//test//resources//config.properties");
		p = new Properties();
		p.load(file);
		
		logger = LogManager.getLogger(this.getClass()); // Log4j
		
		switch (br.toLowerCase()) 
		{
		case "chrome": driver = new ChromeDriver(); break;
		case "edge" : driver = new EdgeDriver(); break;
		case "firefox" : driver = new FirefoxDriver(); break;
		default: System.out.println("Invalid browser"); return;
			
		}
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.get(p.getProperty("appurl"));
		driver.manage().window().maximize();
		
		
	}
	
	
	public String randomstring() 
	{
		
	String	Generatedstring = RandomStringUtils.randomAlphabetic(5);//It will generate the random string and return it & count 5 means it will generate 5 character string randomly(A-Z) every time.
	return Generatedstring;
		
	}
	
	public String randomnumber() 
	{
		
	String	Generatednum = RandomStringUtils.randomNumeric(10);//It will generate the random number string and return it & count 10 means it will generate 10 numbers string randomly(1-100) every time.
	return Generatednum;
		
	}
	
	public String randomalphanumber() 
	{
		
	String Generatedstring = RandomStringUtils.randomAlphabetic(5);
	String	Generatednum = RandomStringUtils.randomNumeric(5);     //It will generate the combination of random string + number and return it & it will generate string xyz + 5 numbers randomly(A-Z+1-100) every time.
	return Generatedstring+Generatednum;
		
	}
	
	@AfterClass (groups = {"Sanity", "Regression", "Master" ,})
	public void teardown()
	{
		driver.quit();
		
	}


	public String captureScreen(String tname) throws Exception 
	
	{
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());// time stamp
		
		TakesScreenshot takescreenshot = (TakesScreenshot) driver;
		File sourcefile = takescreenshot.getScreenshotAs(OutputType.FILE);
		Thread.sleep(3000);
		String targetfilepath = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png"; // Actual name will be generated along with time stamp 
		File targetfile = new File(targetfilepath);
		
		sourcefile.renameTo(targetfile);
		
		return targetfilepath; // Where exactly screenshot is located / path of the screenshot
	}
	
	

}
