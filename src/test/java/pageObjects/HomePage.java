package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class HomePage extends BasePage {

	
	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//span[normalize-space()='My Account']")
	WebElement lnkMyAccount;
	
	@FindBy(xpath="//a[normalize-space()='Register']")
	WebElement lnkRegister;
	
	@FindBy(xpath="//a[normalize-space()='Login']")
	WebElement lnkLogin;
	
	@FindBy(xpath="//input[@placeholder='Search']") 
	WebElement Txtsearch;
	
	@FindBy(xpath="//button[@class='btn btn-default btn-lg']") 
	WebElement Btnsearch;
	
	public void clickMyAccount() {
		lnkMyAccount.click();
	}
	
	public void clickRegister() {
		lnkRegister.click();
	}
	
	public void clickLoginLink() {
		lnkLogin.click();
	}
	
	public void searchProduct(String name) {
		Txtsearch.sendKeys(name);
	}
	
	public void searchBtn() {
		Btnsearch.click();
	}
	
}
