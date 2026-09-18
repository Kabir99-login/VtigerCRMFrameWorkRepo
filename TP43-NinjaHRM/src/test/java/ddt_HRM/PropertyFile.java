package ddt_HRM;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class PropertyFile {

	public static void main(String[] args) throws Throwable 
	{
		//1. Open the file in the read mode
        FileInputStream fis=new FileInputStream("./src/test/resources/CommonData.properties");
        
        //2.Create object of prrerties class to read databased on key
        Properties p=new Properties();
        p.load(fis);
        
        System.out.println("URL "+p.getProperty("Url"));
        System.out.println("UseName "+p.getProperty("Username"));
        System.out.println("PassWord "+p.getProperty("Password"));
        
        WebDriver driver;
        
        if(p.getProperty("Browser").equalsIgnoreCase("Chrome"))
        	driver=new ChromeDriver();
        else if(p.getProperty("Browser").equalsIgnoreCase("FireFox"))
        	driver=new FirefoxDriver();
        else if(p.getProperty("Browser").equalsIgnoreCase("Edge"))
        	driver=new EdgeDriver();
        else
        	driver=new ChromeDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            
            
            
            driver.get(p.getProperty("Url"));
           // driver.get("http://49.249.29.4:8091/");
            Thread.sleep(3000);
            driver.findElement(By.id("username")).clear();
            driver.findElement(By.id("username")).sendKeys(p.getProperty("Username"));
            
            Thread.sleep(3000);
            driver.findElement(By.id("inputPassword")).clear();
            driver.findElement(By.id("inputPassword")).sendKeys(p.getProperty("Password"));
            
            driver.findElement(By.xpath("//button[text()='Sign in']")).click();
            
            
            
         	
        


	}

}
