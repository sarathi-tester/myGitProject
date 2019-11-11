package DriverFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import CommonFunLibray.Erp_Functions;
import Utilities.ExcelFileUtil;

public class DataDrivenFrameWork 
{
	WebDriver driver;
	Erp_Functions st=new Erp_Functions();
	ExtentReports report;
	ExtentTest test;
	@BeforeTest
	public void lunch()
	{
		report=new ExtentReports("./Reports/supplier.html");
		System.setProperty("webdriver.chrome.driver", "./CommonJars/chromedriver.exe");
		driver=new ChromeDriver();
		driver.get("http://webapp.qedge.com");
		driver.manage().window().maximize();
		st.adminLogin(driver, "admin", "master");
	}
	@Test
	public void verify_supplier() throws Throwable
	{
		ExcelFileUtil xl=new ExcelFileUtil();
		int rc=xl.rowCount("suppliers");
		System.out.println("no.of rows"+rc);
		for(int i=1;i<=rc;i++)
		{
			test=report.startTest("Verify Supplier Creation");// it will show the test name in the Reports html file
			String sname=xl.getData("suppliers", i, 0);
			String address=xl.getData("suppliers", i, 1);
			String city=xl.getData("suppliers", i, 2);
			String country=xl.getData("suppliers", i, 3);
			String cperson=xl.getData("suppliers", i, 4);
			String pno=xl.getData("suppliers", i, 5);
			String email=xl.getData("suppliers", i, 6);
			String mobno=xl.getData("suppliers", i, 7);
			String notes=xl.getData("suppliers", i, 8);
			String result=st.supplier(driver, sname, address, city, country, cperson, pno, email, mobno, notes);
			test.log(LogStatus.PASS, "Supplier creation success");
			xl.setData("suppliers", i, 9,result);
			report.flush();
			report.endTest(test);

		}
		
	}
	
	
	@AfterTest
	public void logout()
	{
		driver.close();
	}

}
