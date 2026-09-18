package ddt_HRM;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import genericUtility.ExcelFileUtility;
import genericUtility.JavaUtility;
import genericUtility.ProprtyFileUtility;
import genericUtility.WebDriverUtility;

public class CreateEmployeeUsingUtility {

	public static void main(String[] args) throws Throwable 
	{
		WebDriver driver;
		ProprtyFileUtility pu=new ProprtyFileUtility();
		String browser = pu.toReadDataFromPropertiesFile("Browser");
		String url = pu.toReadDataFromPropertiesFile("Url");
		String userName = pu.toReadDataFromPropertiesFile("Username");
		 String passWord=pu.toReadDataFromPropertiesFile("Password");
		 
		 WebDriverUtility wu=new WebDriverUtility();
		 driver=wu.launchingBrowser(browser);
		 
		 wu.toMaximizePage(driver);
		 wu.waitForPagetoLoad(driver);
		 driver.get(url);
		 
		 driver.findElement(By.id("username")).clear();
			driver.findElement(By.id("username")).sendKeys(userName);
			driver.findElement(By.id("inputPassword")).clear();
			driver.findElement(By.id("inputPassword")).sendKeys(passWord);
			driver.findElement(By.xpath("//button[text()='Sign in']")).click();
			
			driver.findElement(By.linkText("Employees")).click();
			driver.findElement(By.xpath("//span[contains(text(),'Add New Employee')]")).click();
			
			JavaUtility ju=new JavaUtility();
			int num=ju.genRandomNumber();
			
			ExcelFileUtility eu=new ExcelFileUtility();
			String eName = eu.readDataFromExcel("Employee", 1, 0)+num;
			String eEmail=eu.readDataFromExcel("Employee", 1, 1);
			String ePhone = eu.formatedDataFromExcel("Employee", 1, 2);
			System.out.println(ePhone);
			//String ePhone=eu.readDataFromExcel("Employee", 1, 2);
			String eUsername=eu.readDataFromExcel("Employee", 1, 3);
		    String eDesignation=eu.readDataFromExcel("Employee", 1, 4);
			String eExperience=eu.readDataFromExcel("Employee", 1, 5);
			String eProject=eu.readDataFromExcel("Employee", 1, 6);
			
			//driver.findElement(By.xpath("//label[text()='Name*']/following-sibling::input[@type='text']")).clear();
			 driver.findElement(By.xpath("//label[text()='Name*']/following-sibling::input[@type='text']")).sendKeys(eName);
			 //driver.findElement(By.xpath("//label[text()='Email*']/following-sibling::input[@type='email']")).clear();
			 driver.findElement(By.xpath("//label[text()='Email*']/following-sibling::input[@type='email']")).sendKeys(eEmail);
			 //driver.findElement(By.xpath("//label[text()='Phone*']/following-sibling::input[@type='text']")).clear();
			 driver.findElement(By.xpath("//label[text()='Phone*']/following-sibling::input[@type='text']")).sendKeys(ePhone);
			 //driver.findElement(By.xpath("//label[text()='Username*']/following-sibling::input[@type='text']")).clear();
			 driver.findElement(By.xpath("//label[text()='Username*']/following-sibling::input[@type='text']")).sendKeys(eUsername);
			 //driver.findElement(By.xpath("//label[text()='Designation*']/following-sibling::input[@type='text']")).clear();
			 driver.findElement(By.xpath("//label[text()='Designation*']/following-sibling::input[@type='text']")).sendKeys(eDesignation);
			 //driver.findElement(By.xpath("//label[text()='Experience*']/following-sibling::input[@type='text']")).clear();
			 driver.findElement(By.xpath("//label[text()='Experience*']/following-sibling::input[@type='text']")).sendKeys(eExperience);
			
			 WebElement ele = driver.findElement(By.xpath("//select[@name='project']"));
			 wu.selectFromDD(ele, eProject);
			 
			 WebElement ele1 = driver.findElement(By.xpath("//input[@value='Add']"));
	         JavascriptExecutor js=(JavascriptExecutor)driver;
	         js.executeScript("arguments[0].click();", ele1);
			
			


	}

}
