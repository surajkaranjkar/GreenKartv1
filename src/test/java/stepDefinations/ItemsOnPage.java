package stepDefinations;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.LandingPage;
import pageObjects.OrderVerficationPage;

public class ItemsOnPage extends BaseClass {

	@Before
	public void setup() throws IOException {

		configProp = new Properties();
		FileInputStream configPropfile = new FileInputStream("config.properties");
		configProp.load(configPropfile);

		if (configProp.getProperty("browser").equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", configProp.getProperty("chromepath"));
			driver = new ChromeDriver();
		}
		driver.manage().window().maximize();

	}

	@After
	public void tearDown() {
		driver.close();
	}

	@Given("User is on {string} page")
	public void user_is_on_page(String site) {

		lp = new LandingPage(driver);
		if (configProp.getProperty("browser").equals("chrome")) {
			driver.get(configProp.getProperty("baseurl"));
		}
	}

	@When("User selects the item and adds to cart")
	public void user_selects_the_item_and_adds_to_cart(DataTable dataTable) throws InterruptedException {

		List<Map<String, String>> row = dataTable.asMaps(String.class, String.class);

		for (Map<String, String> col : row) {
			for (int i = 0; i < lp.prdtName().size(); i++) {
				if (lp.prdtName().get(i).getText().contains(col.get("Item"))) {
					lp.addQuantity().get(i).clear();
					lp.addQuantity().get(i).sendKeys(col.get("Quantity"));
					lp.btnAddtoCart().get(i).click();

				}

			}

		}
	}

	@When("the user clicks on proceed to check out")
	public void the_user_clicks_on_proceed_to_check_out() throws InterruptedException {
		lp.clickMainCart();
		lp.proceedToCheckout();
		Thread.sleep(2000);
	}

	@Then("user verifies the purchase and place order")
	public void user_verifies_the_purchase_and_place_order() {
		vp = new OrderVerficationPage(driver);
		Assert.assertTrue(OrderVerficationPage.btnPlcOrder.isDisplayed());
		vp.clickPlaceOrder();
	}

	@Then("user selects {string} and proceed")
	public void user_selects_and_proceed(String string) {

	}

	@Then("user verifies {string}")
	public void user_verfies(String string) {

	}

}
