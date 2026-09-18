package ddt_HRM;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.mysql.jdbc.Driver;

import genericUtility.DatabaseUtility;
import genericUtility.ExcelFileUtility;
import genericUtility.JavaUtility;
import genericUtility.ProprtyFileUtility;
import genericUtility.WebDriverUtility;
import objectRepo.Login_Page;

public class CreateProjectUsingUtility {
	public static void main(String[] args) throws Throwable {
		WebDriver driver;
		// 1.Login
		// 1.1 Read data from property file

		ProprtyFileUtility pu = new ProprtyFileUtility();
		String browser = pu.toReadDataFromPropertiesFile("Browser");
		String url = pu.toReadDataFromPropertiesFile("Url");
		String userName = pu.toReadDataFromPropertiesFile("Username");
		String password = pu.toReadDataFromPropertiesFile("Password");

		// 1.2 Launching browser
		WebDriverUtility wu = new WebDriverUtility();
		driver = wu.launchingBrowser(browser);

		wu.waitForPagetoLoad(driver);
		wu.toMaximizePage(driver);
		driver.get(url);

		// 1.4 Login
		/*driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys(userName);
		driver.findElement(By.id("inputPassword")).clear();
		driver.findElement(By.id("inputPassword")).sendKeys(password);
		driver.findElement(By.xpath("//button[text()='Sign in']")).click();*/
		Login_Page lp=new Login_Page(driver);
		lp.userLogin(userName,password);
		
		Thread.sleep(3000);

		// 2.Create project
		// 2.1 go to project feature
		driver.findElement(By.linkText("Projects")).click();

		// 2.2 click on create project
		driver.findElement(By.xpath("//span[contains(text(),'Create Project')]")).click();
		// 2.3 Reading required data to create project from excel
		FileInputStream fis = new FileInputStream("./src/test/resources/TestData.xlsx");
		Workbook wb = WorkbookFactory.create(fis);

		JavaUtility ju = new JavaUtility();
		ju.genRandomNumber();

		ExcelFileUtility eu = new ExcelFileUtility();
		String pName = eu.readDataFromExcel("Project", 2, 0) + ju.genRandomNumber();
		String pManager = eu.readDataFromExcel("Project", 2, 1);
		String pStatus = eu.readDataFromExcel("Project", 2, 2);
		// 2.4 Creating project
		driver.findElement(By.xpath("//input[@name='projectName']")).clear();
		driver.findElement(By.xpath("//input[@name='projectName']")).sendKeys(pName);

		driver.findElement(By.xpath("//input[@name='createdBy']")).clear();
		driver.findElement(By.xpath("//input[@name='createdBy']")).sendKeys(pManager);
		WebElement ele = driver
				.findElement(By.xpath("//label[contains(.,'Project Status')]/../select[@name='status']"));
		Select sel = new Select(ele);
		sel.selectByVisibleText(pStatus);

		driver.findElement(By.xpath("//input[@value='Add Project']")).click();

		// 2.5 getting Project id
		String projectID = driver.findElement(By.xpath("//td[text()='" + pName + "']/preceding-sibling::td")).getText();

		wb.getSheet("Project").getRow(2).createCell(3).setCellValue(projectID);
		wb.getSheet("Project").getRow(2).getCell(0).setCellValue(pName);

		// 2.6 Writing it in sheet
		FileOutputStream fos = new FileOutputStream("./src/test/resources/TestData.xlsx");
		wb.write(fos);
		
		//db validation
		/*Driver d=new Driver();
		DriverManager.registerDriver(d);
		Connection con=DriverManager.getConnection("jdbc:mysql://49.249.29.4:3307/ninza_hrm", "root@%", "root");
		Statement s = con.createStatement();
		boolean b = s.execute("select * from project where project_name='"+pName+"';");*/
		DatabaseUtility du=new DatabaseUtility();
		boolean b = du.validateDataEntry("project","project_name", pName,"jdbc:mysql://49.249.29.4:3307/ninza_hrm", "root@%", "root");
		if(b==true)
			System.out.println("Project with "+pName+" got created in the frontend and available in the backend");
		else
			System.out.println("Project with "+pName+" didn't got created in the frontend and is not available in the backend");
		
		System.out.println("Date "+ju.currentDate());
		

	}
}
