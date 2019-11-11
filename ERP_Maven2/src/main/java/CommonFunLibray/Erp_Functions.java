package CommonFunLibray;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class Erp_Functions 
{
	// Login method
	public void adminLogin(WebDriver driver,String username,String password)
	{
		driver.findElement(By.name("username")).clear();
		driver.findElement(By.name("username")).sendKeys(username);
		driver.findElement(By.name("password")).clear();
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.name("btnsubmit")).click();
		if(driver.findElement(By.partialLinkText("Logout")).isDisplayed())
		{
			System.out.println("Login Success");
		}
		else
		{
			System.out.println("Login Fail");
		}
		
	}
	
	//Supplier Creation method
	public String supplier(WebDriver driver, String sname, 
							String Address,String city,String country,
							String cperson,String pno,String email,String mobno,String notes) throws Throwable
	{
		driver.findElement(By.partialLinkText("Suppliers")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[3]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/a[1]/span[1]")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//input[@id='x_Supplier_Name']")).sendKeys(sname);
		driver.findElement(By.xpath("//textarea[@id='x_Address']")).sendKeys(Address);
		driver.findElement(By.xpath("//input[@id='x_City']")).sendKeys(city);
		driver.findElement(By.xpath("//input[@id='x_Country']")).sendKeys(country);
		driver.findElement(By.xpath("//input[@id='x_Contact_Person']")).sendKeys(cperson);
		driver.findElement(By.xpath("//input[@id='x_Phone_Number']")).sendKeys(pno);
		driver.findElement(By.xpath("//input[@id='x__Email']")).sendKeys(email);
		driver.findElement(By.xpath("//input[@id='x_Mobile_Number']")).sendKeys(mobno);
		driver.findElement(By.xpath("//textarea[@id='x_Notes']")).sendKeys(notes);
		Thread.sleep(5000);
		driver.findElement(By.xpath("//button[@id='btnAction']")).sendKeys(Keys.ENTER);
		Thread.sleep(5000);
		driver.findElement(By.xpath("//button[contains(text(),'OK!')]")).click();
		String status="";
		try
		{
			if(driver.getCurrentUrl().contains("a_supplierslist"))
			{
				System.out.println("Supplier Creation Success");
			
			}
			else
			{
				System.out.println("Supplier Creation Fail");
			}
			status="pass";
			
		}
		catch(Exception e)
		{
			status="Fail";
		}
		return status;
	}

}
