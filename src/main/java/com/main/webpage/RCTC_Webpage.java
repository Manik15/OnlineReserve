package com.main.webpage;

import java.util.Map;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.common.utilities.CommonUtilties;
import com.gargoylesoftware.htmlunit.javascript.host.security.FederatedCredential;

public class RCTC_Webpage {

	public WebDriver driver=null;
	public Map<String, String> map= null;
	public RCTC_Webpage(WebDriver driver,Map<String, String>map)
	{
		
		this.driver= driver;
		this.map=map;
		PageFactory.initElements(driver, this);
		//driver.findElement(By.c)
	}
	
	
	//Login Page
	
	
	@FindBy (id="usernameId")
	static
	WebElement userName;
	
	@FindBy(className="loginPassword")
	static
	WebElement password;
	
	@FindBy (id="loginbutton")
	static
	WebElement login;
	
	//JOURNEYPLAN
	
	@FindBy (id="jpform:fromStation")
	static
	WebElement fromStation;
	
	@FindBy (id="jpform:toStation")
	static
	WebElement toStation;
	
	@FindBy (id="jpform:journeyDateInputDate")
	static
	WebElement date;
	
	@FindBy (id="jpform:jpsubmit")
	static
	WebElement submit;
	
	@FindBy (xpath="//input[@name='quota']")
	static
	WebElement quotaSel;
	
	@FindBy(id="altavlfrm:continue")
	static
	WebElement continuePrev;
	
	@FindBy(xpath="(//input[starts-with(@id, 'addPassengerForm:psdetail:0:p')])[1]")
	static
	WebElement nameCus;
	//addPassengerForm:psdetail:0:p700791733
	//addPassengerForm:psdetail:0:p15088528
	
	@FindBy(id="addPassengerForm:psdetail:0:psgnAge")
	static
	WebElement ageCus;
	
	@FindBy(id="addPassengerForm:psdetail:0:psgnGender")
	static
	WebElement genderCus;
	
	@FindBy(id="addPassengerForm:psdetail:0:berthChoice")
	static
	WebElement berthCus;
	
	@FindBy(id="addPassengerForm:mobileNo")
	static
	WebElement mobCus;
	
	@FindBy(id="validate")
	static
	WebElement next;
	
	@FindBy(xpath="//a[contains(text(),'Select Passenger From Your Master List')]")
	static
	WebElement selectMaster;
	
