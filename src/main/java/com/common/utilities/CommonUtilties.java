package com.common.utilities;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonUtilties {

//	public static boolean checkAlert(WebDriver driver) {
//	    try {
//	        WebDriverWait wait = new WebDriverWait(driver, 2);
//	        wait.until(ExpectedConditions.alertIsPresent());
//	        Alert alert = driver.switchTo().alert();
//	        alert.accept();
//	        return true;
//	    } catch (NoAlertPresentException e) {
//	        //exception handling
//	    		return false;
//	    }
//	}
	
	public static void waitForElementClickable(WebDriver driver, WebElement element)
	{
		WebDriverWait cWait= new WebDriverWait(driver, 10);
		
		cWait.until(ExpectedConditions.visibilityOf(element));
		
		
	}
	
	public static boolean isAlertPresent(WebDriver driver){ 
	    try{ 
	        Alert a = new WebDriverWait(driver, 10).until(ExpectedConditions.alertIsPresent());
	        if(a!=null){
	            System.out.println("Alert is present");
	            driver.switchTo().alert().accept();
	            return true;
	        }
	        
	        else{
	            throw new Throwable();
	        }
	    } 
	    catch (Throwable e) {
	        System.err.println("Alert isn't present!!");
	        return false; 
	    } 

	} 
}
