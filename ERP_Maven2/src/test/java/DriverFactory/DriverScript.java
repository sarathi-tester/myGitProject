package DriverFactory;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import CommonFunLibray.FunctionLibrary;
import Utilities.ExcelFileUtil;

public class DriverScript 

{
	WebDriver driver;
	ExtentReports report;
	ExtentTest test;
	// creating Excel file object
	public void startTest() throws Throwable
	{
		ExcelFileUtil excel= new ExcelFileUtil();
	for(int i=1;i<=excel.rowCount("MaterTestCases");i++)
	{
		String modulestatus="";
		if(excel.getData("MaterTestCases", i, 2).equalsIgnoreCase("Y"))
		{
			//get module name column
			String TCModule=excel.getData("MaterTestCases", i, 1); 
			System.out.println(TCModule);
			// Generate html report
			report=new ExtentReports("./Reports/"+TCModule+FunctionLibrary.generateDate()+".html");
			int rowcount=excel.rowCount(TCModule);
			
			System.out.println(rowcount);
			test=report.startTest(TCModule);
			
			for(int j=1;j<=rowcount;j++)
			{
				String Description=excel.getData(TCModule, j, 0);
				String objecttype=excel.getData(TCModule, j, 1);
				String locatortype=excel.getData(TCModule, j, 2);
				String locatorvalue=excel.getData(TCModule, j, 3);
				String testdata=excel.getData(TCModule, j, 4);
			try
			{	
				if(objecttype.equalsIgnoreCase("startBroswer"))
				{
					driver=FunctionLibrary.startBroswer(driver);
					test.log(LogStatus.INFO, Description);
				}
				else if(objecttype.equalsIgnoreCase("openApplication"))
				{
					FunctionLibrary.openApplication(driver);
					test.log(LogStatus.INFO, Description);	
				}
				else if(objecttype.equalsIgnoreCase("waitForElement"))
				{
					FunctionLibrary.waitForElement(driver, locatortype, locatorvalue, testdata);
					test.log(LogStatus.INFO, Description);
				}
				else if(objecttype.equalsIgnoreCase("tableValidation"))
				{
					FunctionLibrary.tableValidation(driver, testdata);
					test.log(LogStatus.INFO, Description);
				}
				else if(objecttype.equalsIgnoreCase("typeAction"))
				{
					FunctionLibrary.typeAction(driver, locatortype, locatorvalue, testdata);
					test.log(LogStatus.INFO, Description);
				}
				else if(objecttype.equalsIgnoreCase("clickAction"))
				{
					FunctionLibrary.clickAction(driver, locatortype, locatorvalue);
					test.log(LogStatus.INFO, Description);
				}
				else if(objecttype.equalsIgnoreCase("closeBrowser"))
				{
					FunctionLibrary.closeBrowser(driver);
					test.log(LogStatus.INFO, Description);
				}
				else if(objecttype.equalsIgnoreCase("captureData"))
				{
					FunctionLibrary.captureData(driver, locatortype, locatorvalue);
					test.log(LogStatus.INFO, Description);
				}
				else if(objecttype.equalsIgnoreCase("tableValidation"))
				{
					FunctionLibrary.tableValidation(driver, testdata);
					test.log(LogStatus.INFO, Description);
				}
				else if(objecttype.equalsIgnoreCase("tableValidation1"))
				{
					FunctionLibrary.tableValidation1(driver, testdata);
					test.log(LogStatus.INFO, Description);
				}
				else if(objecttype.equalsIgnoreCase("stockCategories"))
				{
					FunctionLibrary.stockCategories(driver);
					test.log(LogStatus.INFO, Description);
				}
				
				excel.setData(TCModule, j, 5, "PASS");
				test.log(LogStatus.PASS, Description);
				File srcFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(srcFile, new File("./Screenshots/"+Description+FunctionLibrary.generateDate()+".png"));
						
				modulestatus="true";
				
			}
			catch(Exception e)
			{
				excel.setData(TCModule, j, 5, "FAIL");
				test.log(LogStatus.FAIL, Description);
				File srcFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(srcFile, new File("./Screenshots/"+Description+FunctionLibrary.generateDate()+".png"));
				modulestatus="false";
			}
			
			if(modulestatus.equalsIgnoreCase("true"))
			{
				excel.setData("MaterTestCases", i, 3, "PASS");
			}
			if(modulestatus.equalsIgnoreCase("false"))
			{
				excel.setData("MaterTestCases", i, 3, "FAIL");
			}

			}
			report.flush();
			report.endTest(test);
		}
		else
		{
			// Write as into status coloumn in mastertestcase sheet
			excel.setData("MaterTestCases", i, 3, "Blocked");
		}
	}
	}
	
}
