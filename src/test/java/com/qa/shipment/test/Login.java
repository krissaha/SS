package com.qa.shipment.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Login 
{
	public static WebDriver driver;
	
	@BeforeMethod
	public void logincredentials() throws InterruptedException
	{	
		//Launching chrome driver
		System.setProperty("webdriver.chrome.driver","E:\\Selenium\\chromedriver.exe");
	    driver=new ChromeDriver();
	    //Launching url
		driver.get("https://appdev.nvisionglobal.com/Account/Login");
		driver.manage().window().maximize();
		// Entering email and password
		driver.findElement(By.xpath("//input[@id='Email']")).sendKeys("saishreedas@gmail.com");
		driver.findElement(By.xpath("//input[@id='Password']")).sendKeys("Liza@1993");
		// Enter Captcha
		String hiddenCaptchaVal = "";
		hiddenCaptchaVal = driver.findElement(By.id("HiddenCaptcha")).getAttribute("value");
	    driver.findElement(By.id("Captcha")).sendKeys(hiddenCaptchaVal);
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("//input[@id='btnLogin']")).click();
	  }
	
	
	@Test(enabled = false)
	public void search_shipment_global() throws InterruptedException
	{
		// Validating user are in Company Selection page
		WebElement actual = driver.findElement(By.xpath("//a[@class='logoarea']"));
		Assert.assertTrue(actual.isDisplayed());
		//Select Customer Tricorbraun
		Select oS = new Select(driver.findElement(By.id("SelectedCompanyId")));
		oS.selectByValue("5791");
		// Click on Submit button 		
		driver.findElement(By.className("newsc")).click();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		// validation for Dashboard page
		String s = "Dashboard";
		Assert.assertEquals(s, driver.getTitle());
		driver.findElement(By.xpath("//span[contains(text(),'Shipment')]")).click();
		driver.findElement(By.xpath("//div[@class='dropdown slick-slide slick-active' and @data-slick-index='0']")).click();
		driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
		driver.findElement(By.xpath("//input[@data-id='1140687']")).click();
		driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
		driver.findElement(By.xpath("//div[@class='dropdown slick-slide slick-active' and @data-slick-index='2']")).click();
	}
	
	@Test(enabled = false)
	public void search_shipment_number() throws InterruptedException
	{
		// Validating user are in Company Selection page
		WebElement actual = driver.findElement(By.xpath("//a[@class='logoarea']"));
		Assert.assertTrue(actual.isDisplayed());
		//Select Customer AAA Sign
		Select oS = new Select(driver.findElement(By.id("SelectedCompanyId")));
		oS.selectByValue("2134");
		// Click on Submit button 		
		driver.findElement(By.className("newsc")).click();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		// validation for Dashboard page
		String s = "Dashboard";
		Assert.assertEquals(s,driver.getTitle());
		//Click on shipment module from the left panel 
		driver.findElement(By.xpath("//span[contains(text(),'Shipment')]")).click();
		Thread.sleep(5000);
		// Delete date fields 
		driver.findElement(By.xpath("//input[@id='CreatedDateFrom']")).clear();
		driver.findElement(By.xpath("//input[@id='CreatedDateTo']")).clear();
		// Enter Text in Shipment number
		driver.findElement(By.xpath("//textarea[@id='ShipmentNo']")).sendKeys("11500183");
		//click on search
		driver.findElement(By.xpath("//div[@class='dropdown slick-slide slick-active' and @data-slick-index='0']")).click();
		Thread.sleep(2000);
		// Select the check box 
		driver.findElement(By.xpath("//input[@data-id='1140548']")).click();
		// click on edit button
		driver.findElement(By.xpath("//div[@class='dropdown slick-slide slick-active' and @data-slick-index='2']")).click();
		//Thread.sleep(10000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,-1000)");
		Thread.sleep(10000);
	}
	
	
	@Test(enabled=true)
	public void create_shipment() throws InterruptedException, IOException
	{
		// Validation Company Selection page
		WebElement actual = driver.findElement(By.xpath("//a[@class='logoarea']"));
		Assert.assertTrue(actual.isDisplayed());
		// Select Customer Tricorbraun
		Select oS = new Select(driver.findElement(By.id("SelectedCompanyId")));
		oS.selectByValue("5791");
		driver.findElement(By.className("newsc")).click();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		// Validation for Dashboard page
		String s = "Dashboard";
		Assert.assertEquals(s, driver.getTitle());
		driver.findElement(By.xpath("//span[contains(text(),'Shipment')]")).click();
		
		// Shipment Creation process.		
		driver.findElement(By.xpath("//div[@class='dropdown slick-slide slick-active' and @data-slick-index='1']")).click();
		Thread.sleep(2000);
		driver.manage().timeouts().pageLoadTimeout(5,TimeUnit.SECONDS);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,-2000)");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[@class='show-sidebar']")).click();
		driver.findElement(By.xpath("//span[contains(text(), '- Select Branch -')]")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//ul[@id='BranchKey_listbox']//child::li[3]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[contains(text(), '- Select -')]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//ul[@id='ShipmentType_listbox']//child::li[2]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[contains(text(), '- Select - ')]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//ul[@id='RatingPreferences_listbox']//child::li[2]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@class='col-xs-7 col-md-7 padleftres']//span[contains(text(), '- Select -')]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//ul[@id='ShipmentTerms_listbox']//child::li[2]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//a[contains(text(),'Add New Orgin/Destination')]")).click();
		Thread.sleep(2000);
		// Add Origin details
		driver.findElement(By.xpath("//span[contains(text(),'Both')]")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//ul[@id='ddlStopType_listbox']//child::li[2]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[@id='lookupLocationID']")).click();
		Thread.sleep(5000);
		// Select details from the child window 				
		driver.findElement(By.xpath("//div[@id='modalDialog']//span[contains(text(), '- Select -')]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//ul[@id='LocationCountry_listbox']//child::li[2]")).click();
		Thread.sleep(2000);
	    driver.findElement(By.xpath("//div[@id='modalDialog']//button[@id='btnLocationSearch']")).click();
	    Thread.sleep(10000);
	    driver.findElement(By.xpath("//*[@id='Location']/div[3]/table/tbody/tr[1]/td[1]")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("//div[@class='ui-dialog-buttonset']//button[@id='Select']")).click();
	    Thread.sleep(5000);
	    // Calender input code!!
	    driver.findElement(By.xpath("//*[@id='frmInnerGrid']/div/div/div[1]/div[1]/div/div[2]/span/span/span/span[1]")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("//div[@class='k-animation-container']//a[@class='k-link k-nav-today']")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("//a[@id='btnEdit' and @class='k-button k-button-icontext k-primary k-grid-update btnEdit']")).click();
	    Thread.sleep(7000);
	    
	    // Add Destination details
	    driver.findElement(By.xpath("//a[contains(text(),'Add New Orgin/Destination')]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[contains(text(),'Both')]")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//ul[@id='ddlStopType_listbox']//child::li[3]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[@id='lookupLocationID']")).click();
		Thread.sleep(5000);
		// Select details from the child window 				
		driver.findElement(By.xpath("//div[@id='modalDialog']//span[contains(text(), '- Select -')]")).click();
		Thread.sleep(10000);
		driver.findElement(By.xpath("//ul[@id='LocationCountry_listbox']//child::li[3]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@id='modalDialog']//button[@id='btnLocationSearch']")).click();
		Thread.sleep(10000);
		driver.findElement(By.xpath("//*[@id='Location']/div[3]/table/tbody/tr[3]/td[1]/input")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("//div[@class='ui-dialog-buttonset']//button[@id='Select']")).click();
	    Thread.sleep(5000);
	 // Calender input code!!
	    driver.findElement(By.xpath("//*[@id='frmInnerGrid']/div/div/div[1]/div[1]/div/div[2]/span/span/span/span[1]")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("//div[@class='k-animation-container']//a[@class='k-link k-nav-today']")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("//a[@id='btnEdit' and @class='k-button k-button-icontext k-primary k-grid-update btnEdit']")).click();
	    Thread.sleep(7000);		
	     
	     
	     
	}
	
	@AfterMethod()
	public void tearDown()
	{
		driver.close();
	}
}