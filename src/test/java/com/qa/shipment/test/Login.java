package com.qa.shipment.test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Login 
{
	public static WebDriver driver;
	public static Properties prop;
	public static WebDriverWait wait;
	
	@BeforeMethod
	public void login() throws Exception
	{	
		// Initialization of properties file
		prop= new Properties();
		FileInputStream ip = new FileInputStream("E:/Selenium/Shipment/src/main/java/com/qa/shipment/config/Config.properties");
		prop.load(ip);
		System.setProperty("webdriver.chrome.driver","E:\\Selenium\\chromedriver.exe");  //Launching chrome driver
		driver=new ChromeDriver();
	    driver.get(prop.getProperty("Url")); //Launching url
	    driver.manage().deleteAllCookies();
	    driver.manage().window().maximize();
		driver.findElement(By.xpath("//input[@id='Email']")).sendKeys(prop.getProperty("Email"));  // Entering email and password
		driver.findElement(By.xpath("//input[@id='Password']")).sendKeys(prop.getProperty("Password"));
		String hiddenCaptchaVal = "";   // Enter Captcha
		hiddenCaptchaVal = driver.findElement(By.id("HiddenCaptcha")).getAttribute("value");
	    driver.findElement(By.id("Captcha")).sendKeys(hiddenCaptchaVal);
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("//input[@id='btnLogin']")).click();
	  }
	
	@Test(enabled = false)
	public void global_shipment_search() throws InterruptedException
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
		Thread.sleep(5000);
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
		Thread.sleep(5000);
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("window.scrollBy(0,-2000)");
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
		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		js1.executeScript("window.scrollBy(0,-2000)");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[@class='show-sidebar']")).click();
		Thread.sleep(3000);
		WebElement shipmentno = driver.findElement(By.id("ShipmentNumber"));
				
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
		Thread.sleep(4000);
		driver.findElement(By.xpath("//ul[@id='ddlStopType_listbox']//child::li[3]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[@id='lookupLocationID']")).click();
		Thread.sleep(4000);
		// Select details from the child window 				
		driver.findElement(By.xpath("//div[@id='modalDialog']//input[@id='LocId']")).sendKeys("DEN USAUR");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@id='modalDialog']//button[@id='btnLocationSearch']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@id='Location']/div[3]/table/tbody/tr[3]/td[1]/input")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@class='ui-dialog-buttonset']//button[@id='Select']")).click();
		Thread.sleep(5000);
		JavascriptExecutor js2 = (JavascriptExecutor) driver;
		js2.executeScript("window.scrollBy(0,200)");
		Thread.sleep(5000);
		// Calender input code!!
		driver.findElement(By.xpath("//input[@id='ShipperRequestedDeliveryDate_2_2']")).sendKeys("1/10/2019");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[@id='btnEdit' and @class='k-button k-button-icontext k-primary k-grid-update btnEdit']")).click();
		Thread.sleep(7000);	
		    
		// Add New Product Details
		driver.findElement(By.xpath("//div[@id='ShipmentProductGrid']//div[@class='k-header k-grid-toolbar k-grid-top']//a[contains(@class,'k-button k-button-icontext')]")).click();
		Thread.sleep(3000);  
		driver.findElement(By.xpath("//input[@name='txtProduct']")).sendKeys("ABCDEFGH");
		driver.findElement(By.xpath("//input[@name='txtProductDtlsID']")).sendKeys("ABCDEFGH");
		driver.findElement(By.xpath("//input[@id='Quantity']")).sendKeys("10");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//span[contains(text(),'- Select -  ')]")).click();
		Thread.sleep(3000);
		// Unit Of Measure
		driver.findElement(By.xpath("//ul[@id='UnitMeasurelookup_listbox']//li[@class='k-item'][contains(text(),'Bag')]")).click();
		driver.findElement(By.xpath("//input[@id='Weight']")).sendKeys("100");
		Thread.sleep(3000);
		//Weight Unit Of Measure
		driver.findElement(By.xpath("//span[contains(text(),'- Select -  ')]")).click();
		Thread.sleep(2000);
		driver.findElement((By.xpath("//ul[@id='WeigthUomlookup_listbox']//child::li[2]"))).click();
		//Billed Quantity
		driver.findElement(By.xpath("//input[@id='BillQty']")).sendKeys("10");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='Length']")).sendKeys("10");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='Width']")).sendKeys("10");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='Height']")).sendKeys("10");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[contains(text(),'- Select -  ')]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//ul[@id='DimUomlookup_listbox']//child::li[2]")).click();
		Thread.sleep(3000);
		driver.findElement(By.linkText("Update")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@class='dropdown slick-slide slick-active']//span[contains(text(),'Save')]")).click();
		Thread.sleep(3000);
		// Switching to Alert        
        Alert alert = driver.switchTo().alert();	
        alert.accept();
        Thread.sleep(10000);
        //Success! Shipment Successfully Updated.
        boolean b1 = driver.findElement(By.xpath("//div[@id='result_info']")).isDisplayed();
        if(b1 == true)
        {
        	WebElement el = driver.findElement(By.xpath("//div[@id='result_info']"));
        	System.out.println(el.getText());
        	try{
                List<WebElement> elements = driver.findElements(By.xpath("//div[@id='divShipmentNumber']//input[@id='ShipmentNumber']"));
                for(WebElement ele : elements){
                    System.out.println("------------------------------------------------------------");
                    System.out.println("Shipment number = " + ele.getAttribute("value"));
                    System.out.println("------------------------------------------------------------");
                }}finally{}
        }
        else{
        	System.out.println("Shipment number not displayed");
        }
}
	
	@Test(enabled=false)
	public void Edit_shipment_number() throws InterruptedException
	{
		// Validating user are in Company Selection page
		WebElement actual = driver.findElement(By.xpath("//a[@class='logoarea']"));
		Assert.assertTrue(actual.isDisplayed());
		// Select Customer Tricorbraun
		Select oS = new Select(driver.findElement(By.id("SelectedCompanyId")));
		oS.selectByValue("5791");
		// Click on Submit button
		driver.findElement(By.className("newsc")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		// validation for Dashboard page
		String s = "Dashboard";
		Assert.assertEquals(s, driver.getTitle());
		// Click on shipment module from the left panel
		driver.findElement(By.xpath("//span[contains(text(),'Shipment')]")).click();
		Thread.sleep(5000);
		// Delete date fields
		driver.findElement(By.xpath("//input[@id='CreatedDateFrom']")).clear();
		driver.findElement(By.xpath("//input[@id='CreatedDateTo']")).clear();
		// Enter Text in Shipment number
		driver.findElement(By.xpath("//textarea[@id='ShipmentNo']")).sendKeys("11305481");
		// click on search
		driver.findElement(By.xpath("//div[@class='dropdown slick-slide slick-active' and @data-slick-index='0']")).click();
		Thread.sleep(5000);
		// Select the check box
		driver.findElement(By.xpath("//td[@role='gridcell']//input[@type='checkbox']")).click();
		// click on edit button
		driver.findElement(By.xpath("//div[@class='dropdown slick-slide slick-active' and @data-slick-index='2']")).click();
		Thread.sleep(5000);
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("window.scrollBy(0,-700)");
		Thread.sleep(5000);
		driver.findElement(By.xpath("//div[@class='show-sidebar']")).click();
		Thread.sleep(3000);
		driver.findElement((By.xpath("//a[@class='k-button k-button-icontext k-grid-edit btnProEdit']"))).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@id='Length']")).sendKeys("20");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='Width']")).sendKeys("20");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='Height']")).sendKeys("20");
		Thread.sleep(2000);
		driver.findElement(By.linkText("Update")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@class='dropdown slick-slide slick-active']//span[contains(text(),'Save')]")).click();
		Thread.sleep(5000);
		Alert alert = driver.switchTo().alert();	
        alert.accept();
        Thread.sleep(10000);
		
		
	}
	
	@AfterMethod()
	public void tearDown()
	{
		driver.close();
	}
}

