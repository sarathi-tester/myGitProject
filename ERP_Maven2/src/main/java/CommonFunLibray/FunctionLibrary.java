package CommonFunLibray;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Utilities.ExcelFileUtil;
import Utilities.PropertyFileUtil;

public class FunctionLibrary

{
	// accessing browser value from property file
	public static WebDriver startBroswer(WebDriver driver) throws Throwable
	{
		if(PropertyFileUtil.getValueForKey("Browser").equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "./CommonJars/chromedriver.exe");
			driver=new ChromeDriver();
		}
		else if(PropertyFileUtil.getValueForKey("Browser").equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", "./CommonJars/geckodriver.exe");
		}
		else if(PropertyFileUtil.getValueForKey("Browser").equalsIgnoreCase("IE"))
		{
			
		}
		return driver;
	}
// Accessing url from property file
	public static void openApplication(WebDriver driver) throws Throwable
	{
		driver.get(PropertyFileUtil.getValueForKey("URL"));
	
		
		  driver.manage().window().maximize();
	}
	
/* TesterName: Sarathi                                    
 * Module Name: Send keys for typeAction
 * Creation date:26/06/2019
 */
	public static void typeAction(WebDriver driver,String locatortype,String locatorvalue,String testdata)throws Throwable
	{
		if(locatortype.equalsIgnoreCase("id"))
		{
			driver.findElement(By.id(locatorvalue)).clear();
			driver.findElement(By.id(locatorvalue)).sendKeys(testdata);
		}
		else if(locatortype.equalsIgnoreCase("name"))
		{
			driver.findElement(By.name(locatorvalue)).clear();
			driver.findElement(By.name(locatorvalue)).sendKeys(testdata);
		}
		else if(locatortype.equalsIgnoreCase("xpath"))
		{
			driver.findElement(By.xpath(locatorvalue)).clear();
			driver.findElement(By.xpath(locatorvalue)).sendKeys(testdata);
		}
	}
	
/* TesterName: Sarathi
 * Module Name: closeBrowser
 * Creation date:26/06/2019
 */
	
	public static void closeBrowser (WebDriver driver)
	{
		driver.close();
	}
/* TesterName: Sarathi
 * Module Name: clickAction
 * Creation date:26/06/2019
*/
	public static void clickAction(WebDriver driver,String locatortype,String locatorvalue)
	{
		if(locatortype.equalsIgnoreCase("name"))
		{
			driver.findElement(By.name(locatorvalue)).click();
		}
		else if(locatortype.equalsIgnoreCase("xpath"))
		{ 
			//driver.findElement(By.xpath(locatorvalue)).click();
			driver.findElement(By.xpath(locatorvalue)).click(); 
		}
		else if(locatortype.equalsIgnoreCase("id"))
		{
			driver.findElement(By.id(locatorvalue)).sendKeys(Keys.ENTER);
		}
	}
/* TesterName: Sarathi
 * Module Name: titleValidation
 * Creation date:26/06/2019
*/
public static void titleValidation(WebDriver driver, String exp_title)
{
	String act_title=driver.getTitle();
	Assert.assertEquals(act_title, exp_title);
}
/* TesterName: Sarathi
 * Module Name: waitForElement
 * Creation date:26/06/2019
*/
public static void waitForElement(WebDriver driver,String locatortype,String locatorvalue,String timetowait)
{
	WebDriverWait wait=new WebDriverWait(driver, Integer.parseInt(timetowait));
	if(locatortype.equalsIgnoreCase("id"))
	{
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorvalue)));
	}
	else if(locatortype.equalsIgnoreCase("name"))
	{
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locatorvalue)));
	}
	else if(locatortype.equalsIgnoreCase("xpath"))
	{
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorvalue)));
	}
	
}
// to capture date format
public static String generateDate()
{
	DateFormat sdf=new SimpleDateFormat("YYYY_MM_dd_ss");
	Date date=new Date();
	return sdf.format(date);
}
// to capture supplier number into notepad
public static void captureData(WebDriver driver, String locatortype,String locatorvalue) throws Throwable
{
	String data="";
	if(locatortype.equalsIgnoreCase("id"))
	{
		data=driver.findElement(By.id(locatorvalue)).getAttribute("value");
	}
	else if(locatortype.equalsIgnoreCase("xpath"))
	{
		data=driver.findElement(By.xpath(locatorvalue)).getAttribute("value");
	}
	FileWriter fw=new FileWriter("D:\\OjtEvening\\Stock_Accounting\\CaptureData\\snumber.txt");
	BufferedWriter bw=new BufferedWriter(fw);
	bw.write(data);
	bw.flush();
	bw.close();
		
}
//Table validation for Supplier list table
public static void tableValidation(WebDriver driver,String column) throws Throwable
{
	FileReader fr=new FileReader("D:\\OjtEvening\\Stock_Accounting\\CaptureData\\snumber.txt");
	BufferedReader br=new BufferedReader(fr);
//	String exp_data=br.readLine();
String exp_data=br.readLine();
	//System.out.println(exp_data);
	//int column1=Integer.parseInt(column);
	if(driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("search.box"))).isDisplayed())
	{
		driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("search.box"))).clear();
		driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("search.box"))).sendKeys(exp_data);
		driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("search.btn"))).click();
	}
	else 
	{
		driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("search.panel"))).click();
		driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("search.box"))).clear();
		driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("search.box"))).sendKeys(exp_data);
		driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("search.btn"))).click();	
	}
	// Storing supplier list table
	WebElement table=driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("webtable.path")));
	// count no.of rows in table
	List<WebElement>rows=table.findElements(By.tagName("tr"));
		String act_data=driver.findElement(By.xpath("//table[@id='tbl_a_supplierslist']/tbody/tr[1]/td[6]/div/span/span")).getText();
		System.out.println(act_data);
		System.out.println(exp_data);
		Assert.assertEquals(act_data, exp_data);
	
	
}
//Stock categories
public static void stockCategories(WebDriver driver) throws Throwable
{
	WebElement stockitem=driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[2]/div[1]/div[1]/ul[1]/li[2]/a[1]"));
	WebElement stockcategorie=driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[2]/div[1]/div[1]/ul[1]/li[2]/ul[1]/li[1]/a[1]"));
	Actions ac=new Actions(driver);
	ac.moveToElement(stockitem).build().perform();
	//Thread.sleep(5000);
	ac.moveToElement(stockcategorie).click().build().perform();
}

//Stock categories table validation
public static void tableValidation1(WebDriver driver,String exp_data ) throws Throwable
{
	
	if(driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("search.box1"))).isDisplayed())
	{
		driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("search.box1"))).clear();
		driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("search.box1"))).sendKeys(exp_data);
		driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("search.btn1"))).click();
	}
	else 
	{
		driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("search.panel1"))).click();
		driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("search.box1"))).clear();
		driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("search.box1"))).sendKeys(exp_data);
		driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("search.btn1"))).click();	
	}
	// Storing supplier list table
	WebElement table=driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("webtable.path1")));
	// count no.of rows in table
	List<WebElement>rows=table.findElements(By.tagName("tr"));
	
		String act_data=driver.findElement(By.xpath("//span[contains(text(),'2514w')]")).getText();
		System.out.println(act_data);
		System.out.println(exp_data);
		Assert.assertEquals(act_data, exp_data);

	
}




}
