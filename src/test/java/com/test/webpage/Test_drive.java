package com.test.webpage;

import java.io.IOException;
import java.util.Map;

import org.testng.annotations.Test;

import com.common.utilities.BaseClass;
import com.main.webpage.RCTC_Webpage;

public class Test_drive extends TestBase{
	
	@Test(dataProvider= "userFormData", priority=1)
	public void navigate_RCTC(Map<String, String> map) throws IOException, InterruptedException
	{
		
//		driver= initializeDriver();
//		driver.manage().window().maximize();
		
//		String user= map.get("UserName");
//		String pass= map.get("Password");
		
		System.out.println("hola"+" "+map.size());
//		
//		System.out.println("hi"+" "+map.entrySet());

		for(int i=1;i<map.size();i++)
		{
			
			String user= map.get("UserName");
			String pass= map.get("Password");
			System.out.println("hi again"+user+pass);
			Test_WebPage hPage= new Test_WebPage(driver,map);
			
			hPage.userSign(user, pass);
			
		}
	
	}
	
	@Test(dataProvider="mergedList", priority=2)
	public void journeyPlanner(Map<String, String>map) throws InterruptedException
	{
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
		
		Test_WebPage jPage= new Test_WebPage(driver,map);
		
		// jPagejourneyPlan(from, to, date,quota,trainNum,classDetail,fetch);
		
		jPage.journeyPlan(map);
		
		//a[contains(text(),'12510')]/parent::td//following-sibling::td
		//a[contains(text(),'12510')]/parent::td//following-sibling::td[15]//a[contains(text(),'2A')]
	}
	
	
}
