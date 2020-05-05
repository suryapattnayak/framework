package com.Amazon.Test;

import org.testng.annotations.Test;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;

public class testcase1 {

	private  WebDriver driver = null;
	private String projectPath = null;

	Properties prop=new Properties();
	
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
	@Parameters({"word"})
	public void Atest(String word) throws InterruptedException, IOException {
		FileInputStream propfile = new FileInputStream(System.getProperty("user.dir")+"/resources/application.properties");
		prop.load(propfile);
		driver.findElement(By.xpath(prop.getProperty("Amazon_Searchbar"))).sendKeys(word);
		//Thread.sleep(4000);
		driver.findElement(By.xpath(prop.getProperty("Amazon_Searchbar_click"))).click();
		Thread.sleep(4000);
	}
	
	@Test
	@Parameters({"word"})
	public void Ftest(String word) throws InterruptedException, IOException {
		FileInputStream propfile = new FileInputStream(System.getProperty("user.dir")+"/resources/application.properties");
		prop.load(propfile);
		driver.findElement(By.xpath(prop.getProperty("Flipkart_Searchbar"))).sendKeys(word);
		//Thread.sleep(4000);
		driver.findElement(By.xpath(prop.getProperty("Flipkart_Searchbar_click"))).click();
		Thread.sleep(4000);
	}
	

	@AfterTest
	public void afterTest() {
		
		driver.close();
		driver.quit();
	}
	@Test
	public void see()
	{
		System.out.println("working");
	}
}
