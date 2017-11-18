package com.excelDataPackage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.excelDataPackage.*;

public class ExcelToDataProvider {


	public static FileInputStream xFile=null;
    static ExcelApiTest eat = null;
    public static XSSFRow headerRow= null;
    public static XSSFWorkbook xWorkBook=null;
    public static XSSFSheet xSheet=null;
    public static XSSFRow row= null;
    
     
     

    public Object[][] testData(String xlFilePath, String sheetName) throws Exception
    {
        Object[][] excelData = null;
        eat = new ExcelApiTest(xlFilePath);
        int rows = eat.getRowCount(sheetName);
        //rows=getRowCount(sheetName);
        int columns = eat.getColumnCount(sheetName);
        
        
                 
        excelData = new Object[rows-1][columns];
         
        for(int i=1; i<rows; i++)
        {
            for(int j=0; j<columns; j++)
            {
            		
                excelData[i-1][j] = eat.getCellData(sheetName, j, i);
                
            }
             
        }
        
        return excelData;
    }
    
    
    public static Object[][] testData1(String xFilePath, String sheetName) throws Exception
    {
    		xFile= new FileInputStream(xFilePath);
   		xWorkBook= new XSSFWorkbook(xFile);
    	
    		eat= new ExcelApiTest(xFilePath);
    		xSheet= xWorkBook.getSheet(sheetName);
    	
    	
    		Object[][] excelData1= null;
    		
    		//eat= new ExcelApiTest(xFilePath);
    		int rows= eat.getRowCount(sheetName);
    		int cols= eat.getColumnCount(sheetName);
    		
    		System.out.println(rows + " " + cols);
    		excelData1 = new Object[rows-1][1];
    		Map<String, String> map =null;
    		
    		headerRow= xSheet.getRow(0);
    		
    		for(int i=1;i<rows;i++)
    		{
    			row= xSheet.getRow(i);
    			map= new HashMap<String, String>();
    			
    			for(int j=0;j<cols;j++) 
    			{
    				
    				String headerValue= eat.getCellData(headerRow.getCell(j));
    				String rowValue= eat.getCellData(row.getCell(j));
    				map.put(headerValue, rowValue);
    				
    			}
    			System.out.println(map);
    			excelData1[i-1][0]=map;
    		}
    	
    	return excelData1;
    }

}
