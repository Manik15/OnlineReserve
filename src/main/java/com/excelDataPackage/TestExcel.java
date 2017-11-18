package com.excelDataPackage;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestExcel {
	String xlFilePath = "IRCTC.xlsx";
    String sheetName = "AddPassengerList";
    ExcelApiTest eat = null;
     
    @Test(dataProvider = "userData")
    public void fillUserForm(String Name, String Age, String Gender, String mobile)
    {
       System.out.println("UserName: "+ Name);
       System.out.println("PassWord: "+ Age);
       System.out.println("DateCreated: "+ Gender);
       System.out.println("NoOfAttempts: "+ mobile);
    }
     
     
    @DataProvider(name="userData")
    public Object[][] userFormData() throws Exception
    {
    		System.out.println(xlFilePath);
    		
        Object[][] data = testData(xlFilePath, sheetName);
        return data;
    }
     
    public Object[][] testData(String xlFilePath, String sheetName) throws Exception
    {
    		System.out.println("hello");
        Object[][] excelData = null;
        eat = new ExcelApiTest(xlFilePath);
        int rows = eat.getRowCount(sheetName);
        int columns = eat.getColumnCount(sheetName);
        
        System.out.println(columns);
                 
        excelData = new Object[rows-1][columns];
         
        for(int i=1; i<rows; i++)
        {
            for(int j=0; j<columns; j++)
            {
                excelData[i-1][j] = eat.getCellData(sheetName, j, i);
            }
             
        }
        System.out.println(excelData);
        return excelData;
    }

}
