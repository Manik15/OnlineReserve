package com.test.webpage;

import java.io.IOException;
import java.util.Map;

import org.junit.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.common.utilities.BaseClass;
import com.excelDataPackage.ExcelToDataProvider;
import com.main.webpage.RCTC_Webpage;

public class RCTC_testClass extends  BaseClass {

	
	
//	@BeforeClass
//	public void initBrowser() throws IOException
//	{
//		driver= initializeDriver();
//		driver.manage().window().maximize();
//	}

	
	@Test(dataProvider= "mergedList")
	public void navigate_RCTC(Map<String, String> map) throws IOException, InterruptedException
	{
		
		driver= initializeDriver();
		driver.manage().window().maximize();
		
		String user= map.get("UserName");
		String pass= map.get("Password");

		System.out.println(user +" "+ pass);
		
		RCTC_Webpage hPage= new RCTC_Webpage(driver,map);
		
		hPage.userSign(map);	
		
		String from= map.get("From Station");
		String to = map.get("To Station");
		String date= map.get("Journey Date");
		String quota= map.get("Quota");
		String trainNum= map.get("Train No.");
		String fetch= map.get("Fetch from MasterList");
		String classDetail= map.get("Class");
		
		//s = s.indexOf(".") < 0 ? s : s.replaceAll("0*$", "").replaceAll("\\.$", "");
		trainNum=trainNum.indexOf(".") < 0 ? trainNum : trainNum.replaceAll("0*$", "").replaceAll("\\.$", "");
		
		System.out.println(quota+trainNum+classDetail);
		
		RCTC_Webpage jPage= new RCTC_Webpage(driver,map);
		
		// jPagejourneyPlan(from, to, date,quota,trainNum,classDetail,fetch);
		
		//jPage.journeyPlan(map);
		
		//a[contains(text(),'12510')]/parent::td//following-sibling::td
		//a[contains(text(),'12510')]/parent::td//following-sibling::td[15]//a[contains(text(),'2A')]
	}
	
	
	
	
	
//	@Test(dataProvider= "userFormData", priority=1)
//	public void navigate_RCTC(Map<String, String> map) throws IOException, InterruptedException
//	{
//		
//		driver= initializeDriver();
//		driver.manage().window().maximize();
//		
//		String user= map.get("UserName");
//		String pass= map.get("Password");
//
//		System.out.println(user +" "+ pass);
//		
//		RCTC_Webpage hPage= new RCTC_Webpage(driver,map);
//		
//		hPage.userSign(user, pass);	
//		
//			
//	}
//	
//	@Test(dataProvider="mergedList", priority=2)
//	public void journeyPlanner(Map<String, String>map) throws InterruptedException
//	{
//		String from= map.get("From Station");
//		String to = map.get("To Station");
//		String date= map.get("Journey Date");
//		String quota= map.get("Quota");
//		String trainNum= map.get("Train No.");
//		String fetch= map.get("Fetch from MasterList");
//		String classDetail= map.get("Class");
//		
//		//s = s.indexOf(".") < 0 ? s : s.replaceAll("0*$", "").replaceAll("\\.$", "");
//		trainNum=trainNum.indexOf(".") < 0 ? trainNum : trainNum.replaceAll("0*$", "").replaceAll("\\.$", "");
//		
//		System.out.println(quota+trainNum+classDetail);
//		
//		RCTC_Webpage jPage= new RCTC_Webpage(driver,map);
//		
//		// jPagejourneyPlan(from, to, date,quota,trainNum,classDetail,fetch);
//		
//		jPage.journeyPlan(map);
//		
//		//a[contains(text(),'12510')]/parent::td//following-sibling::td
//		//a[contains(text(),'12510')]/parent::td//following-sibling::td[15]//a[contains(text(),'2A')]
//	}
	
//	@AfterClass
//	public void closeBrowser() {
//		driver.quit();
//	}
}
