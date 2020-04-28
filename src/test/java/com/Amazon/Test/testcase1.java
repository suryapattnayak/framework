package com.Amazon.Test;

import org.testng.annotations.Test;

import com.Amazon.ExcelReader.Exelreader;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;

public class testcase1 {

	private  WebDriver driver = null;
	private String browserName = null;
	private String projectPath = null;
	String word=null;

	@BeforeTest
	@Parameters({"browserName","Url"})
	public void openBrowser(String browserName,String urL) throws InterruptedException {  
		projectPath = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver",projectPath+ "/driver/chrome/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(urL);
		driver.manage().window().maximize();
		//Thread.sleep(4000);
	}
	
	@Test
	public void test() throws InterruptedException {
		System.out.println("hii");
		word=Exelreader.wordRead("Sheet1", "Word", 2);
		//System.out.println("word"+word);
		driver.findElement(By.xpath("//input[@type='text'][@name='field-keywords']")).sendKeys(word);
		//Thread.sleep(4000);
		driver.findElement(By.xpath("//input[@type='submit'][@value='Go']")).click();
		Thread.sleep(4000);
	}
	
	

	@AfterTest
	public void afterTest() {
		
		driver.close();
		driver.quit();
	}

}
