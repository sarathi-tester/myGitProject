package stepDefinitions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StepDefinitions
{
	WebDriver driver;
	@When("^I login into Sctock acounting URL on chrome browser and click go$")
	public void i_login_into_Sctock_acounting_URL_on_chrome_browser_and_click_go() throws Throwable 
	{
		System.setProperty("webdriver.chrome.driver", "D:\\OjtEvening\\ERP_Maven2\\CommonJars\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.get("http://webapp.qedge.com"); 
	}

	@When("^I click reset button on login page$")
	public void i_click_reset_button_on_login_page() throws Throwable 
	{
	   driver.findElement(By.xpath("//button[@id='btnreset']")).click();
	}

	@When("^I enter admin in UserNameField$")
	public void i_enter_admin_in_UserNameField() throws Throwable 
	{
	    driver.findElement(By.xpath("//input[@id='username']")).sendKeys("admin");
	}

	@When("^I enter master in PasswordField$")
	public void i_enter_master_in_PasswordField() throws Throwable {
		 driver.findElement(By.xpath("//input[@id='password']")).sendKeys("master");
	}

	@When("^I click on login button$")
	public void i_click_on_login_button() throws Throwable {
		driver.findElement(By.xpath("//button[@id='btnsubmit']")).click();
	}

	@When("^I wait for logout link$")
	public void i_wait_for_logout_link() throws Throwable 
	{
		WebDriverWait wait=new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//a[@id='logout']"))));
		
	}
	   

	@Then("^I validate the title of the homepage$")
	public void i_validate_the_title_of_the_homepage() throws Throwable 
	{
	    String act_title=driver.getTitle();
	    Assert.assertEquals(act_title, "Dashboard « Stock Accounting");
	}

	@Then("^I close the Chrome$")
	public void i_close_the_Chrome() throws Throwable 
	{
		driver.close();
	}
}
