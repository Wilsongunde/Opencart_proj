package utilities;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviders {
	
	//DataProvider 1
	
	@DataProvider(name="LoginData")
	public Object[][] getData() throws Exception
	{
		String path=".\\testData\\Opencart_logindata.xlsx"; //taking xl file from testData
		
        Excelutils xlutil=new Excelutils(path); //creating an object for XLUtility
        
        int totalrows=xlutil.getrowcount("Sheet1");
        int totalcols=xlutil.getcellcount("Sheet1",1); // Row and column
        
        Object logindata[][]=new Object[totalrows][totalcols]; //created for two dimension array which can store xl data
        
        for(int i=1;i<=totalrows;i++)//i=1  //read the data from xl storing in two dimensional array
        {
            for(int j=0;j<totalcols;j++)//j=0  //i is rows j is col
            {
                logindata[i-1][j]=xlutil.getcelldata("Sheet1",i, j); //1,0    //i-1 means array index start from zero so [1-1][0] -> [0][0]
            }
        }
        return logindata; //returning two dimension array
    }
	
	//DataProvider2 (When we need another more data provider method for other test cases, we can keep adding more data provider methods in the same class)
	
	//DataProvider3
}


