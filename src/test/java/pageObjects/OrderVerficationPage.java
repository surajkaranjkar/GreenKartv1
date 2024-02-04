package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderVerficationPage {
	public WebDriver ldriver;

	public OrderVerficationPage(WebDriver driver) {
		ldriver = driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy (xpath= "//*[text()='Place Order']")
	@CacheLookup
	public static WebElement btnPlcOrder;
	
	@FindBy (xpath= "//*[text()='Apply']")
	@CacheLookup
	WebElement btnApply;
	
	@FindBy(xpath ="//*[@id='productCartTables']//tbody")
	@CacheLookup
	public static WebElement productCartTable;
	
	@FindBy(xpath ="//*[@id='productCartTables']//tbody//tr[1]")
	@CacheLookup
	public static WebElement productCartTableRows;
	
	@FindBy(xpath ="//input[@class='promoCode']")
	@CacheLookup
	WebElement txtPromoCode;
	@FindBy(xpath = "//span[@class='promoInfo']")
	@CacheLookup
	WebElement lblpromoInfo;
	

	public String checkLblPromoInfo() {
		String promocodesuccessfail=lblpromoInfo.getText();
return promocodesuccessfail;
	}
	
	public void clickPlaceOrder() {
		btnPlcOrder.click();
	}
	
	public void btnApply() {
		btnApply.click();
	}
	
	public void enterPromoCode(String promocode) {
		txtPromoCode.clear();
		txtPromoCode.sendKeys(promocode);
	}
}
