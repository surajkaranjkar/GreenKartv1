package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CountryPage {

	public WebDriver ldriver;

	public CountryPage(WebDriver driver) {
		ldriver = driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath = "//select")
	@CacheLookup
	WebElement countryStaticDropdown;
	
	@FindBy(xpath = "//input[@type='checkbox']")
	@CacheLookup
	public static WebElement policyCheckBox;
	
	@FindBy(xpath = "//button[normalize-space()='Proceed']")
	@CacheLookup
	WebElement btnProceed;
	
	@FindBy(xpath = "//*[contains(text(),'Thank you, your order has been ')]")
	@CacheLookup
	WebElement successMessage;
	@FindBy(xpath="//b[normalize-space()='Please accept Terms & Conditions - Required']")
	@CacheLookup
	WebElement errorMessage;
	
	public void selectValueFromdown(String country){
		Select dropdown=new Select(countryStaticDropdown);
		dropdown.selectByValue(country);
	}
	
	public void selectCheckbox() {
		policyCheckBox.click();
	}
	public void btnProceed() {
		btnProceed.click();
	}
	
	public String successMessage() {
		String successMessage1=successMessage.getText();
		return successMessage1;
	}
	public String errorMessage() {
		String errorMessage1=errorMessage.getText();
		return errorMessage1;
	}
}
