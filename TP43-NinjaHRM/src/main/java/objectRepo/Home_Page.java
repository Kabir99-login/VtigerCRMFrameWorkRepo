package objectRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Home_Page {

   WebDriver driver;
   public Home_Page(WebDriver driver) {
	   this.driver=driver;
       PageFactory.initElements(driver, this);
   }


	@FindBy(linkText = "Organization")
    private WebElement contactsLink;

    @FindBy(linkText = "Campaigns")
    private WebElement campaignsLink;

    @FindBy(xpath = "//img[@src='themes/softed/images/menuDnArrow.gif']")
    private WebElement administratorImage;

    @FindBy(linkText = "Sign Out")
    private WebElement signOutLink;

 

    public WebElement getContactsLink() {
        return contactsLink;
    }

    public WebElement getCampaignsLink() {
        return campaignsLink;
    }

    public WebElement getAdministratorImage() {
        return administratorImage;
    }

    public WebElement getSignOutLink() {
        return signOutLink;
    }

    public void logout(WebDriver driver) {
        Actions act = new Actions(driver);
        act.moveToElement(administratorImage).perform();
        signOutLink.click();
    }

	public WebElement getLogoutButton() {
		// TODO Auto-generated method stub
		return null;
	}

	public WebElement getMoreOptionsLink() {
		// TODO Auto-generated method stub
		return null;
	}

	public WebElement getAdministratorIcon() {
		// TODO Auto-generated method stub
		return null;
	}
}