	@FindBy(xpath=" //a[contains(text(),'Select Passengers')]")
	static
	WebElement clickMaster;
	
	
	public static String ifList= null;
	
	
	
	
	public void userSign(Map<String, String> map) throws InterruptedException
	{
		String user= map.get("UserName");
		String pass= map.get("Password");
		
		
		//Thread.sleep(2000);
		String num=null;
		
		String from= map.get("From Station");
		String to = map.get("To Station");
		String date= map.get("Journey Date");
		String quota= map.get("Quota");
		String trainNum= map.get("Train No.");
		trainNum=trainNum.indexOf(".") < 0 ? trainNum : trainNum.replaceAll("0*$", "").replaceAll("\\.$", "");
		String fetch= map.get("Fetch from MasterList");
		String classDetail= map.get("Class");
		String name= map.get("Name");
		String age = map.get("Age");
		age=age.indexOf(".") < 0 ? age : age.replaceAll("0*$", "").replaceAll("\\.$", "");
		String gender= map.get("Gender");
		String berth= map.get("Berth");
		String mobile= map.get("Mobile");
		mobile=mobile.indexOf(".") < 0 ? mobile : mobile.replaceAll("0*$", "").replaceAll("\\.$", "");
		String masterName=map.get("MasterName");
		
		CommonUtilties.waitForElementClickable(driver,userName);
		userName.sendKeys(user);
		
		//driver.findElement(By.x)
		password.sendKeys(pass);
		Thread.sleep(10000);
		login.click();
		
		
		fromStation.sendKeys(from);
		toStation.sendKeys(to);
		RCTC_Webpage.date.sendKeys(date);
		submit.click();
		Thread.sleep(5000);
		
		System.out.println(quota);
		
		if(quota.equals("GENERAL"))
			num="1";
		else if(quota.equals("PHYSICALLY HANDICAP"))
			num="2";
		else
			num="3";
		
		System.out.println(num);
		//quota
		//(quotaSel)[num].
		driver.findElement(By.xpath("(//input[@name='quota'])["+num+"]")).click();
		
		Thread.sleep(5000);
		//train and class selection
		//a[contains(text(),'12510')]/parent::td//following-sibling::td[15]//a[contains(text(),'2A')]
		
		driver.findElement(By.xpath("//a[contains(text(),'"+trainNum+"')]/parent::td//following-sibling::td[15]//a[contains(text(),'"+classDetail+"')]")).click();;
		
		//select date of travel with availability
		//(//td/a[@id='12510-SL-GN-0'])[2]
		
		driver.findElement(By.xpath("(//td/a[@id='"+trainNum+"-SL-GN-0'])[2]")).click();
		
		//Handling Alerts
		
		Boolean alertResult=CommonUtilties.isAlertPresent(driver);
		System.out.println(alertResult);
		Thread.sleep(5000);
		
		
		if(alertResult.equals(true))
		{
		continuePrev.click();
		Thread.sleep(10000);
		}
		else
			System.out.println("No alert");
		if(fetch.equals("No"))
		{
			Thread.sleep(2000);
			nameCus.sendKeys(name);
			ageCus.sendKeys(age);
			
			//gender
			Select dropdown = new Select(driver.findElement(By.id("addPassengerForm:psdetail:0:psgnGender")));
			dropdown.selectByVisibleText(gender);

			//berth
			Select dropBerth = new Select(driver.findElement(By.id("addPassengerForm:psdetail:0:berthChoice")));
			//addPassengerForm:psdetail:0:berthChoice
			dropBerth.selectByVisibleText(berth);
			mobCus.clear();
			mobCus.sendKeys(mobile);
			
			Thread.sleep(10000);
			//Enter captcha
			
			//next.click();
			
		}
		else
		{
			selectMaster.click();
			
			
			
			//click the master customer
			
			//span[contains(text(),'Manikantan U V')]/parent::td//following-sibling::td[9]
			
			System.out.println(masterName);
			
			driver.findElement(By.xpath("//span[contains(text(),'"+masterName+"')]/parent::td//following-sibling::td[9]/input")).click();
			
			Thread.sleep(5000);
			clickMaster.click();
			
			mobCus.clear();
			mobCus.sendKeys(mobile);
			Thread.sleep(20000);
			
		}
		Actions actions= new Actions(driver);
		
		actions.moveToElement(next).doubleClick().build().perform();		
		
		
		
	}
	
	
//	public void userSign(String user,String pass) throws InterruptedException
//	{
//		
//		CommonUtilties.waitForElementClickable(driver,userName);
//		userName.sendKeys(user);
//		
//		//driver.findElement(By.x)
//		password.sendKeys(pass);
//		Thread.sleep(10000);
//		login.click();
//		Thread.sleep(2000);
//		
//	}
//	
//	
//
//	public  void journeyPlan(Map<String, String> mapPlan) throws InterruptedException {
//		// TODO Auto-generated method stub
//		
//		String num=null;
//		
//		String from= map.get("From Station");
//		String to = map.get("To Station");
//		String date= map.get("Journey Date");
//		String quota= map.get("Quota");
//		String trainNum= map.get("Train No.");
//		trainNum=trainNum.indexOf(".") < 0 ? trainNum : trainNum.replaceAll("0*$", "").replaceAll("\\.$", "");
//		String fetch= map.get("Fetch from MasterList");
//		String classDetail= map.get("Class");
//		String name= map.get("Name");
//		String age = map.get("Age");
//		age=age.indexOf(".") < 0 ? age : age.replaceAll("0*$", "").replaceAll("\\.$", "");
//		String gender= map.get("Gender");
//		String berth= map.get("Berth");
//		String mobile= map.get("Mobile");
//		mobile=mobile.indexOf(".") < 0 ? mobile : mobile.replaceAll("0*$", "").replaceAll("\\.$", "");
//		String masterName=map.get("MasterName");
//		
//		fromStation.sendKeys(from);
//		toStation.sendKeys(to);
//		RCTC_Webpage.date.sendKeys(date);
//		submit.click();
//		Thread.sleep(5000);
//		
//		System.out.println(quota);
//		
//		if(quota.equals("GENERAL"))
//			num="1";
//		else if(quota.equals("PHYSICALLY HANDICAP"))
//			num="2";
//		else
//			num="3";
//		
//		System.out.println(num);
//		//quota
//		//(quotaSel)[num].
//		driver.findElement(By.xpath("(//input[@name='quota'])["+num+"]")).click();
//		
//		Thread.sleep(5000);
//		//train and class selection
//		//a[contains(text(),'12510')]/parent::td//following-sibling::td[15]//a[contains(text(),'2A')]
//		
//		driver.findElement(By.xpath("//a[contains(text(),'"+trainNum+"')]/parent::td//following-sibling::td[15]//a[contains(text(),'"+classDetail+"')]")).click();;
//		
//		//select date of travel with availability
//		//(//td/a[@id='12510-SL-GN-0'])[2]
//		
//		driver.findElement(By.xpath("(//td/a[@id='"+trainNum+"-SL-GN-0'])[2]")).click();
//		
//		//Handling Alerts
//		
//		Boolean alertResult=CommonUtilties.isAlertPresent(driver);
//		System.out.println(alertResult);
//		Thread.sleep(5000);
//		
//		
//		if(alertResult.equals(true))
//		{
//		continuePrev.click();
//		Thread.sleep(10000);
//		}
//		else
//			System.out.println("No alert");
//		if(fetch.equals("No"))
//		{
//			Thread.sleep(2000);
//			nameCus.sendKeys(name);
//			ageCus.sendKeys(age);
//			
//			//gender
//			Select dropdown = new Select(driver.findElement(By.id("addPassengerForm:psdetail:0:psgnGender")));
//			dropdown.selectByVisibleText(gender);
//
//			//berth
//			Select dropBerth = new Select(driver.findElement(By.id("addPassengerForm:psdetail:0:berthChoice")));
//			//addPassengerForm:psdetail:0:berthChoice
//			dropBerth.selectByVisibleText(berth);
//			mobCus.clear();
//			mobCus.sendKeys(mobile);
//			
//			Thread.sleep(10000);
//			//Enter captcha
//			
//			//next.click();
//			
//		}
//		else
//		{
//			selectMaster.click();
//			
//			
//			
//			//click the master customer
//			
//			//span[contains(text(),'Manikantan U V')]/parent::td//following-sibling::td[9]
//			
//			System.out.println(masterName);
//			
//			driver.findElement(By.xpath("//span[contains(text(),'"+masterName+"')]/parent::td//following-sibling::td[9]/input")).click();
//			
//			Thread.sleep(5000);
//			clickMaster.click();
//			
//			mobCus.clear();
//			mobCus.sendKeys(mobile);
//			Thread.sleep(20000);
//			
//		}
//		Actions actions= new Actions(driver);
//		
//		actions.moveToElement(next).doubleClick().build().perform();		
//		
//		
//		
//	}
	
}
