package com.common.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import com.excelDataPackage.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class BaseClass extends ExcelToDataProvider{

	

	public static WebDriver driver=null;
	
	public static WebDriver initializeDriver() throws IOException
	{
		Properties prop= new Properties();
		
		FileInputStream fis= new FileInputStream("datairc.properties");
		
		prop.load(fis);
		String browserName= prop.getProperty("browser");
		String urlName= prop.getProperty("baseURL");
		
		System.out.println(browserName + urlName);
		
		if(browserName.equals("chrome"))
		{
			System.setProperty("WebDriver.chrome.driver", "chromedriver");
			driver= new ChromeDriver();
			driver.get(urlName);
		}
		
		else if(browserName.equals("FireFox"))
		{
			//Firefox code
		}
		
		else if(browserName.equals("IE"))
		{
			//IE code
		}
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}
	
	 	@DataProvider
	    public static Object[][] userFormData() throws Exception
	    {
	    		//System.out.println(xlFilePath);
	    		
	        Object[][] data = testData1(ConstantDetails.path, ConstantDetails.loginSheetName);
	        
	        return data;
	    }
	 	
//	 	@Test(dataProvider="mergedList")
//	 	public void usingMergeDP(HashMap<String, String> mapList)
//	 	{
//	 		
//	 		System.out.println(mapList.get("Mobile"));
//	 		
//	 		
//	 	}
	 	
	 	
	 	@DataProvider(name="journeyDetails")
	 	public static Object[][] journeyDetails() throws Exception
	 	{
	 		Object[][] journeyData= testData1(ConstantDetails.path, ConstantDetails.journeySheetName);
	 	
	 		return journeyData;
	 	}
	 	

	 	@DataProvider(name="masterDetails")
	 	public static Object[][] masterDetails() throws Exception
	 	{
	 		Object[][] masterData= testData1(ConstantDetails.path, ConstantDetails.masterSheetName);
	 	
	 		return masterData;
	 	}
	 	
	 	@DataProvider(name="passengerList")
	 	public static Object[][] passenderList() throws Exception
	 	{
	 		Object[][] passengerData= testData1(ConstantDetails.path, ConstantDetails.passengerSheetName);
	 	
	 		return passengerData;
	 	}
	 	
	 	@SuppressWarnings("unchecked")
		@DataProvider
	 	public static Object[][] mergedList() throws Exception
	 	{
	 		HashMap<String, String> mergedData= new HashMap<String, String>();
	 		//mergedData.putAll((HashMap<String, String>)userFormData()[0][0]);
	 		mergedData.putAll((HashMap<String, String>)journeyDetails()[0][0]);
	 		mergedData.putAll((HashMap<String, String>)passenderList()[0][0]);
	 		mergedData.putAll((HashMap<String, String>)masterDetails()[0][0]);
	 		Object [][] mergedInfo= new Object[1][];
	 		mergedInfo[0]= new Object[] {mergedData};
	 		
	 		return mergedInfo;
	 	}
	     
	 	
	
	
}
