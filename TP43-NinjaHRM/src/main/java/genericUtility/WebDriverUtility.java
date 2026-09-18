package genericUtility;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class WebDriverUtility {
	
    public WebDriver driver;
	public WebDriver launchingBrowser(String browser)
	{
		if(browser.equalsIgnoreCase("Chrome")) {
        	final Map<String, Object> chromePrefs = new HashMap<>();
		chromePrefs.put("credentials_enable_service", false);
		chromePrefs.put("profile.password_manager_enabled", false);
		chromePrefs.put("profile.password_manager_leak_detection", false); // <======== This is the important one

		final ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.setExperimentalOption("prefs", chromePrefs);
        	driver=new ChromeDriver(chromeOptions);
        }
        else if(browser.equalsIgnoreCase("FireFox"))
        	driver=new FirefoxDriver();
        else if(browser.equalsIgnoreCase("Edge"))
        	driver=new EdgeDriver();
        else
        	driver=new ChromeDriver();
		
            return driver;
}
	public void waitForPagetoLoad(WebDriver driver)
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}
	public void toMaximizePage(WebDriver driver)
	{
		driver.manage().window().maximize();
	}
	
	public void handlingWindows(WebDriver driver,String targetTitle)
	{
		Set<String> windowIds = driver.getWindowHandles();
		for(String windowId:windowIds) 
		{
			driver.switchTo().window(windowId);
			if(driver.getTitle().contains(targetTitle))
				break;
		}
			
	}
	public void selectFromDD(WebElement ele,String visibleText)
	{
		Select sel=new Select(ele);
		sel.selectByVisibleText(visibleText);
	}
	public void selectFromDD(String value,WebElement ele)
	{
		Select sel=new Select(ele);
		sel.selectByValue(value);
	}
	public void selectFromDD(int index, WebElement ele)
	{
		Select sel=new Select(ele);
		sel.selectByIndex(index);
	}
	public void moveToElementAction(WebDriver driver, WebElement ele)
	{
		Actions act=new Actions(driver);
				act.moveToElement(ele).perform();
	}
	

}
