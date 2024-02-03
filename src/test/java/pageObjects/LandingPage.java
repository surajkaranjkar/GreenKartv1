package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.WebElement;

public class LandingPage {

	 WebDriver ldriver;

	public LandingPage(WebDriver driver) {
		ldriver = driver;
		PageFactory.initElements(driver,this);
	}

	@FindBy(xpath = "//input[@type='search']")
	@CacheLookup
	public static WebElement txtBoxSrch;

	@FindBy(xpath = "//button[@class='search-button']")
	@CacheLookup
	WebElement btnSrch;

	@FindBy(xpath = "//button[text()='ADD TO CART']")
	@CacheLookup
	 List<WebElement> btnAddtoCart;

	@FindBy(xpath = "//input[@value='1']")
	@CacheLookup
	List<WebElement> numBoxQuntity;

	@FindBy(xpath = "//img[@alt='Cart']")
	@CacheLookup
	WebElement btnMainCart;
	
	@FindBy(xpath = "//h4[@class='product-name']")
	@CacheLookup
	List<WebElement> lblPrdtName;
	
	@FindBy(xpath = "//button[text()='PROCEED TO CHECKOUT']")
	@CacheLookup
	WebElement btnPrcedCheckout;
	
	public void enterSearchItems(String search) {
		txtBoxSrch.clear();
		txtBoxSrch.sendKeys(search);
		

	}

	public void clickSearchButton() {
		btnSrch.click();

	}

public List<WebElement> addQuantity() {
		
		return numBoxQuntity;
		}

	public void clickMainCart() {
		btnMainCart.click();

	}
	
	public List<WebElement> prdtName() {
		
		return lblPrdtName;
		
	}
	public void proceedToCheckout() {
		btnPrcedCheckout.click();

	}
	
	public List<WebElement> btnAddtoCart() {
		
		return btnAddtoCart;
		}
}