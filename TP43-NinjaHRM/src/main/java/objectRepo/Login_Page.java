package objectRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Login_Page {
	public Login_Page(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="username")
	private WebElement userName;
	
	@FindBy(name = "password")
	private WebElement password;
	
	@FindBy(xpath = "//button[text()='Sign in']")
	private WebElement login;

	public WebElement getUserName() {
		return userName;
	}

    public WebElement getPassword() {
		return password;
	}


	public WebElement getLogin() {
		return login;
	}
	
	public void userLogin(String un,String pwd)
	{
		userName.clear();
		userName.sendKeys(un);
		password.clear();
		password.sendKeys(pwd);
		login.click();
		
	}



}
