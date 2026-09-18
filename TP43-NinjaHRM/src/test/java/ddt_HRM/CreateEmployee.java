package ddt_HRM;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class CreateEmployee {

	public static void main(String[] args) throws Throwable 
	{
		WebDriver driver;
		FileInputStream fis=new FileInputStream("./src/test/resources/CommonData.properties");
		Properties p=new Properties();
		p.load(fis);
		
		String browser = p.getProperty("Browser");
		String url = p.getProperty("Url");
		String username = p.getProperty("Username");
		String password = p.getProperty("Password");
		
		if(browser.equalsIgnoreCase("Chrome"))
		 {
        final Map<String, Object> chromePrefs = new HashMap<>();
		chromePrefs.put("credentials_enable_service", false);
		chromePrefs.put("profile.password_manager_enabled", false);
		chromePrefs.put("profile.password_manager_leak_detection", false); // <======== This is the important one

		final ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.setExperimentalOption("prefs", chromePrefs);
			driver=new ChromeDriver(chromeOptions);
		}
		else if(browser.equalsIgnoreCase("Firefox"))
			driver=new FirefoxDriver();
		else if(browser.equalsIgnoreCase("Edge"))
			driver=new EdgeDriver();
		else
			driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get(url);
		
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys(username);
		driver.findElement(By.id("inputPassword")).clear();
		driver.findElement(By.id("inputPassword")).sendKeys(password);
		driver.findElement(By.xpath("//button[text()='Sign in']")).click();
		
		driver.findElement(By.linkText("Employees")).click();
		driver.findElement(By.xpath("//span[contains(text(),'Add New Employee')]")).click();
		
		 fis=new FileInputStream("./src/test/resources/TestData.xlsx");
		 Workbook wb=WorkbookFactory.create(fis);
		 
		 Random r=new Random();
		 int num = r.nextInt();
		 
		 String eName = wb.getSheet("Employee").getRow(1).getCell(0).toString()+num;
		 String eEmail = wb.getSheet("Employee").getRow(1).getCell(1).toString();
		 
		 DataFormatter df=new DataFormatter();
		 String ePhone =df.formatCellValue(wb.getSheet("Employee").getRow(1).getCell(2));
		 String eUsername= wb.getSheet("Employee").getRow(1).getCell(3).toString()+num;
		 String eDesignation = wb.getSheet("Employee").getRow(1).getCell(4).toString();
		 String eExperience = wb.getSheet("Employee").getRow(1).getCell(5).toString();
		 String eProject = wb.getSheet("Employee").getRow(1).getCell(6).toString();
		 
		 driver.findElement(By.xpath("//label[text()='Name*']/following-sibling::input[@type='text']")).clear();
		 driver.findElement(By.xpath("//label[text()='Name*']/following-sibling::input[@type='text']")).sendKeys(eName);
		 driver.findElement(By.xpath("//label[text()='Email*']/following-sibling::input[@type='email']")).clear();
		 driver.findElement(By.xpath("//label[text()='Email*']/following-sibling::input[@type='email']")).sendKeys(eEmail);
		 driver.findElement(By.xpath("//label[text()='Phone*']/following-sibling::input[@type='text']")).clear();
		 driver.findElement(By.xpath("//label[text()='Phone*']/following-sibling::input[@type='text']")).sendKeys(ePhone);
		 driver.findElement(By.xpath("//label[text()='Username*']/following-sibling::input[@type='text']")).clear();
		 driver.findElement(By.xpath("//label[text()='Username*']/following-sibling::input[@type='text']")).sendKeys(eUsername);
		 driver.findElement(By.xpath("//label[text()='Designation*']/following-sibling::input[@type='text']")).clear();
		 driver.findElement(By.xpath("//label[text()='Designation*']/following-sibling::input[@type='text']")).sendKeys(eDesignation);
		 driver.findElement(By.xpath("//label[text()='Experience*']/following-sibling::input[@type='text']")).clear();
		 driver.findElement(By.xpath("//label[text()='Experience*']/following-sibling::input[@type='text']")).sendKeys(eExperience);
		 Thread.sleep(3000);
         WebElement ele = driver.findElement(By.xpath("//select[@name='project']"));
         System.out.println("P:"+eProject);
         Select sel=new Select(ele);
         sel.selectByVisibleText(eProject);
         
         Thread.sleep(3000);
         WebElement ele1 = driver.findElement(By.xpath("//input[@value='Add']"));
         JavascriptExecutor js=(JavascriptExecutor)driver;
         js.executeScript("arguments[0].click();", ele1);

	}

}
