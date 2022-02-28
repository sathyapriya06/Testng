package com.flipcart.stepdefenition;

import java.io.File;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Flipcartmob {
static WebDriver driver;
static long startTime;


@BeforeClass
public static void LaunchBrowser() {
	System.out.println("browser launch");
	WebDriverManager.chromedriver().setup();
	driver=new ChromeDriver();
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	driver.get("https://www.flipkart.com/");
	
	
}
@AfterClass
public static void QuitBrowser() {
	System.out.println("Quit");
	driver.quit();
}
@BeforeMethod

public void beforemethod() {
	System.out.println("before method");
	startTime =System.currentTimeMillis();
	

}

@AfterMethod

public void aftermethod() {
	long endTime  =System.currentTimeMillis();
	System.out.println("Time taken is:"+ (endTime-startTime));
	
} 

static String mName1;
static String mName2;



@Test(priority = 1)
public void login() {
   System.out.println("login method 1");
   
   driver.findElement(By.xpath("//button[text()='✕']")).click();
}
   
   
   @Test(priority = 2)
   public void search() {
	   System.out.println("search method 2 ");
  driver.findElement(By.name("q")).sendKeys("iphone",Keys.ENTER);
   WebElement imobile1=driver.findElement(By.xpath("(//div[contains(text(),'APPLE')])[2]"));

   mName1=imobile1.getText();
   System.out.println("Expected mobile name:"+mName1);
   imobile1.click();
   
   
}
@Test(priority = 3)
public void  windowhandling() throws InterruptedException {
 System.out.println("windowhandling  method 3");
String pwin= driver.getWindowHandle();
Set<String>cwin=driver.getWindowHandles();
for(String x:cwin) {
	if(!x.equals(pwin)) {
		driver.switchTo().window(x);  
		
	}
}

   WebElement imobile2=driver.findElement(By.xpath("//span[contains(text(),'APPLE')]"));
   mName2=imobile2.getText();
   System.out.println("Expected mobile name:"+mName2);
   imobile2.click();
}
@Test(priority = 4,enabled=false)
public void validation() {                      
	 System.out.println("validation method 4");         
   boolean equals = mName1.equals(mName2);
   System.out.println(equals);
}
static int counter=0;
@Test(priority = 5,invocationCount=2)
 

public void ScreenShot() throws Exception {
System.out.println("screenshot method - 5");
++counter;
 TakesScreenshot tk = (TakesScreenshot)driver;
 File sourc = tk.getScreenshotAs(OutputType.FILE);
 File dest = new File(".//target//report"+counter+".png");
 FileUtils.copyFile(sourc,dest);
}
}


    
    
    
    
    
    
    
    
   
