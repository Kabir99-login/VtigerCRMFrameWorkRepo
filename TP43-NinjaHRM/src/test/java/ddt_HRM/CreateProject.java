package ddt_HRM;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class CreateProject {

	public static void main(String[] args) throws Throwable 
	{
		
		
		
		//1.Login
		//1.1 Read data from property file
		FileInputStream fis=new FileInputStream("./src/test/resources/CommonData.properties");
		Properties p=new Properties();
        p.load(fis);
        
        String browser=p.getProperty("Browser");
        String url=p.getProperty("Url");
        String userName=p.getProperty("Username");
        String passWord=p.getProperty("Password");
        
		
		//1.2 Launching browser
		 WebDriver driver;
	        
	        if(p.getProperty("Browser").equalsIgnoreCase("Chrome")) {
	        	final Map<String, Object> chromePrefs = new HashMap<>();
			chromePrefs.put("credentials_enable_service", false);
			chromePrefs.put("profile.password_manager_enabled", false);
			chromePrefs.put("profile.password_manager_leak_detection", false); // <======== This is the important one

			final ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.setExperimentalOption("prefs", chromePrefs);
	        	driver=new ChromeDriver(chromeOptions);
	        }
	        else if(p.getProperty("Browser").equalsIgnoreCase("FireFox"))
	        	driver=new FirefoxDriver();
	        else if(p.getProperty("Browser").equalsIgnoreCase("Edge"))
	        	driver=new EdgeDriver();
	        else
	        	driver=new ChromeDriver();
	            driver.manage().window().maximize();
	            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	         
	            //1.3 Launch the browser
	            driver.get(url);
	            
	            //1.4 Login
	            driver.findElement(By.id("username")).clear();
	            driver.findElement(By.id("username")).sendKeys(userName);
	            driver.findElement(By.id("inputPassword")).clear();
	            driver.findElement(By.id("inputPassword")).sendKeys(passWord);
	            driver.findElement(By.xpath("//button[text()='Sign in']")).click();
	            Thread.sleep(3000);
	           
	            //2.Create project
                //2.1 go to project feature
	          driver.findElement(By.linkText("Projects")).click();
	          
	          //2.2 click on create project
	          driver.findElement(By.xpath("//span[contains(text(),'Create Project')]")).click();
	          //2.3 Reading required data to create project from excel
	          fis=new FileInputStream("./src/test/resources/TestData.xlsx");
	          Workbook wb=WorkbookFactory.create(fis);
	          
	          //2.3.1 Random number generating for new project name
	          Random r=new Random();
	          int num=r.nextInt();
	          
	          String pName=wb.getSheet("Project").getRow(2).getCell(0).toString()+num;
	          String pManager=wb.getSheet("Project").getRow(2).getCell(1).toString();
	          String pStatus=wb.getSheet("Project").getRow(2).getCell(2).toString();
	          
	          //2.4 Creating project
	          driver.findElement(By.xpath("//input[@name='projectName']")).clear();
	          driver.findElement(By.xpath("//input[@name='projectName']")).sendKeys(pName);
	          
	          driver.findElement(By.xpath("//input[@name='createdBy']")).clear();
	          driver.findElement(By.xpath("//input[@name='createdBy']")).sendKeys(pManager);
	          WebElement ele = driver.findElement(By.xpath("//label[contains(.,'Project Status')]/../select[@name='status']"));
	          Select sel=new Select(ele);
	          sel.selectByVisibleText(pStatus);
	          
	          driver.findElement(By.xpath("//input[@value='Add Project']")).click();
	          
	          //2.5 getting Project id
	          String projectID=driver.findElement(By.xpath("//td[text()='"+pName+"']/preceding-sibling::td")).getText();
	          wb.getSheet("Project").getRow(2).createCell(3).setCellValue(projectID);
	          wb.getSheet("Project").getRow(2).getCell(0).setCellValue(pName);
	          
	          //2.6 Writing it in sheet
	          FileOutputStream fos=new FileOutputStream("./src/test/resources/TestData.xlsx");
	          wb.write(fos);
	          
	          
	          
	            
	            
	            
		

	}

}
